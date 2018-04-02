package edu.casetools.rcase.modelio.properties.audit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.module.api.RCaseStereotypes;
import edu.casetools.rcase.module.impl.RCasePeerModule;
import edu.casetools.rcase.utils.tables.TableUtils;

public class ReqAuditRuleManager {
	
	public enum VERDICT {SATISFIED, UNSATISFIED, UNKNOWN, NULL};
	
	public List<MObject> getAllObjectives(){
		return TableUtils.getInstance().getAllElementsStereotypedAs(new ArrayList<>(), RCaseStereotypes.STEREOTYPE_OBJECTIVE);
	}

	
	public void checkObjective(MObject objective){
		//List<MObject> allObjectives = getAllObjectives();
		
	}
	
	public VERDICT auditObjective(MObject objective){
		boolean isMade 	   = false;
		boolean isHindered = false;
		boolean isUnknown  = false;
		
		for (Iterator<?> localIterator = ((ModelElement) objective).getImpactedDependency().iterator(); localIterator.hasNext();) {
		    Dependency dependency = (Dependency) localIterator.next();
		    if ( dependency.isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_HURT)  ||
		    	 dependency.isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_BREAK)  )
		    		isHindered = true;
		    if(dependency.isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_MAKE)){
		    		isMade = true;
		    }
		    if(dependency.isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_UNKNOWN) ){
		    		isUnknown = true;
		    }
		}
		
		if(isUnknown) return VERDICT.UNKNOWN;
		else if(isHindered) return VERDICT.UNSATISFIED;
		else if(isMade) return VERDICT.SATISFIED;
		else return VERDICT.NULL;
	}

	
	

}
