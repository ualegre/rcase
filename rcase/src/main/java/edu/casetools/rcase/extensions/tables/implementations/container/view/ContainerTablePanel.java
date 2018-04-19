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
package edu.casetools.rcase.extensions.tables.implementations.container.view;

import java.awt.FontMetrics;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.extensions.tables.TablePanel;
import edu.casetools.rcase.extensions.tables.implementations.container.ContainerTable;
import edu.casetools.rcase.extensions.tables.implementations.container.control.table.ContainerTableModelListener;
import edu.casetools.rcase.extensions.tables.implementations.container.model.ContainerTableModel;
import edu.casetools.rcase.module.impl.RCaseModule;
import edu.casetools.rcase.utils.tables.RowHeaderUtils;
import edu.casetools.rcase.utils.tables.RowHeaderUtils.ROW_HEADER;
import edu.casetools.rcase.utils.tables.TableUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ContainerTablePanel.
 */
public class ContainerTablePanel extends TablePanel {

    private static final long serialVersionUID = 4124415156518855899L;
    private ContainerTableModel tableModel;

    /**
     * Instantiates a new container table panel.
     *
     * @param main
     *            the main
     */
    public ContainerTablePanel(ContainerTable main) {
	initTableModel(main);
	initTable();

    }

    /**
     * Inits the table model.
     *
     * @param main
     *            the main
     */
    public void initTableModel(ContainerTable main) {
	List<MObject> list = TableUtils.getInstance().getRequirementsContainers(RCaseModule.getInstance());

	if ((null != list) && (!list.isEmpty()))
	    this.tableModel = new ContainerTableModel(list.get(0));
	else
	    this.tableModel = new ContainerTableModel();

	this.tableModel.addTableModelListener(new ContainerTableModelListener(main));
    }

    /**
     * Inits the table.
     */
    public void initTable() {

	table = new JTable();
	table.setModel(this.tableModel);
	table.setSurrendersFocusOnKeystroke(true);

	initScrollPane();
	RowHeaderUtils.getInstance().addRowHeader(table, ROW_HEADER.CONTAINER, null);

    }

    /**
     * Highlight last row.
     *
     * @param row
     *            the row
     */
    public void highlightLastRow(int row) {
	int lastrow = this.tableModel.getRowCount();
	if (row == lastrow - 1) {
	    table.setRowSelectionInterval(lastrow - 1, lastrow - 1);
	} else {
	    table.setRowSelectionInterval(row + 1, row + 1);
	}

	table.setColumnSelectionInterval(0, 0);
    }

    /**
     * Removes the row.
     */
    public void removeRow() {
	this.tableModel.removeSelectedRow(table.getSelectedRow());
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
     * Refresh.
     *
     * @param container
     *            the container
     */
    public void refresh(MObject container) {
	this.tableModel.refresh(container);
	refreshRowHeaderRenderer();
    }

    private void refreshRowHeaderRenderer() {
	int widthFactor;
	JLabel label;
	List<Object> columnData;
	for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
	    TableColumn col = table.getColumnModel().getColumn(i);
	    columnData = tableModel.getData().getColumnContent(i);
	    columnData.add(tableModel.getData().getxHeaderList().get(i).getName());
	    String longerValue = TableUtils.getInstance().getContainerTableLongerString(columnData);
	    label = new JLabel(longerValue);
	    FontMetrics fontMetrics = label.getFontMetrics(label.getFont());
	    widthFactor = fontMetrics.stringWidth(label.getText());
	    col.setPreferredWidth(widthFactor + 7);
	}
	// this.scroller.set
    }

    /**
     * Gets the table model.
     *
     * @return the table model
     */
    public ContainerTableModel getTableModel() {
	return this.tableModel;
    }

}
