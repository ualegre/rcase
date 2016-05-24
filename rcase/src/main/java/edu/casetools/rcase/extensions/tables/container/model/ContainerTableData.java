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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Modelio. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package edu.casetools.rcase.extensions.tables.container.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.casetools.rcase.module.api.RCaseProperties;
import edu.casetools.rcase.module.i18n.I18nMessageService;

public class ContainerTableData implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4662930661432067043L;
    protected ArrayList<ContainerRow> dataList;
    protected ArrayList<ContainerTableHeaderData> xHeaderList;

    public ContainerTableData() {
	xHeaderList = new ArrayList<>();
	initDefaultData();
	dataList = new ArrayList<>();
    }

    private void initDefaultData() {
	ContainerTableHeaderData name = new ContainerTableHeaderData(
		I18nMessageService.getString("Table.Container.Header.Name"), RCaseProperties.PROPERTY_NAME,
		DataTypes.STRING_TYPE);
	ContainerTableHeaderData id = new ContainerTableHeaderData(
		I18nMessageService.getString("Table.Container.Header.Id"), RCaseProperties.PROPERTY_REQUIREMENT_ID,
		DataTypes.STRING_TYPE);
	ContainerTableHeaderData text = new ContainerTableHeaderData(
		I18nMessageService.getString("Table.Container.Header.Description"),
		RCaseProperties.PROPERTY_REQUIREMENT_DESCRIPTION, DataTypes.STRING_TYPE);
	xHeaderList.add(name);
	xHeaderList.add(id);
	xHeaderList.add(text);
    }

    public List<ContainerRow> getDataList() {
	return dataList;
    }

    public void setDataList(List<ContainerRow> dataList) {
	this.dataList = (ArrayList<ContainerRow>) dataList;
    }

    public List<ContainerTableHeaderData> getxHeaderList() {
	return xHeaderList;
    }

    public void setxHeaderList(List<ContainerTableHeaderData> xHeaderList) {
	this.xHeaderList = (ArrayList<ContainerTableHeaderData>) xHeaderList;
    }

    public int newDataList() {
	int size = 0;
	if (null != dataList)
	    size = dataList.size();
	dataList = new ArrayList<>();
	return size;
	// Returns old data list size
    }

    public void addRow(ContainerRow row) {
	dataList.add(row);

    }

    public List<Object> getColumnContent(int column) {
	ArrayList<Object> columnContent = new ArrayList<>();
	for (int i = 0; i < dataList.size(); i++) {
	    columnContent.add(dataList.get(i).getRowContent().get(column));
	}
	return columnContent;
    }

}
