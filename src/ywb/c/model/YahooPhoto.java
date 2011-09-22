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

import com.google.gwt.core.client.JavaScriptObject;

public interface YahooPhoto {

	String getUrl();
	
	String getThumbUrl();
	
	String getThumbWidth();
	
	String getThumbHeight();

	/**
	 * JSO_ implementation
	 */
	class JSO_ extends JavaScriptObject implements YahooPhoto {

		protected JSO_() {

		}

		@Override
		public final native String getUrl() /*-{
			return this.Url;
		}-*/;

		@Override
		public final native String getThumbUrl() /*-{
			return this.Thumbnail.Url;
		}-*/;

		@Override
		public final native String getThumbWidth() /*-{
			return this.Thumbnail.Width;
		}-*/;

		@Override
		public final native String getThumbHeight() /*-{
			return this.Thumbnail.Height;
		}-*/;
	}

}