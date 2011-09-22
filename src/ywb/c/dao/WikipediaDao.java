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

import ywb.c.Log;
import ywb.c.api.ResponseReader;
import ywb.c.api.API.Response;
import ywb.c.di.Context;
import ywb.c.di.Storage;
import ywb.c.ui.MiscUtils;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.URL;

public class WikipediaDao {

	public static void get(final String wikiTitle, Context ctx, final Response<String[]> response) {

		String devUrl = "http://127.0.0.1:8888/zz_wiki.html";
		String prodUrl = "http://en.wikipedia.org/w/index.php?action=render&title=" + wikiTitle;

		final Storage storage = ctx.getStorage();

		if (storage.hasKey(wikiTitle)) {
			String result = storage.getItem(wikiTitle);
			Log.info("WikipediaDao::get from Storage '" + wikiTitle + "'");

			String[] data = new String[] { result, result };
			response.read(data);

		} else {

			Log.info("WikipediaDao::get from Wikipedia '" + wikiTitle + "'");
			String url = ctx.isDevMode() ? devUrl : prodUrl;

			getWiki(url, new ResponseReader() {
				@Override
				public void onSuccess(com.google.gwt.http.client.Response resp) {
					String fullText = resp.getText();

					// int beginInx = fullText.indexOf("<p>");
					// int endInx = fullText.indexOf("<table id=\"toc\"");
					// // Window.alert("beginInx=" + beginInx + ", endInx=" + endInx);
					// String result = fullText.substring(beginInx, endInx);
					// Window.alert("[" + result + "]");
					String result = fullText;
					result = MiscUtils.filterWiki(result);

					String[] data = new String[] { result, result };
					response.read(data);
					storage.setItem(wikiTitle, result);
				}

				@Override
				public String getName() {
					return null;
				}
			});

		}
	}

	public static void getWiki(String url, ResponseReader responseReader) {

		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
		try {
			builder.sendRequest(null, new Callback_(responseReader));
		} catch (RequestException e) {
			Log.error("RequestException: " + e.getMessage());
			responseReader.onError(null, e);
		}
	}

	private static class Callback_ implements RequestCallback {
		ResponseReader reader;

		public Callback_(ResponseReader reader) {
			this.reader = reader;
		}

		public void onError(Request request, Throwable e) {
			Log.warn("RequestCallback::onError");
			reader.onError(null, e);
		}

		@Override
		public void onResponseReceived(Request request, com.google.gwt.http.client.Response response) {
			if (200 == response.getStatusCode()) {
				reader.onSuccess(response);
			} else {
				Log.warn("RequestCallback::onResponseReceived, not supported status '" + response.getStatusCode() + "'");
				reader.onError(response, null);
			}
		}
	}

}
