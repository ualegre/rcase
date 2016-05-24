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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Modelio. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package edu.casetools.rcase.extensions.excel.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.POIXMLProperties;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties;

import edu.casetools.rcase.extensions.excel.model.CellType;
import edu.casetools.rcase.extensions.excel.model.TableCell;
import edu.casetools.rcase.module.i18n.I18nMessageService;

/**
 * The Class Exporter has the common logic to export tables into excel
 * documents.
 */
public abstract class Exporter {
    private static final Logger logger = Logger.getLogger(Exporter.class.getName());
    private File file;
    protected Workbook workbook;
    protected SpreadsheetVersion version;
    protected CellStyle headerCellStyle = null;
    protected CellStyle contentCellStyle = null;
    private CellStyle elementNameCellStyle = null;

    /**
     * Instantiates a new exporter.
     *
     * @param file
     *            the file where the table is going to be saved.
     */
    public Exporter(File file) {
	init(file);
	initFile(file);
    }

    public Exporter() {
	// TODO Auto-generated constructor stub
    }

    private void init(File file) {
	this.file = file;
    }

    private void initFile(File file) {
	if (file.getName().endsWith(".xlsx")) {
	    initExcel2007();
	} else {
	    initExcel1997();
	}
    }

    private void initExcel1997() {
	version = SpreadsheetVersion.EXCEL97;
	workbook = new HSSFWorkbook();
    }

    private void initExcel2007() {
	version = SpreadsheetVersion.EXCEL2007;
	workbook = new XSSFWorkbook();
    }

    /**
     * Exports the data into a document.
     */
    public abstract void export();

    /**
     * Creates the document.
     */
    protected void createDocument() {

	try (FileOutputStream out = new FileOutputStream(file.getAbsolutePath());) {
	    workbook.write(out);
	    close(out);
	} catch (IOException e) {
	    logger.log(Level.SEVERE, e.getMessage(), e);
	    MessageDialog.openError(Display.getCurrent().getActiveShell(), I18nMessageService.getString("Info.Error"),
		    I18nMessageService.getString("Error.ImpossibleToWriteFile",
			    new String[] { file.getAbsolutePath() }));
	}
    }

    /**
     * Closes the file.
     *
     * @param out
     *            the file to close.
     */
    protected void close(FileOutputStream out) {
	try {
	    out.close();
	} catch (IOException e) {
	    logger.log(Level.SEVERE, e.getMessage(), e);
	}
    }

    /**
     * Sets the styles of the headers, content and element names.
     */
    protected void setStyles() {
	createHeaderStyle();
	createContentStyle();
	createElementNameStyle();
    }

    private void createHeaderStyle() {
	headerCellStyle = workbook.createCellStyle();
	headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
	headerCellStyle.setFillPattern((short) 1);
	headerCellStyle.setWrapText(false);
	Font headerFont = workbook.createFont();
	headerFont.setColor(IndexedColors.WHITE.getIndex());
	headerFont.setBoldweight((short) 700);
	headerCellStyle.setFont(headerFont);

	headerCellStyle.setBorderBottom((short) 1);
	headerCellStyle.setBottomBorderColor(IndexedColors.WHITE.getIndex());
	headerCellStyle.setBorderLeft((short) 1);
	headerCellStyle.setLeftBorderColor(IndexedColors.WHITE.getIndex());
	headerCellStyle.setBorderRight((short) 1);
	headerCellStyle.setRightBorderColor(IndexedColors.WHITE.getIndex());
	headerCellStyle.setBorderTop((short) 1);
	headerCellStyle.setTopBorderColor(IndexedColors.WHITE.getIndex());
    }

    private void createContentStyle() {
	contentCellStyle = workbook.createCellStyle();
	contentCellStyle.setVerticalAlignment((short) 0);
	contentCellStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
	contentCellStyle.setFillPattern((short) 1);
	this.contentCellStyle.setWrapText(true);

	this.contentCellStyle.setBorderBottom((short) 1);
	this.contentCellStyle.setBottomBorderColor(IndexedColors.WHITE.getIndex());
	this.contentCellStyle.setBorderLeft((short) 1);
	this.contentCellStyle.setLeftBorderColor(IndexedColors.WHITE.getIndex());
	this.contentCellStyle.setBorderRight((short) 1);
	this.contentCellStyle.setRightBorderColor(IndexedColors.WHITE.getIndex());
	this.contentCellStyle.setBorderTop((short) 1);
	this.contentCellStyle.setTopBorderColor(IndexedColors.WHITE.getIndex());
    }

    private void createElementNameStyle() {
	this.elementNameCellStyle = this.workbook.createCellStyle();
	this.elementNameCellStyle.setVerticalAlignment((short) 0);
	this.elementNameCellStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
	this.elementNameCellStyle.setFillPattern((short) 1);
	this.elementNameCellStyle.setWrapText(true);
	Font elementNameFont = this.workbook.createFont();
	elementNameFont.setBoldweight((short) 700);
	this.elementNameCellStyle.setFont(elementNameFont);

	this.elementNameCellStyle.setBorderBottom((short) 1);
	this.elementNameCellStyle.setBottomBorderColor(IndexedColors.WHITE.getIndex());
	this.elementNameCellStyle.setBorderLeft((short) 1);
	this.elementNameCellStyle.setLeftBorderColor(IndexedColors.WHITE.getIndex());
	this.elementNameCellStyle.setBorderRight((short) 1);
	this.elementNameCellStyle.setRightBorderColor(IndexedColors.WHITE.getIndex());
	this.elementNameCellStyle.setBorderTop((short) 1);
	this.elementNameCellStyle.setTopBorderColor(IndexedColors.WHITE.getIndex());
    }

