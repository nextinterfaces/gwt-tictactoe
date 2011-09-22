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

import ywb.c.JsUtils;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;

public interface Photo {

	String getOriginalContextUrl();

	String getUrl();

	void setUrl(String x);

	String getThumbUrl();

	void setThumbUrl(String x);

	int getWidth();

	void setWidth(int x);

	int getHeight();

	void setHeight(int x);

	int getThumbWidth();

	void setThumbWidth(int x);

	int getThumbHeight();

	void setThumbHeight(int x);

	String json();

	/**
	 * JSO_ implementation
	 */
	class JSO_ extends JavaScriptObject implements Photo {

		protected JSO_() {
		}

		public final native String getUrl() /*-{
			return this.url;
		}-*/;

		public final native String getThumbUrl() /*-{
			return this.thumbUrl;
		}-*/;

		public final native int getThumbWidth() /*-{
			return this.thumbWidth;
		}-*/;

		public final native int getThumbHeight() /*-{
			return this.thumbHeight;
		}-*/;

		public final native int getWidth() /*-{
			return this.width;
		}-*/;

		public final native int getHeight() /*-{
			return this.height;
		}-*/;

		public final native String getOriginalContextUrl() /*-{
			return this.originalContextUrl;
		}-*/;

		public final native void setUrl(String x) /*-{
			this.url = x;
		}-*/;

		public final native void setThumbUrl(String x) /*-{
			this.thumbUrl = x;
		}-*/;

		public final native void setThumbWidth(int x) /*-{
			this.thumbWidth = x;
		}-*/;

		public final native void setThumbHeight(int x) /*-{
			this.thumbHeight = x;
		}-*/;

		public final native void setWidth(int x) /*-{
			this.width = x;
		}-*/;

		public final native void setHeight(int x) /*-{
			this.height = x;
		}-*/;

		public final native void setOriginalContextUrl(String x) /*-{
			this.originalContextUrl = x;
		}-*/;

		@Override
		public final String json() {
			return toJSONObject().toString();
		}

		public final JSONObject toJSONObject() {
			JSONObject jso = JsUtils.toJSONObject("url", getUrl(), "thumbUrl", getThumbUrl(), "width", getWidth(), "height",
					getHeight(), "thumbWidth", getThumbWidth(), "thumbHeight", getThumbHeight(), "originalContextUrl",
					getOriginalContextUrl());
			return jso;
		}
	}

	class Bean implements Photo {

		String url;
		String thumbUrl;
		int thumbWidth;
		int thumbHeight;
		int width;
		int height;
		String originalContextUrl;

		public Bean(String url_, String thumbUrl_, int width_, int height_, int thumbWidth_, int thumbHeight_,
				String originalContextUrl_) {

			this.url = url_;
			this.thumbUrl = thumbUrl_;
			this.thumbWidth = thumbWidth_;
			this.thumbHeight = thumbHeight_;
			this.width = width_;
			this.height = height_;
			this.originalContextUrl = originalContextUrl_;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getThumbUrl() {
			return thumbUrl;
		}

		public void setThumbUrl(String thumbUrl) {
			this.thumbUrl = thumbUrl;
		}

		public int getThumbWidth() {
			return thumbWidth;
		}

		public void setThumbWidth(int thumbWidth) {
			this.thumbWidth = thumbWidth;
		}

		public int getThumbHeight() {
			return thumbHeight;
		}

		public void setThumbHeight(int thumbHeight) {
			this.thumbHeight = thumbHeight;
		}

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public String getOriginalContextUrl() {
			return originalContextUrl;
		}

		public void setOriginalContextUrl(String originalContextUrl) {
			this.originalContextUrl = originalContextUrl;
		}

		@Override
		public String json() {
			return 
				"{" + "\"url\": \"" + getUrl() + "\", " + 
				"\"thumbUrl\": \"" + getThumbUrl() + "\", " + 
				"\"width\": "	+ getWidth() + ", " + 
				"\"height\": " + getHeight() + ", " + 
				"\"thumbWidth\": " + getThumbWidth() + ", "	+ 
				"\"originalContextUrl\": " + getOriginalContextUrl() + ", "	+ 
				"\"thumbHeight\": " + getThumbHeight() + " " + "}";
		}

	}

}