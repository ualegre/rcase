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
package edu.casetools.rcase.extensions.excel.view;

import java.io.File;

import javax.swing.JFileChooser;

import edu.casetools.rcase.extensions.excel.control.DependencyTableExporter;
import edu.casetools.rcase.extensions.tables.implementations.traceability.DependencyTable;

/**
 * The Class DependencyTableFileChooser enables to choose a file where to save
 * the dependency table file.
 */
public class DependencyTableFileChooser extends ExportFileChooser {

    private static final long serialVersionUID = -4103957984245673330L;
    private DependencyTableExporter exporter;

    /**
     * Exports the content of the table into the selected file.
     *
     * @param main
     *            the dependency table.
     */
    public void export(DependencyTable main) {

	int result = this.showSaveDialog(main);

	if (result == JFileChooser.APPROVE_OPTION) {
	    File selectedFile = this.getSelectedFile();
	    this.exporter = new DependencyTableExporter(selectedFile, main.getTablePanel().getTableModel());
	    this.exporter.export();
	}

    }
}
