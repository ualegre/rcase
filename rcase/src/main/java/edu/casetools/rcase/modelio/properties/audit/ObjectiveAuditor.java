package edu.casetools.rcase.modelio.properties.audit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.modelio.properties.audit.SatisfactionAuditor.SATISFACTION;
import edu.casetools.rcase.module.api.RCaseProperties;
import edu.casetools.rcase.module.api.RCaseStereotypes;
import edu.casetools.rcase.module.i18n.I18nMessageService;
import edu.casetools.rcase.module.impl.RCasePeerModule;
import edu.casetools.rcase.utils.PropertiesUtils;
import edu.casetools.rcase.utils.tables.TableUtils;

public class ObjectiveAuditor {
	
	private final String CRITICAL  = I18nMessageService.getString("Objective.properties.prioritytype.critical");
	private final String IMPORTANT = I18nMessageService.getString("Objective.properties.prioritytype.important");
	
	public enum VERDICT {PASS, WARNING, FAIL, UNDEFINED_PRIORITY};
	private HashMap<MObject,SATISFACTION> satisfactionResults;

	
	public ObjectiveAuditor(){
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
			SATISFACTION objSatisfaction = auditHighLevelObjective(objective);
			satisfactionResults.put(objective,objSatisfaction);
		}
	}
	


	public SATISFACTION  auditHighLevelObjective(MObject objective){
		List<MObject> andChildrenObjectives = getChildrenObjectives(objective, RCaseStereotypes.STEREOTYPE_AND);
		List<MObject> orChildrenObjectives  = getChildrenObjectives(objective, RCaseStereotypes.STEREOTYPE_OR);
		List<MObject> xorChildrenObjectives = getChildrenObjectives(objective, RCaseStereotypes.STEREOTYPE_XOR);
		List<MObject> equalObjectives 		= getChildrenObjectives(objective, RCaseStereotypes.STEREOTYPE_EQUAL);
		SATISFACTION result = null;
		
		if(andChildrenObjectives.isEmpty() && orChildrenObjectives.isEmpty() && xorChildrenObjectives.isEmpty()){
			return auditLowLevelObjectives(objective);
		}else if(satisfactionResults.get(objective) == null){
			result = auditSiblings(andChildrenObjectives, orChildrenObjectives, xorChildrenObjectives);				
		}
		if(!equalObjectives.isEmpty())
			propagateEqualObjectives(equalObjectives,result);
		return result;
	}

	private SATISFACTION auditSiblings(List<MObject> andChildrenObjectives, List<MObject> orChildrenObjectives,
			List<MObject> xorChildrenObjectives) {
		SATISFACTION result;
		SATISFACTION andResult = null, orResult = null, siblingResult = null, xorResult = null;
		if(!andChildrenObjectives.isEmpty())
			andResult =  auditAndObjectives(andChildrenObjectives);
		if (!orChildrenObjectives.isEmpty()){
			orResult = auditOrObjectives(orChildrenObjectives);
			if(!andChildrenObjectives.isEmpty())
				siblingResult = addSiblingResult(andResult,orResult);				
		}
		if(!xorChildrenObjectives.isEmpty())
			xorResult = auditXorObjectives(xorChildrenObjectives);
			if(!andChildrenObjectives.isEmpty()){
				if(siblingResult!=null)
					result = addSiblingResult(siblingResult,xorResult);
				else
					result = addSiblingResult(andResult,xorResult);
			} else if (!orChildrenObjectives.isEmpty()){
				result = addSiblingResult(orResult,xorResult);
			} else
				result = xorResult;
		return result;
	}

	private SATISFACTION addSiblingResult(SATISFACTION andResult, SATISFACTION orResult) {
		SatisfactionAuditor satAuditor = new SatisfactionAuditor();
		satAuditor.addResult(andResult);
		satAuditor.addResult(orResult);
		return satAuditor.auditAND();
	}

	private void propagateEqualObjectives(List<MObject> equalObjectives, SATISFACTION result) {
		for(MObject objective : equalObjectives){
			satisfactionResults.put(objective, result);
		}
		
	}

	private SATISFACTION auditAndObjectives(List<MObject> andChildrenObjectives) {
		SatisfactionAuditor satAuditor = commonAuditHighLevelObjective(andChildrenObjectives);
		return satAuditor.auditAND();
	}
	
	private SATISFACTION auditOrObjectives(List<MObject> andChildrenObjectives) {
		SatisfactionAuditor satAuditor = commonAuditHighLevelObjective(andChildrenObjectives);
		return satAuditor.auditOR();
	}
	
	
	private SATISFACTION auditXorObjectives(List<MObject> andChildrenObjectives) {
		SatisfactionAuditor satAuditor = commonAuditHighLevelObjective(andChildrenObjectives);
		return satAuditor.auditXOR();
	}


	private SatisfactionAuditor commonAuditHighLevelObjective(List<MObject> andChildrenObjectives) {
		SatisfactionAuditor satAuditor = new SatisfactionAuditor();
		for(MObject childObjective: andChildrenObjectives)
		{
			SATISFACTION result = null;
			if(satisfactionResults.get(childObjective)==null){
				result = auditHighLevelObjective(childObjective);	
			}else{
				result = satisfactionResults.get(childObjective);
			}
			if(result!=null){
				satisfactionResults.put(childObjective, result);
				satAuditor.addResult(result);
			}
		}
		return satAuditor;
	}

	public List<MObject> getChildrenObjectives(MObject objective, String stereotypeName){		
		List<MObject> childrenObjective = new ArrayList<>();
		for (Iterator<?> localIterator = ((ModelElement) objective).getImpactedDependency().iterator(); localIterator.hasNext();) {
		    Dependency relationship = (Dependency) localIterator.next();
		    	
			    if(((ModelElement) relationship).isStereotyped(RCasePeerModule.MODULE_NAME, stereotypeName)){
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
	
	public SATISFACTION auditLowLevelObjectives(MObject objective){
		SatisfactionAuditor satAuditor = new SatisfactionAuditor();

		for (Iterator<?> localIterator = ((ModelElement) objective).getImpactedDependency().iterator(); localIterator.hasNext();) {
		    Dependency dependency = (Dependency) localIterator.next();
		    
		   satAuditor = auditContribution(satAuditor, dependency);

		}
		
		SATISFACTION result = satAuditor.audit();
		satisfactionResults.put(objective, result);
		return result;
	}

	private SatisfactionAuditor auditContribution(SatisfactionAuditor satAuditor, Dependency dependency) {
		
	   if(auditSatisfactionContribution(dependency))
		   satAuditor.addSatisfied();
	   else if (auditPositiveContribution(dependency))
		   satAuditor.addPartiallySatisfied();
	   else if (auditUnknownContribution(dependency))
		   satAuditor.addUnknown();
	   else if (auditNegativeContribution(dependency))
		   satAuditor.addPartiallyDissatisfied();
	   else if (auditDissatisfactionContribution(dependency))
		   satAuditor.addDissatisfied();
		
		return satAuditor;
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
