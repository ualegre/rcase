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
package edu.casetools.rcase.utils;

import java.io.File;

import javax.swing.ImageIcon;

import org.modelio.api.module.IModule;


/**
 * The Class ResourcesManager manages the resources of the system such as Images
 * and Styles.
 */
public class ResourcesManager {
    private static ResourcesManager instance = null;
    private IModule module;

    /**
     * Gets the single instance of ResourcesManager.
     *
     * @return single instance of ResourcesManager
     */
    public static ResourcesManager getInstance() {
	if (instance == null) {
	    instance = new ResourcesManager();
	}
	return instance;
    }

    /**
     * Sets the jmdac.
     *
     * @param module
     *            the new jmdac
     */
    public void setJMDAC(IModule module) {
	this.module = module;
    }

    /**
     * Gets the image.
     *
     * @param imageName
     *            the image name
     * @return the image
     */
    public String getImage(String imageName) {
	return this.module.getModuleContext().getConfiguration().getModuleResourcesPath().toString()
		// + File.separator + "res" + File.separator + "icons"
		+ File.separator + imageName;
    }

    /**
     * Gets the style.
     *
     * @param styleName
     *            the style name
     * @return the style
     */
    public String getStyle(String styleName) {
	return this.module.getModuleContext().getConfiguration().getModuleResourcesPath() + File.separator + "res"
		+ File.separator + "style" + File.separator + styleName;
    }

//    /**
//     * Gets the property name.
//     *
//     * @param propertyName
//     *            the property name
//     * @return the property name
//     */
//    public String getPropertyName(String propertyName) {
//	return I18nMessageService.getString("Ui.Property." + propertyName + ".Name");
//    }

    /**
     * Register styles.
     *
     * @param styleNames
     *            the style names
     */
    public void registerStyles(String[] styleNames) {
	for (String name : styleNames) {
	    String path = this.getStyle(name);
	    File style = new File(path);
	    this.module.getModuleContext().getModelioServices().getDiagramService().registerStyle(name, "default",
		    style);
	}
    }

    /**
     * Creates the image icon.
     *
     * @param imageName
     *            the image name
     * @return the image icon
     */
    public ImageIcon createImageIcon(String imageName) {
	String path = ResourcesManager.getInstance().getImage(imageName);
	if (path != null)
	    return new ImageIcon(path);
	else
	    return null;

    }

}
