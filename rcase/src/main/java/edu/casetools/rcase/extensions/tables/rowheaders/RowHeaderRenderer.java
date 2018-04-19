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
package edu.casetools.rcase.extensions.tables.rowheaders;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import edu.casetools.rcase.module.impl.RCaseModule;
import edu.casetools.rcase.utils.tables.ModelioTableUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class RowHeaderRenderer.
 */
public class RowHeaderRenderer implements TableCellRenderer {
    JLabel label;
    JTable rowHeadersTable;

    /**
     * Instantiates a new row header renderer.
     *
     * @param rowHeadersTable the row headers table
     */
    public RowHeaderRenderer(JTable rowHeadersTable) {
        label = new JLabel();
        this.rowHeadersTable = rowHeadersTable;
    }

    /* (non-Javadoc)
     * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {
        label = ModelioTableUtils.getInstance().createJLabel(RCaseModule.getInstance(), value.toString());

        if (isSelected) {
            label.setForeground(rowHeadersTable.getSelectionForeground());
            label.setBackground(rowHeadersTable.getSelectionBackground());
        } else {
            label.setBackground(rowHeadersTable.getBackground());
            label.setForeground(rowHeadersTable.getForeground());
        }
        return label;
    }
}
