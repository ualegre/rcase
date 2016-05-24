/*
 * Copyright 2015 @author Unai Alegre 
 * 
 * This file is part of RCASE (Requirements for Context-Aware Systems Engineering), a module 
 * of Modelio that aids the requirements elicitation phase of a Context-Aware System (C-AS). 
 * 
 * RCASE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * RCASE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package edu.casetools.rcase.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.EList;
import org.modelio.api.model.IUmlModel;
import org.modelio.api.modelio.Modelio;
import org.modelio.metamodel.factory.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.module.api.RCaseProperties;
import edu.casetools.rcase.module.impl.RCasePeerModule;

// TODO: Auto-generated Javadoc
/**
 * The Class PropertiesUtils.
 */
public class PropertiesUtils {
    private static final Logger logger = Logger.getLogger(PropertiesUtils.class.getName());
    private static PropertiesUtils instance = null;
    private static final String SEPARATOR_ELEMENT = ";";
    private static final String SEPARATOR_NAMESPACE = "::";
    private static final String ONE = "1";
    private static final String ZERO = "0";
    private static final String FALSE_STRING = "false";
    private static final String EMPTY = "";

    /**
     * Gets the single instance of PropertiesUtils.
     *
     * @return single instance of PropertiesUtils
     */
    public static PropertiesUtils getInstance() {
	if (instance == null) {
	    instance = new PropertiesUtils();
	}
	return instance;
    }

    /**
     * Accept.
     *
     * @param selectedElement
     *            the selected element
     * @return true, if successful
     */
    public boolean accept(MObject selectedElement) {
	IUmlModel model = Modelio.getInstance().getModelingSession().getModel();

	for (MObject libRoot : model.getLibraryRoots()) {
	    if (selectedElement.equals(libRoot))
		return false;
	}

	for (MObject modelRoot : model.getModelRoots()) {
	    if (selectedElement.equals(modelRoot))
		return false;
	}

	return !selectedElement.equals(model);
    }

    /**
     * Gets the absolute names with separator.
     *
     * @param listElt
     *            the list elt
     * @return the absolute names with separator
     */
    public String getAbsoluteNamesWithSeparator(List<ModelElement> listElt) {
	String result = "";
	int size = listElt.size();
	if (size > 1) {
	    for (ModelElement elt : listElt) {
		result = result.concat(" " + SEPARATOR_ELEMENT + " " + getAbsoluteName(elt));
	    }

	    result = result.replaceFirst(SEPARATOR_ELEMENT, "");
	} else if (1 == size) {
	    result = getAbsoluteName(listElt.get(0));
	}
	return result;
    }

    /**
     * Gets the absolute name.
     *
     * @param elt
     *            the elt
     * @return the absolute name
     */
    public String getAbsoluteName(ModelElement elt) {
	String result = elt.getName();
	MObject temp = elt;
	while (null != temp.getCompositionOwner()) {
	    temp = temp.getCompositionOwner();
	    if (temp instanceof ModelElement) {
		result = ((ModelElement) temp).getName() + SEPARATOR_NAMESPACE + result;
	    }
	}
	return result;
    }

    /**
     * Compute property list.
     *
     * @param element
     *            the element
     * @return the list
     */
    public List<Stereotype> computePropertyList(ModelElement element) {
	List<Stereotype> result = new ArrayList<>();
	int i = 0;

	for (Stereotype ster : element.getExtension()) {
	    if ((ster.getOwner().getOwnerModule().getName().equals(RCasePeerModule.MODULE_NAME))
		    && (!result.contains(ster))) {
		result.add(ster);

		Stereotype parent = ster.getParent();
		while ((null != parent) && (!result.contains(parent))) {
		    result.add(i, parent);
		    ster = parent;
		    parent = ster.getParent();
		}
		i = result.size();
	    }

	}

	return result;
    }

    /**
     * Adds the value.
     *
     * @param modulename
     *            the modulename
     * @param name
     *            the name
     * @param value
     *            the value
     * @param element
     *            the element
     * @param related
     *            the related
     * @param modulelink
     *            the modulelink
     * @param stereotypeLink
     *            the stereotype link
     */
    public void addValue(String modulename, String name, String value, ModelElement element, ModelElement related,
	    String modulelink, String stereotypeLink) {
	boolean exist = false;

	TaggedValue tag = null;
	EList<TaggedValue> tagElements = element.getTag();
	IUmlModel model = Modelio.getInstance().getModelingSession().getModel();

	if (!tagElements.isEmpty()) {
	    for (TaggedValue currentTag : tagElements) {
		TagType type = currentTag.getDefinition();
		String tagname = type.getName();

		if (tagname.equals(name)) {
		    exist = true;
		    tag = currentTag;
		    break;
		}
	    }

	}

	if (!exist) {
	    try {
		tag = model.createTaggedValue(modulename, name, element);
	    } catch (ExtensionNotFoundException e) {
		logger.log(Level.WARNING, e.getMessage(), e);
	    }

	}

	setTaggedValue(tag, element, value, related, modulelink, stereotypeLink);
    }

    /**
     * Adds the value.
     *
     * @param modulename
     *            the modulename
     * @param name
     *            the name
     * @param values
     *            the values
     * @param element
     *            the element
     */
    public void findAndAddValue(String modulename, String name, String values, ModelElement element) {
	boolean exist = false;
	EList<TaggedValue> tagElements = element.getTag();
	TaggedValue tagValueFound = null;

	if (!tagElements.isEmpty()) {
	    for (TaggedValue tag : tagElements) {
		String tagname = tag.getDefinition().getName();

		if (tagname.equals(name)) {
		    exist = true;
		    tagValueFound = tag;
		}
	    }

	}

	addValue(modulename, name, values, element, exist, tagValueFound);
    }

