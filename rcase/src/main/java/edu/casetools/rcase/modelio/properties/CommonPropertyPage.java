/*
 * Copyright 2015 @author Unai Alegre 
 * 
 * This file is part of RCASE (Requirements for Context-Aware Systems Engineering), a module 
 * of Modelio that aids the requirements elicitation phase of a Context-Aware System (C-AS). 
 * 
 * RCASE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * RCASE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package edu.casetools.rcase.modelio.properties;

import java.util.ArrayList;
import java.util.Iterator;

import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

import edu.casetools.rcase.module.i18n.I18nMessageService;
import edu.casetools.rcase.utils.PropertiesUtils;

/**
 * The Class CommonPropertyPage has the methods for creating a Property Page.
 */
public class CommonPropertyPage implements IPropertyContent {

    public static final String EMPTY = "";

    /*
     * (non-Javadoc)
     * 
     * @see edu.casesuite.modelio.properties.IPropertyContent#
     * update (org.modelio.metamodel.uml.infrastructure.ModelElement,
     * org.modelio.api.module.propertiesPage.IModulePropertyTable)
     */
    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
	ArrayList<ModelElement> value = new ArrayList<>();
	for (Iterator<?> localIterator = element.getImpactedDependency().iterator(); localIterator.hasNext();) {
	    Dependency dependency = (Dependency) localIterator.next();
	    if (dependency.isStereotyped("ModelerModule", "trace"))
		value.add(dependency.getImpacted());
	}

	String valuetab = PropertiesUtils.getInstance().getAbsoluteNamesWithSeparator(value);
	if (!valuetab.equals(EMPTY)) {
	    table.addConsultProperty(I18nMessageService.getString("Ui.Trace.From"), valuetab);
	}
	value = new ArrayList<>();
	for (Dependency dependency : element.getDependsOnDependency()) {
	    if (dependency.isStereotyped("ModelerModule", "trace")) {
		value.add(dependency.getDependsOn());
	    }
	}
	valuetab = PropertiesUtils.getInstance().getAbsoluteNamesWithSeparator(value);
	if (!valuetab.equals(EMPTY))
	    table.addConsultProperty(I18nMessageService.getString("Ui.Trace.To"), valuetab);
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.casesuite.modelio.properties.IPropertyContent#
     * changeProperty(org.modelio.metamodel.uml.infrastructure.ModelElement,
     * int, java.lang.String)
     */
    @Override
    public void changeProperty(ModelElement element, int row, String value) {
	/*
	 * The changeProperty needs to be empty. It is only here because the
	 * interface forces it.
	 */
    }
}
