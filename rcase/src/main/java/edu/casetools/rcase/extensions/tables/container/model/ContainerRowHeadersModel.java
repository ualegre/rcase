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
package edu.casetools.rcase.extensions.tables.container.model;

import java.util.ArrayList;

import edu.casetools.rcase.extensions.tables.rowheaders.RowHeadersTableModel;

/**
 * Table Model for the row number column. It just has one column (the numbers)
 * 
 * @author Oliver Watkins
 */
public class ContainerRowHeadersModel extends RowHeadersTableModel {

    private static final long serialVersionUID = 3181986871606119741L;
    private ArrayList<Integer> numbersList = new ArrayList<>();
    private int startNumber;

    /**
     * Initalise model
     * 
     * @param maxNumber
     *            determined by JTable row size
     * @param startingNumber
     *            usually zero or 1
     */
    public ContainerRowHeadersModel(int maxNumber, int startingNumber) {
	// start at starting number and then go to row count (plus starting
	// number amount)
	this.startNumber = startingNumber;
	int j = 0;
	for (int i = startingNumber; i < maxNumber + this.startNumber; i++) {
	    numbersList.add(new Integer(j + this.startNumber));
	    j++;
	}
    }

    @Override
    public int getRowCount() {
	if (null != numbersList)
	    return numbersList.size();
	else
	    return 0;
    }

    @Override
    public Class<Integer> getColumnClass(int columnIndex) {
	return Integer.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	return numbersList.get(rowIndex);
    }

    @Override
    public String getMaxValue() {
	if (null != numbersList && !numbersList.isEmpty()) {
	    Integer integer = (Integer) getValueAt(numbersList.size() - 1, 0);
	    return Integer.toString(integer);
	} else
	    return "";
    }

    @Override
    public void addRow() {
	if (!numbersList.isEmpty()) {
	    Integer maxNum = numbersList.get(numbersList.size() - 1);

	    numbersList.add(numbersList.size(), new Integer(maxNum.intValue() + 1));
	} else {
	    numbersList.add(numbersList.size(), new Integer(this.startNumber));
	}
	this.fireTableDataChanged();
    }

    @Override
    public void removeRow() {
	numbersList.remove(numbersList.size() - 1);
    }

}
