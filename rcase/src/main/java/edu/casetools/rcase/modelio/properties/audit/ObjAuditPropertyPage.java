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
package edu.casetools.rcase.modelio.properties.audit;

import java.util.List;
import java.util.Map.Entry;

import org.modelio.api.module.IModule;
import org.modelio.api.module.propertiesPage.AbstractModulePropertyPage;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.modelio.properties.audit.SatisfactionAuditor.SATISFACTION;
import edu.casetools.rcase.module.i18n.I18nMessageService;

/**
 * The Class RcasePropertyPage is the own property page of the R-CASE module.
 */
public class ObjAuditPropertyPage extends AbstractModulePropertyPage {

	public ObjAuditPropertyPage(IModule module, String name, String label, String bitmap) {
	        super(module, name, label, bitmap);
	 }
	 
	/**
	 * This method is called when a value has been edited
	 * in the property box in the row.
	 * Here we simply have to update the currently selected
	 * element name.
	 * Note: One transaction is already open. So it is not 
	 * necessary to create another one. 
	 */
	
	@Override
	public void changeProperty(List<MObject> elements, int row, String value) {
	
	}

    /**
     * This method is called when the current selection 
     * changes and that the property box contents requires
     * an update.
     * In this example, simply add one property (the Name of
     * the currently selected element)
     */

	@Override
	public void update(List<MObject> elements, IModulePropertyTable table) {
		ObjectiveAuditor auditRules = new ObjectiveAuditor();
		table.addConsultProperty(I18nMessageService.getString("Ui.Verdict.Name"), auditRules.audit().toString());
		for (Entry<MObject, SATISFACTION> entry : auditRules.getSatisfactionResults().entrySet()){
			table.addConsultProperty(entry.getKey().getName(), entry.getValue().toString());
		} 		
		auditRules = null;
	}
}