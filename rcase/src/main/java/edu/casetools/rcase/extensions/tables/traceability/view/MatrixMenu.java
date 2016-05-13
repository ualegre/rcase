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

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.modelio.metamodel.uml.infrastructure.Stereotype;

import edu.casetools.rcase.extensions.tables.traceability.DependencyTable;
import edu.casetools.rcase.extensions.tables.traceability.control.ComboBoxListener;
import edu.casetools.rcase.extensions.tables.traceability.control.MatrixMenuListener;
import edu.casetools.rcase.module.i18n.I18nMessageService;
import edu.casetools.rcase.utils.tables.TableUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class MatrixMenu.
 */
public class MatrixMenu extends JPanel {

    private static final long serialVersionUID = 1478577245641896113L;
    private JComboBox<String> linkTypesComboBox;
    private JLabel linkTypeLabel;
    private String[] buttons = { I18nMessageService.getString("Menu.Matrix.Refresh"),
	    I18nMessageService.getString("Menu.Matrix.Filter.Rows"),
	    I18nMessageService.getString("Menu.Matrix.Filter.Columns"),
	    I18nMessageService.getString("Menu.Matrix.Export"), I18nMessageService.getString("Menu.Matrix.Import") };
    private DependencyTable main;

    /**
     * Instantiates a new matrix menu.
     *
     * @param main
     *            the main
     * @param columnDialog
     *            the column dialog
     */
    public MatrixMenu(DependencyTable main) {
	this.main = main;
	this.setLayout(new FlowLayout());
	createLinkType(main);
	createButtons();
    }

    private void createLinkType(DependencyTable main) {

	linkTypeLabel = new JLabel(I18nMessageService.getString("Menu.Matrix.Combobox.Label"));
	this.add(linkTypeLabel);
	linkTypesComboBox = new JComboBox<>();
	refresh();
	linkTypesComboBox.addActionListener(new ComboBoxListener(main));
	this.add(linkTypesComboBox);

    }

    private void createButtons() {
	for (int i = 0; i < buttons.length; i++) {
	    this.add(createButton(buttons[i]));
	}

    }

    private JButton createButton(String name) {
	JButton button = new JButton(name);
	button.setFocusable(false);
	button.addActionListener(new MatrixMenuListener(this.main));
	button.setActionCommand(name);
	return button;
    }

    /**
     * Gets the button names.
     *
     * @return the button names
     */
    public String[] getButtonNames() {
	return buttons;
    }

    /**
     * Refresh.
     *
     * @return the string
     */
    public String refresh() {
	List<String> content = new ArrayList<>();

	List<Stereotype> stereotypes = TableUtils.getInstance().getAllDependenciesStereotypes();
	if (null != stereotypes) {
	    for (Stereotype s : stereotypes) {
		content.add(s.getName());
	    }
	}
	linkTypesComboBox.setModel(new DefaultComboBoxModel<String>(new Vector<String>(content)));
	if (linkTypesComboBox.getItemCount() > 0) {
	    linkTypesComboBox.setSelectedItem(0);
	    return (String) linkTypesComboBox.getSelectedItem();
	}

	return "";

    }

    /**
     * Gets the combo box.
     *
     * @return the combo box
     */
    public JComboBox<String> getComboBox() {
	return linkTypesComboBox;
    }

}
