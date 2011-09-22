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

import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.Response;

public abstract class JsonArrayStringReader extends ResponseReader {

	@Override
	public String getName() {
		return "JsonArrayStringReader";
	}

	@Override
	public void onSuccess(Response response) {
		String txt = response.getText();
		String escapedJSON = JsonUtils.escapeJsonForEval(txt);
		JsArrayString j = JsonUtils.safeEval(escapedJSON);
		ArrayList<String> msgs = JsUtils.toListString(j);
		read(msgs);
	}

	abstract public void read(ArrayList<String> data);

}
