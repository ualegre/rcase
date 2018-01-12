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
package edu.casetools.rcase.module.api;

/**
 * The Class RCaseProperties contains the name of each of the properties from
 * the property pages.
 */

public class RCaseProperties {

    /****************************************************************************
     * Common properties
     **************************************************************************/

    public static final String PROPERTY_NAME = "Name";

    /****************************************************************************
     * Requirement related properties
     **************************************************************************/

    public static final String PROPERTY_REQUIREMENT_ID = "ReqTagId";

    public static final String PROPERTY_REQUIREMENT_DESCRIPTION = "TagText";

    /****************************************************************************
     * Situation of Interest related properties
     **************************************************************************/

    public static final String PROPERTY_SITUATION_OF_INTEREST_ID = "SOIId";

    public static final String PROPERTY_SITUATION_OF_INTEREST_DESCRIPTION = "SOIText";

    public static final String PROPERTY_SITUATION_OF_INTEREST_DETECTION = "SOIDetection";

    /****************************************************************************
     * Context Attribute related properties
     **************************************************************************/
    // Context Attribute
    public static final String PROPERTY_CONTEXT_ID = "CATagId";

    public static final String PROPERTY_CONTEXT_TEXT = "CATagText";

	public static final String PROPERTY_STAKEHOLDER_ID = "StakTagId";

	public static final String PROPERTY_STAKEHOLDER_DESCRIPTION = "StakTagDescription";

	public static final String PROPERTY_STAKEHOLDER_TYPE = "StakTagType";

	public static final String PROPERTY_STAKEHOLDER_SUPPORT = "StakTagSupport";

	public static final String PROPERTY_STAKEHOLDER_POWER = "StakTagPower";

	public static final String PROPERTY_ETHICAL_PROFILE_ID = "EthProfTagId";

	public static final String PROPERTY_ETHICAL_PROFILE_DESCRIPTION = "EthTagDescription";

	public static final String PROPERTY_ETHICAL_PROFILE_GOV_DEPENDENCY = "EthTagGovDependency";

	public static final String PROPERTY_ETHICAL_PROFILE_VULNERABILITY = "EthTagVulnerability";

	public static final String PROPERTY_ETHICAL_PROFILE_GRAVITY = "EthTagGravity";

	public static final String PROPERTY_ETHICAL_PROFILE_VALUE_IN_RISK = "EthTagValueInRisk";

	public static final String PROPERTY_ETHICAL_PROFILE_POLICY_IMPACT = "EthTagPolicyImpact";

	public static final String PROPERTY_VALUE_ID = "ValueTagId";

	public static final String PROPERTY_VALUE_DESCRIPTION = "ValueTagDescription";

	public static final String PROPERTY_VALUE_TYPE = "ValueTagType";

	public static final String PROPERTY_VALUE_ENHANCER_ID = "ValueEnhTagId";

	public static final String PROPERTY_VALUE_ENHANCER_DESCRIPTION = "ValueEnhTagDescription";

	public static final String PROPERTY_VALUE_ENHANCER_TYPE = "ValueEnhTagType";

	public static final String PROPERTY_PARTICIPATION_ID = "PartTagId";

	public static final String PROPERTY_PARTICIPATION_DESCRIPTION = "PartTagDescription";

	public static final String PROPERTY_PARTICIPATION_TYPE = "PartTagType";

	public static final String PROPERTY_OBJECTIVE_ID = "ObjTagId";

	public static final String PROPERTY_OBJECTIVE_PRIORITY_LVL = "ObjTagPriority";

	public static final String PROPERTY_OBJECTIVE_DESCRIPTION = "ObjTagDescription";

	public static final String PROPERTY_REFINE_OBJ_REFINEMENT_TYPE = "RefTypeTag";

    private RCaseProperties() {

    }

}
