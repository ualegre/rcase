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

import java.util.List;
import java.util.Map;

import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.modelio.api.diagram.IDiagramService;
import org.modelio.api.module.IModule;

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
     * Initializes the customizer.
     *
     * @param module
     *            the module where the diagram is going to be customized.
     * @param tools
     *            the tool palette that is going to be customized.
     * @param hParameters
     *            the h parameters
     * @param keepBasePalette
     *            the variable selects whether if to keep the base palette of
     *            the original diagram or start from scratch.
     */
    public void initialize(IModule module, List<org.modelio.api.diagram.tools.PaletteEntry> tools,
	    Map<String, String> hParameters, boolean keepBasePalette) {
	/* Method empty because is forced */
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
}
