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
package edu.casetools.rcase.modelio.menu.diagrams;

import java.util.List;

import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.module.IModule;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.modelio.menu.CreateStaticDiagram;
import edu.casetools.rcase.module.api.RCaseResources;
import edu.casetools.rcase.module.api.RCaseStereotypes;
import edu.casetools.rcase.module.i18n.I18nMessageService;
import edu.casetools.rcase.module.impl.RCaseModule;
import edu.casetools.rcase.utils.DiagramUtils;

/**
 * The Class CreateRequirementsDiagram creates a Requirements Diagram.
 */
public class CreateStakeholderDiagram extends CreateStaticDiagram {

    /*
     * (non-Javadoc)
     * 
     * @see edu.casesuite.modelio.menu.CreateDiagram#createOwnDiagram
     * (java.util.List, org.modelio.api.model.IModelingSession)
     */
    @Override
    protected StaticDiagram createOwnDiagram(List<MObject> selectedElements, IModelingSession session) {
	String name = I18nMessageService.getString("Ui.Command.CreateStakeholderDiagram.Label");
	StaticDiagram diagram = DiagramUtils.getInstance().createStaticDiagram(RCaseModule.getInstance(), selectedElements, session, name,
		RCaseStereotypes.STEREOTYPE_DIAGRAM_STAKEHOLDER);
	diagram = (StaticDiagram) addStyle(diagram, RCaseResources.STYLE_STAKEHOLDER_DIAGRAM);
	return diagram;
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
	return commonCheck(selectedElements, module);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modelio.api.module.commands.DefaultModuleCommandHandler#isActiveFor
     * (java.util.List, org.modelio.api.module.IModule)
     */
    @Override
    public boolean isActiveFor(List<MObject> selectedElements, IModule module) {
	return commonCheck(selectedElements, module);
    }

}
