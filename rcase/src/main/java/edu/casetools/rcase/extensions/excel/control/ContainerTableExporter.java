/*
 * Copyright 2015 @author Unai Alegre @company Middlesex University
 * 
 * This file is part of R-CASE (Requirements for Context-Aware Systems Engineering), a module 
 * of Modelio that aids the requirements elicitation phase of a Context-Aware System (C-AS). 
 * 
 * R-CASE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * R-CASE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package edu.casetools.rcase.extensions.excel.control;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import edu.casetools.rcase.extensions.excel.model.CellType;
import edu.casetools.rcase.extensions.excel.model.TableCell;
import edu.casetools.rcase.extensions.tables.container.model.ContainerRow;
import edu.casetools.rcase.extensions.tables.container.model.ContainerTableHeaderData;
import edu.casetools.rcase.extensions.tables.container.model.ContainerTableModel;
import edu.casetools.rcase.module.api.RCaseExporter;
import edu.casetools.rcase.utils.ExcelUtils;

/**
 * The Class ContainerTableExporter has the logic to export the Requirements
 * Container Table into an excel document.
 */
public class ContainerTableExporter extends Exporter implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8073612103066047585L;
    private ContainerTableModel table;

    /**
     * Instantiates a new container table exporter.
     *
     * @param file
     *            the file where the excel document is going to be saved.
     * @param table
     *            the table that is intended to be saved.
     */
    public ContainerTableExporter(File file, ContainerTableModel table) {
	super(file);
	this.table = table;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.middlesex.goodies.casesuite.extensions.excel.control.Exporter#export
     * ()
     */
    @Override
    public void export() {
	setStyles();
	setProperties(RCaseExporter.CONTAINER_TABLE_EXPORTER);
	// Tables can be created in different tabs
	createSheet("test");
	createDocument();
    }

    private void createSheet(String sheetName) {
	Sheet sheet = workbook.createSheet(ExcelUtils.getInstance().createSafeSheetName(sheetName));

	List<ContainerTableHeaderData> columnHeader = table.getData().getxHeaderList();

	createColumnHeaders(sheet, columnHeader);

	sheet.createFreezePane(1, 1);

	int rowNumber = 1;
	for (ContainerRow rowData : table.getData().getDataList()) {
	    createRow(sheet, rowNumber, rowData);
	    rowNumber++;
	}

	resizeColumn(sheet, columnHeader.size());
    }

    private void createColumnHeaders(Sheet sheet, List<ContainerTableHeaderData> columnHeader) {
	int columnNumber = 0;
	Row row = sheet.createRow(0);
	for (ContainerTableHeaderData content : columnHeader) {

	    Object columnName = content.getName();
	    Cell cell = row.createCell(columnNumber, 1);
	    cell.setCellValue(columnName.toString());
	    cell.setCellStyle(headerCellStyle);

	    columnNumber++;
	}
    }

    private void createRow(Sheet sheet, int rowNumber, ContainerRow rowData) {
	int columnNumber = 0;
	Row row = sheet.createRow(rowNumber);

	for (Object value : rowData.getRowContent()) {
	    columnNumber = createCellValue(sheet, rowNumber, (String) value, row, columnNumber);
	}

    }

    private int createCellValue(Sheet sheet, int rowNumber, String value, Row row, int columnNumber) {
	TableCell cellTable = new TableCell(value, CellType.TEXT, new ArrayList<String>());
	Cell cell = row.createCell(columnNumber, getCellType(cellTable.getType()));

	return createCellStyle(sheet, rowNumber, columnNumber, cell, cellTable);
    }

}
