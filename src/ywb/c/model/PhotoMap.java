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
package ywb.c.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ywb.c.JsUtils;
import ywb.c.Log;
import ywb.c.di.Storage;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;

@SuppressWarnings( { "unchecked", "serial" })
public class PhotoMap extends HashMap<String, Photo> {

	private Storage storage;
	private Key key;

	public PhotoMap(Key key, Storage storage) {
		super();
		this.storage = storage;
		this.key = key;
	}

	@Override
	public Photo put(String id, Photo photo) {
//		Log.info("PhotoMap::put '" + id + " '");
		super.put(photo.getUrl(), photo);
//		save();
		return photo;
	}

	public Key getKey() {
		return key;
	}

	@Override
	public void putAll(Map<? extends String, ? extends Photo> m) {
		throw new RuntimeException("PhotoMap::Not implemented");
	}

	@Override
	public Photo remove(Object key) {
		Log.info("PhotoMap::remove " + key);
		Photo up = super.remove(key);
		save();
		return up;
	}

	@Override
	public void clear() {
		Log.info("PhotoMap::clear");
		storage.removeItem(getKey().getName());
		super.clear();
	}

	public void load() {
		Log.info("PhotoMap::load");
		super.clear();
		if (storage.hasKey(getKey().getName())) {
			HashMap<String, Photo> map = getStorageMap(storage, getKey());
			for (String id : map.keySet()) {
				Photo u = map.get(id);
				super.put(u.getUrl(), u);
			}
		}
	}

	public void save() {
		Log.info("PhotoMap::save");
		storage.setItem(getKey().getName(), json());
	}

	public String json() {
		return toJSON(this);
	}

	// ---------------------
	/**
	 * STATIC methods
	 */
	public static String toJSON(HashMap<String, Photo> profiles) {

		StringBuilder buff = new StringBuilder("[");
		boolean started = false;
		for (String key : profiles.keySet()) {
			if (started) {
				buff.append(",");
			}
			Photo p = profiles.get(key);
			buff.append(p.json());
			started = true;
		}
		buff.append("]");

		return buff.toString();
	}

	public static HashMap<String, Photo> getStorageMap(Storage storage, Key key) {
		Log.info("::getFromStorage");
		String json = storage.getItem(key.getName());
		HashMap map = new HashMap<String, Photo>();
		if (json == null || json.trim().length() < 1) {
			return map;
		}
		JsArray<? extends JavaScriptObject> j = JsonUtils.safeEval(json);
		ArrayList<Photo> data = JsUtils.toArray(j);
		for (Photo u : data) {
			map.put(u.getUrl(), u);
		}
		return map;
	}

	// ---------------------
	/**
	 * JSO_ implementation
	 */
	public static class JSO_ extends JavaScriptObject {

		protected JSO_() {
		}

		public final native Photo get(String id_) /*-{
			var photo = this[id_];
			//photo.url = id_;
			return photo;
		}-*/;
	}

}
