package edu.casetools.rcase.modelio.properties.audit;

import java.util.Map.Entry;

import org.modelio.api.modelio.Modelio;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.modelio.properties.IPropertyContent;
import edu.casetools.rcase.modelio.properties.audit.ReqAuditRuleManager.SATISFACTION;
import edu.casetools.rcase.module.i18n.I18nMessageService;
import edu.casetools.rcase.module.impl.RCaseModule;

public class ReqAuditPropertyPage implements IPropertyContent {

	 @SuppressWarnings("deprecation")
	@Override
	    public void changeProperty(ModelElement element, int row, String value) {
		 	//No property changes are allowed.
		 	Modelio.getInstance().getNavigationService().fireNavigate((MObject)element);
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
