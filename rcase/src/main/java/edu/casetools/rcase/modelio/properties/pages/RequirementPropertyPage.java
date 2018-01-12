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

import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

import edu.casetools.rcase.modelio.properties.IPropertyContent;
import edu.casetools.rcase.module.api.RCaseProperties;
import edu.casetools.rcase.module.api.RCaseStereotypes;
import edu.casetools.rcase.module.i18n.I18nMessageService;
import edu.casetools.rcase.module.impl.RCasePeerModule;
import edu.casetools.rcase.utils.PropertiesUtils;

public class RequirementPropertyPage implements IPropertyContent {

    @Override
    public void changeProperty(ModelElement element, int row, String value) {
		switch (row) {
			case 1:
			    PropertiesUtils.getInstance().findAndAddValue(RCasePeerModule.MODULE_NAME,
				    RCaseProperties.PROPERTY_REQUIREMENT_ID, value, element);
			    break;
			case 2:
			    element.setName(value);
			    break;
			case 3:
			    PropertiesUtils.getInstance().findAndAddValue(RCasePeerModule.MODULE_NAME,
				    RCaseProperties.PROPERTY_REQUIREMENT_DESCRIPTION, value, element);
			    break;
			case 4:
			    PropertiesUtils.getInstance().findAndAddValue(RCasePeerModule.MODULE_NAME,
				    RCaseProperties.PROPERTY_REQUIREMENT_CATEGORY, value, element);
			    break;
			case 5:
			    PropertiesUtils.getInstance().findAndAddValue(RCasePeerModule.MODULE_NAME,
				    RCaseProperties.PROPERTY_REQUIREMENT_TYPE, value, element);
			    break;
			default:
			    break;
		}
    }

    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
	
		String property = PropertiesUtils.getInstance().getTaggedValue(RCaseProperties.PROPERTY_REQUIREMENT_ID,
				element);		
		table.addProperty(I18nMessageService.getString("Ui.Requirement.Property.TagId"), property);
		
		table.addProperty(RCaseProperties.PROPERTY_NAME, element.getName());
	
		property = PropertiesUtils.getInstance().getTaggedValue(RCaseProperties.PROPERTY_REQUIREMENT_DESCRIPTION,
			element);
		table.addProperty(I18nMessageService.getString("Ui.Requirement.Property.TagText"), property);
		
		property = getRequirementCategory(PropertiesUtils.getInstance().getTaggedValue(RCaseProperties.PROPERTY_REQUIREMENT_TYPE,
					element));
		table.addProperty(I18nMessageService.getString("Ui.Requirement.Property.TagCategory"), property);
			
		property = PropertiesUtils.getInstance().getTaggedValue(RCaseProperties.PROPERTY_REQUIREMENT_TYPE,
				element);
		
		table.addProperty(I18nMessageService.getString("Ui.Requirement.Property.TagType"), property,
				getDefaultRequirementsTypesString());
			
