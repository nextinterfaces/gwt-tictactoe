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
package ywb.c.page;

import org.adamtacy.client.ui.effects.core.NMorphStyle;

import ywb.c.FxUtil;
import ywb.c.UiResources;
import ywb.c.api.API.Response;
import ywb.c.dao.WikipediaDao;
import ywb.c.di.Context;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;

public class WikiPage extends PopupPanel implements IPage {

	final static UiResources RES = UiResources.INSTANCE;
	private boolean isHidden = true;
	final int POSITION_X = 330;
	final int POSITION_Y = 88;

	private String country;

	private Context ctx;

	public WikiPage(Context ctx_) {
		this.ctx = ctx_;
		setStyleName("yWikiPage");
		setWidth("677px");

		setPopupPosition(POSITION_X, POSITION_Y);

//		setWidget(new HTML("ads"));
		show();
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;

		final WikiWidget wikiWidget = new WikiWidget(country);
		setWidget(wikiWidget);

		WikipediaDao.get(country, ctx, new Response<String[]>() {
			@Override
			public void read(String[] wikiTexts) {
				wikiWidget.loadContent(wikiTexts);
			}
		});
	}

	@Override
	public void doHide() {
		ctx.getUi().getBrandingPage().doHide();
		if (isHidden) {
			return;
		}

		NMorphStyle eff = FxUtil.doHide(this, null);
		eff.play();

		isHidden = true;
	}

	@Override
	public void doShow() {
		ctx.getUi().getBrandingPage().doShow();
		if (!isHidden) {
			return;
		}
		setPopupPosition(POSITION_X, POSITION_Y);

		NMorphStyle eff = FxUtil.doShow(this, null);
		eff.play();

		isHidden = false;
	}

	@Override
	public void doClear() {
		clear();
		// wikiHMTL.setText("");
	}

}
