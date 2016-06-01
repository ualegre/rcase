/*
 * Copyright 2015 @author Unai Alegre 
 * 
 * This file is part of RCASE (Requirements for Context-Aware Systems Engineering), a module 
 * of Modelio that aids the requirements elicitation stage of a Context-Aware System (C-AS). 
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
 * along with RCASE.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package edu.casetools.rcase.extensions.tables.implementations.contextmodel.model;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class DependencyTableDataHandler.
 */
public class ContextModelTableDataHandler implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7241959769908081831L;

    /**
     * Update headers.
     *
     * @param data
     *            the data
     * @return the dependency table data
     */
    public ContextModelTableData updateHeaders(ContextModelTableData data) {
	ContextModelTableData auxiliarData = data;

	return auxiliarData;

    }

    // /**
    // * Creates the content.
    // *
    // * @param data
    // * the data
    // */
    // public void createContent(ContextModelTableData data) {
    // for (int i = 0; i < data.dataList.size(); i++) {
    // // createRows(i, data);
    // }
    // }

    // private void createRows(Object rowHeaderElement, ContextModelTableData
    // data) {
    // ContextModelTableRow row = new
    // ContextModelTableRow((ModelElement)rowHeaderElement);
    // for (Object column : data.getHeaders()) {
    // // Object value =
    // // ModelioTableUtils.getInstance().getTableValues(column,
    // // rowHeaderElement, data);
    // // row.add(value);
    // }
    // data.dataList.add(row);
    // }

}
