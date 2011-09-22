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
package ywb.c.api;

import ywb.c.Log;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;

public class HTTP {

	public static void doPost(String url, String postData, ResponseReader reader) {

		RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, URL.encode(url));
		builder.setHeader("Content-Type", "application/x-www-form-urlencoded");
		try {
			builder.sendRequest(postData, new Callback_(reader));
		} catch (RequestException e) {
			reader.onError(null, e);
		}
	}

	public static void doPostJSON(String url, String jsonData, ResponseReader reader) {

		RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, URL.encode(url));
		builder.setHeader("Content-Type", "application/json");
		try {
			builder.sendRequest(jsonData, new Callback_(reader));
		} catch (RequestException e) {
			reader.onError(null, e);
		}
	}

	public static void doGet(String url, ResponseReader responseReader) {

		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));

		try {
			builder.sendRequest(null, new Callback_(responseReader));
		} catch (RequestException e) {
			Window.alert("doGet:exc " + e.getMessage());
			Log.error("RequestException: " + e.getMessage());
			responseReader.onError(null, e);
		}
	}

	public static void doGetRelaxed(String url, ResponseReader responseReader) {

		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
		try {
			builder.sendRequest(null, new RelaxedCallback_(responseReader));
		} catch (RequestException e) {
			responseReader.onError(null, e);
		}
	}

	public static void doDelete(String url, String postData, ResponseReader reader) {

		RequestBuilder builder = new RequestBuilder(RequestBuilder.DELETE, URL.encode(url));
		try {
			builder.sendRequest(postData, new Callback_(reader));
		} catch (RequestException e) {
			reader.onError(null, e);
		}
	}

	public static void doDeleteJSON(String url, String jsonData, ResponseReader reader) {
		RequestBuilder builder = new RequestBuilder(RequestBuilder.DELETE, URL.encode(url));
		builder.setHeader("Content-Type", "application/json");
		try {
			builder.sendRequest(jsonData, new Callback_(reader));
		} catch (RequestException e) {
			reader.onError(null, e);
		}
	}

	public static void doPut(String url, String postData, ResponseReader reader) {

		RequestBuilder builder = new RequestBuilder(RequestBuilder.PUT, URL.encode(url));
		builder.setHeader("Content-Type", "application/x-www-form-urlencoded");
		try {
			builder.sendRequest(postData, new Callback_(reader));
		} catch (RequestException e) {
			reader.onError(null, e);
		}
	}

	public static void doPutJSON(String url, String jsonData, ResponseReader reader) {

		RequestBuilder builder = new RequestBuilder(RequestBuilder.PUT, URL.encode(url));
		builder.setHeader("Content-Type", "application/json");
		try {
			builder.sendRequest(jsonData, new Callback_(reader));
		} catch (RequestException e) {
			reader.onError(null, e);
		}
	}

	/**
	 * Wrapper for request callback invoking ResponseReader strategy. This is a strict Callback invoking ::onSuccess only
	 * on 200 OK response.
	 */
	public static class Callback_ implements RequestCallback {
		ResponseReader reader;

		public Callback_(ResponseReader reader) {
			this.reader = reader;
		}

		public void onError(Request request, Throwable e) {
			Log.warn("RequestCallback::onError");
//			reader.onError(null, e);
		}

		public void onResponseReceived(Request request, Response response) {
			if (200 == response.getStatusCode()) {
				Window.alert("success ");
			reader.onSuccess(response);
			} else {
				Log.warn("RequestCallback::onResponseReceived, not supported status '" + response.getStatusCode() + "'");
//				reader.onError(response, null);
				Window.alert("RequestCallback::onResponseReceived, not supported status '" + response.getStatusCode() + "'");
			}
		}
	}

	/**
	 * A relaxed callback invoking ResponseReader strategy
	 */
	private static class RelaxedCallback_ implements RequestCallback {
		ResponseReader reader;

		public RelaxedCallback_(ResponseReader reader) {
			this.reader = reader;
		}

		public void onError(Request request, Throwable e) {
			reader.onError(null, e);
		}

		public void onResponseReceived(Request request, Response response) {
			reader.onSuccess(response);
		}
	}

}
