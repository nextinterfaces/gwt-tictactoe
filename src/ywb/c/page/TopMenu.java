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
import org.adamtacy.client.ui.effects.events.EffectCompletedEvent;
import org.adamtacy.client.ui.effects.events.EffectCompletedHandler;
import org.adamtacy.client.ui.effects.impl.Fade;

import ywb.c.FxUtil;
import ywb.c.UiResources;
import ywb.c.di.Context;
import ywb.c.di.UiComponents;
import ywb.c.di.UiGinjector;
import ywb.c.event.AzNameEvent;
import ywb.c.event.AzNameHandler;
import ywb.c.event.BackButtonEvent;
import ywb.c.event.FeelLuckyEvent;
import ywb.c.event.MenuOptionEvent;
import ywb.c.event.MenuOptionHandler;
import ywb.c.event.RedcarpetOptionEvent;
import ywb.c.event.RedcarpetOptionHandler;
import ywb.c.event.SearchEvent;
import ywb.c.event.SearchHandler;
import ywb.c.event.SwimwearEvent;
import ywb.c.event.WallpaperEvent;
import ywb.c.event.BackButtonEvent.Type;
import ywb.c.model.Continent;
import ywb.c.model.Key;
import ywb.c.ui.ImageButton;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class TopMenu extends PopupPanel {

	final static UiResources RES = UiResources.INSTANCE;

	private final static UiComponents UI = UiGinjector.INSTANCE.getUiComponents();

	private static int TITLE_POSITION = 0;

	private final ImageButton hBackImg;
	private final ImageButton hBackTitle;
	private final TopLabelPanel hTitle;
	private boolean isHidden = true;

	enum BackBtnState {
		ENABLED, DISABLED
	}

	private Command backCommand;

	private BackBtnState backBtnState = BackBtnState.ENABLED;

	private Context ctx;

	public TopMenu(Context ctx_) {
		this.ctx = ctx_;
		setStyleName("yTopMenu");
		setSize("1024px", "70px");
		setPopupPosition(0, 0);

		FlexTable content = new FlexTable();
		content.setStyleName("content");
		setWidget(content);

		hBackImg = new ImageButton(RES.hBackImg());
		hBackTitle = new ImageButton(RES.hBackFront());
		hTitle = new TopLabelPanel();

		registerBackEvent();

		content.setWidget(0, 0, hBackImg);
		content.setWidget(0, 1, hBackTitle);
		content.setWidget(0, 2, hTitle);

		content.getCellFormatter().setWidth(0, 0, "80px");
		content.getCellFormatter().setWidth(0, 1, "320px");
		content.getCellFormatter().setWidth(0, 2, "624px");

		content.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
		content.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
		content.getCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_LEFT);

		// setBackCommand(getBack2Main());

//		Fade eff = new Fade(hBackImg.getElement());
//		eff.setDuration(0);
//		eff.play();
//		Fade eff2 = new Fade(hBackTitle.getElement());
//		eff2.setDuration(0);
//		eff2.play();

		// FxUtil.moveHorizEffect(-hBackImg.getOffsetWidth(), 0, hBackImg.getElement(), null, 30, 100).play();
		// FxUtil.moveHorizEffect(-hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), 0, hBackTitle.getElement(),
		// null, 30, 100).play();

		getElement().getStyle().setOpacity(0);
		show();
		
		getBack2Main().execute();
		// isHidden = false;

		// listenEvents2();
	}

	public Command getBackCommand() {
		return backCommand;
	}

	public void setBackCommand(Command backCommand) {
		this.backCommand = backCommand;
	}

	public void doShow() {
		if (!isHidden) {
			return;
		}
		setPopupPosition(0, 0);
		NMorphStyle eff = FxUtil.doShow(this, null);
		eff.setDuration(2);
		eff.play();

		isHidden = false;
	}

	public void doHide() {
		if (isHidden) {
			return;
		}
		NMorphStyle eff = FxUtil.doHide(this, new Command() {
			@Override
			public void execute() {
				setPopupPosition(-2000, -2000);
			}
		});
		eff.setDuration(2);
		eff.play();

		isHidden = true;
	}

	private void listenEvents2() {
		ctx.getEventBus().addHandler(AzNameEvent.TYPE, new AzNameHandler() {

			@Override
			public void onExecute(AzNameEvent event) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void setContinent(Continent c) {
		if (c != null) {
			setBackBtnState(BackBtnState.ENABLED);
			hTitle.setText(c.getName());
			hBackTitle.setResource(RES.hBackFront());
			hBackImg.setResource(RES.hBackImg());
			setBackCommand(getBack2Main());
		}

		// else {
		// hTitle.setText("Nachalna stranica");
		// // hBackTitle.setResource(RES.hBackFront());
		// hBackImg.setResource(RES.hBackImg());
		// setBackCommand(getBack2Main());
		//			
		// }
	}

	public void setCountry(String c) {
		hTitle.setText(c);
		slideInButton(hTitle, null, null);
		hBackTitle.setResource(RES.hBackFront());
		hBackImg.setResource(RES.hBackImg());

	}

	public void animateBackBtn() {

		setBackBtnState(BackBtnState.ENABLED);
		hBackTitle.setResource(RES.hBackFront());
		hBackImg.setResource(RES.hBackImg());

		FxUtil.moveHorizEffect(-hBackImg.getOffsetWidth(), 0, hBackImg.getElement(), null, 30, 100).play();
		FxUtil.moveHorizEffect(-hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), 0, hBackTitle.getElement(), null,
				30, 100).play();
	}

	private void listenEvents() {

		ctx.getEventBus().addHandler(MenuOptionEvent.TYPE, new MenuOptionHandler() {
			@Override
			public void onExecute(final MenuOptionEvent e) {
				setBackCommand(getBack2Main());

				slideOutButton(hTitle, null, new Command() {
					@Override
					public void execute() {

						System.out.println("after slideOutButton");

						if (getBackBtnState() == BackBtnState.DISABLED) {
							setBackBtnState(BackBtnState.ENABLED);
							hBackTitle.setResource(RES.hBackFront());
							hBackImg.setResource(RES.hBackImg());

							FxUtil.moveHorizEffect(-hBackImg.getOffsetWidth(), 0, hBackImg.getElement(), null, 30, 100).play();
							FxUtil.moveHorizEffect(-hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), 0,
									hBackTitle.getElement(), null, 30, 100).play();
						}

						if (e.getType() == MenuOptionEvent.Type.AZ) {
							hTitle.setResource(RES.hAz());
							UI.getAzPage().doShow();

						} else if (e.getType() == MenuOptionEvent.Type.RED_CARPET) {
							hTitle.setResource(RES.hRedcarpet());
							UI.getRedcarpetPage().doShow();

						} else if (e.getType() == MenuOptionEvent.Type.SWIMWEAR) {
							hTitle.setResource(RES.hSwimwear());
							UI.getSwimwearPage().doShow();
							ctx.getEventBus().fireEvent(new SwimwearEvent());

						} else if (e.getType() == MenuOptionEvent.Type.FEEL_LUCKY) {
							String randomName = ctx.getNamseDao().getRandomName();
							hTitle.setText(randomName);
							UI.getFeelLuckyPage().setSearchName(randomName);
							UI.getFeelLuckyPage().doShow();
							ctx.getEventBus().fireEvent(new FeelLuckyEvent(randomName));

						} else if (e.getType() == MenuOptionEvent.Type.WALLPAPER) {
							hTitle.setResource(RES.hWallpaper());
							UI.getWallpaperPage().doShow();
							ctx.getEventBus().fireEvent(new WallpaperEvent());

						} else if (e.getType() == MenuOptionEvent.Type.SEARCH) {
							hTitle.setResource(RES.hSearch());
							UI.getSearchPage().doShow();

						} else if (e.getType() == MenuOptionEvent.Type.SETTINGS) {
							hTitle.setResource(RES.hSettings());
							UI.getSettingsPage().doShow();
						}

						slideInButton(hTitle, null, null);

					}
				});

				UI.getFrontTopMenu().doHide(null);

			}
		});

		ctx.getEventBus().addHandler(RedcarpetOptionEvent.TYPE, new RedcarpetOptionHandler() {
			@Override
			public void onExecute(final RedcarpetOptionEvent e) {

				setBackCommand(getBack2Redcarpet());

				Command cmd = new Command() {
					@Override
					public void execute() {
						slideOutButton(hTitle, null, new Command() {
							@Override
							public void execute() {
								if (e.getType() == RedcarpetOptionEvent.Type.OSCARS) {
									hTitle.setResource(RES.hOscar());
									UI.getOscarsPage().setSearchKey(Key.OSCARS);
									UI.getOscarsPage().doShow();

								} else if (e.getType() == RedcarpetOptionEvent.Type.GOLDENGLOBE) {
									hTitle.setResource(RES.hGoldenglobe());
									UI.getGoldenPage().setSearchKey(Key.GOLDEN_GLOBE);
									UI.getGoldenPage().doShow();

								} else if (e.getType() == RedcarpetOptionEvent.Type.GRAMMY) {
									hTitle.setResource(RES.hGrammy());
									UI.getGrammyPage().setSearchKey(Key.GRAMMY);
									UI.getGrammyPage().doShow();

								} else if (e.getType() == RedcarpetOptionEvent.Type.CANNES) {
									hTitle.setResource(RES.hCannes());
									UI.getCannesPage().setSearchKey(Key.CANNES);
									UI.getCannesPage().doShow();
								}

								slideInButton(hTitle, null, null);
							}
						});
					}
				};

				setBackBtnState(BackBtnState.ENABLED);
				hBackTitle.setResource(RES.hBackRedcarpet());
				hBackImg.setResource(RES.hBackImg());

				FxUtil.moveHorizEffect(-hBackImg.getOffsetWidth(), 0, hBackImg.getElement(), null, 30, 100).play();
				FxUtil.moveHorizEffect(-hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), 0, hBackTitle.getElement(),
						cmd, 30, 100).play();
			}
		});

		ctx.getEventBus().addHandler(AzNameEvent.TYPE, new AzNameHandler() {
			@Override
			public void onExecute(final AzNameEvent e) {

				setBackCommand(getBack2Az());

				Command cmd = new Command() {
					@Override
					public void execute() {
						slideOutButton(hTitle, null, new Command() {
							@Override
							public void execute() {
								hTitle.setText(e.getName());

								slideInButton(hTitle, null, null);

								UI.getAzNamePage().setSearchName(e.getName());
								UI.getAzNamePage().doShow();
							}
						});
					}
				};

				setBackBtnState(BackBtnState.ENABLED);
				hBackTitle.setResource(RES.hBackAz());
				hBackImg.setResource(RES.hBackImg());

				FxUtil.moveHorizEffect(-hBackImg.getOffsetWidth(), 0, hBackImg.getElement(), null, 30, 100).play();
				FxUtil.moveHorizEffect(-hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), 0, hBackTitle.getElement(),
						cmd, 30, 100).play();
			}
		});

		ctx.getEventBus().addHandler(SearchEvent.TYPE, new SearchHandler() {
			@Override
			public void onExecute(final SearchEvent e) {

				setBackCommand(getBack2Search());

				Command cmd = new Command() {
					@Override
					public void execute() {
						slideOutButton(hTitle, null, new Command() {
							@Override
							public void execute() {
								hTitle.setText(e.getName());

								slideInButton(hTitle, null, null);

								UI.getSearchResultPage().setSearchKey(new Key(e.getName(), e.getName()));
								UI.getSearchResultPage().doShow();
							}
						});
					}
				};

				setBackBtnState(BackBtnState.ENABLED);
				hBackTitle.setResource(RES.hBackSearch());
				hBackImg.setResource(RES.hBackImg());

				FxUtil.moveHorizEffect(-hBackImg.getOffsetWidth(), 0, hBackImg.getElement(), null, 30, 100).play();
				FxUtil.moveHorizEffect(-hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), 0, hBackTitle.getElement(),
						cmd, 30, 100).play();
			}
		});
	}

	public BackBtnState getBackBtnState() {
		return backBtnState;
	}

	public void setBackBtnState(BackBtnState backBtnState) {
		this.backBtnState = backBtnState;
	}

	private void registerBackEvent() {
		MouseDownHandler handler = new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				Command cm = getBackCommand();
				if (cm != null) {
					cm.execute();
				}
			}
		};
		hBackImg.addMouseDownHandler(handler);
		hBackTitle.addMouseDownHandler(handler);
	}

	private Command getBack2Main() {
		Command cmd = new Command() {
			@Override
			public void execute() {
				UI.getPageManager().hidePages();

				if (getBackBtnState() != BackBtnState.ENABLED) {
					return;
				}
				ctx.getEventBus().fireEvent(new BackButtonEvent(Type.FRONT_PAGE));

				Command afterBackCmd = new Command() {
					@Override
					public void execute() {
						FxUtil.moveHorizEffect(-hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), -80,
								hBackTitle.getElement(), null, 30, 100).play();
					}
				};
				FxUtil.moveHorizEffect(0, -hBackImg.getOffsetWidth(), hBackImg.getElement(), null, 100, 30).play();
				FxUtil.moveHorizEffect(0, -hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), hBackTitle.getElement(),
						afterBackCmd, 100, 30).play();

				setBackBtnState(BackBtnState.DISABLED);
				setBackCommand(null);

				slideOutButton(hTitle, null, null);
			}
		};

		return cmd;
	}

	private Command getBack2Redcarpet() {
		Command cmd = new Command() {
			@Override
			public void execute() {

				UI.getPageManager().hidePages();
				ctx.getEventBus().fireEvent(new MenuOptionEvent(MenuOptionEvent.Type.RED_CARPET));

				ctx.getEventBus().fireEvent(new BackButtonEvent(Type.RED_CARPET));

				Command afterCmd = new Command() {
					@Override
					public void execute() {
						hBackTitle.setResource(RES.hBackFront());
						FxUtil.moveHorizEffect(-hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), 0,
								hBackTitle.getElement(), null, 30, 100).play();
					}
				};
				FxUtil.moveHorizEffect(0, -hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), hBackTitle.getElement(),
						afterCmd, 100, 30).play();

				setBackBtnState(BackBtnState.ENABLED);
				setBackCommand(getBack2Main());
			}
		};

		return cmd;
	}

	private Command getBack2Az() {
		Command cmd = new Command() {
			@Override
			public void execute() {

				UI.getPageManager().hidePages();
				ctx.getEventBus().fireEvent(new MenuOptionEvent(MenuOptionEvent.Type.AZ));
				ctx.getEventBus().fireEvent(new BackButtonEvent(Type.AZ));

				Command afterCmd = new Command() {
					@Override
					public void execute() {
						hBackTitle.setResource(RES.hBackFront());
						FxUtil.moveHorizEffect(-hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), 0,
								hBackTitle.getElement(), null, 30, 100).play();
					}
				};
				FxUtil.moveHorizEffect(0, -hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), hBackTitle.getElement(),
						afterCmd, 100, 30).play();

				setBackBtnState(BackBtnState.ENABLED);
				setBackCommand(getBack2Main());
			}
		};

		return cmd;
	}

	private Command getBack2Search() {
		Command cmd = new Command() {
			@Override
			public void execute() {

				UI.getPageManager().hidePages();
				ctx.getEventBus().fireEvent(new MenuOptionEvent(MenuOptionEvent.Type.SEARCH));
				ctx.getEventBus().fireEvent(new BackButtonEvent(Type.SEARCH));

				Command afterCmd = new Command() {
					@Override
					public void execute() {
						hBackTitle.setResource(RES.hBackFront());
						FxUtil.moveHorizEffect(-hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), 0,
								hBackTitle.getElement(), null, 30, 100).play();
					}
				};
				FxUtil.moveHorizEffect(0, -hBackImg.getOffsetWidth() - hBackTitle.getOffsetWidth(), hBackTitle.getElement(),
						afterCmd, 100, 30).play();

				setBackBtnState(BackBtnState.ENABLED);
				setBackCommand(getBack2Main());
			}
		};

		return cmd;
	}

	public void slideOutButton(final Widget btn, final Command beforeCmd, final Command afterCmd) {

		if (beforeCmd != null) {
			beforeCmd.execute();
		}

		// if(afterCmd != null){
		// afterCmd.execute();
		// }

		Fade f = new Fade(btn.getElement());
		f.setDuration(0.3);
		f.addEffectCompletedHandler(new EffectCompletedHandler() {

			@Override
			public void onEffectCompleted(EffectCompletedEvent event) {
				if (afterCmd != null) {
					afterCmd.execute();
				}
			}
		});
		f.play();

		// FxUtil.doHide(btn, afterCmd).play();

		// NMorphStyle eff = FxUtil.moveHorizEffect(btn.getAbsoluteLeft(), 1024, btn.getElement(), afterCmd, 100, 30);
		// eff.addEffectCompletedHandler(new EffectCompletedHandler() {
		// @Override
		// public void onEffectCompleted(EffectCompletedEvent event) {
		// btn.getElement().getStyle().setOpacity(0);
		// }
		// });
		// eff.play();
	}

	public void slideInButton(final Widget btn, final Command beforeCmd, final Command afterCmd) {

		NMorphStyle eff = FxUtil.moveHorizEffect(1024, TITLE_POSITION, btn.getElement(), afterCmd, 30, 100);
		eff.play();
	}

}
