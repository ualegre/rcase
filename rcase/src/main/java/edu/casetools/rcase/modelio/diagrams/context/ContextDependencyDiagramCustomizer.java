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
package edu.casetools.rcase.modelio.diagrams.context;

import java.util.List;
import java.util.Map;

import org.eclipse.gef.palette.PaletteRoot;
import org.modelio.api.modelio.Modelio;
import org.modelio.api.modelio.diagram.IDiagramCustomizer;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.api.modelio.diagram.tools.PaletteEntry;
import org.modelio.api.module.IModule;

import edu.casetools.rcase.modelio.diagrams.DiagramCustomizer;
import edu.casetools.rcase.module.api.RCaseTools;
import edu.casetools.rcase.module.i18n.I18nMessageService;

/**
 * The Class ContextDependencyDiagramCustomizer customizes the palette of the
 * Context Dependency Diagram.
 */
public class ContextDependencyDiagramCustomizer extends DiagramCustomizer implements IDiagramCustomizer {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modelio.api.diagram.IDiagramCustomizer#fillPalette(org.eclipse.gef
     * .palette.PaletteRoot)
     */
    @Override
    public void fillPalette(PaletteRoot paletteRoot) {
	IDiagramService toolRegistry = Modelio.getInstance().getDiagramService();

	paletteRoot.add(createBasics());
	paletteRoot.add(createNodesGroup(toolRegistry));
	paletteRoot.add(createRelationsGroup(toolRegistry));
	paletteRoot.add(createNotesGroup(toolRegistry));
	paletteRoot.add(createDefaultFreeDrawingGroup(toolRegistry));
    }

    private org.eclipse.gef.palette.PaletteEntry createNodesGroup(IDiagramService toolRegistry) {
	String groupName = I18nMessageService.getString("ContextPaletteGroup.Bloc");
	String[] toolNames = new String[] { RCaseTools.TOOL_SITUATIONAL_PARAMETER,
		RCaseTools.TOOL_SITUATION_OF_INTEREST };
	return createGroup(groupName, toolNames, toolRegistry, 0);
    }

    private org.eclipse.gef.palette.PaletteEntry createNotesGroup(IDiagramService toolRegistry) {
	String groupName = I18nMessageService.getString("ContextPaletteGroup.Notes");
	String[] toolNames = new String[] { // IRCaseTools.TOOL_USE_CASE,
		RCaseTools.TOOL_NOTE };
	return createGroup(groupName, toolNames, toolRegistry, 0);
    }

    private org.eclipse.gef.palette.PaletteEntry createRelationsGroup(IDiagramService toolRegistry) {
	String groupName = I18nMessageService.getString("ContextPaletteGroup.Dependency");
	return createGroup(groupName, new String[] { RCaseTools.TOOL_CONTEXT_DEPENDENCY, RCaseTools.TOOL_CONTEXT_DERIVE,
		RCaseTools.TOOL_CONTEXT_IDENTIFIES }, toolRegistry, 0);
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
    public void initialize(IModule module, List<PaletteEntry> tools, Map<String, String> hParameters,
	    boolean keepBasePalette) {
	/* Method empty because is forced */
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modelio.api.diagram.IDiagramCustomizer#keepBasePalette()
     */
    @Override
    public boolean keepBasePalette() {
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modelio.api.diagram.IDiagramCustomizer#getParameters()
     */
    @Override
    public Map<String, String> getParameters() {
	return null;
    }
}