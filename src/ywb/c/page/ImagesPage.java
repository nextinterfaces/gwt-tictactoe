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
import ywb.c.Globals;
import ywb.c.UiResources;
import ywb.c.di.Context;
import ywb.c.model.Key;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;

public class ImagesPage extends PopupPanel implements IPage {

	final static UiResources RES = UiResources.INSTANCE;
	private boolean isHidden = true;

	private SearchImageWidget searchWidget;

	private final Context ctx;
	private Key searchKey;

	public ImagesPage(Context ctx_) {
		this.ctx = ctx_;
		setStyleName("yAzPage yPopupPage");
		setWidth("680px");

		setPopupPosition(336, 88);
		show();

		FlowPanel namesP = new FlowPanel();

		ScrollPanel scrollPanel = new ScrollPanel(namesP);
		scrollPanel.setSize(Globals.WRAPPER_WIDTH + "px", Globals.WRAPPER_HEIGHT + "px");

		setWidget(scrollPanel);
	}

	public Key getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(Key searchKey) {
		this.searchKey = searchKey;
	}

	@Override
	public void doHide() {
		ctx.getUi().getBrandingPage().doHide();
		doClear();
		if (isHidden) {
			return;
		}

		NMorphStyle eff = FxUtil.doHide(this, null);
		// eff.addEffectCompletedHandler(new EffectCompletedHandler() {
		// @Override
		// public void onEffectCompleted(EffectCompletedEvent event) {
		// setPopupPosition(1025, 0);
		// }
		// });
		eff.play();

		isHidden = true;
	}

	@Override
	public void doShow() {
		if (searchWidget == null) {
			System.out.println("::doShow " + getSearchKey());
			searchWidget = new SearchImageWidget(getSearchKey(), 2, 10, ctx);
			setWidget(searchWidget);
		}
		if (!isHidden) {
			return;
		}
		// this.getElement().getStyle().setOpacity(0);
		setPopupPosition(336, 88);
		NMorphStyle eff = FxUtil.doShow(this, null);
		eff.play();

		isHidden = false;

		ctx.getUi().getBrandingPage().doShow();
	}

	@Override
	public void doClear() {
		System.out.println("::doClear");
		if (searchWidget != null) {
			searchWidget.removeFromParent();
			searchWidget = null;
		}
	}

}
