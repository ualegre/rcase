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
package edu.casetools.rcase.modelio.diagrams;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.Modelio;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.tools.DefaultBoxTool;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.vcore.smkernel.mapi.MObject;


/**
 * The Class ElementTool has the common methods to create the tool of a diagram
 * element.
 */
@SuppressWarnings("deprecation")
public abstract class ElementTool extends DefaultBoxTool {
    private static final Logger logger = Logger.getLogger(ElementTool.class.getName());

    /**
     * Represent as an image.
     *
     * @param graph
     *            the graph
     * @return the list
     */
    protected abstract List<IDiagramGraphic> representationConfigs(List<IDiagramGraphic> graph);

    /**
     * Creates the customized element.
     *
     * @param session
     *            the session
     * @param element
     *            the element
     * @return the m object
     */
    protected abstract MObject createOwnElement(IModelingSession session, MObject element);

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modelio.api.diagram.tools.DefaultBoxTool#acceptElement(org.modelio
     * .api.diagram.IDiagramHandle, org.modelio.api.diagram.IDiagramGraphic)
     */
    @Override
    public boolean acceptElement(IDiagramHandle representation, IDiagramGraphic target) {
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modelio.api.diagram.tools.DefaultBoxTool#actionPerformed(org.modelio
     * .api.diagram.IDiagramHandle, org.modelio.api.diagram.IDiagramGraphic,
     * org.eclipse.draw2d.geometry.Rectangle)
     */
    @Override
    public void actionPerformed(IDiagramHandle representation, IDiagramGraphic target, Rectangle rect) {
	ITransaction transaction = null;
	try {
	    MObject element = getOrigin(target.getElement());
	    transaction = createElement(representation, element, target, rect);
	} finally {
	    closeTransaction(transaction);
	}

    }

    /**
     * Closes a transaction.
     *
     * @param transaction
     *            the transaction to be closed
     * @return void
     */
    private void closeTransaction(ITransaction transaction) {
	try {
	    transaction.close();
	} catch (NullPointerException e) {
	    logger.log(Level.SEVERE, e.getMessage(), e);
	}
    }

    /**
     * Creates the element (Calls createOwnElement function).
     *
     * @param representation
     *            the diagram handler
     * @param element
     *            the element to be represented
     * @param target
     *            the target where the element will be represented
     * @param rect
     *            the rectangle where the element is going to be represented
     * @return the i transaction
     */
    /*
     * IDiagramGraphic is forced by DefaultBoxTool
     */
    public ITransaction createElement(IDiagramHandle representation, MObject element, IDiagramGraphic target, // NOSONAR
	    Rectangle rect) {

	IModelingSession session = Modelio.getInstance().getModelingSession();

	ITransaction transaction = session.createTransaction("Create");

	MObject createdElement = createOwnElement(session, element);

	List<IDiagramGraphic> graph = representation.unmask(createdElement, rect.x, rect.y);

	graph = representationConfigs(graph);

	if ((null != graph) && (!graph.isEmpty()) && (graph.get(0) instanceof IDiagramNode)) {
	    ((IDiagramNode) graph.get(0)).setBounds(rect);
	}

	representation.save();
	representation.close();
	transaction.commit();

	return transaction;
    }

    private MObject getOrigin(MObject element) {
	MObject auxiliarElement = element;
	if (auxiliarElement instanceof AbstractDiagram)
	    auxiliarElement = ((AbstractDiagram) auxiliarElement).getOrigin();

	return auxiliarElement;
    }

    /**
     * Converts a MObject into an ArrayList<MObject>
     *
     * @param element
     *            the element to be converted (adapted)
     * @return the array list
     */
    protected ArrayList<MObject> adaptElement(MObject element) {
		ArrayList<MObject> elementAdaptor = new ArrayList<>();
		elementAdaptor.add(element);
		return elementAdaptor;
    }
    
    protected  void setDefaultRepresentationConfigs(IDiagramNode dnode) {
	    dnode.setProperty("REPMODE", "SIMPLE");
	    dnode.setProperty("FILLMODE", "SOLID");
	    dnode.setProperty("LINECOLOR", "0,0,0");
	    dnode.setProperty("TEXTCOLOR", "0,0,0");
	    dnode.setProperty("INTAUTOUNMASK", "TRUE");
	    dnode.setProperty("INNERUNMASKFILTER", "ALL");
	    dnode.setProperty("SHOWSTEREOTYPES", "ICON");
	}

}