		checkDependencies(element, table);

    }

	private String[] getDefaultRequirementsTypesString() {
		return new String[] { I18nMessageService.getString("RequirementStereotype.properties.type.functionalcompleteness"),
			I18nMessageService.getString("RequirementStereotype.properties.type.functionalcorrectness"),
			I18nMessageService.getString("RequirementStereotype.properties.type.functionalappropriateness"),
			I18nMessageService.getString("RequirementStereotype.properties.type.maturity"),
			I18nMessageService.getString("RequirementStereotype.properties.type.availability"),
			I18nMessageService.getString("RequirementStereotype.properties.type.faulttolerance"),
			I18nMessageService.getString("RequirementStereotype.properties.type.recoverability"),
			I18nMessageService.getString("RequirementStereotype.properties.type.timebehaviour"),
			I18nMessageService.getString("RequirementStereotype.properties.type.resourceusage"),
			I18nMessageService.getString("RequirementStereotype.properties.type.capacity"),
			I18nMessageService.getString("RequirementStereotype.properties.type.confidentiality"),
			I18nMessageService.getString("RequirementStereotype.properties.type.integrity"),
			I18nMessageService.getString("RequirementStereotype.properties.type.nonrepudiation"),
			I18nMessageService.getString("RequirementStereotype.properties.type.authenticity"),
			I18nMessageService.getString("RequirementStereotype.properties.type.accountability"),
			I18nMessageService.getString("RequirementStereotype.properties.type.coexistence"),					
			I18nMessageService.getString("RequirementStereotype.properties.type.interoperability"),
			I18nMessageService.getString("RequirementStereotype.properties.type.modularity"),
			I18nMessageService.getString("RequirementStereotype.properties.type.reusability"),
			I18nMessageService.getString("RequirementStereotype.properties.type.analysability"),
			I18nMessageService.getString("RequirementStereotype.properties.type.modifyability"),
			I18nMessageService.getString("RequirementStereotype.properties.type.testability"),	
			I18nMessageService.getString("RequirementStereotype.properties.type.adaptability"),
			I18nMessageService.getString("RequirementStereotype.properties.type.installability"),
			I18nMessageService.getString("RequirementStereotype.properties.type.replaceability"),
			I18nMessageService.getString("RequirementStereotype.properties.type.appropriatenessrecognizability"),
			I18nMessageService.getString("RequirementStereotype.properties.type.learnability"),
			I18nMessageService.getString("RequirementStereotype.properties.type.operability"),	
			I18nMessageService.getString("RequirementStereotype.properties.type.usererrorprotection"),
			I18nMessageService.getString("RequirementStereotype.properties.type.userinterfaceaesthetics"),						
			I18nMessageService.getString("RequirementStereotype.properties.type.accesibility") };
	}

    private String getRequirementCategory(String taggedValue) {
		
    	String finalValue = "";
    	if(taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.functionalcompleteness")) |
    	   taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.functionalcorrectness")) |
		   taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.functionalappropriateness")))
    	{
    		finalValue = I18nMessageService.getString("RequirementStereotype.properties.category.functionalsuitability");
    		
		} else if(taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.maturity")) |
		    	   taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.availability")) |
		    	   taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.faulttolerance")) |
		    	   taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.recoverability")))
		{ 
			finalValue = I18nMessageService.getString("RequirementStereotype.properties.category.maintainability");
		
		}  else if(taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.timebehaviour")) |
		    	   taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.resourceusage")) |
		    	   taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.capacity")))
		{ 
			finalValue = I18nMessageService.getString("RequirementStereotype.properties.category.performanceefficiency");
		
		}  else if(taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.confidentiality")) |
		    	   taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.integrity")) |
		    	   taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.nonrepudiation")) |
		    	   taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.authenticity")) |
		    	   taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.accountability")))
		{ 
			finalValue = I18nMessageService.getString("RequirementStereotype.properties.category.security");
		
		}   else if(taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.coexistence")) |
		    	   taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.interoperability")))
		{ 
			finalValue = I18nMessageService.getString("RequirementStereotype.properties.category.compatibility");
		
		}  else if(taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.confidentiality")) |
		    	   taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.integrity")) |
		    	   taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.nonrepudiation")) |
		    	   taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.authenticity")) |
		    	   taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.accountability")))
		{ 
			finalValue = I18nMessageService.getString("RequirementStereotype.properties.category.security");
		
		}  else if(taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.modularity")) |
		    	   taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.reusability")) |
		    	   taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.analysability")) |
		    	   taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.modifyability")) |
		    	   taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.testability")))
		{ 
			finalValue = I18nMessageService.getString("RequirementStereotype.properties.category.maintainability");
		
		}  else if(taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.adaptability")) |
		    	   taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.installability")) |
		    	   taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.replaceability")))
		{ 
			finalValue = I18nMessageService.getString("RequirementStereotype.properties.category.portability");
		
		} else if(taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.appropriatenessrecognizability")) |
		    	   taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.learnability")) |
		    	   taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.operability")) |
		    	   taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.usererrorprotection")) |
		    	   taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.userinterfaceaesthetics")) |
		    	   taggedValue.equals(I18nMessageService.getString("RequirementStereotype.properties.type.accesibility")))
		{ 
			finalValue = I18nMessageService.getString("RequirementStereotype.properties.category.usability");
		
		}  

		return finalValue;
	}

	private void checkDependencies(ModelElement element, IModulePropertyTable table) {
		checkDependency(RCaseStereotypes.STEREOTYPE_DERIVE, "Derive", element, table);
		checkDependency(RCaseStereotypes.STEREOTYPE_COPY, "Copy", element, table);
		checkDependency(RCaseStereotypes.STEREOTYPE_PART, "Part", element, table);
		checkDependency(RCaseStereotypes.STEREOTYPE_REFINE, "Refine", element, table);
		checkDependency(RCaseStereotypes.STEREOTYPE_SATISFY, "Satisfy", element, table);
		checkDependency(RCaseStereotypes.STEREOTYPE_VERIFY, "Verify", element, table);
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
