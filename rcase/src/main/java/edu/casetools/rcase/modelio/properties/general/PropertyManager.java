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
package edu.casetools.rcase.modelio.properties.general;

import java.util.List;

import org.modelio.api.modelio.model.IMetamodelExtensions;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.Class;

import edu.casetools.rcase.modelio.properties.CommonPropertyPage;
import edu.casetools.rcase.modelio.properties.IPropertyContent;
import edu.casetools.rcase.modelio.properties.general.pages.ContextAttributePropertyPage;
import edu.casetools.rcase.modelio.properties.general.pages.ContextAwareFeaturePropertyPage;
import edu.casetools.rcase.modelio.properties.general.pages.ContextPreferencePropertyPage;
import edu.casetools.rcase.modelio.properties.general.pages.EthicalProfilePropertyPage;
import edu.casetools.rcase.modelio.properties.general.pages.ObjectivePropertyPage;
import edu.casetools.rcase.modelio.properties.general.pages.ParticipationPropertyPage;
import edu.casetools.rcase.modelio.properties.general.pages.RefineObjPropertyPage;
import edu.casetools.rcase.modelio.properties.general.pages.RequirementPropertyPage;
import edu.casetools.rcase.modelio.properties.general.pages.SituationDetectionPlanPropertyPage;
import edu.casetools.rcase.modelio.properties.general.pages.SituationOfInterestPropertyPage;
import edu.casetools.rcase.modelio.properties.general.pages.StakeholderPropertyPage;
import edu.casetools.rcase.modelio.properties.general.pages.TestCasePropertyPage;
import edu.casetools.rcase.modelio.properties.general.pages.ValueEnhancerPropertyPage;
import edu.casetools.rcase.modelio.properties.general.pages.ValuePropertyPage;
import edu.casetools.rcase.module.api.RCaseStereotypes;
import edu.casetools.rcase.module.impl.RCaseModule;
import edu.casetools.rcase.module.impl.RCasePeerModule;
import edu.casetools.rcase.utils.PropertiesUtils;

/**
 * The Class PropertyManager manages all the property pages.
 */
public class PropertyManager {
    private IPropertyContent propertyPage;
    private IMetamodelExtensions extensions;
    private List<Stereotype> sterList;

    /**
     * Change property.
     *
     * @param element
     *            the element
     * @param row
     *            the row
     * @param value
     *            the value
     * @return the int
     */
    public int changeProperty(ModelElement element, int row, String value) {
	init(element);

	int currentRow = row;
	for (Stereotype ster : sterList) {

	    getPropertyPages(extensions, ster);

	    if (null != this.propertyPage) {
		this.propertyPage.changeProperty(element, currentRow, value);
		currentRow -= ster.getDefinedTagType().size();
		this.propertyPage = null;
	    }
	}

	return currentRow;
    }

    /**
     * Update.
     *
     * @param element
     *            the element
     * @param table
     *            the table
     */
    public void update(ModelElement element, IModulePropertyTable table) {
	init(element);

	for (Stereotype ster : sterList) {

	    getPropertyPages(extensions, ster);
	    updatePropertyPage(element, table);

	}

	this.propertyPage = new CommonPropertyPage();
	this.propertyPage.update(element, table);
    }

    private void init(ModelElement element) {
	this.propertyPage = null;
	extensions = RCaseModule.getInstance().getModuleContext().getModelingSession().getMetamodelExtensions();
	sterList = PropertiesUtils.getInstance().computePropertyList(element);
    }

    private void updatePropertyPage(ModelElement element, IModulePropertyTable table) {
	if (null != this.propertyPage) {
	    this.propertyPage.update(element, table);
	    this.propertyPage = null;
	}
    }

