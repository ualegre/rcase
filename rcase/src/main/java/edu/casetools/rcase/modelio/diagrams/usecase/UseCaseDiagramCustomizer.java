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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Modelio. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package edu.casetools.rcase.modelio.diagrams.usecase;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.modelio.api.diagram.IDiagramCustomizer;
import org.modelio.api.modelio.Modelio;

import edu.casetools.rcase.modelio.diagrams.DiagramCustomizer;
import edu.casetools.rcase.module.api.RCaseTools;
import edu.casetools.rcase.module.i18n.I18nMessageService;

/**
 * The Class UseCaseDiagramCustomizer customizes the palette of the Use Case
 * Diagram.
 */
public class UseCaseDiagramCustomizer extends DiagramCustomizer implements IDiagramCustomizer {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modelio.api.diagram.IDiagramCustomizer#fillPalette(org.eclipse.gef
     * .palette.PaletteRoot)
     */
    @Override
    public void fillPalette(PaletteRoot paletteRoot) {
	for (Iterator<?> localIterator = paletteRoot.getChildren().iterator(); localIterator.hasNext();) {
	    Object children = localIterator.next();
	    if (children instanceof PaletteDrawer) {
		PaletteDrawer currentDrawer = (PaletteDrawer) children;
		String drawerLabel = currentDrawer.getLabel();
		if (drawerLabel.equals(I18nMessageService.getString("UseCasePaletteGroup.Nodes"))) {
		    currentDrawer.add(3,
			    Modelio.getInstance().getDiagramService().getRegisteredTool(RCaseTools.TOOL_USECASE));
		    currentDrawer.getChildren().remove(4);
		}
	    }
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modelio.api.diagram.IDiagramCustomizer#keepBasePalette()
     */
    @Override
    public boolean keepBasePalette() {
	return true;
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
