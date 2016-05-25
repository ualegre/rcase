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
package edu.casetools.rcase.extensions.tables.implementations.traceability.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

// TODO: Auto-generated Javadoc
/**
 * The Class DependencyTableModel.
 */
public class DependencyTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 4329075668436833054L;
    private DependencyTableData data;
    private DependencyTableDataHandler dataHandler;

    /**
     * Instantiates a new dependency table model.
     */
    public DependencyTableModel() {
	this.data = new DependencyTableData();
	this.dataHandler = new DependencyTableDataHandler();
    }

    /**
     * Instantiates a new dependency table model.
     *
     * @param data
     *            the data
     */
    public DependencyTableModel(DependencyTableData data) {
	this.data = data;
	this.dataHandler = new DependencyTableDataHandler();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    @Override
    public int getColumnCount() {
	return this.data.getxHeaderList().size();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
     */
    @Override
    public boolean isCellEditable(int row, int column) {
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object,
     * int, int)
     */
    @Override
    public void setValueAt(Object value, int row, int column) {
	List<Object> valueAt = this.data.getDataList().get(row);
	valueAt.set(column, value);
	fireTableCellUpdated(row, column);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.TableModel#getRowCount()
     */
    @Override
    public int getRowCount() {
	return this.data.getyHeaderList().size();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.AbstractTableModel#getColumnName(int)
     */
    @Override
    public String getColumnName(int column) {
	return this.data.getxHeaderList().get(column).getName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
     */
    @Override
    public Class<?> getColumnClass(int column) {
	return String.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    @Override
    public Object getValueAt(int row, int column) {
	List<Object> valueAt = this.data.getDataList().get(row);
	return valueAt.get(column).toString();
    }

    /**
     * Gets the data.
     *
     * @return the data
     */
    public DependencyTableData getData() {
	return this.data;
    }

    /**
     * Refresh.
     */
    public void refresh() {
	int oldSize = this.data.newDataList();
	this.data = this.dataHandler.updateHeaders(this.data);
	this.dataHandler.createContent(this.data);
	this.refresh(oldSize);
    }

    private void refresh(int oldSize) {
	int oldSizeAuxiliar = oldSize;
	if (oldSizeAuxiliar > this.data.getDataList().size())
	    oldSizeAuxiliar = this.data.getDataList().size();
	refreshExistentValues(oldSizeAuxiliar);
	if (this.data.getDataList().size() > oldSizeAuxiliar)
	    createNewValues(oldSizeAuxiliar);
    }

    private void refreshExistentValues(int oldSize) {
	for (int row = 0; row < oldSize; row++) {
	    for (int column = 0; column < this.data.getDataList().get(row).size(); column++) {
		this.setValueAt(this.data.getDataList().get(row).get(column), row, column);
	    }
	}
    }

    private void createNewValues(int oldSize) {
	for (int row = oldSize; row < data.getDataList().size(); row++) {
	    fireTableRowsInserted(oldSize - 1, data.getDataList().size() - 1);
	}
    }

}
