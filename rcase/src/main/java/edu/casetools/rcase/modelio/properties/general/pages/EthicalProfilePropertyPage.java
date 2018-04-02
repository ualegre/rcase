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

public class EthicalProfilePropertyPage implements IPropertyContent {

    @Override
    public void changeProperty(ModelElement element, int row, String value) {
		switch (row) {
			case 1:
			    PropertiesUtils.getInstance().findAndAddValue(RCasePeerModule.MODULE_NAME,
				    RCaseProperties.PROPERTY_ETHICAL_PROFILE_ID, value, element);
			    break;
			case 2:
			    element.setName(value);
			    break;
			case 3:
			    PropertiesUtils.getInstance().findAndAddValue(RCasePeerModule.MODULE_NAME,
				    RCaseProperties.PROPERTY_ETHICAL_PROFILE_DESCRIPTION, value, element);
			    break;
			case 4:
			    PropertiesUtils.getInstance().findAndAddValue(RCasePeerModule.MODULE_NAME,
				    RCaseProperties.PROPERTY_ETHICAL_PROFILE_GOV_DEPENDENCY, value, element);
			    break;
			case 5:
			    PropertiesUtils.getInstance().findAndAddValue(RCasePeerModule.MODULE_NAME,
				    RCaseProperties.PROPERTY_ETHICAL_PROFILE_VULNERABILITY, value, element);
			    break;		
			case 6:
			    PropertiesUtils.getInstance().findAndAddValue(RCasePeerModule.MODULE_NAME,
				    RCaseProperties.PROPERTY_ETHICAL_PROFILE_GRAVITY, value, element);
			    break;	
			case 7:
			    PropertiesUtils.getInstance().findAndAddValue(RCasePeerModule.MODULE_NAME,
				    RCaseProperties.PROPERTY_ETHICAL_PROFILE_VALUE_IN_RISK, value, element);
			    break;		
			case 8:
			    PropertiesUtils.getInstance().findAndAddValue(RCasePeerModule.MODULE_NAME,
				    RCaseProperties.PROPERTY_ETHICAL_PROFILE_POLICY_IMPACT, value, element);
			    break;	
			default:
			    break;
		}
    }

    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
	
		String property = PropertiesUtils.getInstance().getTaggedValue(RCaseProperties.PROPERTY_ETHICAL_PROFILE_ID,
				element);		
		table.addProperty(I18nMessageService.getString("EthicalProfile.properties.id"), property);
		
		table.addProperty(RCaseProperties.PROPERTY_NAME, element.getName());
	
		property = PropertiesUtils.getInstance().getTaggedValue(RCaseProperties.PROPERTY_ETHICAL_PROFILE_DESCRIPTION,
			element);
		table.addProperty(I18nMessageService.getString("EthicalProfile.properties.desciption"), property);
		
		property = PropertiesUtils.getInstance().getTaggedValue(RCaseProperties.PROPERTY_ETHICAL_PROFILE_GOV_DEPENDENCY,
				element);
		table.addProperty(I18nMessageService.getString("EthicalProfile.properties.govdependency"), property,
				new String[] { I18nMessageService.getString("EthicalProfile.properties.profile.high"),
					I18nMessageService.getString("EthicalProfile.properties.profile.medium"),
					I18nMessageService.getString("EthicalProfile.properties.profile.low"),
					I18nMessageService.getString("EthicalProfile.properties.profile.none") });
		
		property = PropertiesUtils.getInstance().getTaggedValue(RCaseProperties.PROPERTY_ETHICAL_PROFILE_VULNERABILITY,
				element);
		table.addProperty(I18nMessageService.getString("EthicalProfile.properties.vulnerability"), property,
				new String[] { I18nMessageService.getString("EthicalProfile.properties.profile.high"),
					I18nMessageService.getString("EthicalProfile.properties.profile.medium"),
					I18nMessageService.getString("EthicalProfile.properties.profile.low"),
					I18nMessageService.getString("EthicalProfile.properties.profile.none") });
		
		property = PropertiesUtils.getInstance().getTaggedValue(RCaseProperties.PROPERTY_ETHICAL_PROFILE_GRAVITY,
				element);
		table.addProperty(I18nMessageService.getString("EthicalProfile.properties.gravity"), property,
				new String[] { I18nMessageService.getString("EthicalProfile.properties.profile.high"),
					I18nMessageService.getString("EthicalProfile.properties.profile.medium"),
					I18nMessageService.getString("EthicalProfile.properties.profile.low"),
					I18nMessageService.getString("EthicalProfile.properties.profile.none") });
		
		property = PropertiesUtils.getInstance().getTaggedValue(RCaseProperties.PROPERTY_ETHICAL_PROFILE_VALUE_IN_RISK,
				element);
		table.addProperty(I18nMessageService.getString("EthicalProfile.properties.valueinrisk"), property,
				new String[] { I18nMessageService.getString("EthicalProfile.properties.profile.high"),
					I18nMessageService.getString("EthicalProfile.properties.profile.medium"),
					I18nMessageService.getString("EthicalProfile.properties.profile.low"),
					I18nMessageService.getString("EthicalProfile.properties.profile.none") });
		
		property = PropertiesUtils.getInstance().getTaggedValue(RCaseProperties.PROPERTY_ETHICAL_PROFILE_POLICY_IMPACT,
				element);
		table.addProperty(I18nMessageService.getString("EthicalProfile.properties.policyimpact"), property,
				new String[] { I18nMessageService.getString("EthicalProfile.properties.profile.high"),
					I18nMessageService.getString("EthicalProfile.properties.profile.medium"),
					I18nMessageService.getString("EthicalProfile.properties.profile.low"),
					I18nMessageService.getString("EthicalProfile.properties.profile.none") });
		

		


    }


}
