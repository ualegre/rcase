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

import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.modelio.api.modelio.diagram.IDiagramService;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import edu.casetools.rcase.module.i18n.I18nMessageService;

/**
 * The Class DiagramCustomizer has the common methods for customizing diagrams.
 */
public class DiagramCustomizer {

    /**
     * Creates the basics of a palette drawer.
     *
     * @param toolRegistry
     *            the tool registry of the diagram service
     * @return the palette drawer
     */
    protected PaletteDrawer createBasics() {
	PaletteDrawer commonGroup = new PaletteDrawer("Default", null);
	commonGroup.setInitialState(0);
	commonGroup.add(new SelectionToolEntry());
	commonGroup.add(new MarqueeToolEntry());
	return commonGroup;
    }

    /**
     * Creates the default free drawing group.
     *
     * @param toolRegistry
     *            the tool registry
     * @return the palette entry
     */
    protected PaletteEntry createDefaultFreeDrawingGroup(IDiagramService toolRegistry) {
	String groupName = I18nMessageService.getString("ContextPaletteGroup.Freedrawing");
	String[] toolNames = new String[] { "CREATE_DRAWING_RECTANGLE", "CREATE_DRAWING_ELLIPSE", "CREATE_DRAWING_TEXT",
		"CREATE_DRAWING_LINE" };
	return createGroup(groupName, toolNames, toolRegistry, 1);
    }

    /**
     * Creates the default notes group.
     *
     * @param toolRegistry
     *            the tool registry
     * @return the palette entry
     */
    protected PaletteEntry createDefaultNotesGroup(IDiagramService toolRegistry) {
	String groupName = I18nMessageService.getString("ContextPaletteGroup.NotesAndConstraints");
	String[] toolNames = new String[] { "Problem", "Rationale", "CREATE_NOTE", "CREATE_CONSTRAINT",
		"CREATE_EXTERNDOCUMENT" };
	return createGroup(groupName, toolNames, toolRegistry, 1);
    }

    /**
     * Creates a customized group.
     *
     * @param groupName
     *            the group name
     * @param toolNames
     *            the list of the name of the tools that will be initialized.
     * @param toolRegistry
     *            the tool registry
     * @param initialState
     *            the initial state
     * @return the palette drawer
     */
    protected PaletteDrawer createGroup(String groupName, String[] toolNames, IDiagramService toolRegistry,
	    int initialState) {
	PaletteDrawer group = new PaletteDrawer(groupName, null);
	for (int i = 0; i < toolNames.length; i++) {
	    group.add(toolRegistry.getRegisteredTool(toolNames[i]));
	}
	group.setInitialState(initialState);
	return group;
    }
    
    /**
     * Creates the note and constraint and dependency group.
     * 
     * @param imageService
     *            service used to get metaclasses bitmaps.
     * @return The created group.
     */
    @objid("7a1a5af2-55b6-11e2-877f-002564c97630")
    protected PaletteDrawer createCommonGroup(IDiagramService toolRegistry) {
	// common group
	String groupName = I18nMessageService.getString("ContextCommunicationPaletteGroup.Common");
	String[] toolNames = new String[] { "CREATE_NOTE", "CREATE_CONSTRAINT", "CREATE_EXTERNDOCUMENT",
		"CREATE_DEPENDENCY", "CREATE_TRACEABILITY", "CREATE_RELATED_DIAGRAM_LINK" };
	return createGroup(groupName, toolNames, toolRegistry, 0);
    }
}
