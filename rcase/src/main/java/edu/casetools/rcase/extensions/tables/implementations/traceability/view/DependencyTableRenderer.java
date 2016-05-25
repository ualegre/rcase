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
package edu.casetools.rcase.extensions.tables.implementations.traceability.view;

import java.awt.Color;
import java.awt.Component;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

/**
 * The Class DependencyTableRenderer.
 */
public class DependencyTableRenderer implements TableCellRenderer {
    JComboBox<String> combo;

    /**
     * Instantiates a new dependency table renderer.
     *
     * @param comboBoxContent
     *            the combo box content
     */
    public DependencyTableRenderer(List<String> comboBoxContent) {
	String[] auxiliarComboBoxContent = comboBoxContent.toArray(new String[comboBoxContent.size()]);
	combo = new JComboBox<>(auxiliarComboBoxContent);
	combo.setForeground(new Color(77, 75, 71));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.
     * swing.JTable, java.lang.Object, boolean, boolean, int, int)
     */
    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected, boolean hasFocus,
	    int row, int column) {
	combo.setSelectedItem(value);
	return new JTextField(combo.getSelectedItem().toString());

    }

}
