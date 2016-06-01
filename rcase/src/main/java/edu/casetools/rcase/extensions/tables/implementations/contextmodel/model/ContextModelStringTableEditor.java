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

import java.awt.Component;
import java.util.EventObject;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import javax.swing.table.TableCellEditor;

import edu.casetools.rcase.extensions.tables.implementations.contextmodel.control.ContextModelTableStringEditorListener;

// TODO: Auto-generated Javadoc
/**
 * The Class DependencyTableEditor.
 */
public class ContextModelStringTableEditor extends JTextField implements TableCellEditor {

    private static final long serialVersionUID = 5217804945725716522L;

    private enum FIRE_EDITING {
	CANCEL, STOP
    };

    /** The listener list. */
    protected EventListenerList eventListenerList = new EventListenerList();

    /** The change event. */
    protected ChangeEvent changeEvent = new ChangeEvent(this);

    private int row;
    private int column;

    /**
     * Instantiates a new dependency table editor.
     *
     * @param comboBoxNames
     *            the combo box names
     * @param model
     *            the model
     */
    public ContextModelStringTableEditor(ContextModelTableModel model) {
	addActionListener(new ContextModelTableStringEditorListener(this, model));

    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.CellEditor#addCellEditorListener(javax.swing.event.
     * CellEditorListener)
     */
    @Override
    public void addCellEditorListener(CellEditorListener listener) {
	eventListenerList.add(CellEditorListener.class, listener);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.CellEditor#removeCellEditorListener(javax.swing.event.
     * CellEditorListener)
     */
    @Override
    public void removeCellEditorListener(CellEditorListener listener) {
	eventListenerList.remove(CellEditorListener.class, listener);
    }

    /**
     * Fire editing stopped.
     */
    protected void fireEditingStopped() {
	defaultFireEditing(FIRE_EDITING.STOP);
    }

    /**
     * Fire editing canceled.
     */
    protected void fireEditingCanceled() {
	defaultFireEditing(FIRE_EDITING.CANCEL);
    }

    /**
     * Default fire editing.
     *
     * @param eventType
     *            the event type
     */
    protected void defaultFireEditing(FIRE_EDITING eventType) {
	CellEditorListener listener;
	Object[] listeners = eventListenerList.getListenerList();
	for (int i = 0; i < listeners.length; i++) {
	    if (CellEditorListener.class == listeners[i]) {
		listener = (CellEditorListener) listeners[i + 1];
		if (eventType == FIRE_EDITING.CANCEL)
		    listener.editingCanceled(changeEvent);
		else if (eventType == FIRE_EDITING.STOP)
		    listener.editingStopped(changeEvent);
	    }
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.CellEditor#cancelCellEditing()
     */
    @Override
    public void cancelCellEditing() {
	fireEditingCanceled();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.CellEditor#stopCellEditing()
     */
    @Override
    public boolean stopCellEditing() {
	fireEditingStopped();
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.CellEditor#isCellEditable(java.util.EventObject)
     */
    @Override
    public boolean isCellEditable(EventObject event) {
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.CellEditor#shouldSelectCell(java.util.EventObject)
     */
    @Override
    public boolean shouldSelectCell(EventObject event) {
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.CellEditor#getCellEditorValue()
     */
    @Override
    public Object getCellEditorValue() {
	return super.getText();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.swing.table.TableCellEditor#getTableCellEditorComponent(javax.swing
     * .JTable, java.lang.Object, boolean, int, int)
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	this.row = row;
	this.column = column;
	this.setText((String) value);
	return this;
    }

    /**
     * Gets the column.
     *
     * @return the column
     */
    public int getColumn() {
	return this.column;
    }

    /**
     * Gets the row.
     *
     * @return the row
     */
    public int getRow() {
	return this.row;
    }

}