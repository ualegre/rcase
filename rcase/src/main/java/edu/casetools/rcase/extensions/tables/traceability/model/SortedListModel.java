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
package edu.casetools.rcase.extensions.tables.traceability.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.AbstractListModel;

// TODO: Auto-generated Javadoc
/**
 * The Class SortedListModel.
 */
public class SortedListModel extends AbstractListModel<Object> {

    private static final long serialVersionUID = -3493968298932509507L;
    private TreeSet<Object> model;

    /**
     * Instantiates a new sorted list model.
     */
    public SortedListModel() {
	model = new TreeSet<>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.ListModel#getSize()
     */
    @Override
    public int getSize() {
	return model.size();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.ListModel#getElementAt(int)
     */
    @Override
    public Object getElementAt(int index) {
	return model.toArray()[index];
    }

    /**
     * Adds the.
     *
     * @param element
     *            the element
     */
    public void add(Object element) {
	if (model.add(element)) {
	    fireContentsChanged(this, 0, getSize());
	}
    }

    /**
     * Adds the all.
     *
     * @param elements
     *            the elements
     */
    public void addAll(Object[] elements) {
	Collection<Object> collection = Arrays.asList(elements);
	model.addAll(collection);
	fireContentsChanged(this, 0, getSize());
    }

    /**
     * Clear.
     */
    public void clear() {
	model.clear();
	fireContentsChanged(this, 0, getSize());
    }

    /**
     * Contains.
     *
     * @param element
     *            the element
     * @return true, if successful
     */
    public boolean contains(Object element) {
	return model.contains(element);
    }

    /**
     * First element.
     *
     * @return the object
     */
    public Object firstElement() {
	return model.first();
    }

    /**
     * Iterator.
     *
     * @return the iterator
     */
    public Iterator<Object> iterator() {
	return model.iterator();
    }

    /**
     * Last element.
     *
     * @return the object
     */
    public Object lastElement() {
	return model.last();
    }

    /**
     * Removes the element.
     *
     * @param element
     *            the element
     * @return true, if successful
     */
    public boolean removeElement(Object element) {
	boolean removed = model.remove(element);
	if (removed) {
	    fireContentsChanged(this, 0, getSize());
	}
	return removed;
    }
}
