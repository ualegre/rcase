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
package edu.casetools.rcase.extensions.tables.traceability.view.edit;

import java.util.List;

import org.modelio.metamodel.uml.infrastructure.Stereotype;

import edu.casetools.rcase.extensions.tables.traceability.DependencyTable;
import edu.casetools.rcase.module.api.RCaseView;
import edu.casetools.rcase.module.i18n.I18nMessageService;

// TODO: Auto-generated Javadoc
/**
 * The Class EditColumnDialog.
 */
public class EditColumnDialog extends EditDialog {

    private static final long serialVersionUID = 8407897711894489190L;

    /**
     * Instantiates a new edits the column dialog.
     *
     * @param main
     *            the main
     */
    public EditColumnDialog(DependencyTable main) {
	super(main, I18nMessageService.getString("Dialogs.Name.Column"));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.casesuite.extensions.tables.traceability.view.edit
     * .EditDialog#getFilterStereotypes()
     */
    @Override
    protected List<Stereotype> getFilterStereotypes() {
	return this.data.getxStereotypeFilter();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.casesuite.extensions.tables.traceability.view.edit
     * .EditDialog#getStereotypes()
     */
    @Override
    protected List<Stereotype> getStereotypes() {
	return this.data.getxStereotypes();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.casesuite.extensions.tables.traceability.view.edit
     * .EditDialog#setOkActionCommand()
     */
    @Override
    protected void setOkActionCommand() {
	ok.setActionCommand(RCaseView.OK_COLUMN);
    }

}
