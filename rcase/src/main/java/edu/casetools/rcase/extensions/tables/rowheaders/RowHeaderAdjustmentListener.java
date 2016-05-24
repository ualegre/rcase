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
package edu.casetools.rcase.extensions.tables.rowheaders;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JLabel;
import javax.swing.JTable;

import edu.casetools.rcase.utils.tables.RowHeaderUtils;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving rowHeaderAdjustment events. The class
 * that is interested in processing a rowHeaderAdjustment event implements this
 * interface, and the object created with that class is registered with a
 * component using the component's <code>addRowHeaderAdjustmentListener<code>
 * method. When the rowHeaderAdjustment event occurs, that object's appropriate
 * method is invoked.
 *
 * @see RowHeaderAdjustmentEvent
 */
public class RowHeaderAdjustmentListener implements AdjustmentListener {

    JTable rowHeadersTable;
    JLabel label;

    /**
     * Instantiates a new row header adjustment listener.
     *
     * @param rowHeadersTable
     *            the row headers table
     * @param label
     *            the label
     */
    public RowHeaderAdjustmentListener(JTable rowHeadersTable, JLabel label) {
	this.rowHeadersTable = rowHeadersTable;
	this.label = label;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.AdjustmentListener#adjustmentValueChanged(java.awt.event.
     * AdjustmentEvent)
     */
    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
	RowHeaderUtils.getInstance().adjustRowHeaderWidth(rowHeadersTable, label);
    }

}
