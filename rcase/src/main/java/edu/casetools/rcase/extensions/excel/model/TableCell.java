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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package edu.casetools.rcase.extensions.excel.model;

import java.util.List;

/**
 * The Class TableCell represents a cell of an excel table.
 */
public class TableCell {
    private String value;
    private String[] possibleValues;
    private CellType type;

    /**
     * Instantiates a new table cell.
     *
     * @param value
     *            the value of the cell.
     * @param type
     *            the type of cell.
     * @param possibleValues
     *            the possible values that the cell type can have.
     */
    public TableCell(String value, CellType type, List<String> possibleValues) {
	this.value = value;
	this.type = type;
	this.possibleValues = possibleValues.toArray(new String[possibleValues.size()]);
    }

    /**
     * Gets the value of the cell.
     *
     * @return the value of the cell.
     */
    public String getValue() {
	return value;
    }

    /**
     * Gets the type of cell.
     *
     * @return the type of cell.
     */
    public CellType getType() {
	return type;
    }

    /**
     * Gets the possible values that the cell can have.
     *
     * @return the possible values that the cell can have.
     */
    public String[] getPossibleValues() {
	return possibleValues;
    }
}