    private void addValue(String modulename, String name, String values, ModelElement element, boolean exist,
	    TaggedValue tagValueFound) {
	if (!exist) {
	    createTaggedValue(modulename, name, values, element);
	} else if ((tagValueFound != null) && (tagValueFound.getDefinition().getParamNumber().equals(ZERO))) {
	    tagValueFound.delete();
	} else
	    setTaggedValue(name, element, values);
    }

    private void createTaggedValue(String modulename, String name, String values, ModelElement element) {
	try {
	    TaggedValue taggedValue = Modelio.getInstance().getModelingSession().getModel()
		    .createTaggedValue(modulename, name, element);
	    element.getTag().add(taggedValue);
	    if (!taggedValue.getDefinition().getParamNumber().equals(ZERO))
		setTaggedValue(name, element, values);
	} catch (ExtensionNotFoundException e) {
	    logger.log(Level.WARNING, e.getMessage(), e);
	}
    }

    /**
     * Sets the tagged value.
     *
     * @param name
     *            the name
     * @param elt
     *            the elt
     * @param value
     *            the value
     */
    public void setTaggedValue(String name, ModelElement elt, String value) {
	EList<TaggedValue> tagElements = elt.getTag();
	IUmlModel model = Modelio.getInstance().getModelingSession().getModel();
	if (!tagElements.isEmpty()) {
	    for (TaggedValue tag : tagElements) {
		String tagname = tag.getDefinition().getName();
		if (tagname.equals(name)) {
		    List<?> actuals = tag.getActual();
		    TagParameter firstElt = createTagParameter(model, tag, actuals);
		    deleteTag(value, tag, firstElt);
		}
	    }
	}
    }

    private void deleteTag(String value, TaggedValue tag, TagParameter firstElt) {
	if (((value.equals(FALSE_STRING)) && (tag.getDefinition().getParamNumber().equals(ZERO)))
		|| ((value.equals(EMPTY)) && (tag.getDefinition().getParamNumber().equals(ONE))))
	    tag.delete();
	else
	    firstElt.setValue(value);
    }

    private TagParameter createTagParameter(IUmlModel model, TaggedValue tag, List<?> actuals) {
	TagParameter firstElt;
	if ((null != actuals) && (!actuals.isEmpty())) {
	    firstElt = (TagParameter) actuals.get(0);
	} else {
	    firstElt = model.createTagParameter();
	    tag.getActual().add(firstElt);
	}
	return firstElt;
    }

    /**
     * Sets the tagged value.
     *
     * @param foundTagValue
     *            the tv found
     * @param elt
     *            the elt
     * @param value
     *            the value
     * @param related
     *            the related
     * @param modulelink
     *            the modulelink
     * @param stereotypeLink
     *            the stereotype link
     */
    public void setTaggedValue(TaggedValue foundTagValue, ModelElement elt, String value, ModelElement related,
	    String modulelink, String stereotypeLink) {
	TagParameter firstElt;
	IUmlModel model = Modelio.getInstance().getModelingSession().getModel();
	ArrayList<Dependency> linksList = new ArrayList<>(elt.getDependsOnDependency());
	for (Dependency existingLinks : linksList) {
	    if (existingLinks.isStereotyped(modulelink, stereotypeLink)) {
		existingLinks.delete();
	    }
	}

	List<?> actuals = foundTagValue.getActual();
	if ((null != actuals) && (actuals.isEmpty())) {
	    firstElt = (TagParameter) actuals.get(0);
	} else {
	    firstElt = model.createTagParameter();
	    foundTagValue.getActual().add(firstElt);
	}

	if (value.equals(FALSE_STRING)) {
	    foundTagValue.delete();
	} else {
	    firstElt.setValue(value);

	    try {
		model.createDependency(elt, related, modulelink, stereotypeLink);
	    } catch (ExtensionNotFoundException e) {
		logger.log(Level.WARNING, e.getMessage(), e);
	    }

	}
    }

    /**
     * Gets the tagged value.
     *
     * @param tagtype
     *            the tagtype
     * @param element
     *            the element
     * @return the tagged value
     */
    public String getTaggedValue(String tagtype, ModelElement element) {
	for (TaggedValue tag : element.getTag()) {
	    TagType type = tag.getDefinition();
	    String tagname = type.getName();

	    if (tagname.equals(tagtype)) {
		List<?> actuals = tag.getActual();
		if ((null != actuals) && (!actuals.isEmpty())) {
		    return ((TagParameter) actuals.get(0)).getValue();
		}
	    }
	}

	if (tagtype.equals(RCaseProperties.PROPERTY_NAME)) {
	    return element.getName();
	}

	return "";
    }

    /**
     * Checks for tagged value.
     *
     * @param tagtype
     *            the tagtype
     * @param element
     *            the element
     * @return true, if successful
     */
    public boolean hasTaggedValue(String tagtype, ModelElement element) {
	List<?> tagElements = element.getTag();
	Iterator<?> itChildren = tagElements.iterator();

	while (itChildren.hasNext()) {
	    TaggedValue tag = (TaggedValue) itChildren.next();
	    TagType type = tag.getDefinition();
	    String tagname = type.getName();
	    if (tagname.equals(tagtype)) {
		return true;
	    }
	}

	return false;
    }

}
