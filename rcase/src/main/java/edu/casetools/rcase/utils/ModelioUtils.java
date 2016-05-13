/*
 * Copyright 2015 @author Unai Alegre @company Middlesex University
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
package edu.casetools.rcase.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.modelio.api.model.IModelingSession;
import org.modelio.api.modelio.Modelio;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.vcore.smkernel.mapi.MObject;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelioUtils.
 */
public class ModelioUtils {
    private static ModelioUtils instance = null;
    private static final String LOCAL_MODULE = "LocalModule";

    /**
     * Gets the single instance of ModelioUtils.
     *
     * @return single instance of ModelioUtils
     */
    public static ModelioUtils getInstance() {
	if (instance == null) {
	    instance = new ModelioUtils();
	}
	return instance;
    }

    /**
     * Gets the all elements.
     *
     * @return the all elements
     */
    public List<MObject> getAllElements() {
	ArrayList<MObject> vector = new ArrayList<>();
	IModelingSession session = Modelio.getInstance().getModelingSession();
	for (MObject rootObj : session.getModel().getModelRoots()) {
	    if (((rootObj instanceof GeneralClass) || (rootObj instanceof Project))
		    && (!rootObj.getName().equals(LOCAL_MODULE))) {

		for (MObject child : rootObj.getCompositionChildren()) {
		    vector = getElementsFromMObject(vector, child);
		}

	    }
	}
	return vector;
    }

    private ArrayList<MObject> getElementsFromMObject(ArrayList<MObject> vector, MObject project) {
	ArrayList<MObject> auxiliarVector = vector;
	for (MObject child : project.getCompositionChildren()) {

	    if (child instanceof Package) {
		auxiliarVector = getElementsFromMObject(auxiliarVector, child);
	    }

	    auxiliarVector.add(child);
	}
	return auxiliarVector;

    }

    /**
     * Gets the stereotype of element by name.
     *
     * @param elementName
     *            the element name
     * @return the stereotype of element by name
     */
    public Stereotype getStereotypeOfElementByName(String elementName) {
	ModelElement element = (ModelElement) getElementByName(elementName);
	if (element != null) {
	    EList<Stereotype> extensions = element.getExtension();
	    for (Stereotype stereotype : extensions) {
		return stereotype;
	    }
	}
	return null;
    }

    /**
     * Gets the element by name.
     *
     * @param elementName
     *            the element name
     * @return the element by name
     */
    public MObject getElementByName(String elementName) {
	List<MObject> elementsList = this.getAllElements();
	for (MObject element : elementsList) {
	    if (element.getName().equals(elementName))
		return element;
	}
	return null;
    }

}
