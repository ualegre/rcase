/*
 * Copyright 2015 @author Unai Alegre @company Middlesex University
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
package edu.casetools.rcase.extensions.tables.container.model;

import java.util.ArrayList;
import java.util.List;

import org.modelio.vcore.smkernel.mapi.MObject;

public class ContainerRow {

    List<Object> rowContent;
    MObject requirement;

    public ContainerRow(MObject requirement) {
	rowContent = new ArrayList<>();
	this.requirement = requirement;
    }

    public List<Object> getRowContent() {
	return rowContent;
    }

    public void setRowContent(List<Object> rowContent) {
	this.rowContent = rowContent;
    }

    public MObject getRequirement() {
	return requirement;
    }

    public void setRequirement(MObject requirement) {
	this.requirement = requirement;
    }

}
