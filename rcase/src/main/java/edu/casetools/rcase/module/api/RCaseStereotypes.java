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
package edu.casetools.rcase.module.api;

/**
 * The Class RCaseStereotypes contains the name of the Diagrams, Elements and
 * Relations.
 */
public class RCaseStereotypes {

    // Diagram
    public static final String STEREOTYPE_DIAGRAM_REQUIREMENTS = "RequirementDiagram";
    // Elements
    public static final String STEREOTYPE_REQUIREMENT = "RequirementStereotype";

    public static final String STEREOTYPE_SITUATION_OF_INTEREST = "SituationOfInterestStereotype";

    public static final String STEREOTYPE_REQUIREMENT_CONTAINER = "RequirementContainerStereotype";

    public static final String STEREOTYPE_TESTCASE = "TestCaseStereotype";
    // Relations
    public static final String STEREOTYPE_PART = "PartStereotype";

    public static final String STEREOTYPE_COPY = "CopyStereotype";

    public static final String STEREOTYPE_DERIVE = "DeriveStereotype";

    public static final String STEREOTYPE_VERIFY = "VerifyStereotype";

    public static final String STEREOTYPE_SATISFY = "SatisfyStereotype";

    public static final String STEREOTYPE_REFINE = "RefineStereotype";

    // Diagram
    public static final String STEREOTYPE_DIAGRAM_CONTEXT = "DiagramStereotype";
    // Elements
    public static final String STEREOTYPE_SITUATIONAL_PARAMETER = "SituationalParameterStereotype";

    public static final String STEREOTYPE_USECASE = "UseCaseStereotype";

    // Relations
    public static final String STEREOTYPE_CONTEXT_DERIVE = "ContextDeriveStereotype";

    public static final String STEREOTYPE_CONTEXT_DEPENDENCY = "ContextDependencyStereotype";

    public static final String STEREOTYPE_CONTEXT_IDENTIFIES = "IdentifiesStereotype";

    public static final String STEREOTYPE_DIAGRAM_USECASE = "UseCaseDiagram";

    public static final String DEFAULT_STEREOTYPE = STEREOTYPE_COPY;

    private RCaseStereotypes() {

    }

}
