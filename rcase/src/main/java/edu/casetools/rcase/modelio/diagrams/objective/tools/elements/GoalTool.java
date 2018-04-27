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
package edu.casetools.rcase.modelio.diagrams.objective.tools.elements;

import java.util.List;

import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.modelio.diagrams.ElementTool;
import edu.casetools.rcase.module.api.RCaseColours;
import edu.casetools.rcase.module.api.RCaseStereotypes;
import edu.casetools.rcase.module.i18n.I18nMessageService;
import edu.casetools.rcase.module.impl.RCaseModule;
import edu.casetools.rcase.module.impl.RCasePeerModule;
import edu.casetools.rcase.utils.ElementUtils;

	/**
	 * The Class RequirementContainerTool is the tool for creating a Requirement
	 * Container.
	 */
	public class GoalTool extends ElementTool {
	
	    /*
	     * (non-Javadoc)
	     * 
	     * @see edu.casesuite.modelio.diagrams.ElementTool# createOwnElement
	     * (org.modelio.api.model.IModelingSession,
	     * org.modelio.vcore.smkernel.mapi.MObject)
	     */
	    @Override
	    public MObject createOwnElement(IModelingSession session, MObject element) {
		String name = I18nMessageService.getString("Names.Goal");
		Class object = ElementUtils.getInstance().createClass(RCaseModule.getInstance(), adaptElement(element), session, name,
			RCaseStereotypes.STEREOTYPE_OBJECTIVE);
		return ElementUtils.getInstance().addStereotype(RCasePeerModule.MODULE_NAME, object, RCaseStereotypes.STEREOTYPE_GOAL);
	
	}


    /*
     * (non-Javadoc)
     * 
     * @see edu.casesuite.modelio.diagrams.ElementTool# representAsImage
     * (java.util.List)
     */
    @Override
    protected List<IDiagramGraphic> representationConfigs(List<IDiagramGraphic> graph) {

	if ((null != graph) && (!graph.isEmpty()) && (graph.get(0) instanceof IDiagramNode)) {
	    IDiagramNode dnode = (IDiagramNode) graph.get(0);
	    this.setDefaultRepresentationConfigs(dnode);
	    dnode.setProperty("FILLCOLOR", RCaseColours.RED2);
	}

	return graph;
    }

}
