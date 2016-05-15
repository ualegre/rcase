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

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.AssertionFailedException;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.factory.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

import edu.casetools.rcase.modelio.properties.IPropertyContent;
import edu.casetools.rcase.module.api.RCaseProperties;
import edu.casetools.rcase.module.i18n.I18nMessageService;
import edu.casetools.rcase.module.impl.RCasePeerModule;
import edu.casetools.rcase.utils.PropertiesUtils;

public class SituationalParameterPropertyPage implements IPropertyContent {

    private static final Logger logger = Logger.getLogger(SituationalParameterPropertyPage.class.getName());

    // TODO Reduce the complexity of the switch case
    @Override
    public void changeProperty(ModelElement element, int row, String value) {
	try {
	    switch (row) {
	    case 1:
		element.setName(value);
		break;
	    case 2:
		PropertiesUtils.getInstance().findAndAddValue(RCasePeerModule.MODULE_NAME,
			RCaseProperties.PROPERTY_CONTEXT_ID, value, element);
		break;
	    case 3:
		PropertiesUtils.getInstance().findAndAddValue(RCasePeerModule.MODULE_NAME,
			RCaseProperties.PROPERTY_CONTEXT_COST, value, element);
		break;
	    case 4:
		PropertiesUtils.getInstance().findAndAddValue(RCasePeerModule.MODULE_NAME,
			RCaseProperties.PROPERTY_CONTEXT_CONSTRAINTS, value, element);
		break;
	    case 5:
		element.putTagValue(RCasePeerModule.MODULE_NAME, RCaseProperties.PROPERTY_CONTEXT_RESPONSIBILITY,
			value);
		break;
	    case 6:
		element.putTagValue(RCasePeerModule.MODULE_NAME, RCaseProperties.PROPERTY_CONTEXT_FREQUENCY, value);
		break;
	    case 7:
		element.putTagValue(RCasePeerModule.MODULE_NAME, RCaseProperties.PROPERTY_CONTEXT_SOURCE, value);
		break;
	    case 8:
		element.putTagValue(RCasePeerModule.MODULE_NAME, RCaseProperties.PROPERTY_CONTEXT_SENSORTYPE, value);
		break;
	    case 9:
		element.putTagValue(RCasePeerModule.MODULE_NAME, RCaseProperties.PROPERTY_CONTEXT_AQUPROCESS, value);
		break;
	    default:
		break;
	    }
	} catch (ExtensionNotFoundException | AssertionFailedException e) {
	    logger.log(Level.SEVERE, e.getMessage(), e);
	}

    }

    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
	String property;

	table.addProperty(RCaseProperties.PROPERTY_NAME, element.getName());

	String string = PropertiesUtils.getInstance().getTaggedValue(RCaseProperties.PROPERTY_CONTEXT_ID, element);
	table.addProperty(I18nMessageService.getString("Ui.SituationalParameter.Property.TagId"), string);

	string = PropertiesUtils.getInstance().getTaggedValue(RCaseProperties.PROPERTY_CONTEXT_COST, element);
	table.addProperty(I18nMessageService.getString("Ui.SituationalParameter.Property.TagCost"), string);

	string = PropertiesUtils.getInstance().getTaggedValue(RCaseProperties.PROPERTY_CONTEXT_CONSTRAINTS, element);
	table.addProperty(I18nMessageService.getString("Ui.SituationalParameter.Property.TagConstraints"), string);

	// TagResponsibility
	property = element.getTagValue(RCasePeerModule.MODULE_NAME, RCaseProperties.PROPERTY_CONTEXT_RESPONSIBILITY);
	table.addProperty(I18nMessageService.getString("Ui.SituationalParameter.Property.TagResponsibility"), property,
		new String[] { I18nMessageService.getString("Ui.SituationalParameter.Property.TagResponsibility.Pull"),
			I18nMessageService.getString("Ui.SituationalParameter.Property.TagResponsibility.Push"),
			I18nMessageService.getString("Ui.SituationalParameter.Property.TagResponsibility.Other") });

	// TagFrequency
	property = element.getTagValue(RCasePeerModule.MODULE_NAME, RCaseProperties.PROPERTY_CONTEXT_FREQUENCY);
	table.addProperty(I18nMessageService.getString("Ui.SituationalParameter.Property.TagFrequency"), property,
		new String[] { I18nMessageService.getString("Ui.SituationalParameter.Property.TagFrequency.Instant"),
			I18nMessageService.getString("Ui.SituationalParameter.Property.TagFrequency.Interval"),
			I18nMessageService.getString("Ui.SituationalParameter.Property.TagFrequency.Other") });

	// TagSource
	property = element.getTagValue(RCasePeerModule.MODULE_NAME, RCaseProperties.PROPERTY_CONTEXT_SOURCE);
	table.addProperty(I18nMessageService.getString("Ui.SituationalParameter.Property.TagSource"), property,
		new String[] { I18nMessageService.getString("Ui.SituationalParameter.Property.TagSource.Hardware"),
			I18nMessageService.getString("Ui.SituationalParameter.Property.TagSource.Middleware"),
			I18nMessageService.getString("Ui.SituationalParameter.Property.TagSource.Server"),
			I18nMessageService.getString("Ui.SituationalParameter.Property.TagSource.Other") });

	// TagSensorType
	property = element.getTagValue(RCasePeerModule.MODULE_NAME, RCaseProperties.PROPERTY_CONTEXT_SENSORTYPE);
	table.addProperty(I18nMessageService.getString("Ui.SituationalParameter.Property.TagSensorType"), property,
		new String[] { I18nMessageService.getString("Ui.SituationalParameter.Property.TagSensorType.Physical"),
			I18nMessageService.getString("Ui.SituationalParameter.Property.TagSensorType.Virtual"),
			I18nMessageService.getString("Ui.SituationalParameter.Property.TagSensorType.Logical"),
			I18nMessageService.getString("Ui.SituationalParameter.Property.TagSensorType.Other") });

	// TagAcqProcess
	property = element.getTagValue(RCasePeerModule.MODULE_NAME, RCaseProperties.PROPERTY_CONTEXT_AQUPROCESS);
	table.addProperty(I18nMessageService.getString("Ui.SituationalParameter.Property.AcqProcess"), property,
		new String[] { I18nMessageService.getString("Ui.SituationalParameter.Property.AcqProcess.Sense"),
			I18nMessageService.getString("Ui.SituationalParameter.Property.AcqProcess.Derive"),
			I18nMessageService.getString("Ui.SituationalParameter.Property.AcqProcess.Manual"),
			I18nMessageService.getString("Ui.SituationalParameter.Property.AcqProcess.Other") });

    }

}
