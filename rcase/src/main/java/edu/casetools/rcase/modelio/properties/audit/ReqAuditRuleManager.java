package edu.casetools.rcase.modelio.properties.audit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.module.api.RCaseProperties;
import edu.casetools.rcase.module.api.RCaseStereotypes;
import edu.casetools.rcase.module.i18n.I18nMessageService;
import edu.casetools.rcase.module.impl.RCasePeerModule;
import edu.casetools.rcase.utils.PropertiesUtils;
import edu.casetools.rcase.utils.tables.TableUtils;

public class ReqAuditRuleManager {
	
	public enum SATISFACTION {SATISFIED, PARTIALLY_SATISFIED, UNKNOWN, PARTIALLY_DISSATISFIED, DISSATISFIED, NOT_EVALUATED};
	public enum VERDICT {PASS, WARNING, FAIL, UNDEFINED_PRIORITY};
	private HashMap<MObject,SATISFACTION> satisfactionResults;
	private final String CRITICAL  = I18nMessageService.getString("Objective.properties.prioritytype.critical");
	private final String IMPORTANT = I18nMessageService.getString("Objective.properties.prioritytype.important");
	
	public ReqAuditRuleManager(){
		satisfactionResults = new HashMap<>();
	}
	
	public VERDICT audit(){
		auditSatisfactionFunctions();		
		for(Entry<MObject, SATISFACTION> entry : satisfactionResults.entrySet()){
			ModelElement objective = (ModelElement) entry.getKey();
			if(auditObjectivePriority(objective,"")){
				return VERDICT.UNDEFINED_PRIORITY;
			}
			if(entry.getValue() == SATISFACTION.DISSATISFIED){
				if(auditObjectivePriority(objective,CRITICAL)){
					return VERDICT.FAIL;
				}
				else if(auditObjectivePriority(objective,IMPORTANT)){
					return VERDICT.WARNING;
				}
			}
		}
		return VERDICT.PASS;
	}


	private boolean auditObjectivePriority(ModelElement objective, String priorityLvl) {
		String priorityLevel = PropertiesUtils.getInstance()
		.getTaggedValue(RCaseProperties.PROPERTY_OBJECTIVE_PRIORITY_LVL, objective);
		return (priorityLevel.equals(priorityLvl));
	}


	private void auditSatisfactionFunctions() {
		for(MObject objective : getAllHigherLevelObjectives()){
			auditChildrenObjectives(objective);
		}
	}
	
	public void auditChildrenObjectives(MObject objective){
		List<MObject> childrenObjectives = getChildrenObjectives(objective);
		if(childrenObjectives.isEmpty()){
			auditLowLevelObjective(objective);
		}else if(satisfactionResults.get(objective) == null){
			for(MObject childObjective: childrenObjectives)
			{
				if(satisfactionResults.get(childObjective)==null)
					auditChildrenObjectives(childObjective);
			}
		}
	}
	
