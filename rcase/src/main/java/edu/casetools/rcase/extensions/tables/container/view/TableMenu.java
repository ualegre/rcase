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
package edu.casetools.rcase.extensions.tables.container.view;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.extensions.tables.container.ContainerTable;
import edu.casetools.rcase.extensions.tables.container.control.menu.ComboBoxListener;
import edu.casetools.rcase.extensions.tables.container.control.menu.MenuListener;
import edu.casetools.rcase.module.api.RCaseStereotypes;
import edu.casetools.rcase.module.api.RCaseView;
import edu.casetools.rcase.module.i18n.I18nMessageService;
import edu.casetools.rcase.utils.tables.TableUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class TableMenu.
 */
public class TableMenu extends JPanel {

    private static final long serialVersionUID = 5436513751113151508L;
    private JComboBox<String> comboBox;
    private JLabel label;
    private String[] buttons = { I18nMessageService.getString("Menu.Container.Refresh"),
	    I18nMessageService.getString("Menu.Container.New"), I18nMessageService.getString("Menu.Container.Delete"),
	    I18nMessageService.getString("Menu.Container.Export"),
	    I18nMessageService.getString("Menu.Container.Import") };

    /**
     * Instantiates a new table menu.
     *
     * @param main
     *            the main
     */
    public TableMenu(ContainerTable main) {
	this.setLayout(new FlowLayout());
	MenuListener listener = new MenuListener(main);
	ComboBoxListener cbListener = new ComboBoxListener(main);
	createComboBox(cbListener);
	createButtons(listener);
    }

    private void createButtons(MenuListener listener) {
	for (int i = 0; i < buttons.length; i++) {
	    this.add(createButton(buttons[i], listener));
	}
    }

    private void createComboBox(ComboBoxListener cbListener) {

	label = new JLabel(I18nMessageService.getString("Menu.Container.Combobox.Label"));
	this.add(label);
	comboBox = new JComboBox<>(new Vector<String>(createDependencyList()));
	comboBox.addActionListener(cbListener);
	comboBox.setActionCommand(RCaseView.COMOBOX_COMMAND);
	this.add(comboBox);

    }

    private List<String> createDependencyList() {
	List<String> linkList = new ArrayList<>();
	List<MObject> list = new ArrayList<>();
	list = TableUtils.getInstance().getAllElementsStereotypedAs(list,
		RCaseStereotypes.STEREOTYPE_REQUIREMENT_CONTAINER);

	for (MObject container : list) {
	    linkList.add(container.getName());
	}
	return linkList;
    }

    private JButton createButton(String name, MenuListener listener) {
	JButton button = new JButton(name);
	button.setFocusable(false);
	button.addActionListener(listener);
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
     * Gets the combo box.
     *
     * @return the combo box
     */
    public JComboBox<String> getComboBox() {
	return comboBox;
    }

    /**
     * Refresh.
     *
     * @param containerName
     *            the container name
     */
    public void refresh(String containerName) {

	List<String> containers = createDependencyList();
	comboBox.removeAllItems();
	for (String name : containers) {
	    comboBox.addItem(name);
	    if (name.equals(containerName))
		comboBox.setSelectedItem(name);
	}

    }

}
