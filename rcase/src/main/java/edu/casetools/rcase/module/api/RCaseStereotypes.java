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

    public static final String STEREOTYPE_DIAGRAM_USECASE = "UseCaseDiagram"; 
    public static final String STEREOTYPE_USECASE 		  = "UseCaseStereotype";
    
	public static final String STEREOTYPE_DIAGRAM_STAKEHOLDER = "StakeholderDiagramStereotype";
	public static final String STEREOTYPE_STAKEHOLDER 		  = "StakeholderStereotype";
	public static final String STEREOTYPE_USER_PROFILE 		  = "UserProfileStereotype";
	public static final String STEREOTYPE_VALUE 			  = "ValueStereotype";
	public static final String STEREOTYPE_VALUE_ENHANCER 	  = "ValueEnhancerStereotype";
	public static final String STEREOTYPE_PARTICIPATION 	  = "ParticipationStereotype";
	public static final String STEREOTYPE_GRID 				  = "GridStereotype";
	public static final String STEREOTYPE_ETHICAL_PROFILE 	  = "EthicalProfileStereotype";
	public static final String STEREOTYPE_INTERESTED_IN 	  = "InterestedInStereotype";
	public static final String STEREOTYPE_REQUEST 			  = "RequestStereotype";
	public static final String STEREOTYPE_PROVIDE 			  = "ProvideStereotype";
	public static final String STEREOTYPE_HAS 				  = "HasStereotype";
	public static final String STEREOTYPE_INFLUENCE 		  = "InfluenceStereotype";
	public static final String STEREOTYPE_ENHANCE 			  = "EnhanceStereotype";
	
    public static final String STEREOTYPE_DIAGRAM_REQUIREMENTS  = "RequirementsDiagramStereotype";
    public static final String STEREOTYPE_REQUIREMENT 			= "RequirementStereotype";
    public static final String STEREOTYPE_REQUIREMENT_CONTAINER = "RequirementContainerStereotype";
    public static final String STEREOTYPE_TESTCASE 				= "TestCaseStereotype";
    public static final String STEREOTYPE_PART 					= "PartStereotype";
    public static final String STEREOTYPE_COPY 					= "CopyStereotype";
    public static final String STEREOTYPE_DERIVE 				= "DeriveStereotype";
    public static final String STEREOTYPE_VERIFY 				= "VerifyStereotype";
    public static final String STEREOTYPE_SATISFY 				= "SatisfyStereotype";
    public static final String STEREOTYPE_REFINE 				= "RefineStereotype";   
	public static final String STEREOTYPE_ARGUMENTATION 		= "ArgumentationStereotype";
	
	public static final String STEREOTYPE_DIAGRAM_OBJECTIVE = "ObjectiveDiagramStereotype";
	public static final String STEREOTYPE_GOAL 				= "GoalStereotype";
	public static final String STEREOTYPE_OBSTACLE 			= "ObstacleStereotype";
	public static final String STEREOTYPE_RESOURCE 			= "ResourceStereotype";
	public static final String STEREOTYPE_SOFT_GOAL 		= "SoftGoalStereotype";
	public static final String STEREOTYPE_OBJECTIVE 		= "ObjectiveStereotype";
	public static final String STEREOTYPE_HINDERS 			= "HindersStereotype";
	public static final String STEREOTYPE_MITIGATES 		= "MitigatesStereotype";
	public static final String STEREOTYPE_AND 				= "AndStereotype";	
	public static final String STEREOTYPE_OR 				= "OrStereotype";	
	public static final String STEREOTYPE_XOR 				= "XorStereotype";
	public static final String STEREOTYPE_REFINEOBJ 		= "RefineObjStereotype";	
	public static final String STEREOTYPE_CONTRIBUTION 		= "ContributesStereotype";
	public static final String STEREOTYPE_MAKE 				= "MakeStereotype";
	public static final String STEREOTYPE_HELP 				= "HelpStereotype";
	public static final String STEREOTYPE_UNKNOWN 			= "UnknownStereotype";
	public static final String STEREOTYPE_HURT 				= "HurtStereotype";
	public static final String STEREOTYPE_BREAK 			= "BreakStereotype";
	public static final String STEREOTYPE_EQUAL 			= "EqualStereotype";
	
    public static final String STEREOTYPE_DIAGRAM_SITUATIONS_OF_INTEREST = "SituationsOfInterestDiagramStereotype";  
    public static final String STEREOTYPE_SITUATION_OF_INTEREST 		 = "SituationOfInterestStereotype";
	public static final String STEREOTYPE_CONTEXT_AWARE_FEATURE 		 = "ContextAwareFeatureStereotype";
    public static final String STEREOTYPE_TRIGGERS 						 = "TriggersStereotype";
    public static final String STEREOTYPE_ARISES   						 = 	"ArisesStereotype";
    
    public static final String STEREOTYPE_DIAGRAM_CONTEXT 	= "ContextDiagramStereotype";
    public static final String STEREOTYPE_CONTEXT_ATTRIBUTE = "ContextAttributeStereotype";
    
    public static final String STEREOTYPE_CONTEXT_DERIVE 	 = "ContextDeriveStereotype";
    public static final String STEREOTYPE_CONTEXT_DEPENDENCY = "ContextDependencyStereotype";
    public static final String STEREOTYPE_CONTEXT_IDENTIFIES = "IdentifiesStereotype";

    public static final String DEFAULT_STEREOTYPE = STEREOTYPE_COPY;
	
    private RCaseStereotypes() {

    }

}
