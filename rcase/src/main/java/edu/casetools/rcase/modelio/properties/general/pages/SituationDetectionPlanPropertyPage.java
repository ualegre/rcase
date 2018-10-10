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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Modelio. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package edu.casetools.rcase.modelio.properties.general.pages;

import java.util.ArrayList;
import java.util.List;

import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.modelio.properties.IPropertyContent;
import edu.casetools.rcase.module.api.RCaseProperties;
import edu.casetools.rcase.module.api.RCaseStereotypes;
import edu.casetools.rcase.module.i18n.I18nMessageService;
import edu.casetools.rcase.module.impl.RCaseModule;
import edu.casetools.rcase.module.impl.RCasePeerModule;
import edu.casetools.rcase.utils.PropertiesUtils;

public class SituationDetectionPlanPropertyPage implements IPropertyContent {

    @Override
    public void changeProperty(ModelElement element, int row, String value) {
		switch (row) {
			case 1:
			    element.setName(value);
			    break;
			case 2:
			    PropertiesUtils.getInstance().findAndAddValue(RCaseModule.getInstance(), RCasePeerModule.MODULE_NAME,
				    RCaseProperties.PROPERTY_SITUATION_DETECTION_PLAN_ID, value, element);
			    break;
			case 3:
			    PropertiesUtils.getInstance().findAndAddValue(RCaseModule.getInstance(), RCasePeerModule.MODULE_NAME,
				    RCaseProperties.PROPERTY_SITUATION_DETECTION_PLAN_DESCRIPTION, value, element);
			    break;
			case 4:
			    PropertiesUtils.getInstance().findAndAddValue(RCaseModule.getInstance(), RCasePeerModule.MODULE_NAME,
				    RCaseProperties.PROPERTY_SITUATION_DETECTION_PLAN_RECOMMENDATION, value, element);
			    break;
			case 5:
			    PropertiesUtils.getInstance().findAndAddValue(RCaseModule.getInstance(), RCasePeerModule.MODULE_NAME,
				    RCaseProperties.PROPERTY_SITUATION_DETECTION_PLAN_TOBEIMPLEMENTED, value, element);
			    break;
			default:
			    break;
		}
    }

    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
	
		String property = PropertiesUtils.getInstance().getTaggedValue(RCaseProperties.PROPERTY_SITUATION_DETECTION_PLAN_ID,
				element);		
		table.addProperty(I18nMessageService.getString("Ui.SituationDetectionPlan.Property.TagId"), property);
		
		table.addProperty(RCaseProperties.PROPERTY_NAME, element.getName());
	
		property = PropertiesUtils.getInstance().getTaggedValue(RCaseProperties.PROPERTY_SITUATION_DETECTION_PLAN_DESCRIPTION,
			element);
		table.addProperty(I18nMessageService.getString("Ui.SituationDetectionPlan.Property.TagDescription"), property);
		
		property = getSOIDetectionPlanRecommendationValue(element);//PropertiesUtils.getInstance()
				//.getTaggedValue(RCaseProperties.PROPERTY_SITUATION_DETECTION_PLAN_RECOMMENDATION, element);
			table.addConsultProperty(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagRecommendation"), property);//,
					//new String[] { I18nMessageService.getString("Ui.SituationOfInterest.Property.TagRecommendation.Recommended"),
					//	I18nMessageService.getString("Ui.SituationOfInterest.Property.TagRecommendation.RecommendedWarning"),
					//	I18nMessageService.getString("Ui.SituationOfInterest.Property.TagRecommendation.NotRecommended") });
		
		property = PropertiesUtils.getInstance()
				.getTaggedValue(RCaseProperties.PROPERTY_SITUATION_DETECTION_PLAN_TOBEIMPLEMENTED, element);
			table.addProperty(I18nMessageService.getString("Ui.SituationDetectionPlan.Property.TagToBeImplemented"), property,
					new String[] { I18nMessageService.getString("Ui.SituationDetectionPlan.Property.TagToBeImplemented.True"),
						I18nMessageService.getString("Ui.SituationDetectionPlan.Property.TagToBeImplemented.False") });
			
		property = getFailureLikelihoodLevel(element);//PropertiesUtils.getInstance().getTaggedValue(RCaseProperties.PROPERTY_SITUATION_DETECTION_PLAN_FAILURE, element);
			table.addConsultProperty(I18nMessageService.getString("Ui.SituationDetectionPlan.Property.TagFailure"), property);//,
					//new String[] { I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Low"),
					//		I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Medium"),
					//		I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.High") });
			
			property = getFailureImpactLevel(element);//PropertiesUtils.getInstance()
					//.getTaggedValue(RCaseProperties.PROPERTY_SITUATION_DETECTION_PLAN_IMPACT, element);
				table.addConsultProperty(I18nMessageService.getString("Ui.SituationDetectionPlan.Property.TagImpact"), property);//,
						//new String[] { I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Low"),
						//		I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Medium"),
						//		I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.High") });
    }

