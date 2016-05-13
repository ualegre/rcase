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
package edu.casetools.rcase.extensions.tables.traceability.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.casetools.rcase.extensions.excel.view.DependencyTableFileChooser;
import edu.casetools.rcase.extensions.tables.traceability.DependencyTable;
import edu.casetools.rcase.extensions.tables.traceability.model.DependencyTableData;
import edu.casetools.rcase.extensions.tables.traceability.view.edit.EditColumnDialog;
import edu.casetools.rcase.extensions.tables.traceability.view.edit.EditRowDialog;
import edu.casetools.rcase.module.i18n.I18nMessageService;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving matrixMenu events. The class that is
 * interested in processing a matrixMenu event implements this interface, and
 * the object created with that class is registered with a component using the
 * component's <code>addMatrixMenuListener<code> method. When the matrixMenu
 * event occurs, that object's appropriate method is invoked.
 *
 * @see MatrixMenuEvent
 */
public class MatrixMenuListener implements ActionListener {

    private DependencyTable main;
    private EditColumnDialog columnDialog;
    private EditRowDialog rowDialog;
    private DependencyTableFileChooser fileChooser;

    /**
     * Instantiates a new matrix menu listener.
     *
     * @param main
     *            the main
     */
    public MatrixMenuListener(DependencyTable main) {
	this.main = main;
	this.fileChooser = new DependencyTableFileChooser();

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent paramActionEvent) {

	String command = paramActionEvent.getActionCommand();
	DependencyTableData data = this.main.getTablePanel().getTableModel().getData();

	if (command.equals(I18nMessageService.getString("Menu.Matrix.Filter.Columns"))) {
	    editColumns();
	} else if (command.equals(I18nMessageService.getString("Menu.Matrix.Refresh"))) {
	    this.main.refreshComboBoxAndTable(data);
	} else if (command.equals(I18nMessageService.getString("Menu.Matrix.Filter.Rows"))) {
	    editRows();
	} else if (command.equals(I18nMessageService.getString("Menu.Matrix.Export"))) {
	    this.fileChooser.export(this.main);

	}

    }

    private void editRows() {
	if (null == this.rowDialog)
	    this.rowDialog = new EditRowDialog(this.main);
	else
	    this.rowDialog.refresh();
    }

    private void editColumns() {
	if (null == this.columnDialog)
	    this.columnDialog = new EditColumnDialog(this.main);
	else
	    this.columnDialog.refresh();

    }

}
