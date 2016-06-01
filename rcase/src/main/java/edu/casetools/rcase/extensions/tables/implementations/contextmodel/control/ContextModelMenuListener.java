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

import edu.casetools.rcase.extensions.tables.implementations.contextmodel.ContextModelTable;
import edu.casetools.rcase.extensions.tables.implementations.contextmodel.model.ContextModelTableData;
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
public class ContextModelMenuListener implements ActionListener {

    private ContextModelTable main;

    /**
     * Instantiates a new matrix menu listener.
     *
     * @param main
     *            the main
     */
    public ContextModelMenuListener(ContextModelTable main) {
	this.main = main;

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
	ContextModelTableData data = this.main.getTablePanel().getTableModel().getData();

	if (command.equals(I18nMessageService.getString("Menu.Matrix.Refresh"))) {
	    this.main.refreshComboBoxAndTable(data);
	}

    }

}
