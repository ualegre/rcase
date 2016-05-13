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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Modelio. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package edu.casetools.rcase.module.impl;

import org.modelio.api.module.IModuleAPIConfiguration;
import org.modelio.vbasic.version.Version;

import edu.casetools.rcase.module.api.IRCasePeerModule;
import edu.casetools.rcase.module.api.RCaseResources;
import edu.casetools.rcase.utils.ResourcesManager;

public class RCasePeerModule implements IRCasePeerModule {
    public static final String MODULE_NAME = "RCase";
    private RCaseModule module;

    private IModuleAPIConfiguration peerConfiguration;

    public RCasePeerModule(RCaseModule statModuleModule, IModuleAPIConfiguration peerConfiguration) {
	super();
	this.module = statModuleModule;
	this.peerConfiguration = peerConfiguration;
    }

    public void init() {
	ResourcesManager.getInstance().setJMDAC(module);
	initStyles();
    }

    private void initStyles() {
	String[] styles = { RCaseResources.STYLE_CONTEXT_DIAGRAM, RCaseResources.STYLE_REQUIREMENTS_DIAGRAM,
		RCaseResources.STYLE_USECASE_DIAGRAM };
	ResourcesManager.getInstance().registerStyles(styles);
    }

    @Override
    public IModuleAPIConfiguration getConfiguration() {
	return peerConfiguration;
    }

    @Override
    public String getDescription() {
	return this.module.getDescription();
    }

    @Override
    public String getName() {
	return this.module.getName();
    }

    @Override
    public Version getVersion() {
	return this.module.getVersion();
    }

}
