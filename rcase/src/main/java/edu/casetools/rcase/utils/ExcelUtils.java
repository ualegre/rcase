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

import org.modelio.metamodel.uml.infrastructure.Stereotype;

import edu.casetools.rcase.module.i18n.I18nMessageService;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelUtils.
 */
public class ExcelUtils {
    private static ExcelUtils instance = null;
    private static final String STRING_EMPTY = "empty";
    private static final String STRING_NULL = "null";
    private static final String STRING_STEREOTYPE_OPEN = "<< ";
    private static final String STRING_STEREOTYPE_CLOSE = " >>";
    private static final char CHAR_EMPTY = ' ';

    /**
     * Gets the single instance of ExcelUtils.
     *
     * @return single instance of ExcelUtils
     */
    public static ExcelUtils getInstance() {
	if (instance == null) {
	    instance = new ExcelUtils();
	}
	return instance;
    }

    /**
     * Creates the safe sheet name.
     *
     * @param nameProposal
     *            the name proposal
     * @return the string
     */
    public final String createSafeSheetName(String nameProposal) {
	if (null == nameProposal) {
	    return STRING_NULL;
	}
	if (nameProposal.length() < 1) {
	    return STRING_EMPTY;
	}
	int length = Math.min(31, nameProposal.length());
	String shortenname = nameProposal.substring(0, length);
	StringBuilder result = new StringBuilder(shortenname);
	for (int i = 0; i < length; i++) {
	    char ch = result.charAt(i);
	    formatChars(length, result, i, ch);

	}

	return result.toString();
    }

    private StringBuilder formatChars(int length, StringBuilder result, int i, char ch) {
	if (ch == ']')
	    result.setCharAt(i, CHAR_EMPTY);
	if ((ch == '\'') && ((0 == i) || (i == length - 1))) {
	    result.setCharAt(i, CHAR_EMPTY);
	}
	return result;

    }

    /**
     * Validate sheet name.
     *
     * @param sheetName
     *            the sheet name
     */
    public void validateSheetName(String sheetName) {
	char[] validCharArray = { '*', '/', ':', '?', '[', '\\', ']', '\'' };

	if (null == sheetName) {
	    throw new IllegalArgumentException(I18nMessageService.getString("ExcelError.NullName"));
	}
	int len = sheetName.length();
	if (len < 1) {
	    throw new IllegalArgumentException(I18nMessageService.getString("ExcelError.EmptyName"));
	}

	checkChainValidity(sheetName, validCharArray, len);

	if (('\'' == sheetName.charAt(0)) || ('\'' == sheetName.charAt(len - 1)))
	    throw new IllegalArgumentException(
		    new StringBuilder().append(I18nMessageService.getString("ExcelError.InvalidName")).append(sheetName)
			    .append(I18nMessageService.getString("ExcelError.InvalidNameTip")).toString());
    }

    private void checkChainValidity(String sheetName, char[] validCharArray, int len) {
	for (int i = 0; i < len; i++) {
	    char ch = sheetName.charAt(i);
	    boolean validChar = checkCharValidity(validCharArray, ch);
	    if (!validChar)
		throw new IllegalArgumentException(
			new StringBuilder().append(I18nMessageService.getString("ExcelError.InvalidChar")).append(ch)
				.append(I18nMessageService.getString("ExcelError.InvalidCharIndex")).append(i)
				.append(I18nMessageService.getString("ExcelError.InvalidCharName")).append(sheetName)
				.append("'").toString());
	}
    }

    /**
     * Checks if a char is valid
     *
     * @param invalidCharArray:
     *            The different chars considered as invalid
     * @param actualChar:
     *            The char to be compared against the char array
     */
    private boolean checkCharValidity(char[] invalidCharArray, char actualChar) {
	boolean validity = true;
	for (int i = 0; i < invalidCharArray.length; i++) {
	    if (invalidCharArray[i] == actualChar)
		validity = false;
	}
	return validity;
    }

    /**
     * Validate sheet state.
     *
     * @param state
     *            the state
     */
    public void validateSheetState(int state) {
	boolean valid = false;
	for (int stateList = 0; stateList < 2; stateList++) {
	    if (state == stateList)
		valid = true;
	}
	if (!valid)
	    throw new IllegalArgumentException(
		    new StringBuilder().append(I18nMessageService.getString("ExcelError.InvalidState")).append(state)
			    .append("\n").append(I18nMessageService.getString("ExcelError.InvalidStateTip")).toString());

    }

    /**
     * Gets the stereotype.
     *
     * @param elementName
     *            the element name
     * @return the stereotype
     */
    public String getStereotype(String elementName) {
	Stereotype stereotype = ModelioUtils.getInstance().getStereotypeOfElementByName(elementName);
	if (null != stereotype)
	    return STRING_STEREOTYPE_OPEN + stereotype.getName() + STRING_STEREOTYPE_CLOSE;
	else
	    return "";

    }

}
