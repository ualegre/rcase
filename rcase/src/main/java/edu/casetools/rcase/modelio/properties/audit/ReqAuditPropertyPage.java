package edu.casetools.rcase.modelio.properties.audit;

import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.modelio.properties.IPropertyContent;

public class ReqAuditPropertyPage implements IPropertyContent {

	 @Override
	    public void changeProperty(ModelElement element, int row, String value) {
			switch (row) {
				case 1:
//				    PropertiesUtils.getInstance().findAndAddValue(RCasePeerModule.MODULE_NAME,
//					    RCaseProperties.PROPERTY_STAKEHOLDER_ID, value, element);
//				    break;				    
				default:
				    break;
			}
	    }

	    @Override
	    public void update(ModelElement element, IModulePropertyTable table) {
		
	    	ReqAuditRuleManager auditRules = new ReqAuditRuleManager();
	    	for (MObject objective : auditRules.getAllObjectives() ){
				table.addProperty(objective.getName(), auditRules.auditObjective(objective).toString());
	    	}

	    }

}
