package edu.casetools.rcase.modelio.properties.audit;

import java.util.Map.Entry;

import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.modelio.properties.IPropertyContent;
import edu.casetools.rcase.modelio.properties.audit.ReqAuditRuleManager.SATISFACTION;
import edu.casetools.rcase.module.i18n.I18nMessageService;

public class ReqAuditPropertyPage implements IPropertyContent {

	 @Override
	    public void changeProperty(ModelElement element, int row, String value) {
		 	//No property changes are allowed.
	    }

	    @Override
	    public void update(ModelElement element, IModulePropertyTable table) {
		
	    	ReqAuditRuleManager auditRules = new ReqAuditRuleManager();
	    	table.addConsultProperty(I18nMessageService.getString("Ui.Verdict.Name"), auditRules.audit().toString());
	    	for (Entry<MObject, SATISFACTION> entry : auditRules.getSatisfactionResults().entrySet()){
	    		table.addConsultProperty(entry.getKey().getName(), entry.getValue().toString());
	    	} 		

	    }

}
