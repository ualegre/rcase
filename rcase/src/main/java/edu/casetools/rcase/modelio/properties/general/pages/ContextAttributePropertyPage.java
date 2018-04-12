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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.AssertionFailedException;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

import edu.casetools.rcase.modelio.properties.IPropertyContent;
import edu.casetools.rcase.module.api.RCaseProperties;
import edu.casetools.rcase.module.api.RCaseStereotypes;
import edu.casetools.rcase.module.i18n.I18nMessageService;
import edu.casetools.rcase.module.impl.RCasePeerModule;
import edu.casetools.rcase.utils.PropertiesUtils;

public class ContextAttributePropertyPage implements IPropertyContent {

    private static final Logger logger = Logger.getLogger(ContextAttributePropertyPage.class.getName());

    // TODO Reduce the complexity of the switch case
    @Override
    public void changeProperty(ModelElement element, int row, String value) {
	try {
	    switch (row) {
	    case 1:
		PropertiesUtils.getInstance().findAndAddValue(RCasePeerModule.MODULE_NAME,
			RCaseProperties.PROPERTY_CONTEXT_ID, value, element);
		break;
	    case 2:
		element.setName(value);
		break;
	    case 3:
		element.putTagValue(RCasePeerModule.MODULE_NAME, RCaseProperties.PROPERTY_CONTEXT_TEXT, value);
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

	// TagId
	String string = PropertiesUtils.getInstance().getTaggedValue(RCaseProperties.PROPERTY_CONTEXT_ID, element);
	table.addProperty(I18nMessageService.getString("Ui.ContextAttribute.Property.TagId"), string);

	table.addProperty(RCaseProperties.PROPERTY_NAME, element.getName());

	// TagText
	string = PropertiesUtils.getInstance().getTaggedValue(RCaseProperties.PROPERTY_CONTEXT_TEXT, element);
	table.addProperty(I18nMessageService.getString("Ui.ContextAttribute.Property.TagText"), string);

	checkDependencies(element, table);
    }

    private void checkDependencies(ModelElement element, IModulePropertyTable table) {
<<<<<<< HEAD
=======
	checkDependency(RCaseStereotypes.STEREOTYPE_CONTEXT_DETECTS, "Detects", element, table);
>>>>>>> identifies
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
