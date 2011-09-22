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
import ywb.c.di.Context;
import ywb.c.di.UiGinjector;
import ywb.c.di.UiComponents;
import ywb.c.event.AzNameEvent;
import ywb.c.event.AzNameHandler;
import ywb.c.event.FeelLuckyEvent;
import ywb.c.event.FeelLuckyHandler;
import ywb.c.event.MenuOptionEvent;
import ywb.c.event.RedcarpetOptionEvent;
import ywb.c.event.RedcarpetOptionHandler;
import ywb.c.event.SearchEvent;
import ywb.c.event.SearchHandler;
import ywb.c.event.SwimwearEvent;
import ywb.c.event.SwimwearHandler;
import ywb.c.event.WallpaperEvent;
import ywb.c.event.WallpaperHandler;
import ywb.c.event.MenuOptionEvent.Type;
import ywb.c.ui.MiscUtils;
import ywb.c.ui.YButton;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.PopupPanel;

public abstract class LeftMenu extends PopupPanel {

	final ArrayList<YButton> buttons = new ArrayList<YButton>();
	private final static UiComponents UI = UiGinjector.INSTANCE.getUiComponents();

	private Context ctx;

	public LeftMenu(Context ctx_) {
		this.ctx = ctx_;
		setStyleName("yLeftMenu");
		setWidth("300px");
		setPopupPosition(0, 100);

		listenEvents();
	}

	public void addButton(YButton button) {
		buttons.add(button);
	}

	abstract Type getType();

	public void slideOutButton(final YButton btn, final Command beforeCmd, final Command afterCmd, final Type actionType) {

		btn.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {

				if (beforeCmd != null) {
					beforeCmd.execute();
				}
				UI.getPageManager().hidePages();
				ctx.getEventBus().fireEvent(new MenuOptionEvent(actionType));

				// String top = btn.getElement().getStyle().getProperty("top");
				// int abstop = btn.getAbsoluteTop();
				// abstop = abstop - favorites.getOffsetHeight();

				NMorphStyle eff = FxUtil.moveHorizEffect(0, -btn.getOffsetWidth(), btn.getElement(), afterCmd, 100, 50);
				eff.play();
				btn.setEnabled(false);
			}
		});
	}

	private void listenEvents() {
		// eventBus.addHandler(BackButtonEvent.TYPE, new BackButtonHandler() {
		// @Override
		// public void onExecute(final BackButtonEvent e) {
		// if (e.getType() == BackButtonEvent.Type.RED_CARPET) {
		// resetButtons(null); // all buttons
		//          
		// } else {
		// resetButtons(null); // all buttons
		// }
		// }
		// });

		ctx.getEventBus().addHandler(RedcarpetOptionEvent.TYPE, new RedcarpetOptionHandler() {
			@Override
			public void onExecute(RedcarpetOptionEvent e) {
				clearLeftMenuRandom();
			}
		});

		ctx.getEventBus().addHandler(SwimwearEvent.TYPE, new SwimwearHandler() {
			@Override
			public void onExecute(SwimwearEvent e) {
				clearLeftMenuRandom();
			}
		});

		ctx.getEventBus().addHandler(WallpaperEvent.TYPE, new WallpaperHandler() {
			@Override
			public void onExecute(WallpaperEvent event) {
				clearLeftMenuRandom();
			}
		});

		ctx.getEventBus().addHandler(AzNameEvent.TYPE, new AzNameHandler() {
			@Override
			public void onExecute(AzNameEvent e) {
				clearLeftMenuRandom();
			}
		});

		ctx.getEventBus().addHandler(SearchEvent.TYPE, new SearchHandler() {
			@Override
			public void onExecute(SearchEvent e) {
				clearLeftMenuRandom();
			}
		});

		ctx.getEventBus().addHandler(FeelLuckyEvent.TYPE, new FeelLuckyHandler() {
			@Override
			public void onExecute(FeelLuckyEvent e) {
				clearLeftMenuRandom();
			}
		});

	}

	private void clearLeftMenuRandom() {
		for (final YButton btn : buttons) {
			Timer t = new Timer() {
				public void run() {
					btn.setEnabled(false);
					NMorphStyle eff = FxUtil.moveHorizEffect(0, -btn.getOffsetWidth(), btn.getElement(), null, 100, 0);
					eff.play();
				}
			};
			t.schedule(MiscUtils.getTimeout());
		}
	}

	public void resetButtons(final YButton selectedBtn) {
		for (final YButton btn : buttons) {
			if (!btn.equals(selectedBtn) && !btn.isEnabled()) {

				Timer t = new Timer() {
					public void run() {
						NMorphStyle eff = FxUtil.moveHorizEffect(-btn.getOffsetWidth(), 0, btn.getElement(), new Command() {
							@Override
							public void execute() {
								btn.setEnabled(true);
							}
						}, 50, 100);
						eff.play();
					}
				};
				t.schedule(MiscUtils.getTimeout());
			}
		}
	}

}
