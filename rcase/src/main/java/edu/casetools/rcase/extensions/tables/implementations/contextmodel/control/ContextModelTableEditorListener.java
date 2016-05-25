/*
 * Copyright 2015 @author Unai Alegre 
 * 
 * This file is part of RCASE (Requirements for Context-Aware Systems Engineering), a module 
 * of Modelio that aids the requirements elicitation stage of a Context-Aware System (C-AS). 
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
 * along with RCASE.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package edu.casetools.rcase.extensions.tables.implementations.contextmodel.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import org.modelio.api.model.IModelingSession;
import org.modelio.api.model.ITransaction;
import org.modelio.api.modelio.Modelio;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

import edu.casetools.rcase.extensions.tables.implementations.contextmodel.model.ContextModelComboBoxTableEditor;
import edu.casetools.rcase.extensions.tables.implementations.contextmodel.model.ContextModelTableModel;
import edu.casetools.rcase.modelio.properties.pages.SituationalParameterPropertyPage;
import edu.casetools.rcase.module.i18n.I18nMessageService;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving tableEditor events. The class that is
 * interested in processing a tableEditor event implements this interface, and
 * the object created with that class is registered with a component using the
 * component's <code>addTableEditorListener<code> method. When the tableEditor
 * event occurs, that object's appropriate method is invoked.
 *
 * @see TableEditorEvent
 */
public class ContextModelTableEditorListener implements ActionListener {
    ContextModelComboBoxTableEditor editor;
    ContextModelTableModel model;
    SituationalParameterPropertyPage propertyManager;

    /**
     * Instantiates a new table editor listener.
     *
     * @param tableEditor
     *            the table editor
     * @param model
     *            the model
     */
    public ContextModelTableEditorListener(ContextModelComboBoxTableEditor tableEditor, ContextModelTableModel model) {
	this.editor = tableEditor;
	this.model = model;
	propertyManager = new SituationalParameterPropertyPage();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent event) {
	JComboBox<?> combobox = (JComboBox<?>) event.getSource();
	int column = this.editor.getColumn();
	int row = this.editor.getRow();
	ITransaction transaction = null;
	IModelingSession session = Modelio.getInstance().getModelingSession();
	ModelElement element = model.getData().getDataList().get(row).getSituationalParameter();
	try {
	    transaction = session.createTransaction(
		    I18nMessageService.getString("Info.Session.Create", new String[] { " Update Property" }));
	    propertyManager.changeProperty(element, column + 1, combobox.getSelectedItem().toString());
	    this.model.refresh();
	    transaction.commit();
	    transaction.close();
	} finally {
	    if (transaction != null)
		transaction.close();
	}
    }

}
