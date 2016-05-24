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
package edu.casetools.rcase.utils;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.modelio.api.model.IModelingSession;
import org.modelio.api.model.IUmlModel;
import org.modelio.api.modelio.Modelio;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.diagrams.UseCaseDiagram;
import org.modelio.metamodel.factory.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.module.impl.RCasePeerModule;

// TODO: Auto-generated Javadoc
/**
 * The Class DiagramUtils.
 */
public class DiagramUtils {
    private static final Logger logger = Logger.getLogger(DiagramUtils.class.getName());
    private static DiagramUtils instance = null;

    /**
     * Gets the single instance of DiagramUtils.
     *
     * @return single instance of DiagramUtils
     */
    public static DiagramUtils getInstance() {
	if (instance == null) {
	    instance = new DiagramUtils();
	}
	return instance;
    }

    /**
     * Sets the free name.
     *
     * @param element
     *            the element
     * @param testedName
     *            the tested name
     */
    public void setFreeName(ModelElement element, String testedName) {
	List<MObject> nameList = ModelioUtils.getInstance().getAllElements();
	String extension = "";
	int i = 1;
	while (nameExists(nameList, testedName + extension)) {
	    extension = Integer.toString(i);
	    i++;
	}

	element.setName(testedName + extension);
    }

    /**
     * Name exists.
     *
     * @param nameList
     *            the name list
     * @param name
     *            the name
     * @return true, if successful
     */
    public boolean nameExists(List<MObject> nameList, String name) {
	for (MObject object : nameList) {
	    if (object.getName().equals(name))
		return true;
	}
	return false;
    }

    /**
     * Creates the class.
     *
     * @param selectedElements
     *            the selected elements
     * @param session
     *            the session
     * @param name
     *            the name
     * @param stereotypeName
     *            the stereotype name
     * @return the class
     */
    public Class createClass(List<MObject> selectedElements, IModelingSession session, String name,
	    String stereotypeName) {
	Stereotype stereotype = session.getMetamodelExtensions().getStereotype(stereotypeName,
		Modelio.getInstance().getMetamodelService().getMetamodel().getMClass(Class.class));
	for (MObject element : selectedElements) {
	    Class createdElement = session.getModel().createClass(name, (NameSpace) element, stereotype);
	    DiagramUtils.getInstance().setFreeName(createdElement, name);
	    return createdElement;
	}
	return null;
    }

    /**
     * Creates the use case.
     *
     * @param selectedElements
     *            the selected elements
     * @param session
     *            the session
     * @param name
     *            the name
     * @param stereotypeName
     *            the stereotype name
     * @return the use case
     */
    public UseCase createUseCase(List<MObject> selectedElements, IModelingSession session, String name,
	    String stereotypeName) {
	Stereotype stereotype = session.getMetamodelExtensions().getStereotype(stereotypeName,
		Modelio.getInstance().getMetamodelService().getMetamodel().getMClass(UseCase.class));

	for (MObject element : selectedElements) {
	    UseCase createdElement = session.getModel().createUseCase(name, (NameSpace) element, stereotype);
	    DiagramUtils.getInstance().setFreeName(createdElement, name);
	    return createdElement;
	}
	return null;
    }

    /**
     * Creates the package.
     *
     * @param selectedElements
     *            the selected elements
     * @param session
     *            the session
     * @param name
     *            the name
     * @param stereotypeName
     *            the stereotype name
     * @return the package
     */
    public Package createPackage(List<MObject> selectedElements, IModelingSession session, String name,
	    String stereotypeName) {
	Stereotype stereotype = session.getMetamodelExtensions().getStereotype(stereotypeName,
		Modelio.getInstance().getMetamodelService().getMetamodel().getMClass(Package.class));

	for (MObject element : selectedElements) {
	    Package createdElement = session.getModel().createPackage(name, (NameSpace) element, stereotype);
	    DiagramUtils.getInstance().setFreeName(createdElement, name);
	    return createdElement;
	}
	return null;
    }

    /**
     * Creates the diagram.
     *
     * @param selectedElements
     *            the selected elements
     * @param session
     *            the session
     * @param name
     *            the name
     * @param stereotypeName
     *            the stereotype name
     * @return the static diagram
     */
    public StaticDiagram createDiagram(List<MObject> selectedElements, IModelingSession session, String name,
	    String stereotypeName) {
	Stereotype stereotype = session.getMetamodelExtensions().getStereotype(stereotypeName,
		Modelio.getInstance().getMetamodelService().getMetamodel().getMClass(StaticDiagram.class));
	for (MObject element : selectedElements) {
	    StaticDiagram diagram;
	    diagram = session.getModel().createStaticDiagram(name, (ModelElement) element, stereotype);
	    DiagramUtils.getInstance().setFreeName(diagram, name);
	    return diagram;
	}
	return null;
    }

    /**
     * Creates the use case diagram.
     *
     * @param selectedElements
     *            the selected elements
     * @param session
     *            the session
     * @param name
     *            the name
     * @param stereotypeName
     *            the stereotype name
     * @return the use case diagram
     */
    public UseCaseDiagram createUseCaseDiagram(List<MObject> selectedElements, IModelingSession session, String name,
	    String stereotypeName) {
	Stereotype stereotype = session.getMetamodelExtensions().getStereotype(stereotypeName,
		Modelio.getInstance().getMetamodelService().getMetamodel().getMClass(UseCaseDiagram.class));
	for (MObject element : selectedElements) {
	    UseCaseDiagram diagram;
	    diagram = session.getModel().createUseCaseDiagram(name, (ModelElement) element, stereotype);
	    DiagramUtils.getInstance().setFreeName(diagram, name);
	    return diagram;
	}
	return null;
    }

    /**
     * Creates the note.
     *
     * @param model
     *            the model
     * @param owner
     *            the owner
     * @param stereotypeName
     *            the stereotype name
     * @return the note
     * @throws ExtensionNotFoundException
     *             the extension not found exception
     */
    public Note createNote(IUmlModel model, ModelElement owner, String stereotypeName)
	    throws ExtensionNotFoundException {
	return model.createNote(RCasePeerModule.MODULE_NAME, stereotypeName, owner, "");
    }

    /**
     * Creates the dependency.
     *
     * @param origin
     *            the origin
     * @param target
     *            the target
     * @param stereotype
     *            the stereotype
     * @return the dependency
     */
    public Dependency createDependency(ModelElement origin, ModelElement target, String stereotype) {
	try {
	    Dependency dependency = Modelio.getInstance().getModelingSession().getModel().createDependency(origin,
		    target, RCasePeerModule.MODULE_NAME, stereotype);
	    dependency.setName("");
	    return dependency;
	} catch (ExtensionNotFoundException e) {
	    logger.log(Level.SEVERE, e.getMessage(), e);
	}
	return null;
    }

}
