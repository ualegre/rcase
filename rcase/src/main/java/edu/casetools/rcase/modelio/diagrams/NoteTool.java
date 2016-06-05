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
package edu.casetools.rcase.modelio.diagrams;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.Modelio;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.ILinkPath;
import org.modelio.api.modelio.diagram.tools.DefaultAttachedBoxTool;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.metamodel.factory.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.module.i18n.I18nMessageService;

/**
 * The Class NoteTool has the common methods to create the tool of a note.
 */
public abstract class NoteTool extends DefaultAttachedBoxTool {

    private static final Logger logger = Logger.getLogger(NoteTool.class.getName());

    /**
     * Default method to accept the Note when creating it.
     *
     * @param representation
     *            the diagram handler
     * @param targetNode
     *            the target node where the element is going to be represented
     * @return true, if the target element is accepted
     */
    /*
     * IDiagramHandle is forced by DefaultAttachedBoxTool
     */
    protected boolean defaultAcceptElement(IDiagramHandle representation, IDiagramGraphic targetNode) { // NOSONAR
	MObject target = targetNode.getElement();
	return (target instanceof ModelElement) && (target.getStatus().isModifiable());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modelio.api.diagram.tools.DefaultAttachedBoxTool#actionPerformed(
     * org.modelio.api.diagram.IDiagramHandle,
     * org.modelio.api.diagram.IDiagramGraphic,
     * org.modelio.api.diagram.IDiagramLink.LinkRouterKind,
     * org.modelio.api.diagram.ILinkPath, org.eclipse.draw2d.geometry.Point)
     */
    @Override
    public void actionPerformed(IDiagramHandle paramIDiagramHandle, IDiagramGraphic paramIDiagramGraphic,
	    IDiagramLink.LinkRouterKind paramLinkRouterKind, ILinkPath paramILinkPath, Point paramPoint) {
	IModelingSession session = Modelio.getInstance().getModelingSession();
	IUmlModel model = session.getModel();
	ITransaction transaction = session
		.createTransaction(I18nMessageService.getString("Info.Session.Create", new String[] { "" }));
	try {
	    ModelElement owner = (ModelElement) paramIDiagramGraphic.getElement();

	    Note note = createOwnNote(model, owner);

	    paramIDiagramHandle.unmask(note, paramPoint.x, paramPoint.y);
	    paramIDiagramHandle.save();
	    paramIDiagramHandle.close();
	    transaction.commit();
	    transaction.close();
	} catch (ExtensionNotFoundException e) {
	    logger.log(Level.SEVERE, e.getMessage(), e);
	} finally {
	    transaction.close();
	}

    }

    /**
     * Creates the own note.
     *
     * @param model
     *            the model
     * @param owner
     *            the owner
     * @return the note
     * @throws ExtensionNotFoundException
     *             the extension not found exception
     */
    protected abstract Note createOwnNote(IUmlModel model, ModelElement owner) throws ExtensionNotFoundException;

    /*
     * (non-Javadoc)
     * 
     * @see org.modelio.api.diagram.tools.DefaultAttachedBoxTool#
     * actionPerformedInDiagram (org.modelio.api.diagram.IDiagramHandle,
     * org.eclipse.draw2d.geometry.Rectangle)
     */
    @Override
    public void actionPerformedInDiagram(IDiagramHandle arg0, Rectangle arg1) {
    }

}
