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


import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;

public abstract class ResponseReader {

	public abstract void onSuccess(Response response);

	public abstract String getName();

	public void onError(Response response, Throwable e) {
		if (e != null) {
			Window.alert("ResponseReader::onError " + e.getMessage());
			throw new RuntimeException(getName() + " error:" + e);
		}

		if (response != null) {
			int status = response.getStatusCode();

			if (status > 399 && status < 500) {
				String txt = response.getText() != null ? response.getText().trim() : "";
				throw new BusinessException(status, getName() + " error: " + status + ", response \"" + txt + "\"");

			} else {
				String txt = response.getText() != null ? response.getText().trim() : "";
				throw new RuntimeException(getName() + " error: " + status + ", response \"" + txt + "\"");
			}

		} else {
			throw new RuntimeException(getName() + " error: ");
		}
	}

}
