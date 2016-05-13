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
package edu.casetools.rcase.extensions.tables.traceability.view.edit;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import org.modelio.metamodel.uml.infrastructure.Stereotype;

import edu.casetools.rcase.extensions.tables.traceability.DependencyTable;
import edu.casetools.rcase.extensions.tables.traceability.control.dialogs.ButtonPanelListener;
import edu.casetools.rcase.extensions.tables.traceability.model.DependencyTableData;
import edu.casetools.rcase.extensions.tables.traceability.view.DualListBox;
import edu.casetools.rcase.module.api.RCaseView;
import edu.casetools.rcase.module.i18n.I18nMessageService;

// TODO: Auto-generated Javadoc
/**
 * The Class EditDialog.
 */
public abstract class EditDialog extends JDialog {

    private static final long serialVersionUID = -1304193763131918374L;
    DualListBox dual;
    JPanel buttonPanel;
    JButton ok;
    JButton cancel;
    DependencyTableData data;

    /**
     * Instantiates a new edits the dialog.
     *
     * @param main
     *            the main
     * @param name
     *            the name
     */
    public EditDialog(DependencyTable main, String name) {
	this.dual = new DualListBox(name);
	this.data = main.getTablePanel().getTableModel().getData();
	this.createButtonPanel(main);
	this.createSourceElements();
	this.createDestinationElements();
	this.initDialog();
    }

    /**
     * Gets the filter stereotypes.
     *
     * @return the filter stereotypes
     */
    protected abstract List<Stereotype> getFilterStereotypes();

    /**
     * Gets the stereotypes.
     *
     * @return the stereotypes
     */
    protected abstract List<Stereotype> getStereotypes();

    /**
     * Sets the ok action command.
     */
    protected abstract void setOkActionCommand();

    private void createDestinationElements() {
	List<Stereotype> stereotypes = getFilterStereotypes();
	if (null != stereotypes) {
	    for (Stereotype stereotype : stereotypes)
		this.dual.addDestinationElements(new String[] { stereotype.getName() });
	}

    }

    private void createSourceElements() {
	List<Stereotype> stereotypes = getStereotypes();
	if (null != stereotypes) {
	    for (Stereotype stereotype : stereotypes)
		this.dual.addSourceElements(new String[] { stereotype.getName() });
	}
    }

    private void createButtonPanel(DependencyTable main) {
	buttonPanel = new JPanel();
	crateAcceptButtton(main);
	createCancelButton(main);
	buttonPanel.add(ok);
	buttonPanel.add(cancel);
    }

    private void crateAcceptButtton(DependencyTable main) {
	ok = new JButton(I18nMessageService.getString("Buttons.Accept"));
	ok.setFocusable(false);
	setOkActionCommand();
	ok.addActionListener(new ButtonPanelListener(main, this));
    }

    private void createCancelButton(DependencyTable main) {
	cancel = new JButton(I18nMessageService.getString("Buttons.Cancel"));
	cancel.setFocusable(false);
	cancel.setActionCommand(RCaseView.CANCEL_BUTTON);
	cancel.addActionListener(new ButtonPanelListener(main, this));
    }

    private void initDialog() {
	this.add(this.dual, BorderLayout.CENTER);
	this.add(buttonPanel, BorderLayout.SOUTH);
	this.setSize(400, 300);
	this.setVisible(true);
    }

    /**
     * Refresh.
     *
     * @param data
     *            the data
     */
    public void refresh() {
	this.dual.clearSourceListModel();
	this.dual.clearDestinationListModel();
	createSourceElements();
	createDestinationElements();
	this.setVisible(true);
	this.toFront();
    }

    /**
     * Gets the dual list box.
     *
     * @return the dual list box
     */
    public DualListBox getDualListBox() {
	return this.dual;
    }

}
