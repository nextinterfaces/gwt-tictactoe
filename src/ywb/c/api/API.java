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

import java.util.ArrayList;

import ywb.c.Log;
import ywb.c.api.HTTP.Callback_;
import ywb.c.di.Context;
import ywb.c.model.Key;
import ywb.c.model.YahooPhoto;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.search.client.ImageResult;
import com.google.gwt.search.client.ImageSearch;
import com.google.gwt.search.client.ImageTypeValue;
import com.google.gwt.search.client.ResultSetSize;
import com.google.gwt.search.client.SearchResultsHandler;
import com.google.gwt.search.client.SearchUtils;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;

public class API {

	// private HashMap<String, ImageSearch> hashSearches = new HashMap<String, ImageSearch>();

	public static abstract class Response<T> {

		public abstract void read(T data);

		public void afterRead(String jsonData) {
			// Override if jsonData is required
		}

		public void onError(int statusCode, String response) {
			// handler code for error capture
		}
	}

	public void getPhotos(final Key searchKey, final int numberPages, final Response<JsArray<ImageResult>> response) {

		final SearchResultsHandler resultHandler = new SearchResultsHandler() {
			@Override
			public void onSearchResults(SearchResultsEvent event) {
				JsArray<ImageResult> data = (JsArray<ImageResult>) event.getResults();
				response.read(data);
			}
		};

		SearchUtils.loadSearchApi(new Runnable() {
			public void run() {
				ImageSearch search = initImageSearch(resultHandler);
				search.execute(searchKey.getSearchQuery());
				int[] page = { 1 };
				gotoPage(page, numberPages, search);
			}
		});
	}

	private void gotoPage(final int[] page, final int maxPage, final ImageSearch search) {
		if (page[0] > maxPage) {
			return;
		}
		Timer t = new Timer() {
			public void run() {
				search.gotoPage(page[0]);
				Log.info("Opening page " + page[0]);
				page[0] = page[0] + 1;
				gotoPage(page, maxPage, search);
			}
		};

		t.schedule(300);
	}

	public ImageSearch initImageSearch(SearchResultsHandler resultsHandler) {
		// SearchControlOptions options = new SearchControlOptions();
		// We can use custom subclasses
		// options.add(new GoogleCodeWebSearch(), ExpandMode.OPEN);
		// Or configure inline
		// WebSearch ws = new WebSearch();
		// ws.setUserDefinedLabel("Ajaxian");
		// options.add(ws);
		// options.add(new NewsSearch());
		// options.add(new VideoSearch(), ExpandMode.CLOSED);

		final ImageSearch is = new ImageSearch();
		is.setNoHtmlGeneration();
		is.setImageType(ImageTypeValue.FACES);
		is.setSafeSearch(ctx.getSafeSearcrh());
		is.addSearchResultsHandler(resultsHandler);
		is.setResultSetSize(ResultSetSize.LARGE);
//		is.setQueryAddition("tbs=isch:1,iur:fc");
//		 is.setSiteRestriction("wikimedia.org");
//		 is.setSiteRestriction("wikipedia.org");
		// is.setResultSetSize(ResultSetSize.LARGE);
		// is.setColorization(ColorizationValue.BLACK_AND_WHITE);
		// is.setImageSize(ImageSizeValue.SMALL);
		return is;
	}

	public void getYahooImages(final Response<ArrayList<YahooPhoto>> response) {
		// JsonArrayReader<YahooPhoto, JsArray<? extends JavaScriptObject>> reader = new JsonArrayReader<YahooPhoto,
		// JsArray<? extends JavaScriptObject>>() {
		// @Override
		// public void read(ArrayList<YahooPhoto> data, String jsonData) {
		// response.read(data);
		// response.afterRead(jsonData);
		// }
		// };
		String url = "http://search.yahooapis.com/ImageSearchService/V1/imageSearch?query=victoria%2Bsecret&appid=rV3eqIDV34GjBZBX2fRuLJ5qkmKe2qyRXcN_ZtBJv0Eo6Uh7b9OUPx.MbRXI8C6u0xo-&results=5&output=json&";
		// String url =
		// "/yapi/ImageSearchService/V1/imageSearch?query=victoria%2Bsecret&appid=rV3eqIDV34GjBZBX2fRuLJ5qkmKe2qyRXcN_ZtBJv0Eo6Uh7b9OUPx.MbRXI8C6u0xo-&results=5&output=json&";
		// HTTP.doGet(url, null, reader);

		// String url = "http://127.0.0.1:8888/";
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);

		JsonArrayReader<YahooPhoto, JsArray<? extends JavaScriptObject>> reader = new JsonArrayReader<YahooPhoto, JsArray<? extends JavaScriptObject>>() {
			@Override
			public void read(ArrayList<YahooPhoto> data, String jsonData) {
				response.read(data);
				response.afterRead(jsonData);
			}
		};

		try {

			class ResponseReader2 extends ResponseReader {

				@Override
				public String getName() {
					return "ResponseReader2";
				}

				@Override
				public void onSuccess(com.google.gwt.http.client.Response resp) {
					Window.alert("succss: " + resp.getText());
				}
			}

			Window.alert("before GET '" + url + "'");
			builder.sendRequest(null, new Callback_(reader));
		} catch (RequestException e) {
			Window.alert("doGet:exc " + e.getMessage());
			Log.error("RequestException: " + e.getMessage());
			// responseReader.onError(null, e);
		}
	}

	// /**
	// * FIXME. This call doesn't work due to 302 response code not being supported by AJAX.
	// */
	// public void getPhoto(String userId, final Response<Photo> response) {
	// HTTP.doGet(Urls.PHOTOS.url(userId), null, new JsonReader<Photo>() {
	// @Override
	// public void read(Photo photo) {
	// response.read(photo);
	// }
	// });
	// }
	//
	// public void getCountries(final Response<ArrayList<Country>> response) {
	// JsonArrayReader<Country, JsArray<? extends JavaScriptObject>> reader = new JsonArrayReader<Country, JsArray<?
	// extends JavaScriptObject>>() {
	// @Override
	// public void read(ArrayList<Country> data, String jsonData) {
	// response.read(data);
	// response.afterRead(jsonData);
	// }
	// };
	// HTTP.doGet(Urls.COUNTRIES.url(), null, reader);
	// }

	private Context ctx;

	public void init(Context ctx) {
		this.ctx = ctx;
	}

}
