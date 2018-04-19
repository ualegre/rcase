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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package edu.casetools.rcase.extensions.tables.implementations.container;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.extensions.tables.TableWindow;
import edu.casetools.rcase.extensions.tables.implementations.container.view.ContainerTablePanel;
import edu.casetools.rcase.extensions.tables.implementations.container.view.TableMenu;
import edu.casetools.rcase.module.api.RCaseResources;
import edu.casetools.rcase.module.api.RCaseStereotypes;
import edu.casetools.rcase.module.i18n.I18nMessageService;
import edu.casetools.rcase.module.impl.RCaseModule;
import edu.casetools.rcase.module.impl.RCasePeerModule;
import edu.casetools.rcase.utils.tables.TableUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ContainerTable.
 */
public class ContainerTable extends TableWindow {

    private static final long serialVersionUID = 8182285327639747835L;
    private ContainerTablePanel tablePanel = null;
    private TableMenu menu = null;

    /**
     * Instantiates a new container table.
     */
    public ContainerTable() {

	super();
	this.initIcon(RCaseResources.ICON_CONTAINER_TABLE);
	this.initTitle();
	this.initPanels();
	this.windowSettings();

    }

    private void initPanels() {
	this.tablePanel = new ContainerTablePanel(this);
	this.menu = new TableMenu(this);
	this.setLayout(new BorderLayout());
	this.add(this.menu, BorderLayout.NORTH);
	this.add(this.tablePanel, BorderLayout.CENTER);
    }

    private void initTitle() {
	List<MObject> list = TableUtils.getInstance().getRequirementsContainers(RCaseModule.getInstance(), RCasePeerModule.MODULE_NAME);
	String name = "";

	if ((null != list) && (!list.isEmpty()))
	    name = list.get(0).getName();

	name = I18nMessageService.getString("ContainerTable.Title") + " - " + name;

	this.setTitle(name);
    }

    private void initTitle(String command) {
	if (null != command) {
	    String name = I18nMessageService.getString("ContainerTable.Title") + " - " + command;

	    this.setTitle(name);
	}
    }

    /**
     * Gets the menu.
     *
     * @return the menu
     */
    public TableMenu getMenu() {
	return this.menu;
    }

    /**
     * Gets the table panel.
     *
     * @return the table panel
     */
    public ContainerTablePanel getTablePanel() {
	return this.tablePanel;
    }

    /**
     * Refresh table.
     *
     * @param containerName
     *            the container name
     */
    public void refreshTable(String containerName) {
	MObject container = findContainer(containerName);
	if (null != container) {
	    this.tablePanel.refresh(container);
	    this.initTitle(containerName);
	}
    }

    /**
     * Refresh combo and table.
     *
     * @param containerName
     *            the container name
     */
    public void refreshComboAndTable(String containerName) {
	this.menu.refresh(containerName);
	MObject container = findContainer(containerName);
	if (null != container) {
	    this.tablePanel.refresh(container);
	}

    }

    private MObject findContainer(String command) {
	List<MObject> list = new ArrayList<>();
	list = TableUtils.getInstance().getAllElementsStereotypedAs(RCaseModule.getInstance(), RCasePeerModule.MODULE_NAME, list,
		RCaseStereotypes.STEREOTYPE_REQUIREMENT_CONTAINER);
	for (MObject container : list) {
	    if (container.getName().equals(command))
		return container;
	}

	for (MObject container : list) {
	    return container;
	}

	return null;

    }

}
