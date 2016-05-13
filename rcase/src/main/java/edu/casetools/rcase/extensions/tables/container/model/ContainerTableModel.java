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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Modelio. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package edu.casetools.rcase.extensions.tables.container.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.module.api.RCaseStereotypes;
import edu.casetools.rcase.module.impl.RCasePeerModule;
import edu.casetools.rcase.utils.PropertiesUtils;
import edu.casetools.rcase.utils.tables.ModelioTableUtils;

public class ContainerTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 5018212113826939650L;

    protected ContainerTableData data;

    public ContainerTableModel() {
	this.data = new ContainerTableData();
    }

    public ContainerTableModel(MObject requirementsContainer) {

	this.data = new ContainerTableData();
	createContent(requirementsContainer);

    }

    private void createContent(MObject requirementsContainer) {
	List<? extends MObject> children = requirementsContainer.getCompositionChildren();

	for (MObject child : children) {
	    if (child instanceof ModelElement) {
		ModelElement modelElement = (ModelElement) child;
		if (modelElement.isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_REQUIREMENT)) {
		    createData(modelElement);
		    createChildrenData(modelElement);
		}
	    }
	}
	if (children.isEmpty()) {
	    emptyTable();
	}
    }

    private void emptyTable() {
	while (!this.data.getDataList().isEmpty()) {
	    removeSelectedRow(this.data.getDataList().size() - 1);
	}
    }

    private void createData(ModelElement requirement) {
	ContainerRow row = new ContainerRow(requirement);

	for (ContainerTableHeaderData headerData : this.data.getxHeaderList()) {

	    row.getRowContent().add(PropertiesUtils.getInstance().getTaggedValue(headerData.getTagName(), requirement));
	}
	this.addRow(row);
    }

    private void createChildrenData(ModelElement requirement) {
	List<? extends MObject> childrenList = requirement.getCompositionChildren();
	for (int i = 0; i < childrenList.size(); i++) {
	    ModelElement object = (ModelElement) childrenList.get(i);
	    if (object.isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_REQUIREMENT)) {
		createData(object);
		createChildrenData(object);
	    }
	}
    }

    @Override
    public String getColumnName(int columnNumber) {
	return this.data.getxHeaderList().get(columnNumber).getName();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
	return true;
    }

    @Override
    public Class<?> getColumnClass(int columnNumber) {
	return String.class;
    }

    @Override
    public Object getValueAt(int columnNumber, int rowNumber) {
	return this.data.getDataList().get(columnNumber).getRowContent().get(rowNumber);
    }

    @Override
    public void setValueAt(Object value, int columnNumber, int rowNumber) {
	this.data.getDataList().get(columnNumber).getRowContent().set(rowNumber, value);
	fireTableCellUpdated(rowNumber, columnNumber);
    }

    @Override
    public int getRowCount() {
	return this.data.getDataList().size();
    }

    @Override
    public int getColumnCount() {
	return this.data.getxHeaderList().size();
    }

    public void addRow(ContainerRow row) {
	this.data.addRow(row);
	fireTableRowsInserted(this.data.getDataList().size() - 1, this.data.getDataList().size() - 1);
    }

    public void removeSelectedRow(int rowNumber) {
	ModelioTableUtils.getInstance().removeRequirement(this.data.getDataList().get(rowNumber).getRequirement());
	this.data.getDataList().remove(rowNumber);
	fireTableRowsInserted(this.data.getDataList().size() - 1, this.data.getDataList().size() - 1);
    }

    public void refresh(MObject container) {
	this.data.newDataList();
	createContent(container);
    }

    public ContainerTableData getData() {
	return data;
    }

}
