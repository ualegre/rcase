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
package edu.casetools.rcase.modelio.properties.general.pages;

import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

import edu.casetools.rcase.modelio.properties.IPropertyContent;
import edu.casetools.rcase.module.api.RCaseProperties;
import edu.casetools.rcase.module.i18n.I18nMessageService;
import edu.casetools.rcase.module.impl.RCasePeerModule;
import edu.casetools.rcase.utils.PropertiesUtils;

public class ParticipationPropertyPage implements IPropertyContent {

    @Override
    public void changeProperty(ModelElement element, int row, String value) {
		switch (row) {
			case 1:
			    PropertiesUtils.getInstance().findAndAddValue(RCasePeerModule.MODULE_NAME,
				    RCaseProperties.PROPERTY_PARTICIPATION_ID, value, element);
			    break;
			case 2:
			    element.setName(value);
			    break;
			case 3:
			    PropertiesUtils.getInstance().findAndAddValue(RCasePeerModule.MODULE_NAME,
				    RCaseProperties.PROPERTY_PARTICIPATION_DESCRIPTION, value, element);
			    break;
			case 4:
			    PropertiesUtils.getInstance().findAndAddValue(RCasePeerModule.MODULE_NAME,
				    RCaseProperties.PROPERTY_PARTICIPATION_TYPE, value, element);
			    break;
			default:
			    break;
		}
    }

    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
	
		String property = PropertiesUtils.getInstance().getTaggedValue(RCaseProperties.PROPERTY_PARTICIPATION_ID,
				element);		
		table.addProperty(I18nMessageService.getString("EthicalProfile.properties.id"), property);
		
		table.addProperty(RCaseProperties.PROPERTY_NAME, element.getName());
	
		property = PropertiesUtils.getInstance().getTaggedValue(RCaseProperties.PROPERTY_PARTICIPATION_DESCRIPTION,
			element);
		table.addProperty(I18nMessageService.getString("EthicalProfile.properties.desciption"), property);
		
		property = PropertiesUtils.getInstance().getTaggedValue(RCaseProperties.PROPERTY_PARTICIPATION_TYPE,
				element);
		table.addProperty(I18nMessageService.getString("Participation.properties.participationtype"), property,
				new String[] { I18nMessageService.getString("Participation.properties.participationtype.notengaged"),
					I18nMessageService.getString("Participation.properties.participationtype.datasource"),
					I18nMessageService.getString("Participation.properties.participationtype.inform"),
					I18nMessageService.getString("Participation.properties.participationtype.consult"),
					I18nMessageService.getString("Participation.properties.participationtype.involve"),
					I18nMessageService.getString("Participation.properties.participationtype.collaborate"),
					I18nMessageService.getString("Participation.properties.participationtype.empower")});
	

		


    }


}
