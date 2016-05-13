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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package edu.casetools.rcase.modelio.menu.tables;

import java.util.List;

import org.modelio.api.module.IModule;
import org.modelio.api.module.commands.DefaultModuleCommandHandler;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.extensions.tables.container.ContainerTable;

/**
 * The Class OpenContainerTable opens the Requirements Container Table from the
 * contextual menu.
 */
public class OpenContainerTable extends DefaultModuleCommandHandler {

    private ContainerTable tableDialog;

    /**
     * Instantiates a new open container table.
     */
    public OpenContainerTable() {
	super();
	this.tableDialog = null;

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modelio.api.module.commands.DefaultModuleCommandHandler#accept(java
     * .util.List, org.modelio.api.module.IModule)
     */
    @Override
    public boolean accept(List<MObject> selectedElements, IModule module) {
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modelio.api.module.commands.DefaultModuleCommandHandler#
     * actionPerformed (java.util.List, org.modelio.api.module.IModule)
     */
    @Override
    public void actionPerformed(List<MObject> selectedElements, IModule module) {
	if (null != this.tableDialog)
	    this.tableDialog.setVisible(true);
	else
	    this.tableDialog = new ContainerTable();
	this.tableDialog.refreshComboAndTable("");
	this.tableDialog.toFront();

    }

}
