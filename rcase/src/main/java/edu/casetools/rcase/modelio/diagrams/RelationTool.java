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
package edu.casetools.rcase.modelio.diagrams;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.modelio.api.diagram.IDiagramGraphic;
import org.modelio.api.diagram.IDiagramHandle;
import org.modelio.api.diagram.IDiagramLink;
import org.modelio.api.diagram.ILinkPath;
import org.modelio.api.diagram.InvalidDestinationPointException;
import org.modelio.api.diagram.InvalidPointsPathException;
import org.modelio.api.diagram.InvalidSourcePointException;
import org.modelio.api.diagram.tools.DefaultLinkTool;
import org.modelio.api.model.IModelingSession;
import org.modelio.api.model.ITransaction;
import org.modelio.api.modelio.Modelio;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.module.i18n.I18nMessageService;
import edu.casetools.rcase.module.impl.RCasePeerModule;

/**
 * The Class RelationTool has the common methods to create the tool of a
 * relation.
 */
public abstract class RelationTool extends DefaultLinkTool {

    private static final Logger logger = Logger.getLogger(RelationTool.class.getName());

    /**
     * Creates the customized dependency.
     *
     * @param originElement
     *            the origin element
     * @param targetElement
     *            the target element
     * @return the dependency
     */
    public abstract Dependency createDependency(ModelElement originElement, ModelElement targetElement);

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modelio.api.diagram.tools.DefaultLinkTool#actionPerformed(org.modelio
     * .api.diagram.IDiagramHandle, org.modelio.api.diagram.IDiagramGraphic,
     * org.modelio.api.diagram.IDiagramGraphic,
     * org.modelio.api.diagram.IDiagramLink.LinkRouterKind,
     * org.modelio.api.diagram.ILinkPath)
     */
    @Override
    public void actionPerformed(IDiagramHandle representation, IDiagramGraphic origin, IDiagramGraphic target,
	    IDiagramLink.LinkRouterKind kind, ILinkPath path) {
	IModelingSession session = Modelio.getInstance().getModelingSession();
	ITransaction transaction = session
		.createTransaction(I18nMessageService.getString("Info.Session.Create", new String[] { "" }));

	try {
	    ModelElement originElement = (ModelElement) origin.getElement();
	    ModelElement targetElement = (ModelElement) target.getElement();

	    Dependency dependency = createDependency(originElement, targetElement);

	    List<IDiagramGraphic> graphics = representation.unmask(dependency, 0, 0);

	    for (IDiagramGraphic graphic : graphics) {
		createLink(kind, path, graphic);
	    }

	    representation.save();
	    representation.close();
	    transaction.commit();
	    transaction.close();
	} finally {
	    transaction.close();
	}
    }

    private void createLink(IDiagramLink.LinkRouterKind kind, ILinkPath path, IDiagramGraphic graphic) {
	IDiagramLink link = (IDiagramLink) graphic;
	if (null != link) {
	    link.setRouterKind(kind);
	    try {
		link.setPath(path);
	    } catch (InvalidPointsPathException | InvalidSourcePointException | InvalidDestinationPointException e) {
		logger.log(Level.SEVERE, e.getMessage(), e);
	    }
	}
    }

    /**
     * Accept method to accept any elements.
     *
     * @param target
     *            the target
     * @return true, if accepts the element
     */
    protected boolean acceptAnyElement(IDiagramGraphic target) {
	MObject element = target.getElement();
	if (element instanceof ModelElement) {
	    return element.getStatus().isModifiable();
	}
	return false;
    }

    /**
     * Accept method to accept only requirements.
     *
     * @param target
     *            the target
     * @param stereotype
     *            list
     * 
     * @return true, if successful
     */
    protected boolean acceptElement(IDiagramGraphic target, String stereotype) {
	if (target.getElement() instanceof ModelElement) {
	    ModelElement modelElement = (ModelElement) target.getElement();
	    if (modelElement.isStereotyped(RCasePeerModule.MODULE_NAME, stereotype))
		return modelElement.getStatus().isModifiable();
	}
	return false;
    }

}