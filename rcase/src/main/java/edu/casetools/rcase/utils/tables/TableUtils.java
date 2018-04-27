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
package edu.casetools.rcase.utils.tables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.ListModel;

import org.modelio.api.modelio.model.IMetamodelExtensions;
import org.modelio.api.module.AbstractJavaModule;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.utils.ModelioUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class TableUtils.
 */
public class TableUtils {

    private static TableUtils instance = null;

    /**
     * Gets the single instance of TableUtils.
     *
     * @return single instance of TableUtils
     */
    public static TableUtils getInstance() {
	if (instance == null) {
	    instance = new TableUtils();
	}
	return instance;
    }

    /**
     * Gets the header.
     *
     * @param stereotypeVector
     *            the stereotype vector
     * @return the header
     */
    public List<MObject> getHeader(AbstractJavaModule module, String moduleName, List<Stereotype> stereotypeVector) {

	List<MObject> linesHeader = new ArrayList<>();

	for (Stereotype stereotype : stereotypeVector) {
	    linesHeader = getAllElementsStereotypedAs(module, moduleName, linesHeader, stereotype.getName());
	}

	return linesHeader;
    }

    /**
     * Gets the all elements stereotyped as.
     *
     * @param list
     *            the list
     * @param stereotype
     *            the stereotype
     * @return the all elements stereotyped as
     */
    public List<MObject> getAllElementsStereotypedAs(AbstractJavaModule module, String moduleName, List<MObject> list, String stereotype) {
	List<MObject> allElements = ModelioUtils.getInstance().getAllElements(module);

	for (MObject object : allElements) {
	    if (object instanceof ModelElement){
	    	if(((ModelElement) object).isStereotyped(moduleName, stereotype))
	    		list.add(object);
	    }
	}

	return list;
    }

    /**
     * Gets the requirements containers.
     *
     * @return the requirements containers
     */
    public List<MObject> getRequirementsContainers(AbstractJavaModule module, String moduleName, String stereotypeName) {
	List<MObject> list = new ArrayList<>();
	list = getAllElementsStereotypedAs(module, moduleName, list, stereotypeName);
	return list;
    }

    /**
     * Gets the requirement container.
     *
     * @param name
     *            the name
     * @return the requirement container
     */
    public MObject getRequirementContainer(AbstractJavaModule module, String moduleName, String name, String stereotypeName) {
	List<MObject> list = new ArrayList<>();
	list = getAllElementsStereotypedAs(module, moduleName, list, stereotypeName);

	for (MObject requirementContainer : list) {
	    if (requirementContainer.getName().equals(name))
		return requirementContainer;
	}
	return null;
    }

    /**
     * Gets the all dependencies stereotypes.
     *
     * @return the all dependencies stereotypes
     */
    public List<Stereotype> getAllDependenciesStereotypes(AbstractJavaModule module) {

	ArrayList<Stereotype> stereotypes = new ArrayList<>();
	Collection<Dependency> dependencies = getAllDependencies(module);

	for (Dependency dependency : dependencies) {
	    stereotypes = getStereotypesFromDependency(stereotypes, dependency);
	}

	return stereotypes;
    }

    /**
     * Gets the all dependencies.
     *
     * @return the all dependencies
     */
    public Collection<Dependency> getAllDependencies(AbstractJavaModule module) {
	return module.getModuleContext().getModelingSession().findByClass(Dependency.class);
    }

    private ArrayList<Stereotype> getStereotypesFromDependency(ArrayList<Stereotype> list, Dependency dependency) {

	for (Stereotype stereotype : dependency.getExtension()) {
	    if (!list.contains(stereotype))
		list.add(stereotype);
	}

	return list;
    }

    /**
     * Gets the dependency stereotpye from name.
     *
     * @param stereotypeName
     *            the stereotype name
     * @return the dependency stereotpye from name
     */
    public Stereotype getDependencyStereotpyeFromName(AbstractJavaModule module, String moduleName, String stereotypeName) {
	IMetamodelExtensions stereotypes = module.getModuleContext().getModelingSession()
		.getMetamodelExtensions();
	return stereotypes.getStereotype(moduleName, stereotypeName,
		module.getModuleContext().getModelioServices().getMetamodelService().getMetamodel()
			.getMClass(Dependency.class));

    }

    /**
     * Gets the stereotypes from names.
     *
     * @param stereotypeNames
     *            the stereotype names
     * @return the stereotypes from names
     */
    public List<Stereotype> getStereotypesFromNames(AbstractJavaModule module, String moduleName, ListModel<String> stereotypeNames) {
	IMetamodelExtensions stereotypes = module.getModuleContext().getModelingSession()
		.getMetamodelExtensions();
	Stereotype stereotype;
	ArrayList<Stereotype> xStereotypes = new ArrayList<>();
	for (int i = 0; i < stereotypeNames.getSize(); i++) {
	    stereotype = stereotypes.getStereotype(moduleName, stereotypeNames.getElementAt(i),
		    module.getModuleContext().getModelioServices().getMetamodelService()
			    .getMetamodel().getMClass(Class.class));
	    if (null != stereotype)
		xStereotypes.add(stereotype);
	}

	for (int i = 0; i < stereotypeNames.getSize(); i++) {
	    stereotype = stereotypes.getStereotype(moduleName, stereotypeNames.getElementAt(i),
		    module.getModuleContext().getModelioServices().getMetamodelService()
			    .getMetamodel().getMClass(UseCase.class));
	    if (null != stereotype)
		xStereotypes.add(stereotype);
	}

	return xStereotypes;
    }

    /**
     * Gets the dependency table longer string.
     *
     * @param list
     *            the list
     * @return the dependency table longer string
     */
    public String getDependencyTableLongerString(List<MObject> list) {
	String longerString = "";
	if (null != list && !list.isEmpty()) {
	    for (MObject header : list) {
		if (header.getName().length() > longerString.length())
		    longerString = header.getName();
	    }
	}
	return longerString;
    }

    /**
     * Gets the container table longer string.
     *
     * @param list
     *            the list
     * @return the container table longer string
     */
    public String getContainerTableLongerString(List<Object> list) {
	String longerString = "";
	if (null != list && !list.isEmpty()) {
	    for (Object header : list) {
		if (((String) header).length() > longerString.length())
		    longerString = (String) header;
	    }
	}
	return longerString;
    }

}
