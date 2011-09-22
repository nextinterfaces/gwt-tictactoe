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

import java.util.ArrayList;

import org.adamtacy.client.ui.effects.core.NMorphStyle;

import ywb.c.FxUtil;
import ywb.c.UiResources;
import ywb.c.di.Context;
import ywb.c.event.AzNameEvent;
import ywb.c.event.AzNameHandler;
import ywb.c.event.FrontEvent;
import ywb.c.model.Continent;
import ywb.c.ui.ImageButton;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class FrontTopMenu extends PopupPanel {

	final static UiResources RES = UiResources.INSTANCE;

	private boolean isHidden = true;

	final ImageButton hMovies;
	final ImageButton hModels;
	final ImageButton hGirlDay;

	final ArrayList<ImageButton> btns = new ArrayList<ImageButton>();

	private Context ctx;

	public FrontTopMenu(Context ctx_) {
		this.ctx = ctx_;
		show();

		setStyleName("yTopCategoryMenu");
		setSize("680", "60px");
		setPopupPosition(336, 5);
		show();

		final FlexTable content = new FlexTable();
		content.setStyleName("content");
		setWidget(content);

		hMovies = new ImageButton(RES.hMovies());
		hModels = new ImageButton(RES.hModels());
		hGirlDay = new ImageButton(RES.hGirlDay());

		content.setWidget(0, 0, hMovies);
		content.setWidget(0, 1, hModels);
		content.setWidget(0, 2, hGirlDay);

		btns.add(hMovies);
		btns.add(hModels);
		btns.add(hGirlDay);

		hMovies.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {

				hMovies.getElement().getStyle().setOpacity(0.4);
				hModels.getElement().getStyle().setOpacity(1);
				hGirlDay.getElement().getStyle().setOpacity(1);

				ctx.getEventBus().fireEvent(new FrontEvent(Continent.AFRICA));
			}
		});

		hModels.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {

				hMovies.getElement().getStyle().setOpacity(1);
				hModels.getElement().getStyle().setOpacity(0.4);
				hGirlDay.getElement().getStyle().setOpacity(1);

				ctx.getEventBus().fireEvent(new FrontEvent(Continent.NORTH_AMERICA));
			}
		});

		hGirlDay.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {

				hMovies.getElement().getStyle().setOpacity(1);
				hModels.getElement().getStyle().setOpacity(1);
				hGirlDay.getElement().getStyle().setOpacity(0.4);

				doHide(null);
				String name = ctx.getGirlDayDao().getTodayName();
				ctx.getEventBus().fireEvent(new AzNameEvent(name));
				// ctx.getEventBus().fireEvent(new FrontEvent(FrontEvent.Type.LATIN_AMERICA));
			}
		});

		ctx.getEventBus().addHandler(AzNameEvent.TYPE, new AzNameHandler() {
			@Override
			public void onExecute(AzNameEvent event) {
				doHide(null);
			}
		});
	}

	public void doHide(final Command afterCmd) {
		if (isHidden) {
			if (afterCmd != null) {
				afterCmd.execute();
			}
			return;
		}

		doHideBtn(hMovies, 250);
		doHideBtn(hModels, 150);
		doHideBtn(hGirlDay, 50);

		isHidden = true;
	}

	private void doHideBtn(final Widget widg, int schedule) {

		Timer t3 = new Timer() {
			public void run() {
				FxUtil.doHide(widg, null).play();
			}
		};
		t3.schedule(schedule);
	}

	public void doShow() {
		if (!isHidden) {
			return;
		}

		Timer t = new Timer() {
			public void run() {
				NMorphStyle eff = FxUtil.moveHorizEffectAbs(690, 0, hMovies.getElement(), null, 30, 100, 0.3);
				eff.play();
			}
		};
		t.schedule(200);

		Timer t2 = new Timer() {
			public void run() {
				NMorphStyle eff2 = FxUtil.moveHorizEffectAbs(690, hMovies.getWidth() + 10, hModels.getElement(), null, 30, 100,
						0.3);
				eff2.play();
			}
		};
		t2.schedule(400);

		Timer t3 = new Timer() {
			public void run() {
				NMorphStyle eff3 = FxUtil.moveHorizEffectAbs(690, hMovies.getWidth() + 10 + hModels.getWidth() + 10, hGirlDay
						.getElement(), null, 30, 100, 0.3);
				eff3.play();
			}
		};
		t3.schedule(600);

		isHidden = false;
	}

}
