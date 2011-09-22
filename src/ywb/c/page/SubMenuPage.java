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
import org.adamtacy.client.ui.effects.events.EffectCompletedEvent;
import org.adamtacy.client.ui.effects.events.EffectCompletedHandler;
import org.adamtacy.client.ui.effects.impl.NShow;

import ywb.c.FxUtil;
import ywb.c.UiResources;
import ywb.c.api.API.Response;
import ywb.c.dao.WikipediaDao;
import ywb.c.di.Context;
import ywb.c.event.AzNameEvent;
import ywb.c.event.AzNameHandler;
import ywb.c.event.FeelLuckyEvent;
import ywb.c.event.FeelLuckyHandler;
import ywb.c.event.RedcarpetOptionEvent;
import ywb.c.event.RedcarpetOptionHandler;
import ywb.c.event.SubMenuEvent;
import ywb.c.event.SwimwearEvent;
import ywb.c.event.SwimwearHandler;
import ywb.c.event.WallpaperEvent;
import ywb.c.event.WallpaperHandler;
import ywb.c.event.SubMenuEvent.Type;
import ywb.c.ui.ImageButton;
import ywb.c.ui.MiscUtils;
import ywb.c.ui.MiscUtils.CallBack;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;

public class SubMenuPage extends PopupPanel implements IPage {

	final static UiResources RES = UiResources.INSTANCE;
	private boolean isHidden = true;

	private final Context ctx;
	private Type eventType;

	final static String[] OPTIONS_AZ = { "Beach", "Wedding", "Dress", "Kiss", "Fashion", "Swimwear", "Haircut" };
	final static String[] OPTIONS_SWIMWEAR = { "2011", "2010", "2009", "sexy", "magazine", "calendar" };
	final static String[] OPTIONS_REDCARPET = { "2011", "2010", "2009", "2008", "2007" };
	final static String[] OPTIONS_WALLPAPER = {};

	public SubMenuPage(Context ctx_) {
		this.ctx = ctx_;
		setStyleName("ySubMenuPage");
		setWidth("680px");

		setPopupPosition(0, 88);
		setWidth("320px");
		show();
		listenEvents();
	}

	public Type getEventType() {
		return eventType;
	}

	public void setEventType(Type eventType) {
		this.eventType = eventType;
	}

	private void listenEvents() {

		ctx.getEventBus().addHandler(FeelLuckyEvent.TYPE, new FeelLuckyHandler() {
			@Override
			public void onExecute(FeelLuckyEvent e) {
				setEventType(Type.FEEL_LUCKY);
				SubMenuPage.this.getElement().getStyle().setOpacity(0);
				setWidget(new OptionsMenu(e.getName(), OPTIONS_AZ));
				doShow();
			}
		});

		ctx.getEventBus().addHandler(AzNameEvent.TYPE, new AzNameHandler() {
			@Override
			public void onExecute(AzNameEvent e) {
				setEventType(Type.AZ);
				SubMenuPage.this.getElement().getStyle().setOpacity(0);
				setWidget(new OptionsMenu(e.getName(), OPTIONS_AZ));
				doShow();
			}
		});

		ctx.getEventBus().addHandler(SwimwearEvent.TYPE, new SwimwearHandler() {
			@Override
			public void onExecute(SwimwearEvent e) {
				setEventType(Type.SWIMWEAR);
				SubMenuPage.this.getElement().getStyle().setOpacity(0);
				setWidget(new OptionsMenu(null, OPTIONS_SWIMWEAR));
				doShow();
			}
		});

		ctx.getEventBus().addHandler(WallpaperEvent.TYPE, new WallpaperHandler() {
			@Override
			public void onExecute(WallpaperEvent event) {
				setEventType(Type.WALLPAPER);
				SubMenuPage.this.getElement().getStyle().setOpacity(0);
				setWidget(new OptionsMenu(null, OPTIONS_WALLPAPER));
				doShow();
			}
		});

		ctx.getEventBus().addHandler(RedcarpetOptionEvent.TYPE, new RedcarpetOptionHandler() {
			@Override
			public void onExecute(RedcarpetOptionEvent e) {
				if (e.getType() == ywb.c.event.RedcarpetOptionEvent.Type.OSCARS) {
					setEventType(Type.REDCARPET_OSCARS);

				} else if (e.getType() == ywb.c.event.RedcarpetOptionEvent.Type.GOLDENGLOBE) {
					setEventType(Type.REDCARPET_GOLDEN);

				} else if (e.getType() == ywb.c.event.RedcarpetOptionEvent.Type.GRAMMY) {
					setEventType(Type.REDCARPET_GRAMMY);

				} else if (e.getType() == ywb.c.event.RedcarpetOptionEvent.Type.CANNES) {
					setEventType(Type.REDCARPET_CANNES);
				}
				setWidget(new OptionsMenu(null, OPTIONS_REDCARPET));
				doShow();
			}
		});
	}

