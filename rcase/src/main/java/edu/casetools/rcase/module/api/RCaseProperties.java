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

    public static final String PROPERTY_REQUIREMENT_ID = "SOIId";

    public static final String PROPERTY_REQUIREMENT_DESCRIPTION = "SOIText";

    /****************************************************************************
     * Situation of Interest related properties
     **************************************************************************/

    public static final String PROPERTY_SITUATION_OF_INTEREST_ID = "ReqTagId";

    public static final String PROPERTY_SITUATION_OF_INTEREST_DESCRIPTION = "TagText";

    /****************************************************************************
     * Situational Parameter related properties
     **************************************************************************/
    // Situational Parameter
    public static final String PROPERTY_CONTEXT_ID = "CETagId";

    public static final String PROPERTY_CONTEXT_COST = "TagCost";

    public static final String PROPERTY_CONTEXT_STATUS = "TagStatus";

    public static final String PROPERTY_CONTEXT_CREATION_PROCESS = "TagCreationProcess";

    public static final String PROPERTY_CONTEXT_USER_INVOLVEMENT = "TagUserInvolvement";

    public static final String PROPERTY_CONTEXT_VOLATILITY = "TagVolatility";

    private RCaseProperties() {

    }

}
