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
package ywb.c.dao;

import java.util.HashMap;

import ywb.c.Log;
import ywb.c.api.API;
import ywb.c.api.API.Response;
import ywb.c.di.Context;
import ywb.c.di.Storage;
import ywb.c.model.Key;
import ywb.c.model.Photo;
import ywb.c.model.PhotoMap;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.search.client.ImageResult;

public class PhotoDao {

	private Storage storage;
	private API api;
	private Context ctx;

	HashMap<Key, PhotoMap> searchedMaps = new HashMap<Key, PhotoMap>();

	private PhotoMap getPhotoMap(Key searchKey) {
		PhotoMap pm = searchedMaps.get(searchKey);
		if (pm == null) {
			pm = new PhotoMap(searchKey, storage);
			searchedMaps.put(searchKey, pm);
		}
		return pm;
	}

	public void getPhotos(final Key searchKey, int numberPages, final Response<PhotoMap> response) {

//		if (storage.hasKey(searchKey)) {
//			String json = storage.getItem(searchKey);
//			Log.info("PhotoDao::getPhotos from Storage '"+ json + "'");
//			JsArray<Photo.JSO_> jsoArr = JsonUtils.safeEval(json);
//			int len = jsoArr.length();
//			PhotoMap pm = getPhotoMap(searchKey);
//			for (int i = 0; i < len; i++) {
//				Photo p = jsoArr.get(i);
//				pm.put(p.getUrl(), p);
//			}
//			response.read(pm);
//
//		} else {
			Log.info("PhotoDao::getPhotos from API");
			api.getPhotos(searchKey, numberPages, new Response<JsArray<ImageResult>>() {
				@Override
				public void read(JsArray<ImageResult> data) {
					int len = data.length();

					PhotoMap pm = getPhotoMap(searchKey);
					PhotoMap currentMap = new PhotoMap(searchKey, storage);
					for (int i = 0; i < len; i++) {
						ImageResult ir = data.get(i);
						Photo p = new Photo.Bean(ir.getUrl(), ir.getThumbnailUrl(), ir.getWidth(), ir.getHeight(), ir
								.getThumbnailWidth(), ir.getThumbnailHeight(), ir.getOriginalContextUrl());
						pm.put(p.getUrl(), p);
						currentMap.put(p.getUrl(), p);
					}
					pm.save();
					response.read(currentMap);
				}

				@Override
				public void afterRead(String jsonData) {
					// storage.setItem(Key.Countries, jsonData);
					// Log.error("AFTER storing countries " + storage.getItem(Key.Country));
				}
			});
//		}
	}

	public void clear() {
		Log.info("PhotoDao::clear");
		searchedMaps.clear();
		storage.clear();
	}

	private final native String toJSON(JavaScriptObject jso) /*-{
		return jso.toString();
	}-*/;

	public void init(Context ctx) {
		this.storage = ctx.getStorage();
		this.api = ctx.getApi();
		this.ctx = ctx;
	}

}
