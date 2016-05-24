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
package edu.casetools.rcase.extensions.tables.rowheaders;

import java.awt.Container;
import java.awt.Point;

import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving rowHeaderTable events. The class that is
 * interested in processing a rowHeaderTable event implements this interface,
 * and the object created with that class is registered with a component using
 * the component's <code>addRowHeaderTableListener<code> method. When the
 * rowHeaderTable event occurs, that object's appropriate method is invoked.
 *
 * @see RowHeaderTableEvent
 */
public class RowHeaderTableListener implements ListSelectionListener {

    private JTable rowHeadersTable;
    private JTable userTable;
    private JViewport userTableViewPort;
    private JViewport rowHeadersViewPort;

    /**
     * Instantiates a new row header table listener.
     *
     * @param rowHeadersTable
     *            the row headers table
     * @param userTable
     *            the user table
     */
    public RowHeaderTableListener(JTable rowHeadersTable, JTable userTable) {
	this.userTable = userTable;
	this.rowHeadersTable = rowHeadersTable;

	Container parent = userTable.getParent();
	userTableViewPort = (JViewport) parent;

	Container p2 = rowHeadersTable.getParent();
	rowHeadersViewPort = (JViewport) p2;

	Point newPosition = userTableViewPort.getViewPosition();
	rowHeadersViewPort.setViewPosition(newPosition);

	rowHeadersTable.getSelectionModel().addListSelectionListener(this);
	userTable.getSelectionModel().addListSelectionListener(this);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.
     * ListSelectionEvent)
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {

	if (e.getSource() == userTable.getSelectionModel()) {
	    userTableValueChanged();
	} else if (e.getSource() == rowHeadersTable.getSelectionModel()) {

	    rowHeadersTableValueChanged();
	}
    }

    private void rowHeadersTableValueChanged() {
	boolean isColumnSelectionAllowed = userTable.getColumnSelectionAllowed();
	boolean isRowSelectionAllowed = userTable.getRowSelectionAllowed();
	boolean isCellSelectionAllowed = userTable.getCellSelectionEnabled();
	int[] rows = rowHeadersTable.getSelectedRows();

	userTable.getSelectionModel().removeListSelectionListener(this);
	userTable.getSelectionModel().clearSelection();

	if (isRowSelectionAllowed && !isCellSelectionAllowed && !isColumnSelectionAllowed) {
	    allowSelection(rows);
	} else {
	    // looks cleaner
	    userTableViewPort.setScrollMode(JViewport.BACKINGSTORE_SCROLL_MODE);
	    changeSelection(rows);
	}
	// re-adding the listener to the user table
	userTable.getSelectionModel().addListSelectionListener(this);
    }

    private void allowSelection(int[] rows) {
	for (int i = 0; i < rows.length; i++) {
	    userTable.addRowSelectionInterval(rows[i], rows[i]);
	    userTableViewPort.setViewPosition(rowHeadersViewPort.getViewPosition());
	}
    }

    private void changeSelection(int[] rows) {
	for (int i = 0; i < rows.length; i++) {
	    if (0 == i) {
		// need to create row first with change selection
		userTable.changeSelection(rows[i], 0, false, false);
		userTable.changeSelection(rows[i], userTable.getColumnCount(), false, true);

	    } else {
		userTable.addRowSelectionInterval(rows[i], rows[i]);
	    }
	}
    }

    private void userTableValueChanged() {
	rowHeadersTable.getSelectionModel().removeListSelectionListener(this);
	rowHeadersTable.getSelectionModel().clearSelection();

	int[] rows = userTable.getSelectedRows();

	for (int i = 0; i < rows.length; i++) {
	    rowHeadersTable.getSelectionModel().addSelectionInterval(rows[i], rows[i]);

	}

	rowHeadersTable.getSelectionModel().addListSelectionListener(this);
    }
}
