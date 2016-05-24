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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Modelio. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package edu.casetools.rcase.extensions.tables.container.control.table;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.modelio.api.model.IModelingSession;
import org.modelio.api.model.ITransaction;
import org.modelio.api.modelio.Modelio;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.extensions.tables.container.ContainerTable;
import edu.casetools.rcase.extensions.tables.container.model.ContainerTableData;
import edu.casetools.rcase.module.api.RCaseProperties;
import edu.casetools.rcase.module.i18n.I18nMessageService;
import edu.casetools.rcase.module.impl.RCasePeerModule;
import edu.casetools.rcase.utils.PropertiesUtils;

public class ContainerTableModelListener implements TableModelListener {

    ContainerTable table;

    public ContainerTableModelListener(ContainerTable table) {
	this.table = table;
    }

    @Override
    public void tableChanged(TableModelEvent event) {
	if (event.getType() == TableModelEvent.UPDATE) {
	    int column = event.getColumn();
	    int row = event.getFirstRow();
	    ContainerTableData data = this.table.getTablePanel().getTableModel().getData();
	    String value = data.getDataList().get(column).getRowContent().get(row).toString();

	    String tagName = data.getxHeaderList().get(row).getTagName();
	    MObject element = data.getDataList().get(column).getRequirement();
	    if (row == 0 && tagName.equals(RCaseProperties.PROPERTY_NAME)) {
		updateNameChanges(value, element);
	    } else {
		updateTagChanges(tagName, value, element);
	    }

	    selectInterval(column, row);
	}

    }

    private void updateNameChanges(String value, MObject element) {
	IModelingSession session = Modelio.getInstance().getModelingSession();
	ITransaction transaction = session.createTransaction(
		I18nMessageService.getString("Info.Session.Create", new String[] { " Update Name" }));
	element.setName(value);
	transaction.commit();
	transaction.close();
    }

    private void updateTagChanges(String tagName, String value, MObject element) {
	ITransaction transaction = null;
	IModelingSession session = Modelio.getInstance().getModelingSession();
	try {
	    transaction = session.createTransaction(
		    I18nMessageService.getString("Info.Session.Create", new String[] { " Update Property" }));
	    PropertiesUtils.getInstance().findAndAddValue(RCasePeerModule.MODULE_NAME, tagName, value, (ModelElement) element);
	    transaction.commit();
	    transaction.close();
	} finally {
	    if (transaction != null)
		transaction.close();
	}
    }

    private void selectInterval(int column, int row) {
	this.table.getTablePanel().getTable().setColumnSelectionInterval(row, row);
	this.table.getTablePanel().getTable().setRowSelectionInterval(column, column);
    }

}
