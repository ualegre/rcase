/*
 * Copyright 2015 @author Unai Alegre 
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
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.extensions.excel.model.CellType;
import edu.casetools.rcase.extensions.excel.model.TableCell;
import edu.casetools.rcase.extensions.tables.implementations.traceability.model.DependencyTableModel;
import edu.casetools.rcase.module.api.RCaseExporter;
import edu.casetools.rcase.module.impl.RCaseModule;
import edu.casetools.rcase.utils.ExcelUtils;
import edu.casetools.rcase.utils.tables.ModelioTableUtils;

/**
 * The Class DependencyTableExporter has the logic to export the Dependency
 * Table into an excel document.
 */
public class DependencyTableExporter extends Exporter implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4429315345132840757L;
    private DependencyTableModel table;

    /**
     * Instantiates a new dependency table exporter.
     *
     * @param file
     *            the file where the excel document is going to be saved.
     * @param table
     *            the table that is intended to be saved.
     */
    public DependencyTableExporter(File file, DependencyTableModel table) {
	super(file);
	this.table = table;
    }

    public DependencyTableExporter() {
	super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.casesuite.extensions.excel.control.Exporter#export ()
     */
    @Override
    public void export() {
	setStyles();
	setProperties(RCaseExporter.DEPENDENCY_TABLE_EXPORTER);
	createSheet("test"); // Este se pone en un loop para crear varias hojas
			     // en el mismo documento
	createDocument();
    }

    private void createSheet(String sheetName) {
	Sheet sheet = workbook.createSheet(ExcelUtils.getInstance().createSafeSheetName(sheetName));

	List<MObject> columnHeader = table.getData().getxHeaderList();

	createColumnHeaders(sheet, columnHeader, 0);

	sheet.createFreezePane(2, 2);

	int rowNumber = 2;
	for (MObject rowHeader : table.getData().getyHeaderList()) {
	    createRow(sheet, rowNumber, rowHeader, columnHeader);
	    rowNumber++;
	}

	resizeColumn(sheet, columnHeader.size());
    }

    private void createColumnHeaders(Sheet sheet, List<MObject> columnHeader, int rowNumber) {
	int columnNumber = 2;
	Row stereotypesRow = sheet.createRow(rowNumber);

	createColumnHeadersStereotypes(columnHeader, columnNumber, stereotypesRow);

	Row titlesRow = sheet.createRow(rowNumber + 1);
	createColumnHeadersContent(columnHeader, columnNumber, titlesRow);
    }

    private void createColumnHeadersStereotypes(List<MObject> columnHeader, int columnNumber, Row row) {
	int auxiliarColumnNumber = columnNumber;
	for (MObject element : columnHeader) {
	    String value = ExcelUtils.getInstance().getStereotype(RCaseModule.getInstance(), element.getName());
	    auxiliarColumnNumber = createColumnCellContent(auxiliarColumnNumber, row, value);

	}

    }

    private void createColumnHeadersContent(List<MObject> columnHeader, int columnNumber, Row row) {
	int auxiliarColumnNumber = columnNumber;
	for (MObject element : columnHeader) {
	    String value = element.getName();
	    auxiliarColumnNumber = createColumnCellContent(auxiliarColumnNumber, row, value);
	}
    }

    private int createColumnCellContent(int columnNumber, Row row, String value) {
	int auxiliarColumnNumber = columnNumber;
	Cell cell;

	cell = row.createCell(auxiliarColumnNumber, 1);
	cell.setCellValue(value);
	cell.setCellStyle(headerCellStyle);
	auxiliarColumnNumber++;

	return auxiliarColumnNumber;
    }

    private void createRowHeader(Row row, String name, int columnNumber) {
	Cell cell = row.createCell(columnNumber, 1);
	cell.setCellValue(name);
	cell.setCellStyle(headerCellStyle);
    }

    private void createRow(Sheet sheet, int rowNumber, MObject actualRowHeader, List<MObject> columnHeader) {
	int columnNumber = 0;
	Row row = sheet.createRow(rowNumber);

	if (null != actualRowHeader) {
	    String content = ExcelUtils.getInstance().getStereotype(RCaseModule.getInstance(), actualRowHeader.getName());
	    createRowHeader(row, content, columnNumber);
	    columnNumber++;
	    createRowHeader(row, actualRowHeader.getName(), columnNumber);
	    columnNumber++;
	}

	for (MObject actualColumnHeader : columnHeader) {
	    columnNumber = createCellValue(sheet, rowNumber, actualRowHeader, row, columnNumber, actualColumnHeader);
	}

    }

    private int createCellValue(Sheet sheet, int rowNumber, MObject actualRowHeader, Row row, int columnNumber,
	    MObject actualColumnHeader) {
	Cell cell;

	String value = (String) ModelioTableUtils.getInstance().getTableValues(actualColumnHeader, actualRowHeader,
		table.getData());
	TableCell cellTable = new TableCell(value, CellType.TEXT, ModelioTableUtils.getInstance().getPossibleValues());

	cell = row.createCell(columnNumber, getCellType(cellTable.getType()));
	return createCellStyle(sheet, rowNumber, columnNumber, cell, cellTable);
    }

}
