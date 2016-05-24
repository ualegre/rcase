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
package edu.casetools.rcase.extensions.tables.traceability.control.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import edu.casetools.rcase.extensions.tables.traceability.view.DualListBox;
import edu.casetools.rcase.module.api.RCaseView;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving dualList events.
 * The class that is interested in processing a dualList
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addDualListListener<code> method. When
 * the dualList event occurs, that object's appropriate
 * method is invoked.
 *
 * @see DualListEvent
 */
public class DualListListener implements ActionListener {

    DualListBox main;

    /**
     * Instantiates a new dual list listener.
     *
     * @param main the main
     */
    public DualListListener(DualListBox main) {
        this.main = main;
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        String command = arg0.getActionCommand();
        if (command.equals(RCaseView.ADD_FILTER)) {
            destination();
        } else if (command.equals(RCaseView.REMOVE_FILTER)) {
            source();
        }

    }

    /**
     * Destination.
     */
    public void destination() {
        List<String> selected = this.main.getSourceList().getSelectedValuesList();
        this.main.addDestinationElements(selected);
        this.main.clearSourceSelected();
    }

    /**
     * Source.
     */
    public void source() {
        List<String> selected =
            this.main.getDestionationList().getSelectedValuesList();
        this.main.addSourceElements(selected);
        this.main.clearDestinationSelected();
    }

}
