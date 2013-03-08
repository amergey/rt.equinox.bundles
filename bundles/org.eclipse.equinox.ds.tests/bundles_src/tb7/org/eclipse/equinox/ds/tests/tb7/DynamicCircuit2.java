/*******************************************************************************
 * Copyright (c) 1997-2009 by ProSyst Software GmbH
 * http://www.prosyst.com
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    ProSyst Software GmbH - initial API and implementation
 *******************************************************************************/
package org.eclipse.equinox.ds.tests.tb7;

import java.util.Dictionary;

import org.eclipse.equinox.ds.tests.tbc.BoundTester;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;

public class DynamicCircuit2 implements BoundTester {

	private DynamicCircuit1 service;
	private ComponentContext ctxt;
	private boolean boundObjectActivated = false;

	public void activate(ComponentContext ctxt) {
		this.ctxt = ctxt;
	}

	public void deactivate(ComponentContext ctxt) {
		this.ctxt = null;
	}

	public int getBoundObjectsCount() {
		return (service != null ? 1 : 0);
	}

	public Object getBoundService(int index) {
		if (!isBoundServiceActivated()) {
			System.err.println("The bound service must be active at the time of the bind operation");
			return null;
		}
		return service;
	}

	public ServiceReference getBoundServiceRef(int index) {
		return null;
	}

	public Dictionary getProperties() {
		return null;
	}

	public void bind(DynamicCircuit1 service) {
		this.service = service;
		boundObjectActivated = service.isActivated();
	}

	public void unbind(DynamicCircuit1 service) {
		this.service = null;
	}

	public boolean isBoundServiceActivated() {
		return boundObjectActivated;
	}

}
