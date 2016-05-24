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
package edu.casetools.rcase.module.impl;

import org.modelio.api.model.IModelingSession;
import org.modelio.api.module.AbstractJavaModule;
import org.modelio.api.module.IModuleAPIConfiguration;
import org.modelio.api.module.IModuleSession;
import org.modelio.api.module.IModuleUserConfiguration;
import org.modelio.api.module.IParameterEditionModel;
import org.modelio.metamodel.mda.ModuleComponent;

public class RCaseModule extends AbstractJavaModule {

    private RCasePeerModule peerModule = null;

    private RCaseSession session = null;

    public RCaseModule(IModelingSession modelingSession, ModuleComponent moduleComponent,
	    IModuleUserConfiguration moduleConfiguration, IModuleAPIConfiguration peerConfiguration) {
	super(modelingSession, moduleComponent, moduleConfiguration);
	this.session = new RCaseSession(this);
	this.peerModule = new RCasePeerModule(this, peerConfiguration);
	this.peerModule.init();
    }

    @Override
    public RCasePeerModule getPeerModule() {
	return this.peerModule;
    }

    @Override
    public IModuleSession getSession() {
	return this.session;
    }

    @Override
    public IParameterEditionModel getParametersEditionModel() {
	if (null == parameterEditionModel) {
	    parameterEditionModel = super.getParametersEditionModel();
	}
	return parameterEditionModel;
    }

    @Override
    public String getModuleImagePath() {
	return "/res/icons/module_16.png";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((peerModule == null) ? 0 : peerModule.hashCode());
	result = prime * result + ((session == null) ? 0 : session.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	RCaseModule other = (RCaseModule) obj;
	if (peerModule == null) {
	    if (other.peerModule != null)
		return false;
	} else if (!peerModule.equals(other.peerModule))
	    return false;
	if (session == null) {
	    if (other.session != null)
		return false;
	} else if (!session.equals(other.session))
	    return false;
	return true;
    }

}
