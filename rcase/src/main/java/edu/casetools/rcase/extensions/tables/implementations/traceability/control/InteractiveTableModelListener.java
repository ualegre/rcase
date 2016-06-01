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
package edu.casetools.rcase.extensions.tables.implementations.traceability.control;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import edu.casetools.rcase.extensions.tables.implementations.traceability.view.DependencyTablePanel;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving interactiveTableModel events.
 * The class that is interested in processing a interactiveTableModel
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addInteractiveTableModelListener<code> method. When
 * the interactiveTableModel event occurs, that object's appropriate
 * method is invoked.
 *
 * @see InteractiveTableModelEvent
 */
public class InteractiveTableModelListener implements TableModelListener {

	DependencyTablePanel panel;
	
	/**
	 * Instantiates a new interactive table model listener.
	 *
	 * @param panel the panel
	 */
	public InteractiveTableModelListener(DependencyTablePanel panel){
		this.panel = panel;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.event.TableModelListener#tableChanged(javax.swing.event.TableModelEvent)
	 */
	@Override
	public void tableChanged(TableModelEvent event) {
		if (event.getType() == TableModelEvent.UPDATE) {
			int column = event.getColumn();
			int row = event.getFirstRow();
			this.panel.getTable().setColumnSelectionInterval(column, column);
			this.panel.getTable().setRowSelectionInterval(row, row);
		}
	}
}

