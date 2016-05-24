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
package edu.casetools.rcase.extensions.tables.traceability.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import org.modelio.metamodel.uml.infrastructure.Stereotype;

import edu.casetools.rcase.extensions.tables.traceability.DependencyTable;
import edu.casetools.rcase.extensions.tables.traceability.model.DependencyTableData;
import edu.casetools.rcase.utils.tables.TableUtils;

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

    DependencyTable main;

    /**
     * Instantiates a new combo box listener.
     *
     * @param main
     *            the main
     */
    public ComboBoxListener(DependencyTable main) {
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
	Stereotype stereotype = TableUtils.getInstance().getDependencyStereotpyeFromName(selection);

	if (null != stereotype) {
	    DependencyTableData data = this.main.getTablePanel().getTableModel().getData();
	    data.setLinkStereotype(stereotype);
	    this.main.refreshTable(data);
	}

    }

}
