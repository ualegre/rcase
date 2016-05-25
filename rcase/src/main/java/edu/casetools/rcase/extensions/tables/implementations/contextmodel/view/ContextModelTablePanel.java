/*
 * Copyright 2015 @author Unai Alegre 
 * 
 * This file is part of RCASE (Requirements for Context-Aware Systems Engineering), a module 
 * of Modelio that aids the requirements elicitation stage of a Context-Aware System (C-AS). 
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
 * along with RCASE.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package edu.casetools.rcase.extensions.tables.implementations.contextmodel.view;

import java.awt.FontMetrics;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import edu.casetools.rcase.extensions.tables.TablePanel;
import edu.casetools.rcase.extensions.tables.headers.ColumnHeaderRenderer;
import edu.casetools.rcase.extensions.tables.implementations.contextmodel.control.StringTableModelListener;
import edu.casetools.rcase.extensions.tables.implementations.contextmodel.model.ContextModelComboBoxTableEditor;
import edu.casetools.rcase.extensions.tables.implementations.contextmodel.model.ContextModelStringTableEditor;
import edu.casetools.rcase.extensions.tables.implementations.contextmodel.model.ContextModelTableData;
import edu.casetools.rcase.extensions.tables.implementations.contextmodel.model.ContextModelTableModel;
import edu.casetools.rcase.utils.tables.ModelioTableUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ContextModelTablePanel.
 */
public class ContextModelTablePanel extends TablePanel {

    private static final long serialVersionUID = -7681719642990890546L;

    /** The table model. */
    protected ContextModelTableModel tableModel;

    /**
     * Instantiates a new context model table panel.
     */
    public ContextModelTablePanel() {
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
	this.tableModel = new ContextModelTableModel();
	this.tableModel.addTableModelListener(new StringTableModelListener(this));
	table.setSurrendersFocusOnKeystroke(true);

    }

    private void refresh() {
	this.tableModel.refresh();
	table.setModel(this.tableModel);
	setComboBoxStyle();
	table.repaint();
	// RowHeaderUtils.getInstance().addRowHeader(table,
	// ROW_HEADER.DEPENDENCY,
	// this.tableModel.getData().getyHeaderList());
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
	    getContextModelTableEditor(tableColumn, i);
	}
    }

    private void getContextModelTableEditor(TableColumn tableColumn, int column) {
	List<String> possibleValues;
	switch (column) {
	case 0:
	    // Name
	    // element.setName(value);
	    setStringEditor(tableColumn);
	    break;
	case 1:
	    // ID
	    // PropertiesUtils.getInstance().findAndAddValue(RCasePeerModule.MODULE_NAME,
	    // RCaseProperties.PROPERTY_CONTEXT_ID, value, element);
	    setStringEditor(tableColumn);
	    break;
	case 2:
	    // Cost
	    // PropertiesUtils.getInstance().findAndAddValue(RCasePeerModule.MODULE_NAME,
	    // RCaseProperties.PROPERTY_CONTEXT_COST, value, element);
	    setStringEditor(tableColumn);
	    break;
	case 3:
	    // Status
	    possibleValues = ModelioTableUtils.getInstance().getStatusPossibleValues();
	    setComboBoxEditor(tableColumn, possibleValues);
	    break;
	case 4:
	    // Creation Process
	    possibleValues = ModelioTableUtils.getInstance().getCreationProcessPossibleValues();
	    setComboBoxEditor(tableColumn, possibleValues);
	    break;
	case 5:
	    // User intervention
	    possibleValues = ModelioTableUtils.getInstance().getUserInterventionPossibleValues();
	    setComboBoxEditor(tableColumn, possibleValues);
	    break;
	case 6:
	    // Volatility
	    possibleValues = ModelioTableUtils.getInstance().getVolatilityPossibleValues();
	    setComboBoxEditor(tableColumn, possibleValues);
	    break;
	}
    }

    private void setStringEditor(TableColumn tableColumn) {
	ContextModelStringTableEditor comboBoxEditor;
	comboBoxEditor = new ContextModelStringTableEditor(this.tableModel);
	tableColumn.setCellEditor(comboBoxEditor);
    }

    private void setComboBoxEditor(TableColumn tableColumn, List<String> possibleValues) {
	ContextModelComboBoxTableEditor comboBoxEditor;
	comboBoxEditor = new ContextModelComboBoxTableEditor(possibleValues, this.tableModel);
	tableColumn.setCellEditor(comboBoxEditor);
    }

    /**
     * Refresh.
     *
     * @param data
     *            the data
     */
    public void refresh(ContextModelTableData data) {
	this.tableModel = new ContextModelTableModel(data);
	refresh();
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.casesuite.extensions.tables.TablePanel#getTable()
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
    public ContextModelTableModel getTableModel() {
	return this.tableModel;

    }

}
