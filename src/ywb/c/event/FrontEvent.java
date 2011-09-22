/*
 * Copyright 2011 Vancouver Ywebb Consulting Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package ywb.c.event;

import ywb.c.model.Continent;

import com.google.gwt.event.shared.GwtEvent;

public class FrontEvent extends GwtEvent<FrontHandler> {

	public static final GwtEvent.Type<FrontHandler> TYPE = new GwtEvent.Type<FrontHandler>();

	// public static enum Type {
	// AFRICA, NORTH_AMERICA, SOUTH_AMERICA, EUROPE, ASIA, AUSTRALIA, ANTARCTICA
	// }
	//
	private Continent continent;

	public FrontEvent(Continent continent) {
		this.continent = continent;
	}

	// public Type getType() {
	// return type;
	// }

	@Override
	protected void dispatch(FrontHandler handler) {
		handler.onExecute(this);
	}

	public Continent getContinent() {
		return continent;
	}

	@Override
	public GwtEvent.Type<FrontHandler> getAssociatedType() {
		return TYPE;
	}

}
