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
package edu.casetools.rcase.extensions.tables.implementations.container.control.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.extensions.excel.view.ContainerTableFileChooser;
import edu.casetools.rcase.extensions.tables.implementations.container.ContainerTable;
import edu.casetools.rcase.modelio.menu.nodes.CreateRequirement;
import edu.casetools.rcase.modelio.menu.nodes.CreateRequirementContainer;
import edu.casetools.rcase.module.i18n.I18nMessageService;
import edu.casetools.rcase.module.impl.RCaseModule;
import edu.casetools.rcase.utils.tables.TableUtils;

public class MenuListener implements ActionListener {

    ContainerTable mainWindow;
    CreateRequirement createRequirement;
    CreateRequirementContainer createContainer;
    ContainerTableFileChooser fileChooser;

    public MenuListener(ContainerTable mainWindow) {
	this.mainWindow = mainWindow;
	this.createRequirement = new CreateRequirement();
	this.createContainer = new CreateRequirementContainer();
	this.fileChooser = new ContainerTableFileChooser();
    }

    @Override
    public void actionPerformed(ActionEvent paramActionEvent) {
	String command = paramActionEvent.getActionCommand();

	if (command.equals(I18nMessageService.getString("Menu.Container.New")))
	    addRequirement();
	else if (command.equals(I18nMessageService.getString("Menu.Container.Delete")))
	    this.mainWindow.getTablePanel().removeRow();
	else if (command.equals(I18nMessageService.getString("Menu.Container.Refresh"))) {
	    this.mainWindow.refreshComboAndTable(null);
	} else if (command.equals(I18nMessageService.getString("Menu.Container.Export"))) {
	    this.fileChooser.export(this.mainWindow);
	} else if (command.equals(I18nMessageService.getString("Menu.Container.Import"))) {
	    // TODO Implement the importation of tables to the application
	}

    }

    private void addRequirement() {
	ArrayList<MObject> containerAdaptor = new ArrayList<>();
	String name = (String) this.mainWindow.getMenu().getComboBox().getSelectedItem();
	MObject container = TableUtils.getInstance().getRequirementContainer(RCaseModule.getInstance(), name);
	if (container != null) {
	    containerAdaptor.add(container);
	    this.createRequirement.actionPerformed(containerAdaptor, null);
	    this.mainWindow.refreshTable(name);
	}
    }
}
