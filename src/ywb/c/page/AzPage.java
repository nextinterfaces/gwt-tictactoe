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
import ywb.c.event.AzNameEvent;
import ywb.c.model.Continent;
import ywb.c.ui.MiscUtils;
import ywb.c.ui.MiscUtils.CallBack;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;

public class AzPage extends PopupPanel implements IPage {

	final static UiResources RES = UiResources.INSTANCE;
	private boolean isHidden = true;

	final int POSITION_X = 0;
	final int POSITION_Y = 88;

	private final Context ctx;

	FlowPanel contentPanel;

	public AzPage(Context ctx_) {
		this.ctx = ctx_;
		setStyleName("yAzPage yPopupPage");
		setWidth("330px");

		setPopupPosition(POSITION_X, POSITION_Y);
		show();

		contentPanel = new FlowPanel();
		ScrollPanel scrollPanel = new ScrollPanel(contentPanel);
		setWidget(scrollPanel);
		scrollPanel.setSize("330px", Globals.WRAPPER_HEIGHT + "px");
	}

	public void setContinent(Continent continent) {

		MiscUtils.populateNamesScrollPanel(continent, new CallBack() {
			@Override
			public void onClick(String name) {
				ctx.getEventBus().fireEvent(new AzNameEvent(name));
			}
		}, contentPanel, ctx);
	}

	@Override
	public void doHide() {
		if (isHidden) {
			return;
		}
		NMorphStyle eff = FxUtil.doHide(this, null);
		eff.play();

		isHidden = true;
	}

	@Override
	public void doShow() {
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
		// not implemented
	}

}
