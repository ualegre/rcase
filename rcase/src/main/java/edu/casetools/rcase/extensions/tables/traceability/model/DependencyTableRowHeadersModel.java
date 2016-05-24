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
package edu.casetools.rcase.extensions.tables.traceability.model;

import java.util.ArrayList;
import java.util.List;

import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.extensions.tables.rowheaders.RowHeadersTableModel;
import edu.casetools.rcase.utils.tables.TableUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class DependencyTableRowHeadersModel.
 */
public class DependencyTableRowHeadersModel extends RowHeadersTableModel {

    private static final long serialVersionUID = -1800170969691593446L;
    private ArrayList<MObject> rowHeader;

    /**
     * Instantiates a new dependency table row headers model.
     *
     * @param list
     *            the row header
     */
    public DependencyTableRowHeadersModel(List<MObject> list) {
	this.rowHeader = (ArrayList<MObject>) list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
     */
    @Override
    public Class<String> getColumnClass(int columnIndex) {
	return String.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	return rowHeader.get(rowIndex).getName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object,
     * int, int)
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	rowHeader.add(rowIndex, (MObject) aValue);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.TableModel#getRowCount()
     */
    @Override
    public int getRowCount() {
	if (null != rowHeader)
	    return rowHeader.size();
	else
	    return 0;
    }

    /**
     * Update.
     *
     * @param rowHeader
     *            the row header
     */
    public void update(List<MObject> rowHeader) {
	this.rowHeader = (ArrayList<MObject>) rowHeader;
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.casesuite.extensions.tables.rowheaders.
     * RowHeadersTableModel#getMaxValue()
     */
    @Override
    public String getMaxValue() {
	return TableUtils.getInstance().getDependencyTableLongerString(rowHeader);
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.casesuite.extensions.tables.rowheaders.
     * RowHeadersTableModel#removeRow()
     */
    @Override
    public void removeRow() {
	rowHeader.remove(rowHeader.size() - 1);
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.casesuite.extensions.tables.rowheaders.
     * RowHeadersTableModel#addRow()
     */
    @Override
    public void addRow() {
	// TODO Auto-generated method stub

    }

}
