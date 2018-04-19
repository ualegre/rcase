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
package edu.casetools.rcase.utils;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.api.module.AbstractJavaModule;
import org.modelio.metamodel.diagrams.CommunicationDiagram;
import org.modelio.metamodel.diagrams.SequenceDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.diagrams.UseCaseDiagram;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationChannel;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationInteraction;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationMessage;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.vcore.smkernel.mapi.MObject;


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
     * Creates a static diagram.
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
    public StaticDiagram createStaticDiagram(AbstractJavaModule module, List<MObject> selectedElements, IModelingSession session, String name,
	    String stereotypeName) {
	Stereotype stereotype = session.getMetamodelExtensions().getStereotype(stereotypeName,
		module.getModuleContext().getModelioServices().getMetamodelService().getMetamodel()
			.getMClass(StaticDiagram.class));
	for (MObject element : selectedElements) {
	    StaticDiagram diagram;
	    diagram = session.getModel().createStaticDiagram(name, (ModelElement) element, stereotype);
	    ElementUtils.getInstance().setFreeName(diagram, name);
	    return diagram;
	}
	return null;
    }

    /**
     * Creates a use case diagram.
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
    public UseCaseDiagram createUseCaseDiagram(AbstractJavaModule module, List<MObject> selectedElements, IModelingSession session, String name,
	    String stereotypeName) {
	Stereotype stereotype = session.getMetamodelExtensions().getStereotype(stereotypeName,
		module.getModuleContext().getModelioServices().getMetamodelService().getMetamodel()
			.getMClass(UseCaseDiagram.class));
	for (MObject element : selectedElements) {
	    UseCaseDiagram diagram;
	    diagram = session.getModel().createUseCaseDiagram(name, (ModelElement) element, stereotype);
	    ElementUtils.getInstance().setFreeName(diagram, name);
	    return diagram;
	}
	return null;
    }
    
    public SequenceDiagram createAndAddSequenceDiagram(AbstractJavaModule module, String moduleName, List<MObject> selectedElements, IModelingSession session,
    	    String diagramName, String stereotype) {
    	Interaction interaction;
    	for (MObject owner : selectedElements) {
    	    if (owner instanceof Interaction)
    		interaction = (Interaction) owner;
    	    else
    		interaction = createInteraction((ModelElement) owner, session, diagramName);

    	    if (!findOwnedCollaborations(interaction, diagramName))
    		createCollaboration(interaction, session, diagramName);

    	    SequenceDiagram diagram = createSequenceDiagram(module, moduleName, diagramName, stereotype, interaction, session);

    	    module.getModuleContext().getModelioServices().getEditionService().openEditor(diagram);

    	    return diagram;
    	}
    	return null;
        }

        private static SequenceDiagram createSequenceDiagram(AbstractJavaModule module, String moduleName, String diagramName, String stereotype, Interaction interaction,
    	    IModelingSession session) {
    	SequenceDiagram diagram = session.getModel().createSequenceDiagram();
    	diagram.setOrigin(interaction);
    	ElementUtils.getInstance().setFreeName(diagram, diagramName);
    	try {
    	    Stereotype sysSeqSter = session.getMetamodelExtensions().getStereotype(moduleName,
    		    stereotype, module.getModuleContext().getModelioServices().getMetamodelService()
    			    .getMetamodel().getMClass(SequenceDiagram.class));
    	    diagram.getExtension().add(sysSeqSter);
    	} catch (Exception e) {
    	    logger.log(Level.SEVERE, e.getMessage(), e);
    	}
    	return diagram;
        }

        private static void createCollaboration(Interaction interaction, IModelingSession session, String name) {
	    	Collaboration locals = session.getModel().createCollaboration();
	    	ElementUtils.getInstance().setFreeName(locals, name);
	    	interaction.getOwnedCollaboration().add(locals);
        }

        private static void createCollaboration(CommunicationInteraction interaction, IModelingSession session, String name) {
	    	Collaboration locals = session.getModel().createCollaboration();
	    	ElementUtils.getInstance().setFreeName(locals, name);
	    	interaction.getOwnedCollaboration().add(locals);
        }

        private static boolean findOwnedCollaborations(Interaction interaction, String name) {
	    	boolean notFound = false;
	    	for (Collaboration colla : interaction.getOwnedCollaboration()) {
	    	    if (colla.getName().equals(name)) {
	    		notFound = true;
	    		break;
	    	    }
	
	    	}
	    	return notFound;

        }


        private static Interaction createInteraction(ModelElement owner, IModelingSession session, String name) {
	    	Interaction interaction;
	    	interaction = session.getModel().createInteraction();
	    	interaction.setOwner((NameSpace) owner);
	    	ElementUtils.getInstance().setFreeName(interaction,name);
	    	return interaction;
        }


        public CommunicationDiagram createAndAddCommunicationDiagram(AbstractJavaModule module, String moduleName, List<MObject> selectedElements,
    	    IModelingSession session, String diagramName, String stereotype) {
    	CommunicationInteraction interaction;
    	for (MObject owner : selectedElements) {
    	    if (owner instanceof CommunicationInteraction)
    		interaction = (CommunicationInteraction) owner;
    	    else
    		interaction = createCommunicationInteraction(module, (ModelElement) owner, session, diagramName);

    	    if (!findOwnedCollaborations((Interaction) interaction,diagramName))
    		createCollaboration(interaction, session,diagramName);

    	    CommunicationDiagram diagram = createCommunicationDiagram(module, moduleName, diagramName, stereotype, interaction, session);

    	    module.getModuleContext().getModelioServices().getEditionService().openEditor(diagram);

    	    return diagram;
    	}
    	return null;
        }

        private CommunicationInteraction createCommunicationInteraction(AbstractJavaModule module, ModelElement owner, IModelingSession session, String name) {
    	CommunicationInteraction interaction;
    	interaction = (CommunicationInteraction) session.getModel()
    		.createElement(module.getModuleContext().getModelioServices().getMetamodelService()
    			.getMetamodel().getMClass(CommunicationInteraction.class).getName());
    	interaction.setOwner((NameSpace) owner);
    	ElementUtils.getInstance().setFreeName(interaction,name);
    	return interaction;
        }

        private CommunicationDiagram createCommunicationDiagram(AbstractJavaModule module, String moduleName, String diagramName, String stereotype,
    	    CommunicationInteraction interaction, IModelingSession session) {
	    	CommunicationDiagram diagram = session.getModel().createCommunicationDiagram(
	    			diagramName, interaction, null);
	    	diagram.setOrigin(interaction);
	    	ElementUtils.getInstance().setFreeName(diagram, diagramName);
	    	try {
	    	    Stereotype sysSeqSter = session.getMetamodelExtensions().getStereotype(moduleName,
	    		    stereotype, module.getModuleContext().getModelioServices().getMetamodelService()
	    			    .getMetamodel().getMClass(CommunicationDiagram.class));
	    	    diagram.getExtension().add(sysSeqSter);
	    	} catch (Exception e) {
	    	    logger.log(Level.SEVERE, e.getMessage(), e);
	    	}
	    	return diagram;
        }

        public CommunicationMessage createCommunicationMessage(String moduleName, IUmlModel model, ModelElement owner, String stereotypeName, String name) {
	    	CommunicationMessage createdElement = model.createCommunicationMessage();
	    	createdElement.setChannel((CommunicationChannel) owner);
	    	createdElement.setOwnerTemplateParameter(((UmlModelElement) owner).getOwnerTemplateParameter());
	    	try {
	    	    createdElement.addStereotype(moduleName, stereotypeName);
	    	} catch (ExtensionNotFoundException e) {
	    	    logger.log(Level.SEVERE, e.getMessage(), e);
	    	}
	    	ElementUtils.getInstance().setFreeName(createdElement, name);
	    	return createdElement;
        }

}
