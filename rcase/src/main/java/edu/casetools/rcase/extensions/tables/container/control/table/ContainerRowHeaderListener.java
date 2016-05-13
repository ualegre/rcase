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
package edu.casetools.rcase.extensions.tables.container.control.table;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import edu.casetools.rcase.extensions.tables.rowheaders.RowHeadersTableModel;

public class ContainerRowHeaderListener implements TableModelListener {

    JTable userTable;
    JTable rowHeadersTable;

    public ContainerRowHeaderListener(JTable userTable, JTable rowHeadersTable) {
	this.userTable = userTable;
	this.rowHeadersTable = rowHeadersTable;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
	RowHeadersTableModel rowHeadersModel = (RowHeadersTableModel) rowHeadersTable.getModel();

	if (userTable.getRowCount() != rowHeadersModel.getRowCount()) {
	    if (userTable.getRowCount() > rowHeadersModel.getRowCount()) {

		int rowDiff = userTable.getRowCount() - rowHeadersModel.getRowCount();

		for (int i = 0; i < rowDiff; i++) {
		    rowHeadersModel.addRow();
		}
	    } else if (userTable.getRowCount() < rowHeadersModel.getRowCount()) {

		int rowDiff = rowHeadersModel.getRowCount() - userTable.getRowCount();

		for (int i = 0; i < rowDiff; i++) {
		    rowHeadersModel.removeRow();
		}
	    }
	    rowHeadersModel.fireTableDataChanged();
	}
    }

}
