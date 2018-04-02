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

import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * The Interface IPropertyContent defines the content of a property.
 */
public abstract interface IPropertyContent {

    /**
     * Change property.
     *
     * @param paramModelElement
     *            the param model element
     * @param paramInt
     *            the param int
     * @param paramString
     *            the param string
     */
    public abstract void changeProperty(ModelElement paramModelElement, int paramInt, String paramString);

    /**
     * Update.
     *
     * @param paramModelElement
     *            the param model element
     * @param paramIModulePropertyTable
     *            the param i module property table
     */
    public abstract void update(ModelElement paramModelElement, IModulePropertyTable paramIModulePropertyTable);
}
