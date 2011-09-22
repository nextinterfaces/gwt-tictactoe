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

import ywb.c.JsUtils;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;

public abstract class JsonArrayReader<T, J extends JavaScriptObject> extends ResponseReader {

	@Override
	public String getName() {
		return "JsonArrayReader";
	}

	@Override
	public void onSuccess(Response response) {
		String txt = response.getText();
		Window.alert("1 succss '" + txt + "'");
		String escapedJSON = JsonUtils.escapeJsonForEval(txt);
		Window.alert("2 succss '" + escapedJSON + "'");
		JsArray<J> j = JsonUtils.safeEval(escapedJSON);
		Window.alert("3 succss '" + j + "'");
		ArrayList<T> msgs = JsUtils.toArray(j);
		Window.alert("4 msgs '" + msgs + "'");
		read(msgs, escapedJSON);
	}

	abstract public void read(ArrayList<T> data, String jsonData);

}
