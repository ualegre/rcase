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
package edu.casetools.rcase.modelio.properties.pages;

import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

import edu.casetools.rcase.modelio.properties.IPropertyContent;
import edu.casetools.rcase.module.api.RCaseProperties;
import edu.casetools.rcase.module.api.RCaseStereotypes;
import edu.casetools.rcase.module.i18n.I18nMessageService;
import edu.casetools.rcase.module.impl.RCasePeerModule;
import edu.casetools.rcase.utils.PropertiesUtils;

public class RefineObjPropertyPage implements IPropertyContent {

	String type;
	
	public RefineObjPropertyPage(){
		type = "";
	}
	
    @Override
    public void changeProperty(ModelElement element, int row, String value) {
		 
    	PropertiesUtils.getInstance().findAndAddValue(RCasePeerModule.MODULE_NAME,
			    RCaseProperties.PROPERTY_REFINE_OBJ_REFINEMENT_TYPE, value, element);


    }

    @Override
    public void update(ModelElement element, IModulePropertyTable table) {

		if (element.isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_AND)){
			type = I18nMessageService.getString("RefineObj.properties.refinementtype.and");
		} else if (element.isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_OR)){
			type = I18nMessageService.getString("RefineObj.properties.refinementtype.or");
		} else if (element.isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_XOR)){
			type = I18nMessageService.getString("RefineObj.properties.refinementtype.xor");
		}
		table.addProperty(I18nMessageService.getString("RefineObj.properties.refinementtype"), type);

    }

  

}
