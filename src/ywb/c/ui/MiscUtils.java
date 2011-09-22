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
package ywb.c.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ywb.c.di.Context;
import ywb.c.model.Continent;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;

public class MiscUtils {

	public static interface CallBack {
		void onClick(String name);
	}

	public static String filterWiki(String wikiText) {
		int endInx = wikiText.indexOf("id=\"See_also\"");
		if (endInx > 0) {
			wikiText = wikiText.substring(0, endInx);
		}

		wikiText = wikiText.replace("<a ", "<span ");
		wikiText = wikiText.replace("</a>", "</span>");
		wikiText = wikiText.replace("[", "<!--");
		wikiText = wikiText.replace("]", "-->");

		return wikiText;
	}

	public static void populateNamesScrollPanel(Continent continent, final CallBack callback, FlowPanel nested,
			Context ctx) {

		nested.clear();

		for (final String name : ctx.getNamseDao().getNames(continent)) {
			if (name.startsWith("<div")) {
				HTML h = new HTML(name);
				h.setStyleName("yAzNameDivAZ");
				nested.add(h);
				continue;
			} else {

				String ctr = name;
				if (name.length() > 24) {
					ctr = name.substring(0, 24);
				}
				HTML h = new HTML("<span class='yAzName'>" + ctr + "</span>");
				h.setStyleName("yAzNameDiv");
				nested.add(h);
				h.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						callback.onClick(name);
					}
				});
			}
		}
	}

	public static int getTimeout() {
		int timeout = Math.abs(Random.nextInt(1000));
		if (timeout < 1) {
			return 1;
		}
		return timeout;
	}

	public static <T> ArrayList<T> toList(HashMap<String, T> map) {
		ArrayList<T> list = new ArrayList<T>();
		if (map == null) {
			return list;
		}

		for (String key : map.keySet()) {
			list.add(map.get(key));
		}
		return list;

	}

	public static String arrayToJsonStringArr(List<String> arr) {
		StringBuilder buff = new StringBuilder("[\"");
		int cnt = 0;
		int len = arr.size();
		for (String uid : arr) {
			buff.append(uid);
			if (cnt < len - 1) {
				buff.append("\", \"");
			}
			cnt++;
		}
		buff.append("\"]");

		return buff.toString();
	}

	public static String noNull(String str) {
		return noNull(str, "");
	}

	public static String noNull(String str, String default_) {
		if (null == str) {
			return default_;
		}
		return str;
	}

	public static boolean isEmpty(String str) {
		if (null == str || str.trim().length() < 1) {
			return true;
		}
		return false;
	}

	public static void initTextFields(final String infoTxt, final TextBox field) {

		field.addStyleName("info");
		field.setText(infoTxt);

		field.addFocusHandler(new FocusHandler() {
			@Override
			public void onFocus(FocusEvent event) {
				if (infoTxt.equals(field.getText())) {
					field.setText("");
					field.removeStyleName("info");
				}
			}
		});
		field.addBlurHandler(new BlurHandler() {
			@Override
			public void onBlur(BlurEvent event) {
				if ("".equals(field.getText())) {
					field.setText(infoTxt);
					field.addStyleName("info");
				}
			}
		});
	}

	public static void initScrollSize(ScrollPanel listScrollPanel) {
		int topBottomMargins = 80;
		int headerHeight = 52;
		int profileHeight = 140;
		int tableHeight = Window.getClientHeight() - topBottomMargins;
		int contentHeight = tableHeight - headerHeight;
		listScrollPanel.setSize("100%", (contentHeight - profileHeight - 48) + "px");
	}

	public native static void disableTextSelectInternal(Element e, boolean disable)/*-{
		if (disable) {
			e.ondrag = function() {
				return false;
			};
			e.onselectstart = function() {
				return false;
			};
			e.style.MozUserSelect = "none"
		} else {
			e.ondrag = null;
			e.onselectstart = null;
			e.style.MozUserSelect = "text"
		}
	}-*/;

	public static void main(String[] args) {
		String url = "http://www.himho.com/christina-aguilera-national-anthem-super-bowl/";
		url = url.substring(url.indexOf("://") + 3);
		System.out.println(url);
	}

}