	public static String getSOIDetectionPlanRecommendationValue(ModelElement detectionPlan) {
		String failureLikelihood = getFailureLikelihoodLevel(detectionPlan);
		String failureImpact     = getFailureImpactLevel(detectionPlan);
		
		if(failureLikelihood.equals(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Low"))){
			if(failureImpact.equals(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Low"))){
				return I18nMessageService.getString("Ui.SituationOfInterest.Property.TagRecommendation.Recommended");
			} else if(failureImpact.equals(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Medium"))){
				return I18nMessageService.getString("Ui.SituationOfInterest.Property.TagRecommendation.RecommendedWarning");
			} else if(failureImpact.equals(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.High"))){
				return I18nMessageService.getString("Ui.SituationOfInterest.Property.TagRecommendation.RecommendedWarning");
			} else if(failureImpact.equals(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.None"))){
				return I18nMessageService.getString("Ui.SituationOfInterest.Property.TagRecommendation.Recommended");
			}
		} else if(failureLikelihood.equals(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Medium"))){
			if(failureImpact.equals(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Low"))){
				return I18nMessageService.getString("Ui.SituationOfInterest.Property.TagRecommendation.RecommendedWarning");
			} else if(failureImpact.equals(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Medium"))){
				return I18nMessageService.getString("Ui.SituationOfInterest.Property.TagRecommendation.RecommendedWarning");
			} else if(failureImpact.equals(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.High"))){
				return I18nMessageService.getString("Ui.SituationOfInterest.Property.TagRecommendation.NotRecommended");
			} else if(failureImpact.equals(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.None"))){
				return I18nMessageService.getString("Ui.SituationOfInterest.Property.TagRecommendation.RecommendedWarning");
			}
		} else if(failureLikelihood.equals(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.High"))){
			if(failureImpact.equals(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Low"))){
				return I18nMessageService.getString("Ui.SituationOfInterest.Property.TagRecommendation.NotRecommended");
			} else if(failureImpact.equals(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Medium"))){
				return I18nMessageService.getString("Ui.SituationOfInterest.Property.TagRecommendation.NotRecommended");
			} else if(failureImpact.equals(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.High"))){
				return I18nMessageService.getString("Ui.SituationOfInterest.Property.TagRecommendation.NotRecommended");
			} else if(failureImpact.equals(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.None"))){
				return I18nMessageService.getString("Ui.SituationOfInterest.Property.TagRecommendation.RecommendedWarning");
			}
		}
		return I18nMessageService.getString("Ui.SituationOfInterest.Property.TagRecommendation.NotEvaluated");
	}

	private static String getFailureImpactLevel(ModelElement plan) {
		List<MObject> contextAwareFeatures  = getContextAwareFeatures(plan);
		List<String> objectivePriorities = getObjectivePriorities(contextAwareFeatures);
		
		if(objectivePriorities.contains(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.High"))){
			return I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.High");
		} else if(objectivePriorities.contains(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Medium"))){
			return I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Medium");
		} else if(objectivePriorities.contains(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Low"))){
			return I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Low");
		} 
		return I18nMessageService.getString("Ui.SituationOfInterest.Property.TagRecommendation.NotEvaluated");
	}

	private static List<String> getObjectivePriorities(List<MObject> contextAwareFeatures) {
		List<String> priorities = new ArrayList<String>();
		for(MObject contextAwareFeature : contextAwareFeatures){
			for(Dependency dependency : ((ModelElement) contextAwareFeature).getDependsOnDependency()){
				if(dependency.isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_CONTRIBUTION)){
					String priority = PropertiesUtils.getInstance().getTaggedValue(RCaseProperties.PROPERTY_OBJECTIVE_PRIORITY_LVL, dependency.getDependsOn());
					if(dependency.isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_MAKE)){
						if(priority.equals(I18nMessageService.getString("Objective.properties.prioritytype.critical"))){
							priorities.add(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.High"));
						}  else if(priority.equals(I18nMessageService.getString("Objective.properties.prioritytype.important"))){
							priorities.add(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Medium"));
						}  else if(priority.equals(I18nMessageService.getString("Objective.properties.prioritytype.normal"))){
							priorities.add(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Low"));
						}
					} else if(dependency.isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_HELP)){
						if(priority.equals(I18nMessageService.getString("Objective.properties.prioritytype.critical"))){
							priorities.add(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Medium"));
						}  else if(priority.equals(I18nMessageService.getString("Objective.properties.prioritytype.important"))){
							priorities.add(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Medium"));
						}  else if(priority.equals(I18nMessageService.getString("Objective.properties.prioritytype.normal"))){
							priorities.add(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Low"));
						}
					}  else if(dependency.isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_UNKNOWN)){
						if(priority.equals(I18nMessageService.getString("Objective.properties.prioritytype.critical"))){
							priorities.add(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Medium"));
						}  else if(priority.equals(I18nMessageService.getString("Objective.properties.prioritytype.important"))){
							priorities.add(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Medium"));
						}  else if(priority.equals(I18nMessageService.getString("Objective.properties.prioritytype.normal"))){
							priorities.add(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Medium"));
						}
					}  else if(dependency.isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_HURT)){
						if(priority.equals(I18nMessageService.getString("Objective.properties.prioritytype.critical"))){
							priorities.add(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Low"));
						}  else if(priority.equals(I18nMessageService.getString("Objective.properties.prioritytype.important"))){
							priorities.add(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Medium"));
						}  else if(priority.equals(I18nMessageService.getString("Objective.properties.prioritytype.normal"))){
							priorities.add(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Low"));
						}
					} else if(dependency.isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_BREAK)){
						if(priority.equals(I18nMessageService.getString("Objective.properties.prioritytype.critical"))){
							priorities.add(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Low"));
						}  else if(priority.equals(I18nMessageService.getString("Objective.properties.prioritytype.important"))){
							priorities.add(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Medium"));
						}  else if(priority.equals(I18nMessageService.getString("Objective.properties.prioritytype.normal"))){
							priorities.add(I18nMessageService.getString("Ui.SituationOfInterest.Property.TagFrequency.Medium"));
						}
					} 
				}
			}
		}
		return priorities;
	}

	private static List<MObject> getContextAwareFeatures(ModelElement plan) {
		List<MObject> contextAwareFeatures = new ArrayList<>();
		for(Dependency dependency : plan.getDependsOnDependency()){
			if(dependency.isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_CONTEXT_DETECTS)){
				ModelElement soi = (ModelElement) dependency.getDependsOn();
				for(Dependency soiDependency : soi.getDependsOnDependency()){
					if(soiDependency.isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_TRIGGERS)){
						ModelElement contextAwareFeature = (ModelElement) soiDependency.getDependsOn();
						if(contextAwareFeature.isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_CONTEXT_AWARE_FEATURE)){
							if(!contextAwareFeatures.contains(contextAwareFeature)){
								contextAwareFeatures.add(contextAwareFeature);
								contextAwareFeatures = getContextAwareFeatureChildren(contextAwareFeature,contextAwareFeatures);
							}
						}
					}
				}
			}
		}
		return contextAwareFeatures;
	}

	private static List<MObject> getContextAwareFeatureChildren(ModelElement contextAwareFeature,
			List<MObject> contextAwareFeatures) {
		for(Dependency dependency : contextAwareFeature.getImpactedDependency()){
			if(dependency.isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_DERIVE)){
				ModelElement auxContextAwareFeature = (ModelElement) dependency.getImpacted();
				if(auxContextAwareFeature.isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_CONTEXT_AWARE_FEATURE)){
					if(!contextAwareFeatures.contains(auxContextAwareFeature)){
						contextAwareFeatures.add(auxContextAwareFeature);
						contextAwareFeatures = getContextAwareFeatureChildren(auxContextAwareFeature,contextAwareFeatures);
					}
				}
			}
		}
		return contextAwareFeatures;
	}

	private static String getFailureLikelihoodLevel(ModelElement detectionPlan) {
		List<MObject> primaryContextAttributes = getPrimaryContextAttributes(detectionPlan);
		for(MObject contextAttribute : primaryContextAttributes){
			
		}
		return "";
	}

	private static List<MObject> getPrimaryContextAttributes(ModelElement detectionPlan) {
		List<MObject> primaryContextAttributes = new ArrayList<MObject>();
		for(MObject element : detectionPlan.getCompositionChildren()){
			if(isPrimary((ModelElement)element)){
				primaryContextAttributes.add(element);
			}
			//detectionPlan.getCompositionChildren()
			///dependency.getImpacted().getDependsOn();
		}
		return primaryContextAttributes;
	}

	private static boolean isPrimary(ModelElement element) {
		for(Dependency dependency : element.getDependsOnDependency()){
			if(dependency.isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_CONTEXT_DERIVE)){
				if(dependency.getDependsOn().isStereotyped(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_CONTEXT_ATTRIBUTE)){
					return false;
				}
			}
		}
		return true;
	}


}
