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
package edu.casetools.rcase.modelio.menu.nodes;

import java.util.List;

import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.module.IModule;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.modelio.menu.CreateElement;
import edu.casetools.rcase.module.api.RCaseProperties;
import edu.casetools.rcase.module.api.RCaseStereotypes;
import edu.casetools.rcase.module.i18n.I18nMessageService;
import edu.casetools.rcase.module.impl.RCaseModule;
import edu.casetools.rcase.module.impl.RCasePeerModule;
import edu.casetools.rcase.utils.ElementUtils;
import edu.casetools.rcase.utils.PropertiesUtils;

/**
 * The Class CreateRequirement creates a Requirement from the contextual menu.
 */
public class CreateSituationOfInterest extends CreateElement {

    /*
     * (non-Javadoc)
     * 
     * @see edu.casesuite.modelio.menu.CreateElement# createOwnElement
     * (java.util.List, org.modelio.api.model.IModelingSession)
     */
    @Override
    public void createOwnElement(List<MObject> selectedElements, IModelingSession session) {
	String name = I18nMessageService.getString("Names.SituationOfInterest");
	MObject soi = ElementUtils.getInstance().createClass(RCaseModule.getInstance(), selectedElements, session, name,
		RCaseStereotypes.STEREOTYPE_SITUATION_OF_INTEREST);
	
	PropertiesUtils.getInstance().findAndAddValue(RCasePeerModule.MODULE_NAME,
		    RCaseProperties.PROPERTY_SITUATION_OF_INTEREST_RECOMMENDATION, I18nMessageService.getString("Ui.SituationOfInterest.Property.TagRecommendation.NotEvaluated"), (ModelElement)soi);
	

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
	return commonCheck(selectedElements);
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
	return commonCheck(selectedElements);
    }

}
