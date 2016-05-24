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
package edu.casetools.rcase.extensions.tables.traceability.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.extensions.tables.traceability.model.DependencyTableEditor;
import edu.casetools.rcase.extensions.tables.traceability.model.DependencyTableModel;
import edu.casetools.rcase.utils.tables.ModelioTableUtils;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving tableEditor events.
 * The class that is interested in processing a tableEditor
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addTableEditorListener<code> method. When
 * the tableEditor event occurs, that object's appropriate
 * method is invoked.
 *
 * @see TableEditorEvent
 */
public class TableEditorListener implements ActionListener {
    DependencyTableEditor editor;
    DependencyTableModel model;

    /**
     * Instantiates a new table editor listener.
     *
     * @param tableEditor the table editor
     * @param model the model
     */
    public TableEditorListener(DependencyTableEditor tableEditor,
        DependencyTableModel model) {
        this.editor = tableEditor;
        this.model = model;
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        JComboBox<?> combobox = (JComboBox<?>) event.getSource();

        MObject origin =
            this.model.getData().getxHeaderList().get(this.editor.getColumn());
        MObject target =
            this.model.getData().getyHeaderList().get(this.editor.getRow());

        ModelioTableUtils.getInstance().setVal(origin, target,
            this.model.getData().getLinkStereotype(),
            combobox.getSelectedItem().toString());

        this.model.refresh();
    }

}
