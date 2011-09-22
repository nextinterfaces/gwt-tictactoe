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

import ywb.c.di.Context;
import ywb.c.di.UiComponents;
import ywb.c.di.UiEventBus;
import ywb.c.di.UiGinjector;
import ywb.c.di.Storage.JsStorageStrategy;
import ywb.c.event.AzNameEvent;
import ywb.c.event.AzNameHandler;
import ywb.c.event.BackButtonEvent;
import ywb.c.event.BackButtonHandler;
import ywb.c.event.FrontEvent;
import ywb.c.event.FrontHandler;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

public class Main implements EntryPoint {

	public void onModuleLoad() {
		init();
	}

	public void init() {
		Log.info("starting app");
		final RootPanel rootPanel = RootPanel.get("root");
		rootPanel.setVisible(false);

		final UiGinjector ginjector = UiGinjector.INSTANCE;
		ginjector.getStorage().init(new JsStorageStrategy());

		// init and make it visible
		// ginjector.getUiComponents().getLeftMenu().show();
		ginjector.getUiComponents().getTopMenu().doShow();
		ginjector.getUiComponents().getFrontPage().doShow();
		// ginjector.getUiComponents().getTopMenu().getBackCommand().execute();
		// ginjector.getUiComponents().getSubMenuPage();
		// ginjector.getEventBus().fireEvent(new BackButtonEvent(Type.FRONT_PAGE));

		rootPanel.setVisible(true);

		RootPanel loadingPanel = RootPanel.get("loading");
		loadingPanel.setVisible(false);

		if ("8888".equals(Window.Location.getPort())) {
			ginjector.getContext().setDevMode(true);
		}

		Context ctx = ginjector.getContext();
		listenEvents(ctx);

//		ArrayList<String> america = ctx.getNamseDao().getNames(Continent.NORTH_AMERICA);
//		Iterator<String> it = america.iterator();
//		loadCountry(it, ctx);
	}

//	void loadCountry(final Iterator<String> it, final Context ctx) {
//		if (!it.hasNext()) {
//			return;
//		}
//
//		String country = it.next();
//		if (country.contains("<div")) {
//			loadCountry(it, ctx);
//		}
//
//		WikipediaDao.get(country, ctx, new Response<String[]>() {
//			@Override
//			public void read(String[] data) {
//				loadCountry(it, ctx);
//			}
//		});
//	}

	void listenEvents(Context ctx) {

		final UiEventBus eventBus = ctx.getEventBus();
		final UiComponents ui = ctx.getUi();

		// ui.getTopMenu().doHide();

		eventBus.addHandler(FrontEvent.TYPE, new FrontHandler() {
			@Override
			public void onExecute(FrontEvent e) {
				System.out.println("FrontEvent::" + e.getContinent());
				ui.getTopMenu().animateBackBtn();
				ui.getFrontPage().doHide();
				ui.getAzPage().setContinent(e.getContinent());
				ui.getAzPage().doShow();
				ui.getTopMenu().setContinent(e.getContinent());
			}
		});

		eventBus.addHandler(AzNameEvent.TYPE, new AzNameHandler() {
			@Override
			public void onExecute(AzNameEvent e) {
				// ui.getAzNamePage().setSearchName(e.getName());
				// ui.getAzNamePage().doShow();
				ui.getTopMenu().setCountry(e.getName());
				// ui.getTopMenu().animateBackBtn();
				ui.getWikiPage().setCountry(e.getName());
				ui.getWikiPage().doShow();
				ui.getBrandingPage().setCountry(e.getName());
				ui.getBrandingPage().doShow();
			}
		});

		eventBus.addHandler(BackButtonEvent.TYPE, new BackButtonHandler() {
			@Override
			public void onExecute(BackButtonEvent e) {
				// ui.getAzNamePage().setSearchName(e.getName());
				// ui.getAzNamePage().doShow();
				ui.getFrontPage().doShow();
				ui.getTopMenu().setContinent(null);
			}
		});

	}
}
