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
package edu.casetools.rcase.extensions.tables.implementations.contextmodel.control;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.extensions.tables.implementations.contextmodel.model.ContextModelTableData;
import edu.casetools.rcase.extensions.tables.implementations.contextmodel.view.ContextModelTablePanel;
import edu.casetools.rcase.module.api.RCaseProperties;
import edu.casetools.rcase.module.i18n.I18nMessageService;
import edu.casetools.rcase.module.impl.RCaseModule;
import edu.casetools.rcase.module.impl.RCasePeerModule;
import edu.casetools.rcase.utils.PropertiesUtils;

public class StringTableModelListener implements TableModelListener {

    ContextModelTablePanel tablePanel;

    public StringTableModelListener(ContextModelTablePanel table) {
	this.tablePanel = table;
    }

    @Override
    public void tableChanged(TableModelEvent event) {

	if (event.getType() == TableModelEvent.UPDATE) {
	    int column = event.getColumn();
	    int row = event.getFirstRow();
	    ContextModelTableData data = this.tablePanel.getTableModel().getData();
	    ModelElement ContextAttribute = data.getDataList().get(column).getContextAttribute();

	    String tagName = data.getHeaders().get(column).getTagName();
	    String newValue = data.getDataList().get(row).get(column).toString();

	    if (row == 0 && tagName.equals(RCaseProperties.PROPERTY_NAME)) {
		updateNameChanges(newValue, ContextAttribute);
	    } else {
		updateTagChanges(tagName, newValue, ContextAttribute);
	    }

	    selectInterval(column, row);
	}

    }

    private void updateNameChanges(String value, ModelElement element) {
	IModelingSession session = RCaseModule.getInstance().getModuleContext().getModelingSession();
	ITransaction transaction = session.createTransaction(
		I18nMessageService.getString("Info.Session.Create", new String[] { " Update Name" }));
	element.setName(value);
	transaction.commit();
	transaction.close();
    }

    private void updateTagChanges(String tagName, String value, MObject element) {
	ITransaction transaction = null;
	IModelingSession session = RCaseModule.getInstance().getModuleContext().getModelingSession();
	try {
	    transaction = session.createTransaction(
		    I18nMessageService.getString("Info.Session.Create", new String[] { "Update Property" }));
	    PropertiesUtils.getInstance().findAndAddValue(RCaseModule.getInstance(), RCasePeerModule.MODULE_NAME, tagName, value,
		    (ModelElement) element);
	    transaction.commit();
	    transaction.close();
	} finally {
	    if (transaction != null)
		transaction.close();
	}
    }

    private void selectInterval(int column, int row) {
	this.tablePanel.getTable().setColumnSelectionInterval(row, row);
	this.tablePanel.getTable().setRowSelectionInterval(column, column);
    }

}
