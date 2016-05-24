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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.AssertionFailedException;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.factory.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

import edu.casetools.rcase.modelio.properties.IPropertyContent;
import edu.casetools.rcase.module.api.RCaseProperties;
import edu.casetools.rcase.module.api.RCaseStereotypes;
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
			RCaseProperties.PROPERTY_CONTEXT_STATUS, value, element);
		break;
	    case 5:
		element.putTagValue(RCasePeerModule.MODULE_NAME, RCaseProperties.PROPERTY_CONTEXT_CREATION_PROCESS,
			value);
		break;
	    case 6:
		element.putTagValue(RCasePeerModule.MODULE_NAME, RCaseProperties.PROPERTY_CONTEXT_USER_INVOLVEMENT,
			value);
		break;
	    case 7:
		element.putTagValue(RCasePeerModule.MODULE_NAME, RCaseProperties.PROPERTY_CONTEXT_VOLATILITY, value);
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

	// TagId
	String string = PropertiesUtils.getInstance().getTaggedValue(RCaseProperties.PROPERTY_CONTEXT_ID, element);
	table.addProperty(I18nMessageService.getString("Ui.SituationalParameter.Property.TagId"), string);

	// TagCost
	string = PropertiesUtils.getInstance().getTaggedValue(RCaseProperties.PROPERTY_CONTEXT_COST, element);
	table.addProperty(I18nMessageService.getString("Ui.SituationalParameter.Property.TagCost"), string);

	// TagStatus
	property = element.getTagValue(RCasePeerModule.MODULE_NAME, RCaseProperties.PROPERTY_CONTEXT_STATUS);
	table.addProperty(I18nMessageService.getString("Ui.SituationalParameter.Property.TagStatus"), property,
		new String[] { I18nMessageService.getString("Ui.SituationalParameter.Property.TagStatus.Raw"),
			I18nMessageService.getString("Ui.SituationalParameter.Property.TagStatus.PreProcessed"),
			I18nMessageService.getString("Ui.SituationalParameter.Property.TagStatus.Atomic"),
			I18nMessageService.getString("Ui.SituationalParameter.Property.TagStatus.Aggregate") });

	// TagCreationProcess
	property = element.getTagValue(RCasePeerModule.MODULE_NAME, RCaseProperties.PROPERTY_CONTEXT_CREATION_PROCESS);
	table.addProperty(I18nMessageService.getString("Ui.SituationalParameter.Property.TagCreationProcess"), property,
		new String[] {
			I18nMessageService.getString("Ui.SituationalParameter.Property.TagCreationProcess.Sensed"),
			I18nMessageService
				.getString("Ui.SituationalParameter.Property.TagCreationProcess.PreProcessed"),
			I18nMessageService.getString("Ui.SituationalParameter.Property.TagCreationProcess.Derived") });

	// TagUserInvolvement
	property = element.getTagValue(RCasePeerModule.MODULE_NAME, RCaseProperties.PROPERTY_CONTEXT_USER_INVOLVEMENT);
	table.addProperty(I18nMessageService.getString("Ui.SituationalParameter.Property.TagUserInvolvement"), property,
		new String[] {
			I18nMessageService.getString("Ui.SituationalParameter.Property.TagUserInvolvement.Explicit"),
			I18nMessageService.getString("Ui.SituationalParameter.Property.TagUserInvolvement.Implicit"),
			I18nMessageService
				.getString("Ui.SituationalParameter.Property.TagUserInvolvement.Disassociated") });

	// TagVolatility
	property = element.getTagValue(RCasePeerModule.MODULE_NAME, RCaseProperties.PROPERTY_CONTEXT_VOLATILITY);
	table.addProperty(I18nMessageService.getString("Ui.SituationalParameter.Property.TagVolatility"), property,
		new String[] { I18nMessageService.getString("Ui.SituationalParameter.Property.TagVolatility.Static"),
			I18nMessageService.getString("Ui.SituationalParameter.Property.TagVolatility.Profiled"),
			I18nMessageService.getString("Ui.SituationalParameter.Property.TagVolatility.Dynamic") });

	checkDependencies(element, table);
    }

    private void checkDependencies(ModelElement element, IModulePropertyTable table) {
	checkDependency(RCaseStereotypes.STEREOTYPE_CONTEXT_IDENTIFIES, "Identifies", element, table);
	checkDependency(RCaseStereotypes.STEREOTYPE_CONTEXT_DERIVE, "Derives", element, table);
	checkDependency(RCaseStereotypes.STEREOTYPE_CONTEXT_DEPENDENCY, "Dependency", element, table);
    }

    private void checkDependency(String stereotype, String name, ModelElement element, IModulePropertyTable table) {
	ArrayList<ModelElement> value = new ArrayList<>();
	String empty = "";

	for (Iterator<?> localIterator = element.getImpactedDependency().iterator(); localIterator.hasNext();) {
	    Dependency dependency = (Dependency) localIterator.next();
	    if (dependency.isStereotyped(RCasePeerModule.MODULE_NAME, stereotype))
		value.add(dependency.getImpacted());
	}

	String valuetab = PropertiesUtils.getInstance().getAbsoluteNamesWithSeparator(value);
	if (!valuetab.equals(empty)) {
	    table.addConsultProperty(I18nMessageService.getString("Ui." + name + ".From"), valuetab);
	}

	value = new ArrayList<>();
	for (Dependency dependency : element.getDependsOnDependency()) {
	    if (dependency.isStereotyped(RCasePeerModule.MODULE_NAME, stereotype))
		value.add(dependency.getDependsOn());
	}
	valuetab = PropertiesUtils.getInstance().getAbsoluteNamesWithSeparator(value);
	if (!valuetab.equals(empty)) {
	    table.addConsultProperty(I18nMessageService.getString("Ui." + name + ".To"), valuetab);
	}
    }

}
