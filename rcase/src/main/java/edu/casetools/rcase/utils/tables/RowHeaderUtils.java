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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Modelio. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package edu.casetools.rcase.utils.tables;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.UIResource;
import javax.swing.table.JTableHeader;

import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.extensions.tables.container.control.table.ContainerRowHeaderListener;
import edu.casetools.rcase.extensions.tables.container.model.ContainerRowHeadersModel;
import edu.casetools.rcase.extensions.tables.rowheaders.RowHeaderAdjustmentListener;
import edu.casetools.rcase.extensions.tables.rowheaders.RowHeaderRenderer;
import edu.casetools.rcase.extensions.tables.rowheaders.RowHeadersComponentListener;
import edu.casetools.rcase.extensions.tables.rowheaders.RowHeadersTableModel;
import edu.casetools.rcase.extensions.tables.traceability.model.DependencyTableRowHeadersModel;

// TODO: Auto-generated Javadoc
/**
 * TalbeRowUtilities. Utility for adding a row column to a JTable
 * 
 * @author Oliver Watkins modifiied by @author Unai Alegre
 */
public class RowHeaderUtils {

    /**
     * The Enum ROW_HEADER.
     */
    public enum ROW_HEADER {
	CONTAINER, DEPENDENCY
    };

    private static RowHeaderUtils instance = null;

    /**
     * Gets the single instance of RowHeaderUtils.
     *
     * @return single instance of RowHeaderUtils
     */
    public static RowHeaderUtils getInstance() {
	if (instance == null) {
	    instance = new RowHeaderUtils();
	}
	return instance;
    }

    /**
     * Adds the row header.
     *
     * @param userTable
     *            the user table
     * @param type
     *            the type
     * @param list
     *            the row list
     */
    public void addRowHeader(final JTable userTable, ROW_HEADER type, List<MObject> list) {

	Container parentContainer = userTable.getParent();
	JTable rowHeadersTable;

	if (parentContainer instanceof JViewport) {
	    Container parentParentContainer = parentContainer.getParent();

	    if (parentParentContainer instanceof JScrollPane) {
		JScrollPane scrollPane = (JScrollPane) parentParentContainer;
		JViewport viewport = scrollPane.getViewport();

		if (null == viewport || viewport.getView() != userTable) {
		    return;
		}
		if (type.equals(ROW_HEADER.CONTAINER)) {
		    rowHeadersTable = new JTable(new ContainerRowHeadersModel(userTable.getModel().getRowCount(), 1));
		    addHeaderColumn(scrollPane, userTable, rowHeadersTable);
		} else if (type.equals(ROW_HEADER.DEPENDENCY)) {
		    rowHeadersTable = new JTable(new DependencyTableRowHeadersModel(list));
		    addHeaderColumn(scrollPane, userTable, rowHeadersTable);
		}
	    }
	}
    }

    private void addHeaderColumn(JScrollPane scrollPane, JTable userTable, JTable rowHeadersTable) {
	JTable rowHeadersTableAuxiliar = rowHeadersTable;
	JTableHeader tableHeader;
	JLabel label = new JLabel();

	tableHeader = userTable.getTableHeader();
	scrollPane.setColumnHeaderView(tableHeader);

	// rowHeadersTable.getModel().addTableModelListener()
	userTable.getModel().addTableModelListener(new ContainerRowHeaderListener(userTable, rowHeadersTableAuxiliar));
	// label used for rendering and for
	scrollPane.getVerticalScrollBar()
		.addAdjustmentListener(new RowHeaderAdjustmentListener(rowHeadersTableAuxiliar, label));

	// this is where you set the width of the row headers
	rowHeadersTableAuxiliar.createDefaultColumnsFromModel();
	rowHeadersTableAuxiliar = headerLookAndFeel(userTable, rowHeadersTableAuxiliar);
	rowHeadersTableAuxiliar = selectableRenderer(rowHeadersTableAuxiliar);

	scrollPane.setRowHeaderView(rowHeadersTableAuxiliar);
	// set the row header name into the top left corner
	scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, rowHeadersTableAuxiliar.getTableHeader());

	Border border = scrollPane.getBorder();
	if (null == border || border instanceof UIResource) {
	    scrollPane.setBorder(UIManager.getBorder("Table.scrollPaneBorder"));
	}

	scrollPane.addComponentListener(new RowHeadersComponentListener(rowHeadersTableAuxiliar, label, scrollPane));

	rowHeadersTableAuxiliar.setSelectionMode(userTable.getSelectionModel().getSelectionMode());

	adjustRowHeaderWidth(rowHeadersTableAuxiliar, label);
    }

    private JTable selectableRenderer(JTable rowHeadersTable) {
	// adding a renderer
	rowHeadersTable.getColumnModel().getColumn(0).setCellRenderer(new RowHeaderRenderer(rowHeadersTable));

	rowHeadersTable.setRowSelectionAllowed(true);
	rowHeadersTable.setCellSelectionEnabled(true);
	rowHeadersTable.setFocusable(true);
	rowHeadersTable.setDragEnabled(true);
	return rowHeadersTable;
    }

    private JTable headerLookAndFeel(JTable userTable, JTable rowHeadersTable) {
	rowHeadersTable.setBackground(rowHeadersTable.getTableHeader().getBackground());
	rowHeadersTable.setForeground(rowHeadersTable.getTableHeader().getForeground());
	rowHeadersTable.setFont(rowHeadersTable.getTableHeader().getFont());

	rowHeadersTable.setRowHeight(userTable.getRowHeight());

	rowHeadersTable.getTableHeader().setReorderingAllowed(false);
	return rowHeadersTable;
    }

    /**
     * Adjust row header width.
     *
     * @param rowHeadersTable
     *            the row headers table
     * @param label
     *            the label
     */
    public void adjustRowHeaderWidth(final JTable rowHeadersTable, final JLabel label) {

	label.setFont(rowHeadersTable.getFont());
	label.setOpaque(true);
	label.setHorizontalAlignment(JLabel.CENTER);

	RowHeadersTableModel tm = (RowHeadersTableModel) rowHeadersTable.getModel();

	label.setText(tm.getMaxValue());
	FontMetrics fontMetrics = label.getFontMetrics(label.getFont());

	int widthFactor;

	if (null != fontMetrics && null != label.getText()) {
	    widthFactor = fontMetrics.stringWidth(label.getText());

	    rowHeadersTable.setPreferredScrollableViewportSize(new Dimension(widthFactor + 25, 100)); // height
	    // is
	    // ignored
	    rowHeadersTable.repaint();
	}
    }

}
