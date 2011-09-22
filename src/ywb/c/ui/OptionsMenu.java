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

import ywb.c.UiResources;
import ywb.c.api.API.Response;
import ywb.c.dao.WikipediaDao;
import ywb.c.di.Context;
import ywb.c.page.WikiWidget;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;

public class OptionsMenu extends FlowPanel {

	private final Context ctx;
	final static UiResources RES = UiResources.INSTANCE;
	
	OptionsMenu(final String celebName, Context ctx_, String... optons) {
		this.ctx = ctx_;
		final TextBox textBox = new TextBox();

		ArrayList<HTML> arr = new ArrayList<HTML>();
		for (String op : optons) {
			HTML opHTML = new HTML("<span class='yOption'>" + op + "</span>");
			arr.add(opHTML);
			this.add(opHTML);
			initClickHandler(opHTML, arr);
		}
		SimplePanel textWrapper = new SimplePanel();
		textWrapper.setStyleName("gwt-HTML");
		textWrapper.add(textBox);
		this.add(textWrapper);

		textBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				String txt = MiscUtils.noNull(textBox.getText());
				if (txt.length() > 2) {
//					ctx.getEventBus().fireEvent(new SubMenuEvent(getEventType(), txt));
				}
			}
		});

//		Type et = getEventType();
//		if (et == Type.REDCARPET_OSCARS || et == Type.REDCARPET_GOLDEN || et == Type.REDCARPET_GRAMMY
//				|| et == Type.REDCARPET_CANNES) {
//			FlowPanel namesP = new FlowPanel();
//			ScrollPanel scrollPanel = new ScrollPanel(namesP);
//			this.add(scrollPanel);
//			initRedcarpetScroll(scrollPanel, namesP);
//
//		} else if (et == Type.WALLPAPER) {
//			FlowPanel namesP = new FlowPanel();
//			ScrollPanel scrollPanel = new ScrollPanel(namesP);
//			this.add(scrollPanel);
//			initWallpaperScroll(scrollPanel, namesP);
//
//		} else if (et == Type.FEEL_LUCKY || et == Type.AZ) {
			SimplePanel wikiWrapper = new SimplePanel();
			ImageButton wikiBtn = new ImageButton(RES.wiki());
			wikiBtn.addMouseDownHandler(new MouseDownHandler() {
				@Override
				public void onMouseDown(MouseDownEvent event) {
					final WikiWidget popup = new WikiWidget(celebName);
//					popup.doShow();
					WikipediaDao.get(celebName, ctx, new Response<String[]>() {
						@Override
						public void read(String[] wikiTexts) {
							popup.loadContent(wikiTexts);
						}
					});
				}
			});

			wikiWrapper.addStyleName("yWikiButton");
			wikiWrapper.add(wikiBtn);
			this.add(wikiWrapper);
//		}
	}

//	void initWallpaperScroll(ScrollPanel scrollPanel, FlowPanel nested) {
//
//		scrollPanel.setSize("100%", "410px");
//		MiscUtils.populateNamesScrollPanel(new CallBack() {
//			@Override
//			public void onClick(String name) {
//				ctx.getEventBus().fireEvent(new SubMenuEvent(getEventType(), name));
//			}
//		}, nested, ctx);
//	}

//	void initRedcarpetScroll(ScrollPanel scrollPanel, FlowPanel nested) {
//
//		scrollPanel.setSize("100%", "210px");
//		MiscUtils.populateNamesScrollPanel(new CallBack() {
//			@Override
//			public void onClick(String name) {
//				ctx.getEventBus().fireEvent(new SubMenuEvent(getEventType(), name));
//			}
//		}, nested, ctx);
//	}

	void initClickHandler(final HTML op, final ArrayList<HTML> options) {
		op.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
//				ctx.getEventBus().fireEvent(new SubMenuEvent(getEventType(), op.getText()));
				for (HTML html : options) {
					html.removeStyleName("selected");
				}
				op.addStyleName("selected");
			}
		});
	}
}