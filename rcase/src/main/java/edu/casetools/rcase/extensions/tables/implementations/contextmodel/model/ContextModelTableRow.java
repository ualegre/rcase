package edu.casetools.rcase.extensions.tables.implementations.contextmodel.model;

import java.util.ArrayList;

import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.module.api.RCaseProperties;
import edu.casetools.rcase.module.impl.RCasePeerModule;
import edu.casetools.rcase.utils.PropertiesUtils;

public class ContextModelTableRow {
    ModelElement situationalParameter;
    ArrayList<Object> values;

    public ContextModelTableRow(MObject situationalParameter) {
	this.situationalParameter = (ModelElement) situationalParameter;
	this.update();
    }

    private void update() {
	values = new ArrayList<>();
	values.add(situationalParameter.getName());
	// TagId
	values.add(PropertiesUtils.getInstance().getTaggedValue(RCaseProperties.PROPERTY_CONTEXT_ID,
		situationalParameter));

	// TagCost
	values.add(PropertiesUtils.getInstance().getTaggedValue(RCaseProperties.PROPERTY_CONTEXT_COST,
		situationalParameter));

	// TagStatus
	values.add(getTag(RCaseProperties.PROPERTY_CONTEXT_STATUS));

	// TagCreationProcess
	values.add(getTag(RCaseProperties.PROPERTY_CONTEXT_CREATION_PROCESS));

	// TagUserInvolvement
	values.add(getTag(RCaseProperties.PROPERTY_CONTEXT_USER_INVOLVEMENT));

	// TagVolatility
	values.add(getTag(RCaseProperties.PROPERTY_CONTEXT_VOLATILITY));

    }

    private String getTag(String propertyName) {
	String status = situationalParameter.getTagValue(RCasePeerModule.MODULE_NAME, propertyName);
	if (status != null)
	    return status;
	return "";
    }

    public void set(int column, Object value) {
	values.set(column, value);

    }

    public Object get(int column) {
	return values.get(column);
    }

    public int size() {
	return values.size();
    }

    public ModelElement getSituationalParameter() {
	return situationalParameter;
    }
}
