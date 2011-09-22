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
package ywb.c;

import java.util.ArrayList;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

public class JsUtils {

	@SuppressWarnings("unchecked")
	public static <J extends JavaScriptObject> J toJSO(String json) {
		String escapedJSON = JsonUtils.escapeJsonForEval(json);
		JavaScriptObject j = JsonUtils.safeEval(escapedJSON);
		return (J) j;
	}

	public static <J extends JavaScriptObject> JsArray<J> toJSOArray(String json) {
		String escapedJSON = JsonUtils.escapeJsonForEval(json);
		JsArray<J> j = JsonUtils.safeEval(escapedJSON);
		return j;
	}

	@SuppressWarnings("unchecked")
	public static <T> ArrayList<T> toArray(JsArray<? extends JavaScriptObject> jsArray) {
		int len = jsArray.length();
		ArrayList<T> list = new ArrayList<T>(len);
		for (int i = 0; i < len; i++) {
			list.add((T) jsArray.get(i));
		}
		return list;
	};

	public static <T extends JavaScriptObject> ArrayList<T> toList(JsArray<T> jsArr) {
		int len = jsArr.length();
		ArrayList<T> list = new ArrayList<T>(len);
		for (int i = 0; i < len; i++) {
			list.add(jsArr.get(i));
		}
		return list;
	}

	public static ArrayList<String> toListString(JsArrayString jsArr) {
		int len = jsArr.length();
		ArrayList<String> list = new ArrayList<String>(len);
		for (int i = 0; i < len; i++) {
			list.add(jsArr.get(i));
		}
		return list;
	}

	public static native String getUserAgent() /*-{
		return navigator.userAgent.toLowerCase();
	}-*/;

	public static native boolean canGeolocate() /*-{
		if (navigator.geolocation) {
		return true;
		}
		return false;
	}-*/;

	public static native String getGeoLocation() /*-{
		if (navigator.geolocation) {
		return navigator.geolocation.getCurrentPosition(
		function(position) {
		$wnd.__doEvent(position.coords.latitude + ',' + position.coords.longitude, 'LOCATION');
		},
		function(msg) {return msg;}
		);
		}
	}-*/;

	/**
	 * This method will cover similar scenarios: <br/>
	 * {"parentKey" : {"childKey": "ChildValue"}} <br/>
	 * Method is required to avoid NullPointers when ChildValue is NULL
	 */
	public static void putJSONObject(JSONObject parent, Object key, JSONObject child) {
		String _key = (String) key;
		if (child != null && !child.keySet().isEmpty()) {
			parent.put(_key, child);
		}
	}

	public static JSONObject toJSONObject(Object... args) {
		JSONObject jso = new JSONObject();

		if (args == null) {
			return jso;
		}

		int size = args.length;
		if (size % 2 != 0) {
			throw new IllegalArgumentException("Wrong 'toJson' arguments provided");
		}

		for (int i = 0; i < size; i++, i++) {
			Object key = args[i];
			Object val = args[i + 1];
			if (val == null || key == null) {
				// Log.warn("key=val is NULL '" + key + "'='" + val + "'. Skipping..");

			} else if (val instanceof String) {
				putString(jso, key, val);

			} else if (val instanceof Double) {
				putNumber(jso, key, (Double) val);

			} else if (val instanceof Integer) {
				putNumber(jso, key, ((Integer) val).doubleValue());

			} else if (val instanceof Boolean) {
				putBoolean(jso, key, (Boolean) val);
				
			} else if (val instanceof JSONObject) {
				putJSONObject(jso, key, (JSONObject) val);
			}
		}
		return jso;
	}

	public static JSONArray toJSONArray(ArrayList<String> values) {
		JSONArray arr = new JSONArray();
		int i = 0;
		for (String v : values) {
			arr.set(i, new JSONString(v));
			i++;
		}
		return arr;
	}

	public static void putString(JSONObject jso, Object key, Object val) {
		String _key = (String) key;
		String _val = (val != null) ? (String) val : "";
		jso.put(_key, new JSONString(_val));
	}

	public static void putNumber(JSONObject jso, Object key, Double val) {
		String _key = (String) key;
		jso.put(_key, new JSONNumber(val));
	}

	public static void putBoolean(JSONObject jso, Object key, Boolean val) {
		String _key = (String) key;
		jso.put(_key, JSONBoolean.getInstance(val));
	}

}
