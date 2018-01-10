package edu.casetools.rcase.extensions.tables.implementations.contextmodel.model;

import java.util.ArrayList;

import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.module.api.RCaseProperties;
import edu.casetools.rcase.utils.PropertiesUtils;

public class ContextModelTableRow {
    ModelElement ContextAttribute;
    ArrayList<Object> values;

    public ContextModelTableRow(MObject ContextAttribute) {
	this.ContextAttribute = (ModelElement) ContextAttribute;
	this.update();
    }

    private void update() {
	values = new ArrayList<>();
	values.add(ContextAttribute.getName());
	// TagId
	values.add(PropertiesUtils.getInstance().getTaggedValue(RCaseProperties.PROPERTY_CONTEXT_ID, ContextAttribute));

	// TagText
	values.add(
		PropertiesUtils.getInstance().getTaggedValue(RCaseProperties.PROPERTY_CONTEXT_TEXT, ContextAttribute));

    }

//    private String getTag(String propertyName) {
//	String status = ContextAttribute.getTagValue(RCasePeerModule.MODULE_NAME, propertyName);
//	if (status != null)
//	    return status;
//	return "";
//    }

    public void set(int column, Object value) {
	values.set(column, value);

    }

    public Object get(int column) {
	return values.get(column);
    }

    public int size() {
	return values.size();
    }

    public ModelElement getContextAttribute() {
	return ContextAttribute;
    }
}
