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
package edu.casetools.rcase.extensions.tables.implementations.contextmodel;

import java.awt.BorderLayout;

import edu.casetools.rcase.extensions.tables.TableWindow;
import edu.casetools.rcase.extensions.tables.implementations.contextmodel.model.ContextModelTableData;
import edu.casetools.rcase.extensions.tables.implementations.contextmodel.view.ContextModelMenu;
import edu.casetools.rcase.extensions.tables.implementations.contextmodel.view.ContextModelTablePanel;
import edu.casetools.rcase.extensions.tables.implementations.traceability.view.edit.EditColumnDialog;
import edu.casetools.rcase.module.api.RCaseResources;
import edu.casetools.rcase.module.i18n.I18nMessageService;

// TODO: Auto-generated Javadoc
/**
 * The Class ContextModelTable.
 */
public class ContextModelTable extends TableWindow {

    private static final long serialVersionUID = 2116936883356878759L;
    ContextModelTablePanel tablePanel = null;
    ContextModelMenu menu = null;
    EditColumnDialog columnDialog;

    /**
     * Instantiates a new context model table.
     */
    public ContextModelTable() {
	super();
	this.columnDialog = null;
	this.initIcon(RCaseResources.ICON_CONTEXT_MODEL_TABLE);
	this.setTitle(I18nMessageService.getString("Table.ContextModel.Header.Name"));
	createPanels();
	windowSettings();
	refreshComboBoxAndTable(new ContextModelTableData());
    }

    private void createPanels() {
	this.menu = new ContextModelMenu(this);
	this.tablePanel = new ContextModelTablePanel();
	this.setLayout(new BorderLayout());
	this.add(this.menu, BorderLayout.NORTH);
	this.add(this.tablePanel, BorderLayout.CENTER);
    }

    /**
     * Gets the table panel.
     *
     * @return the table panel
     */
    public ContextModelTablePanel getTablePanel() {
	return this.tablePanel;
    }

    /**
     * Refresh.
     */
    public void refresh() {
	this.tablePanel.refresh(this.tablePanel.getTableModel().getData());
    }

    /**
     * Refresh table.
     *
     * @param data
     *            the data
     */
    public void refreshTable(ContextModelTableData data) {
	this.tablePanel.refresh(data);
    }

    /**
     * Refresh combo box and table.
     *
     * @param data
     *            the data
     */
    public void refreshComboBoxAndTable(ContextModelTableData data) {
	String scope = this.menu.refresh();
	data.setScope(scope);
	data.update();
	this.tablePanel.refresh(data);
    }

    /**
     * Gets the menu.
     *
     * @return the menu
     */
    public ContextModelMenu getMenu() {
	return this.menu;
    }

}
