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
import java.util.Collection;

import org.modelio.api.modelio.Modelio;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.rcase.utils.tables.ModelioTableUtils;
import edu.casetools.rcase.utils.tables.TableUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class DependencyTableDataHandler.
 */
public class DependencyTableDataHandler implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7241959769908081831L;

    /**
     * Update headers.
     *
     * @param data
     *            the data
     * @return the dependency table data
     */
    public DependencyTableData updateHeaders(DependencyTableData data) {
	DependencyTableData auxiliarData = data;
	auxiliarData.xStereotypes = getAllProjectElementsStereotypes();
	auxiliarData.yStereotypes = getAllProjectElementsStereotypes();

	auxiliarData = filterHeaders(auxiliarData);

	auxiliarData.xHeaderList = (ArrayList<MObject>) TableUtils.getInstance().getHeader(data.xStereotypes);
	auxiliarData.yHeaderList = (ArrayList<MObject>) TableUtils.getInstance().getHeader(data.yStereotypes);

	return auxiliarData;

    }

    private ArrayList<Stereotype> getAllProjectElementsStereotypes() {
	ArrayList<Stereotype> elements = new ArrayList<>();
	Collection<Class> classes = Modelio.getInstance().getModelingSession().findByClass(Class.class);
	Collection<UseCase> useCases = Modelio.getInstance().getModelingSession().findByClass(UseCase.class);

	for (Class clas : classes) {
	    elements = getStereotypesFromClasses(elements, clas);
	}

	for (UseCase useCase : useCases) {
	    elements = getStereotypesFromUseCases(elements, useCase);
	}

	return elements;
    }

    private ArrayList<Stereotype> getStereotypesFromClasses(ArrayList<Stereotype> list, Class clas) {
	for (Stereotype stereotype : clas.getExtension()) {
	    if (!list.contains(stereotype)) {
		list.add(stereotype);
	    }
	}
	return list;
    }

    private ArrayList<Stereotype> getStereotypesFromUseCases(ArrayList<Stereotype> list, UseCase useCase) {
	for (Stereotype stereotype : useCase.getExtension()) {
	    if (!list.contains(stereotype)) {
		list.add(stereotype);
	    }
	}
	return list;
    }

    private DependencyTableData filterHeaders(DependencyTableData data) {
	DependencyTableData dataAuxiliar = data;
	dataAuxiliar = filterXHeaders(dataAuxiliar);
	return filterYHeaders(dataAuxiliar);
    }

    private DependencyTableData filterYHeaders(DependencyTableData data) {

	for (Stereotype filter : data.yStereotypeFilter) {

	    if (data.yStereotypes.contains(filter)) {
		data.yStereotypes.remove(filter);
	    }
	}
	return data;

    }

    private DependencyTableData filterXHeaders(DependencyTableData data) {
	for (Stereotype filter : data.xStereotypeFilter) {
	    if (data.xStereotypes.contains(filter)) {
		data.xStereotypes.remove(filter);
	    }
	}
	return data;
    }

    /**
     * Creates the content.
     *
     * @param data
     *            the data
     */
    public void createContent(DependencyTableData data) {
	for (MObject rowHeaderElement : data.yHeaderList) {
	    createRows(rowHeaderElement, data);
	}
    }

    private void createRows(MObject rowHeaderElement, DependencyTableData data) {
	ArrayList<Object> row = new ArrayList<>();
	for (MObject column : data.xHeaderList) {
	    Object value = ModelioTableUtils.getInstance().getTableValues(column, rowHeaderElement, data);
	    row.add(value);
	}
	data.dataList.add(row);
    }

}
