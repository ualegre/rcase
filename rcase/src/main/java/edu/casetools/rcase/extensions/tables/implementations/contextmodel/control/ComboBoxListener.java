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

import edu.casetools.rcase.extensions.tables.implementations.contextmodel.ContextModelTable;
import edu.casetools.rcase.extensions.tables.implementations.contextmodel.model.ContextModelTableData;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving comboBox events. The class that is
 * interested in processing a comboBox event implements this interface, and the
 * object created with that class is registered with a component using the
 * component's <code>addComboBoxListener<code> method. When the comboBox event
 * occurs, that object's appropriate method is invoked.
 *
 * @see ComboBoxEvent
 */
public class ComboBoxListener implements ActionListener {

    ContextModelTable main;

    /**
     * Instantiates a new combo box listener.
     *
     * @param main
     *            the main
     */
    public ComboBoxListener(ContextModelTable main) {
	this.main = main;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent event) {

	@SuppressWarnings("unchecked")
	JComboBox<String> combo = (JComboBox<String>) event.getSource();
	String selection = (String) combo.getSelectedItem();
	if (selection != null)
	    update(selection);

    }

    private void update(String selection) {
	ContextModelTableData data = this.main.getTablePanel().getTableModel().getData();
	data.setScope(selection);
	this.main.refreshTable(data);
    }

}
