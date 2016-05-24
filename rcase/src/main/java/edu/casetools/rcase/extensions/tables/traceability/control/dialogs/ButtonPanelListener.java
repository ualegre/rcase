/*
 * Copyright 2015 @author Unai Alegre 
 * 
 * This file is part of RCASE (Requirements for Context-Aware Systems Engineering), a module 
 * of Modelio that aids the requirements elicitation phase of a Context-Aware System (C-AS). 
 * 
 * RCASE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * RCASE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package edu.casetools.rcase.extensions.tables.traceability.control.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ListModel;

import org.modelio.metamodel.uml.infrastructure.Stereotype;

import edu.casetools.rcase.extensions.tables.traceability.DependencyTable;
import edu.casetools.rcase.extensions.tables.traceability.model.DependencyTableData;
import edu.casetools.rcase.extensions.tables.traceability.view.edit.EditDialog;
import edu.casetools.rcase.module.api.RCaseView;
import edu.casetools.rcase.utils.tables.TableUtils;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving buttonPanel events. The class that is
 * interested in processing a buttonPanel event implements this interface, and
 * the object created with that class is registered with a component using the
 * component's <code>addButtonPanelListener<code> method. When the buttonPanel
 * event occurs, that object's appropriate method is invoked.
 *
 * @see ButtonPanelEvent
 */
public class ButtonPanelListener implements ActionListener {

    private DependencyTable main;
    private EditDialog dialog;

    private enum TYPE {
	COL, ROW
    }

    /**
     * Instantiates a new button panel listener.
     *
     * @param main
     *            the main
     * @param dialog
     *            the dialog
     */
    public ButtonPanelListener(DependencyTable main, EditDialog dialog) {
	this.main = main;
	this.dialog = dialog;
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

	if (command.equals(RCaseView.OK_ROW)) {
	    okButtonRow();
	} else if (command.equals(RCaseView.OK_COLUMN)) {
	    okButtonCol();
	}

	this.dialog.dispose();
    }

    private void okButtonCol() {

	DependencyTableData data = refreshData(TYPE.COL);
	this.main.refreshTable(data);

    }

    private DependencyTableData refreshData(TYPE type) {
	List<Stereotype> filteredStereotypes = getFilteredStereotypes();
	DependencyTableData data = getTableData();
	if (type == TYPE.COL)
	    data.setxStereotypeFilter(filteredStereotypes);
	else if (type == TYPE.ROW)
	    data.setyStereotypeFilter(filteredStereotypes);
	return data;
    }

    private void okButtonRow() {

	DependencyTableData data = refreshData(TYPE.ROW);
	this.main.refreshTable(data);
    }

    private DependencyTableData getTableData() {
	DependencyTableData data = this.main.getTablePanel().getTableModel().getData();
	if (null == data)
	    data = new DependencyTableData();
	return data;
    }

    private List<Stereotype> getFilteredStereotypes() {
	ListModel<String> filteredStereotypeNames = this.dialog.getDualListBox().getColumnFilteredStereotypeNames();

	return TableUtils.getInstance().getStereotypesFromNames(filteredStereotypeNames);
    }

}
