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
package ywb.c.di;

import ywb.c.api.API;
import ywb.c.dao.GirlOfDayNamesDao;
import ywb.c.dao.NamesDao;
import ywb.c.dao.PhotoDao;

import com.google.gwt.search.client.SafeSearchValue;
import com.google.inject.Inject;

@SuppressWarnings("unchecked")
public class Context {

	private API api;
	private Storage storage;
	private UiEventBus eventBus;
	private UiComponents ui;
	private PhotoDao photoDao;
	private NamesDao namesDao;
	private GirlOfDayNamesDao girlDayDao;
	private boolean isDevMode = false;
	private SafeSearchValue safeSearcrh = SafeSearchValue.STRICT;

	@Inject
	public Context(UiEventBus eventBus_, Storage storage_, API api_, PhotoDao photoDao_, NamesDao namesDao_,
			GirlOfDayNamesDao girlDayDao_) {
		this.eventBus = eventBus_;
		this.api = api_;
		this.storage = storage_;
		this.photoDao = photoDao_;
		this.namesDao = namesDao_;
		this.girlDayDao = girlDayDao_;
		//
		this.api.init(this);
		this.photoDao.init(this);
		this.namesDao.init(this);
		this.girlDayDao.init(this);
	}

	public boolean isDevMode() {
		return isDevMode;
	}

	public void setDevMode(boolean isDevMode) {
		this.isDevMode = isDevMode;
	}

	public API getApi() {
		return api;
	}

	public Storage getStorage() {
		return storage;
	}

	public UiEventBus getEventBus() {
		return eventBus;
	}

	public PhotoDao getPhotoDao() {
		return photoDao;
	}

	public NamesDao getNamseDao() {
		return namesDao;
	}

	public UiComponents getUi() {
		return ui;
	}

	public void initUi(UiComponents ui) {
		this.ui = ui;
	}

	public SafeSearchValue getSafeSearcrh() {
		return safeSearcrh;
	}

	public void setSafeSearcrh(SafeSearchValue safeSearcrh) {
		this.safeSearcrh = safeSearcrh;
	}

	public GirlOfDayNamesDao getGirlDayDao() {
		return girlDayDao;
	}

}
