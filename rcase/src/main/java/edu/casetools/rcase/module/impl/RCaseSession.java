/*
 * Copyright 2015 @author Unai Alegre @company Middlesex University
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
package edu.casetools.rcase.module.impl;

import org.modelio.api.log.ILogService;
import org.modelio.api.modelio.Modelio;
import org.modelio.api.module.DefaultModuleSession;
import org.modelio.api.module.ModuleException;
import org.modelio.vbasic.version.Version;

// TODO: Auto-generated Javadoc
/**
 * The Class RCaseSession.
 */
public class RCaseSession extends DefaultModuleSession {

    /**
     * Instantiates a new r case session.
     *
     * @param module
     *            the module
     */
    public RCaseSession(RCaseModule module) {
	super(module);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.modelio.api.module.DefaultModuleSession#start()
     */
    @Override
    public boolean start() throws ModuleException {
	// get the version of the module
	Version moduleVersion = module.getVersion();

	// get the Modelio log service
	ILogService logService = Modelio.getInstance().getLogService();

	String message = "Start of " + module.getName() + " " + moduleVersion;
	logService.info(module, message);
	return super.start();
    }

    /**
     * Install.
     *
     * @param modelioPath
     *            the modelio path
     * @param mdaPath
     *            the mda path
     * @return true, if successful
     * @throws ModuleException
     *             the module exception
     */
    public static boolean install(String modelioPath, String mdaPath) throws ModuleException {
	return DefaultModuleSession.install(modelioPath, mdaPath);
    }

}
