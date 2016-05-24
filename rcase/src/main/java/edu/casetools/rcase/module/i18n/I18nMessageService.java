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
package edu.casetools.rcase.module.i18n;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Class I18nMessageService eases the handling of changing the language, by
 * taking the text of the application from a .properties file.
 */
public class I18nMessageService {

    private static final String FILE_NAME_MESSAGES = ".messages";
    private static final Logger logger = Logger.getLogger(I18nMessageService.class.getName());

    private static I18nMessageService instance;

    private ResourceBundle messageResource;

    private I18nMessageService() {
	messageResource = ResourceBundle
		.getBundle(I18nMessageService.class.getPackage().getName() + FILE_NAME_MESSAGES);
    }

    private static I18nMessageService getInstance() {
	if (null == instance) {
	    instance = new I18nMessageService();
	}
	return instance;
    }

    private ResourceBundle getMessageResource() {
	return messageResource;
    }

    /**
     * Gets the string.
     *
     * @param key
     *            the key
     * @return the string
     */
    public static String getString(String key) {
	String message = null;
	try {
	    message = getInstance().getMessageResource().getString(key);
	} catch (MissingResourceException e) {
	    message = '!' + key + '!';
	    logger.log(Level.WARNING, message + "\n" + e.getMessage(), e);
	}
	return message;
    }

    /**
     * Gets the string.
     *
     * @param key
     *            the key
     * @param params
     *            the params
     * @return the string
     */
    public static String getString(String key, String... params) {
	String message = null;
	try {
	    String value = getString(key);
	    message = MessageFormat.format(value, (Object[]) params);
	} catch (MissingResourceException e) {
	    message = '!' + key + '!';
	    logger.log(Level.WARNING, message + "\n" + e.getMessage(), e);
	}
	return message;
    }
}