	@Override
	public void doHide() {
		System.out.println("::doHide submenupage");
		ctx.getUi().getBrandingPage().doHide();
		if (isHidden) {
			return;
		}

		NMorphStyle eff = FxUtil.doHide(this, null);
		eff.addEffectCompletedHandler(new EffectCompletedHandler() {
			@Override
			public void onEffectCompleted(EffectCompletedEvent event) {
				doClear();
			}
		});
		eff.play();

		isHidden = true;
	}

	@Override
	public void doShow() {
		System.out.println("::doShow submenupage");
		if (!isHidden) {
			return;
		}
		setPopupPosition(0, 88);

		NShow eff = new NShow(this.getElement());
		eff.play();

		isHidden = false;

		ctx.getUi().getBrandingPage().doShow();
	}

	@Override
	public void doClear() {
		this.clear();
	}

	// ------- inner class

	class OptionsMenu extends FlowPanel {
		OptionsMenu(final String celebName, String... optons) {
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
						ctx.getEventBus().fireEvent(new SubMenuEvent(getEventType(), txt));
					}
				}
			});

			Type et = getEventType();
			if (et == Type.REDCARPET_OSCARS || et == Type.REDCARPET_GOLDEN || et == Type.REDCARPET_GRAMMY
					|| et == Type.REDCARPET_CANNES) {
				FlowPanel namesP = new FlowPanel();
				ScrollPanel scrollPanel = new ScrollPanel(namesP);
				this.add(scrollPanel);
				initRedcarpetScroll(scrollPanel, namesP);

			} else if (et == Type.WALLPAPER) {
				FlowPanel namesP = new FlowPanel();
				ScrollPanel scrollPanel = new ScrollPanel(namesP);
				this.add(scrollPanel);
				initWallpaperScroll(scrollPanel, namesP);

			} else if (et == Type.FEEL_LUCKY || et == Type.AZ) {
				SimplePanel wikiWrapper = new SimplePanel();
				ImageButton wikiBtn = new ImageButton(RES.wiki());
				wikiBtn.addMouseDownHandler(new MouseDownHandler() {
					@Override
					public void onMouseDown(MouseDownEvent event) {
						final WikiWidget popup = new WikiWidget(celebName);
//						popup.doShow();
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
			}
		}

		void initWallpaperScroll(ScrollPanel scrollPanel, FlowPanel nested) {

			scrollPanel.setSize("100%", "410px");
			MiscUtils.populateNamesScrollPanel(null, new CallBack() {
				@Override
				public void onClick(String name) {
					ctx.getEventBus().fireEvent(new SubMenuEvent(getEventType(), name));
				}
			}, nested, ctx);
		}

		void initRedcarpetScroll(ScrollPanel scrollPanel, FlowPanel nested) {

			scrollPanel.setSize("100%", "210px");
			MiscUtils.populateNamesScrollPanel(null, new CallBack() {
				@Override
				public void onClick(String name) {
					ctx.getEventBus().fireEvent(new SubMenuEvent(getEventType(), name));
				}
			}, nested, ctx);
		}

		void initClickHandler(final HTML op, final ArrayList<HTML> options) {
			op.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					ctx.getEventBus().fireEvent(new SubMenuEvent(getEventType(), op.getText()));
					for (HTML html : options) {
						html.removeStyleName("selected");
					}
					op.addStyleName("selected");
				}
			});
		}
	}

}
