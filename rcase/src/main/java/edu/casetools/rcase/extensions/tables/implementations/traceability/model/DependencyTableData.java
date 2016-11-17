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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package edu.casetools.rcase.extensions.tables.implementations.traceability.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.modelio.api.modelio.model.IMetamodelExtensions;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.module.api.RCaseStereotypes;
import edu.casetools.rcase.module.impl.RCaseModule;
import edu.casetools.rcase.module.impl.RCasePeerModule;
import edu.casetools.rcase.utils.tables.TableUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class DependencyTableData.
 */
public class DependencyTableData implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -191731257462245537L;

    /** The data list. */
    protected ArrayList<ArrayList<Object>> dataList;

    /** The x header list. */
    protected ArrayList<MObject> xHeaderList;

    /** The y header list. */
    protected ArrayList<MObject> yHeaderList;

    /** The x stereotypes. */
    protected ArrayList<Stereotype> xStereotypes;

    /** The y stereotypes. */
    protected ArrayList<Stereotype> yStereotypes;

    /** The x stereotype filter. */
    protected ArrayList<Stereotype> xStereotypeFilter;

    /** The y stereotype filter. */
    protected ArrayList<Stereotype> yStereotypeFilter;

    /** The link stereotype. */
    protected transient Stereotype linkStereotype;

    /**
     * Instantiates a new dependency table data.
     */
    public DependencyTableData() {
	IMetamodelExtensions extensions = RCaseModule.getInstance().getModuleContext().getModelingSession()
		.getMetamodelExtensions();

	xStereotypes = initDefaultStereotypes(extensions);
	yStereotypes = initDefaultStereotypes(extensions);

	linkStereotype = initDefaultLinkStereotype(extensions);

	xStereotypeFilter = initDefaultStereotypeFilter();
	yStereotypeFilter = initDefaultStereotypeFilter();

    }

    private ArrayList<Stereotype> initDefaultStereotypes(IMetamodelExtensions extensions) {
	ArrayList<Stereotype> stereotypes = new ArrayList<>();
	stereotypes.add(extensions.getStereotype(RCasePeerModule.MODULE_NAME, RCaseStereotypes.STEREOTYPE_REQUIREMENT,
		RCaseModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel()
			.getMClass(org.modelio.metamodel.uml.statik.Class.class)));
	return stereotypes;
    }

    private Stereotype initDefaultLinkStereotype(IMetamodelExtensions extensions) {
	return extensions.getStereotype(RCasePeerModule.MODULE_NAME, RCaseStereotypes.DEFAULT_STEREOTYPE,
		RCaseModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel()
			.getMClass(Dependency.class));
    }

    private ArrayList<Stereotype> initDefaultStereotypeFilter() {
	return new ArrayList<>();
    }

    /**
     * Gets the data list.
     *
     * @return the data list
     */
    public List<ArrayList<Object>> getDataList() {
	return dataList;
    }

    /**
     * Sets the data list.
     *
     * @param dataList
     *            the new data list
     */
    public void setDataList(List<ArrayList<Object>> dataList) {
	this.dataList = (ArrayList<ArrayList<Object>>) dataList;
    }

    /**
     * Gets the x header list.
     *
     * @return the x header list
     */
    public List<MObject> getxHeaderList() {
	return xHeaderList;
    }

    /**
     * Sets the x header list.
     *
     * @param xHeaderList
     *            the new x header list
     */
    public void setxHeaderList(List<MObject> xHeaderList) {
	this.xHeaderList = (ArrayList<MObject>) xHeaderList;
    }

    /**
     * Gets the y header list.
     *
     * @return the y header list
     */
    public List<MObject> getyHeaderList() {
	return yHeaderList;
    }

    /**
     * Sets the y header list.
     *
     * @param yHeaderList
     *            the new y header list
     */
    public void setyHeaderList(List<MObject> yHeaderList) {
	this.yHeaderList = (ArrayList<MObject>) yHeaderList;
    }

    /**
     * Gets the x stereotypes.
     *
     * @return the x stereotypes
     */
    public List<Stereotype> getxStereotypes() {
	return xStereotypes;
    }

    /**
     * Sets the x stereotypes.
     *
     * @param xStereotypes
     *            the new x stereotypes
     */
    public void setxStereotypes(List<Stereotype> xStereotypes) {
	this.xStereotypes = (ArrayList<Stereotype>) xStereotypes;
    }

    /**
     * Gets the y stereotypes.
     *
     * @return the y stereotypes
     */
    public List<Stereotype> getyStereotypes() {
	return yStereotypes;
    }

    /**
     * Sets the y stereotypes.
     *
     * @param yStereotypes
     *            the new y stereotypes
     */
    public void setyStereotypes(List<Stereotype> yStereotypes) {
	this.yStereotypes = (ArrayList<Stereotype>) yStereotypes;
    }

    /**
     * Gets the x stereotype filter.
     *
     * @return the x stereotype filter
     */
    public List<Stereotype> getxStereotypeFilter() {
	return xStereotypeFilter;
    }

    /**
     * Sets the x stereotype filter.
     *
     * @param xStereotypeFilter
     *            the new x stereotype filter
     */
    public void setxStereotypeFilter(List<Stereotype> xStereotypeFilter) {
	this.xStereotypeFilter = (ArrayList<Stereotype>) xStereotypeFilter;
    }

    /**
     * Gets the y stereotype filter.
     *
     * @return the y stereotype filter
     */
    public List<Stereotype> getyStereotypeFilter() {
	return yStereotypeFilter;
    }

    /**
     * Sets the y stereotype filter.
     *
     * @param yStereotypeFilter
     *            the new y stereotype filter
     */
    public void setyStereotypeFilter(List<Stereotype> yStereotypeFilter) {
	this.yStereotypeFilter = (ArrayList<Stereotype>) yStereotypeFilter;
    }

    /**
     * Gets the link stereotype.
     *
     * @return the link stereotype
     */
    public Stereotype getLinkStereotype() {
	return linkStereotype;
    }

    /**
     * Sets the link stereotype.
     *
     * @param linkStereotype
     *            the new link stereotype
     */
    public void setLinkStereotype(Stereotype linkStereotype) {
	this.linkStereotype = linkStereotype;
    }

    /**
     * Sets the link stereotype.
     *
     * @param linkStereotypeName
     *            the new link stereotype
     */
    public void setLinkStereotype(String linkStereotypeName) {
	Stereotype auxiliarLinkStereotype = TableUtils.getInstance()
		.getDependencyStereotpyeFromName(linkStereotypeName);
	if (null != auxiliarLinkStereotype)
	    this.linkStereotype = auxiliarLinkStereotype;
    }

    /**
     * New data list.
     *
     * @return the int
     */
    public int newDataList() {
	int size = 0;
	if (null != dataList)
	    size = dataList.size();
	dataList = new ArrayList<>();
	return size;
	// Returns old data list size
    }

}
