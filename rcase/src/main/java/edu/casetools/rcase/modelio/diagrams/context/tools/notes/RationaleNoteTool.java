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
package edu.casetools.rcase.modelio.diagrams.context.tools.notes;

import org.modelio.api.diagram.IDiagramGraphic;
import org.modelio.api.diagram.IDiagramHandle;
import org.modelio.api.model.IUmlModel;
import org.modelio.metamodel.factory.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;

import edu.casetools.rcase.modelio.diagrams.NoteTool;
import edu.casetools.rcase.module.api.RCaseTools;
import edu.casetools.rcase.utils.DiagramUtils;

/**
 * The Class RationaleNoteDiagramCommand is the tool for creating a Rationale
 * Note.
 */
public class RationaleNoteTool extends NoteTool {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modelio.api.diagram.tools.DefaultAttachedBoxTool#acceptElement(org
     * .modelio.api.diagram.IDiagramHandle,
     * org.modelio.api.diagram.IDiagramGraphic)
     */
    @Override
    public boolean acceptElement(IDiagramHandle representation,
        IDiagramGraphic targetNode) {
        return defaultAcceptElement(representation, targetNode);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.casesuite.modelio.diagrams.NoteTool#createOwnNote
     * (org.modelio.api.model.IUmlModel,
     * org.modelio.metamodel.uml.infrastructure.ModelElement)
     */
    @Override
    protected Note createOwnNote(IUmlModel model, ModelElement owner)
        throws ExtensionNotFoundException {
        return DiagramUtils.getInstance().createNote(model, owner,
            RCaseTools.RATIONALE);
    }

}
