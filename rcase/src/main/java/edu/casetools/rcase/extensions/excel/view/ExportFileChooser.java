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
package edu.casetools.rcase.extensions.excel.view;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.casetools.rcase.module.i18n.I18nMessageService;

/**
 * The Class ExportFileChooser has the common methods to choose a file where to
 * save the dependency table file.
 */
public class ExportFileChooser extends JFileChooser {

    private static final long serialVersionUID = -4103957984245673330L;

    /**
     * Instantiates a new export file chooser.
     */
    public ExportFileChooser() {
        initFileChooser();
    }

    private void initFileChooser() {
        this.setCurrentDirectory(new File(System.getProperty("user.home")));
        this.setDialogTitle(I18nMessageService
            .getString("Dialogs.Title.Export"));
        createFileFilters();
    }

    private void createFileFilters() {
        this.setFileFilter(new FileNameExtensionFilter("Excel 97",
            new String[] { "xls" }));
        this.setFileFilter(new FileNameExtensionFilter("Excel 2007",
            new String[] { "xlsx" }));

    }

}
