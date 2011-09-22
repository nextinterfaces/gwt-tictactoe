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
import ywb.c.event.SubMenuEvent;
import ywb.c.event.SubMenuHandler;
import ywb.c.event.SubMenuEvent.Type;
import ywb.c.model.Key;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;

public class AzNamePage extends PopupPanel implements IPage {

	final static UiResources RES = UiResources.INSTANCE;
	private boolean isHidden = true;

	private SearchImageWidget searchWidget;

	private final Context ctx;
	private String searchName;

	public AzNamePage(Context ctx_) {
		this.ctx = ctx_;
		setStyleName("yAzPage yPopupPage");
		setWidth("680px");

		this.getElement().getStyle().setOpacity(0);
		setPopupPosition(336, 88);
		show();

		FlowPanel namesP = new FlowPanel();

		ScrollPanel scrollPanel = new ScrollPanel(namesP);
		scrollPanel.setSize(Globals.WRAPPER_WIDTH + "px", Globals.WRAPPER_HEIGHT + "px");

		setWidget(scrollPanel);

		listenSubMenu();
	}

	void listenSubMenu() {
		ctx.getEventBus().addHandler(SubMenuEvent.TYPE, new SubMenuHandler() {
			@Override
			public void onExecute(SubMenuEvent e) {
				if (e.getType() == Type.AZ) {
					doSubMenuSearch(getSearchName() + " " + e.getSubSearch());
				}
			}
		});
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public void doSubMenuSearch(String query) {
		doClear();
		System.out.println("::doSubMenuSearch '" + query + "' ");
		searchWidget = new SearchImageWidget(new Key(query, query), 2, 10, ctx);
		setWidget(searchWidget);
	}

	@Override
	public void doHide() {
		ctx.getUi().getBrandingPage().doHide();
		doClear();
		if (isHidden) {
			return;
		}
		NMorphStyle eff = FxUtil.doHide(this, null);
		eff.play();

		isHidden = true;
	}

	@Override
	public void doShow() {
		if (searchWidget == null) {
			String query = getSearchName();
			System.out.println("::doShow '" + query + "' ");
			searchWidget = new SearchImageWidget(new Key(query, query), 2, 10, ctx);
			setWidget(searchWidget);
		}
		if (!isHidden) {
			return;
		}
		setPopupPosition(336, 88);

		NMorphStyle eff = FxUtil.doShow(this, null);
		eff.play();

		isHidden = false;

//		ctx.getUi().getBrandingPage().doShow();
	}

	@Override
	public void doClear() {
		if (searchWidget != null) {
			searchWidget.removeFromParent();
			searchWidget = null;
		}
	}

}