    /**
     * Resizes the column.
     *
     * @param sheet
     *            the sheet that owns the column that is intended to be resized.
     * @param columnNumber
     *            the number of the column that is intended to be resized.
     */
    protected void resizeColumn(Sheet sheet, int columnNumber) {
	for (int i = 0; i < columnNumber; i++)
	    sheet.autoSizeColumn(i);
    }

    /**
     * Gets the cell type.
     *
     * @param type
     *            the cell type in enumeration format.
     * @return the cell type in integer format.
     */
    protected int getCellType(CellType type) {
	int ssType;

	if (type == CellType.BOOLEAN)
	    ssType = 4;
	else
	    ssType = 1;

	return ssType;
    }

    /**
     * Creates the cell style.
     *
     * @param sheet
     *            the sheet where to create the style.
     * @param rowNumber
     *            the row number where to create the style.
     * @param columnNumber
     *            the column number where to create the style.
     * @param cell
     *            the cell where to create the style.
     * @param cellTable
     *            the cell table where to create the style.
     * @return the column number
     */
    protected int createCellStyle(Sheet sheet, int rowNumber, int columnNumber, Cell cell, TableCell cellTable) {
	int auxiliarColumnNumber = columnNumber;
	cell.setCellStyle(this.contentCellStyle);
	cell.setCellValue(cellTable.getValue());

	String[] possibleValues = cellTable.getPossibleValues();

	if (0 != possibleValues.length) {
	    DataValidation validation;
	    DataValidationConstraint constraint;
	    CellRangeAddressList addressList = new CellRangeAddressList(rowNumber, rowNumber, auxiliarColumnNumber,
		    auxiliarColumnNumber);
	    if (this.version == SpreadsheetVersion.EXCEL2007) {
		validation = createExcel2007CellStyle(sheet, possibleValues, addressList);
	    } else {
		constraint = DVConstraint.createExplicitListConstraint(possibleValues);
		validation = new HSSFDataValidation(addressList, constraint);
	    }

	    if (validation != null) {
		sheet.addValidationData(validation);
	    }
	}
	auxiliarColumnNumber++;
	return auxiliarColumnNumber;
    }

    private DataValidation createExcel2007CellStyle(Sheet sheet, String[] possibleValues,
	    CellRangeAddressList addressList) {
	DataValidation validation;
	DataValidationConstraint constraint;
	DataValidationHelper validationHelper;
	validationHelper = new XSSFDataValidationHelper((XSSFSheet) sheet);

	constraint = validationHelper.createExplicitListConstraint(possibleValues);
	validation = validationHelper.createValidation(constraint, addressList);
	validation.setEmptyCellAllowed(false);
	validation.setShowErrorBox(true);
	validation.setErrorStyle(0);
	return validation;
    }

    /**
     * Sets the properties of the document.
     *
     * @param templateName
     *            the name of the template.
     */
    protected void setProperties(String templateName) {

	if ((this.version == SpreadsheetVersion.EXCEL2007) && (this.workbook instanceof XSSFWorkbook)) {
	    setExcel2007Properties(templateName);
	} else if (this.workbook instanceof HSSFWorkbook) {
	    setDefaultProperties(templateName);
	}

    }

    private void setDefaultProperties(String templateName) {
	HSSFWorkbook hworkbook = (HSSFWorkbook) this.workbook;
	hworkbook.createInformationProperties();
	SummaryInformation si = hworkbook.getSummaryInformation();
	DocumentSummaryInformation dsi = hworkbook.getDocumentSummaryInformation();
	si.setAuthor(I18nMessageService.getString("Excel.Author"));

	dsi.setCompany(I18nMessageService.getString("Excel.Company"));
	si.setApplicationName(I18nMessageService.getString("Excel.Application"));
	org.apache.poi.hpsf.CustomProperties cp = dsi.getCustomProperties();

	cp.put("Table Template", templateName);
	dsi.setCustomProperties(cp);
    }

    private void setExcel2007Properties(String templateName) {
	XSSFWorkbook xworkbook = (XSSFWorkbook) this.workbook;
	POIXMLProperties properties = xworkbook.getProperties();
	properties.getCoreProperties().setCreator(I18nMessageService.getString("Excel.Author"));

	CTProperties ext = properties.getExtendedProperties().getUnderlyingProperties();
	ext.setCompany(I18nMessageService.getString("Excel.Company"));
	ext.setApplication(I18nMessageService.getString("Excel.Application"));

	POIXMLProperties.CustomProperties customProperties = properties.getCustomProperties();
	customProperties.addProperty("Table Template", templateName);
    }
}
