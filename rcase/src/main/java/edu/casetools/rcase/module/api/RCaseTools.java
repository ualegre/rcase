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
 * The Class RCaseTools contains the name of the tools.
 */
public class RCaseTools {

    // Elements
    public static final String TOOL_REQUIREMENTCONTAINER = "RequirementContainerTool";

    public static final String TOOL_REQUIREMENT = "RequirementTool";

    public static final String TOOL_SITUATION_OF_INTEREST = "SituationOfInterestTool";

    public static final String TOOL_TESTCASE = "TestCaseTool";

    // Relations

    public static final String TOOL_PART = "ReqPart";

    public static final String TOOL_COPY = "ReqCopy";

    public static final String TOOL_DERIVE = "ReqDerive";

    public static final String TOOL_SATISFY = "ReqSatisfy";

    public static final String TOOL_VERIFY = "ReqVerify";

    public static final String TOOL_REFINE = "ReqRefine";

    public static final String TOOL_TRACEABILITY = "CREATE_TRACEABILITY";

    // Elements

    public static final String TOOL_CONTEXT_ATTRIBUTE = "ContextAttributeTool";

    public static final String TOOL_USE_CASE = "UseCaseTool";

    public static final String TOOL_NOTE = "NoteTool";

    // Relations
    public static final String TOOL_CONTEXT_DEPENDENCY = "ContextDependencyTool";

    public static final String TOOL_CONTEXT_IDENTIFIES = "IdentifiesTool";

    public static final String TOOL_CONTEXT_DERIVE = "ContextDeriveTool";

    public static final String TOOL_CONTEXT_NOTE = "ContextNote";

    // Elements

    public static final String TOOL_USECASE = "UseCaseTool";

    public static final String PROBLEM = "Problem";

    public static final String RATIONALE = "Rationale";

    public static final String TOOL_TRIGGERS = "TriggersTool";

    private RCaseTools() {

    }

}
