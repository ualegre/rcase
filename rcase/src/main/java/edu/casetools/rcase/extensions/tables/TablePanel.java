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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package edu.casetools.rcase.extensions.tables;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

// TODO: Auto-generated Javadoc
/**
 * The Class TablePanel.
 */
public abstract class TablePanel extends JPanel {

    private static final long serialVersionUID = 1569930910362340188L;

    /** The table. */
    protected JTable table;

    /** The scroller. */
    protected JScrollPane scroller;

    /**
     * Instantiates a new table panel.
     */
    public TablePanel() {

    }

    /**
     * Inits the scroll pane.
     */
    protected void initScrollPane() {
	scroller = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	table.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));
	table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

	setLayout(new BorderLayout());
	add(scroller, BorderLayout.CENTER);
    }

    /**
     * Gets the table.
     *
     * @return the table
     */
    public abstract JTable getTable();

}