	public List<MObject> getChildrenObjectives(MObject objective){		
		List<MObject> childrenObjective = new ArrayList<>();
		for (Iterator<?> localIterator = ((ModelElement) objective).getImpactedDependency().iterator(); localIterator.hasNext();) {
		    Dependency relationship = (Dependency) localIterator.next();
		    	
			    if(((ModelElement) relationship).isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_REFINEOBJ)){
				    	if(((ModelElement) relationship.getImpacted()).isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_OBJECTIVE)){
				    		childrenObjective.add(relationship.getImpacted());
				    	}
			    }
		}
		return childrenObjective;
	}
	
	public List<MObject> getAllHigherLevelObjectives(){
		List<MObject> lowerLevelObjectives = new ArrayList<>();
		for(MObject objective : getAllObjectives()){	
			if(!hasContribution(objective)) 
				lowerLevelObjectives.add(objective);
		}
		return lowerLevelObjectives;
	}
	
	public List<MObject> getAllObjectives(){
		return TableUtils.getInstance().getAllElementsStereotypedAs(new ArrayList<>(), RCaseStereotypes.STEREOTYPE_OBJECTIVE);
	}

	private boolean hasContribution(MObject objective) {
		boolean hasContribution = false;
		
		for (Iterator<?> localIterator = ((ModelElement) objective).getDependsOnDependency().iterator(); localIterator.hasNext();) {
		    Dependency dependency = (Dependency) localIterator.next();
		    
		    if(dependency.isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_REFINEOBJ)){
		    	hasContribution = true;
		    }   	
		}
		return hasContribution;
	}
	
	public void auditLowLevelObjective(MObject objective){
		boolean hasSatisfactionContribution 	= false;
		boolean hasPositiveContribution 		= false;
		boolean hasUnknownContribution  		= false;
		boolean hasNegativeContribution 		= false;
		boolean hasDissatisfactionContribution 	= false;


		
		for (Iterator<?> localIterator = ((ModelElement) objective).getImpactedDependency().iterator(); localIterator.hasNext();) {
		    Dependency dependency = (Dependency) localIterator.next();
		    
		    hasSatisfactionContribution 	= auditSatisfactionContribution(dependency);
		    hasPositiveContribution 		= auditPositiveContribution(dependency);
		    hasUnknownContribution  		= auditUnknownContribution(dependency);  
		    hasNegativeContribution 		= auditNegativeContribution(dependency);
		    hasDissatisfactionContribution 	= auditDissatisfactionContribution(dependency);
		}
		
		SATISFACTION result = getSatisfactionResult(hasSatisfactionContribution, hasPositiveContribution, hasUnknownContribution,
				hasNegativeContribution, hasDissatisfactionContribution);
		satisfactionResults.put(objective, result);
	}

	private SATISFACTION getSatisfactionResult(boolean hasSatisfactionContribution, boolean hasPositiveContribution,
			boolean hasUnknownContribution, boolean hasNegativeContribution, boolean hasDissatisfactionContribution) {
		if(hasSatisfactionContribution && !hasDissatisfactionContribution && !hasNegativeContribution && !hasUnknownContribution)
			return SATISFACTION.SATISFIED;
		else if (hasDissatisfactionContribution && !hasSatisfactionContribution && !hasPositiveContribution && !hasUnknownContribution)
			return SATISFACTION.DISSATISFIED;
		else if (hasUnknownContribution)
			return SATISFACTION.UNKNOWN;
		else if ((hasSatisfactionContribution || hasPositiveContribution) && (hasDissatisfactionContribution || hasNegativeContribution))
			return SATISFACTION.UNKNOWN;
		else if (hasPositiveContribution && !hasDissatisfactionContribution && !hasNegativeContribution && !hasUnknownContribution)
			return SATISFACTION.PARTIALLY_SATISFIED;
		else if (hasNegativeContribution && !hasSatisfactionContribution && !hasPositiveContribution && !hasUnknownContribution){
			return SATISFACTION.PARTIALLY_DISSATISFIED;
		} else if (!hasSatisfactionContribution && !hasPositiveContribution && !hasUnknownContribution && !hasNegativeContribution && !hasDissatisfactionContribution) 
			return SATISFACTION.DISSATISFIED;
		else return SATISFACTION.NOT_EVALUATED;
	}

	
	private boolean auditSatisfactionContribution(Dependency contribution){
		return  contribution.isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_MAKE) ;
	}
	
	private boolean auditPositiveContribution(Dependency contribution){
		return  contribution.isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_HELP);
	}
	
	private boolean auditUnknownContribution(Dependency contribution){
		return contribution.isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_UNKNOWN);
	}
	
	private boolean auditNegativeContribution(Dependency contribution){
		return  contribution.isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_HURT);
	}
	
	private boolean auditDissatisfactionContribution(Dependency contribution) {
		return contribution.isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_BREAK);
	}
	
	public HashMap<MObject,SATISFACTION> getSatisfactionResults(){
		return satisfactionResults;
	}
	

}