    private void getPropertyPages(IMetamodelExtensions extensions, Stereotype ster) {

		if (ster.equals(extensions.getStereotype(RCasePeerModule.MODULE_NAME,
			RCaseStereotypes.STEREOTYPE_CONTEXT_ATTRIBUTE, RCaseModule.getInstance().getModuleContext()
				.getModelioServices().getMetamodelService().getMetamodel().getMClass(Class.class)))) {
		    this.propertyPage = new ContextAttributePropertyPage();
		}
		
		if (ster.equals(extensions.getStereotype(RCasePeerModule.MODULE_NAME,
				RCaseStereotypes.STEREOTYPE_CONTEXT_PREFERENCE, RCaseModule.getInstance().getModuleContext()
					.getModelioServices().getMetamodelService().getMetamodel().getMClass(Class.class)))) {
			    this.propertyPage = new ContextPreferencePropertyPage();
			}
			
		if (ster.equals(extensions.getStereotype(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_REQUIREMENT,
			RCaseModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel()
				.getMClass(Class.class)))) {
		    this.propertyPage = new RequirementPropertyPage();
		}
	
		if (ster.equals(extensions.getStereotype(RCasePeerModule.MODULE_NAME,
			RCaseStereotypes.STEREOTYPE_SITUATION_OF_INTEREST, RCaseModule.getInstance().getModuleContext()
				.getModelioServices().getMetamodelService().getMetamodel().getMClass(Class.class)))) {
		    this.propertyPage = new SituationOfInterestPropertyPage();
		}
		
		if (ster.equals(extensions.getStereotype(RCasePeerModule.MODULE_NAME,
				RCaseStereotypes.STEREOTYPE_STAKEHOLDER, RCaseModule.getInstance().getModuleContext()
					.getModelioServices().getMetamodelService().getMetamodel().getMClass(Class.class)))) {
			    this.propertyPage = new StakeholderPropertyPage();
		}
		
		if (ster.equals(extensions.getStereotype(RCasePeerModule.MODULE_NAME,
				RCaseStereotypes.STEREOTYPE_ETHICAL_PROFILE, RCaseModule.getInstance().getModuleContext()
					.getModelioServices().getMetamodelService().getMetamodel().getMClass(Class.class)))) {
			    this.propertyPage = new EthicalProfilePropertyPage();
		}
		
		if (ster.equals(extensions.getStereotype(RCasePeerModule.MODULE_NAME,
				RCaseStereotypes.STEREOTYPE_VALUE, RCaseModule.getInstance().getModuleContext()
					.getModelioServices().getMetamodelService().getMetamodel().getMClass(Class.class)))) {
			    this.propertyPage = new ValuePropertyPage();
		}
		
		if (ster.equals(extensions.getStereotype(RCasePeerModule.MODULE_NAME,
				RCaseStereotypes.STEREOTYPE_VALUE_ENHANCER, RCaseModule.getInstance().getModuleContext()
					.getModelioServices().getMetamodelService().getMetamodel().getMClass(Class.class)))) {
			    this.propertyPage = new ValueEnhancerPropertyPage();
		}
		
		if (ster.equals(extensions.getStereotype(RCasePeerModule.MODULE_NAME,
				RCaseStereotypes.STEREOTYPE_PARTICIPATION, RCaseModule.getInstance().getModuleContext()
					.getModelioServices().getMetamodelService().getMetamodel().getMClass(Class.class)))) {
			    this.propertyPage = new ParticipationPropertyPage();
		}
		
		if (ster.equals(extensions.getStereotype(RCasePeerModule.MODULE_NAME,
				RCaseStereotypes.STEREOTYPE_OBJECTIVE, RCaseModule.getInstance().getModuleContext()
					.getModelioServices().getMetamodelService().getMetamodel().getMClass(Class.class)))) {
			    this.propertyPage = new ObjectivePropertyPage();
		}
		
		if (ster.equals(extensions.getStereotype(RCasePeerModule.MODULE_NAME,
				RCaseStereotypes.STEREOTYPE_REFINEOBJ, RCaseModule.getInstance().getModuleContext()
					.getModelioServices().getMetamodelService().getMetamodel().getMClass(Class.class)))) {
			    this.propertyPage = new RefineObjPropertyPage();
		}
		
		if (ster.equals(extensions.getStereotype(RCasePeerModule.MODULE_NAME,
				RCaseStereotypes.STEREOTYPE_TESTCASE, RCaseModule.getInstance().getModuleContext()
					.getModelioServices().getMetamodelService().getMetamodel().getMClass(Class.class)))) {
			    this.propertyPage = new TestCasePropertyPage();
		}
	    
		if (ster.equals(extensions.getStereotype(RCasePeerModule.MODULE_NAME,
				RCaseStereotypes.STEREOTYPE_CONTEXT_AWARE_FEATURE, RCaseModule.getInstance().getModuleContext()
					.getModelioServices().getMetamodelService().getMetamodel().getMClass(Class.class)))) {
			    this.propertyPage = new ContextAwareFeaturePropertyPage();
		}
		
		if (ster.getName().equals(RCaseStereotypes.STEREOTYPE_SITUATION_DETECTION_PLAN)) {
			    this.propertyPage = new SituationDetectionPlanPropertyPage();
		}
	
    }
    
    

}
