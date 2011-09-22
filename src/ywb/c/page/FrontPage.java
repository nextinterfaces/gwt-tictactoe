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
import ywb.c.di.Context;
import ywb.c.event.FrontEvent;
import ywb.c.model.Continent;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;

public class FrontPage extends PopupPanel implements IPage {

	final static UiResources RES = UiResources.INSTANCE;
	private boolean isHidden = true;

	final Image img0;
	final Image img1;
	final Image img2;
	final Image img3;
	final Image img4;
	final Image img5;
	final Image img6;
	private Context ctx;

	final int POSITION_X = 0;
	final int POSITION_Y = 88;

	public FrontPage(Context ctx_) {
		this.ctx = ctx_;
		setStyleName("yFrontPage yPopupPage");

		setPopupPosition(POSITION_X, POSITION_Y);
		show();

		HorizontalPanel namesP = new HorizontalPanel();
		namesP.setStyleName("flow");
		ScrollPanel scrollPanel = new ScrollPanel(namesP);
		scrollPanel.setWidth("1024px");
		setWidget(scrollPanel);

		img0 = new Image(RES.continentNorthAmerica());
		img1 = new Image(RES.continentEurope());
		img2 = new Image(RES.continentLatinAmerica());
		img3 = new Image(RES.continentAsia());
		img4 = new Image(RES.continentAustralia());
		img5 = new Image(RES.continentAfrica());
		img6 = new Image(RES.continentAntarctica());

		img0.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				ctx.getEventBus().fireEvent(new FrontEvent(Continent.NORTH_AMERICA));
			}
		});

		img1.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				ctx.getEventBus().fireEvent(new FrontEvent(Continent.EUROPE));
			}
		});

		img2.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				ctx.getEventBus().fireEvent(new FrontEvent(Continent.SOUTH_AMERICA));
			}
		});

		img3.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				ctx.getEventBus().fireEvent(new FrontEvent(Continent.ASIA));
			}
		});

		img4.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				ctx.getEventBus().fireEvent(new FrontEvent(Continent.AUSTRALIA));
			}
		});

		img5.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				ctx.getEventBus().fireEvent(new FrontEvent(Continent.AFRICA));
			}
		});

		img6.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				ctx.getEventBus().fireEvent(new FrontEvent(Continent.ANTARCTICA));
			}
		});

		namesP.add(img0);
		namesP.add(img1);
		namesP.add(img2);
		namesP.add(img3);
		namesP.add(img4);
		namesP.add(img5);
		namesP.add(img6);
	}

	@Override
	public void doHide() {
		if (isHidden) {
			return;
		}

		NMorphStyle eff = FxUtil.doHide(this, null);
		eff.setDuration(1);
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
		eff.setDuration(1);
		eff.play();

		isHidden = false;
	}

	@Override
	public void doClear() {
		// TODO Auto-generated method stub
	}

}
