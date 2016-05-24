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
package edu.casetools.rcase.extensions.tables.traceability.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import edu.casetools.rcase.extensions.tables.traceability.control.dialogs.DualListListener;
import edu.casetools.rcase.extensions.tables.traceability.model.SortedListModel;
import edu.casetools.rcase.module.api.RCaseView;
import edu.casetools.rcase.module.i18n.I18nMessageService;

// TODO: Auto-generated Javadoc
/**
 * The Class DualListBox.
 */
public class DualListBox extends JPanel {

    private static final long serialVersionUID = 7606384461565677054L;
    private JList<String> sourceList;
    private SortedListModel sourceListModel;
    private JList<String> destList;
    private SortedListModel destListModel;
    private JButton addButton;
    private JButton removeButton;

    /**
     * Instantiates a new dual list box.
     *
     * @param name
     *            the name
     */
    public DualListBox(String name) {
	initScreen(name);
    }

    private void initScreen(String name) {
	setLayout(new GridLayout(0, 2));
	createLists();
	createButtons();
	createPanels(name);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void createLists() {
	this.sourceListModel = new SortedListModel();
	sourceList = new JList(this.sourceListModel);
	this.destListModel = new SortedListModel();
	destList = new JList(this.destListModel);
    }

    private void createPanels(String name) {
	add(createPanel(name + I18nMessageService.getString("DualList.label.Stereotypes"), sourceList, addButton));
	add(createPanel(I18nMessageService.getString("DualList.label.FilteredStereotypes"), destList, removeButton));
    }

    private void createButtons() {
	addButton = createButton(RCaseView.ADD_FILTER);
	removeButton = createButton(RCaseView.REMOVE_FILTER);
    }

    private JPanel createPanel(String name, Component list, JButton button) {
	JPanel panel = new JPanel(new BorderLayout());
	panel.add(new JLabel(name), BorderLayout.NORTH);
	panel.add(new JScrollPane(list), BorderLayout.CENTER);
	panel.add(button, BorderLayout.SOUTH);
	return panel;
    }

    private JButton createButton(String id) {
	JButton button = new JButton(id);
	button.setActionCommand(id);
	button.setFocusable(false);
	button.addActionListener(new DualListListener(this));
	return button;
    }

    /**
     * Clear source selected.
     */
    public void clearSourceSelected() {
	List<String> selected = sourceList.getSelectedValuesList();
	for (int i = selected.size() - 1; i >= 0; --i) {
	    this.sourceListModel.removeElement(selected.get(i));
	}
	sourceList.getSelectionModel().clearSelection();
    }

    /**
     * Clear destination selected.
     */
    public void clearDestinationSelected() {
	List<String> selected = destList.getSelectedValuesList();
	for (int i = selected.size() - 1; i >= 0; --i) {
	    this.destListModel.removeElement(selected.get(i));
	}
	destList.getSelectionModel().clearSelection();
    }

    private void fillListModel(SortedListModel model, List<String> newValue) {
	int size = newValue.size();
	for (int i = 0; i < size; i++) {
	    model.add(newValue.get(i));
	}
    }

    /**
     * Clear source list model.
     */
    public void clearSourceListModel() {
	this.sourceListModel.clear();
    }

    /**
     * Clear destination list model.
     */
    public void clearDestinationListModel() {
	this.destListModel.clear();
    }

    /**
     * Adds the source elements.
     *
     * @param newValue
     *            the new value
     */
    public void addSourceElements(List<String> newValue) {
	fillListModel(this.sourceListModel, newValue);
    }

    /**
     * Sets the source elements.
     *
     * @param newValue
     *            the new source elements
     */
    public void setSourceElements(List<String> newValue) {
	clearSourceListModel();
	addSourceElements(newValue);
    }

    /**
     * Adds the destination elements.
     *
     * @param newValue
     *            the new value
     */
    public void addDestinationElements(List<String> newValue) {
	fillListModel(this.destListModel, newValue);
    }

    /**
     * Adds the source elements.
     *
     * @param newValue
     *            the new value
     */
    public void addSourceElements(Object[] newValue) {
	fillListModel(this.sourceListModel, newValue);
    }

    /**
     * Sets the source elements.
     *
     * @param newValue
     *            the new source elements
     */
    public void setSourceElements(Object[] newValue) {
	clearSourceListModel();
	addSourceElements(newValue);
    }

    /**
     * Adds the destination elements.
     *
     * @param newValue
     *            the new value
     */
    public void addDestinationElements(Object[] newValue) {
	fillListModel(this.destListModel, newValue);
    }

    private void fillListModel(SortedListModel model, Object[] newValues) {
	model.addAll(newValues);
    }

    /**
     * Gets the source list.
     *
     * @return the source list
     */
    public JList<String> getSourceList() {
	return sourceList;
    }

    /**
     * Gets the destionation list.
     *
     * @return the destionation list
     */
    public JList<String> getDestionationList() {
	return destList;
    }

    /**
     * Gets the column stereotype names.
     *
     * @return the column stereotype names
     */
    public ListModel<String> getColumnStereotypeNames() {
	return sourceList.getModel();
    }

    /**
     * Gets the column filtered stereotype names.
     *
     * @return the column filtered stereotype names
     */
    public ListModel<String> getColumnFilteredStereotypeNames() {
	return destList.getModel();
    }

}