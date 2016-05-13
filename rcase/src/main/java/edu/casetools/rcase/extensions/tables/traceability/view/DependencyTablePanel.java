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
package edu.casetools.rcase.extensions.tables.traceability.view;

import java.awt.FontMetrics;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import edu.casetools.rcase.extensions.tables.TablePanel;
import edu.casetools.rcase.extensions.tables.traceability.model.DependencyTableData;
import edu.casetools.rcase.extensions.tables.traceability.model.DependencyTableEditor;
import edu.casetools.rcase.extensions.tables.traceability.model.DependencyTableModel;
import edu.casetools.rcase.utils.tables.ModelioTableUtils;
import edu.casetools.rcase.utils.tables.RowHeaderUtils;
import edu.casetools.rcase.utils.tables.RowHeaderUtils.ROW_HEADER;

// TODO: Auto-generated Javadoc
/**
 * The Class DependencyTablePanel.
 */
public class DependencyTablePanel extends TablePanel {

    private static final long serialVersionUID = -7681719642990890546L;

    /** The table model. */
    protected DependencyTableModel tableModel;

    /**
     * Instantiates a new dependency table panel.
     */
    public DependencyTablePanel() {
	initComponent();
    }

    /**
     * Inits the component.
     */
    public void initComponent() {
	initTable();
	initScrollPane();
    }

    private void initTable() {
	table = new JTable();
	this.tableModel = new DependencyTableModel();
	table.setSurrendersFocusOnKeystroke(true);

    }

    private void refresh() {
	this.tableModel.refresh();
	table.setModel(this.tableModel);
	setComboBoxStyle();
	table.repaint();
	RowHeaderUtils.getInstance().addRowHeader(table, ROW_HEADER.DEPENDENCY,
		this.tableModel.getData().getyHeaderList());

	refreshRowHeaderRenderer();

    }

    private void refreshRowHeaderRenderer() {
	int widthFactor;
	for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
	    TableColumn col = table.getColumnModel().getColumn(i);
	    col.setHeaderRenderer(new ColumnHeaderRenderer());
	    JLabel label = new JLabel((String) col.getHeaderValue());
	    FontMetrics fontMetrics = label.getFontMetrics(label.getFont());
	    widthFactor = fontMetrics.stringWidth(label.getText());
	    col.setPreferredWidth(widthFactor + 25);
	}
	// this.scroller.set
    }

    private void setComboBoxStyle() {
	for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
	    TableColumn tableColumn = table.getColumnModel().getColumn(i);
	    tableColumn.setCellEditor(
		    new DependencyTableEditor(ModelioTableUtils.getInstance().getPossibleValues(), this.tableModel));
	}
    }

    /**
     * Refresh.
     *
     * @param data
     *            the data
     */
    public void refresh(DependencyTableData data) {
	this.tableModel = new DependencyTableModel(data);
	refresh();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.middlesex.goodies.casesuite.extensions.tables.TablePanel#getTable()
     */
    @Override
    public JTable getTable() {
	return table;
    }

    /**
     * Gets the table model.
     *
     * @return the table model
     */
    public DependencyTableModel getTableModel() {
	return this.tableModel;

    }

}
