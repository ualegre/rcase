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
package edu.casetools.rcase.extensions.tables.headers;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import edu.casetools.rcase.utils.tables.RowHeaderUtils;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving rowHeadersComponent events. The class
 * that is interested in processing a rowHeadersComponent event implements this
 * interface, and the object created with that class is registered with a
 * component using the component's <code>addRowHeadersComponentListener<code>
 * method. When the rowHeadersComponent event occurs, that object's appropriate
 * method is invoked.
 *
 * @see RowHeadersComponentEvent
 */
public class RowHeadersComponentListener extends ComponentAdapter {
    JTable rowHeadersTable;
    JLabel label;
    JScrollPane scrollPane;

    /**
     * Instantiates a new row headers component listener.
     *
     * @param rowHeadersTable
     *            the row headers table
     * @param label
     *            the label
     * @param scrollPane
     *            the scroll pane
     */
    public RowHeadersComponentListener(JTable rowHeadersTable, JLabel label, JScrollPane scrollPane) {
	this.rowHeadersTable = rowHeadersTable;
	this.label = label;
	this.scrollPane = scrollPane;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ComponentAdapter#componentResized(java.awt.event.
     * ComponentEvent)
     */
    @Override
    public void componentResized(ComponentEvent e) {
	RowHeaderUtils.getInstance().adjustRowHeaderWidth(rowHeadersTable, label);
    }

}
