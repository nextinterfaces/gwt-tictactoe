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

import org.adamtacy.client.ui.effects.impl.NShow;

import ywb.c.FxUtil;
import ywb.c.UiResources;
import ywb.c.di.Context;
import ywb.c.ui.ImageButton;
import ywb.c.ui.OverlayPopup;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.search.client.SafeSearchValue;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PopupPanel;

public class SettingsPage extends PopupPanel implements IPage {

	final static UiResources RES = UiResources.INSTANCE;
	private boolean isHidden = true;

	private final static int WIDTH = 660;
	private final HTML doneHTML;

	private final Context ctx;

	String disclaimer = "The images are being deliverd to you via Google Image Search service "
			+ "and as such they may be protected by copyrights. "
			+ "We cannot grant you any rights to use them for any purpose other than viewing them. "
			+ "Accordingly, if you would like to use any images you have found through our service, "
			+ "we advise you to contact the site owner to obtain the requisite permissions. ";

	public SettingsPage(Context ctx_) {
		this.ctx = ctx_;
		setStyleName("ySettingsPage yPopupPage");
		setWidth(WIDTH + "px");

		setPopupPosition(336, 88);
		show();

		final ImageButton btnClearCache = new ImageButton(RES.btnClearCache());
		doneHTML = new HTML("Cache cleared");

		btnClearCache.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				ctx.getPhotoDao().clear();
				ctx.getUi().getPageManager().clearPages();

				doneHTML.setText("Cache cleared");
				NShow eff = new NShow(doneHTML.getElement());
				eff.setDuration(0.5);
				eff.play();
			}
		});

		HorizontalPanel cachePanel = new HorizontalPanel();
		cachePanel.setStyleName("cachePanel");
		cachePanel.setWidth("100%");

		FlowPanel content = new FlowPanel();
		content.add(cachePanel);

		HTML disclaimerHTML = new HTML("Disclaimer");
		disclaimerHTML.setStyleName("disclaimer");
		content.add(disclaimerHTML);
		HTML disclaimerText = new HTML(disclaimer);
		disclaimerText.setStyleName("disclaimerText");
		content.add(disclaimerText);

		setWidget(content);

		cachePanel.setCellHorizontalAlignment(doneHTML, HasHorizontalAlignment.ALIGN_RIGHT);
		cachePanel.add(btnClearCache);
		cachePanel.add(doneHTML);

		Button adultBtn = new Button("Private Mode");
		// content.add(adultBtn);

		adultBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final OverlayPopup popup = new OverlayPopup();
				popup.addStyleName("yDialog");
				HTML disclaimer = new HTML(
						"You've gone incognito. Pages you view in this window won't appear in your browser history or search history, and they won't leave other traces, like cookies, on your computer after you close the incognito window. Any files you download or bookmarks you create will be preserved, however.");
				Button okBtn = new Button("Ok");
				Button closeBtn = new Button("Close");
				FlowPanel fp = new FlowPanel();
				fp.setSize("450px", "300px");
				fp.add(disclaimer);
				fp.add(okBtn);
				fp.add(closeBtn);
				popup.setWidget(fp);

				okBtn.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						if (ctx.getSafeSearcrh() == SafeSearchValue.OFF) {
							// System.out.println("is Adult enabled? " + ctx.isAdultEnabled());
							ctx.setSafeSearcrh(SafeSearchValue.STRICT);
						} else {
							ctx.setSafeSearcrh(SafeSearchValue.OFF);
						}
					}
				});
				closeBtn.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						popup.hide();
					}
				});

				popup.doShow();
			}
		});
	}

	@Override
	public void doHide() {
		if (isHidden) {
			return;
		}

		FxUtil.doHide(this, null).play();

		isHidden = true;
	}

	@Override
	public void doShow() {
		doneHTML.setText("");
		doneHTML.getElement().getStyle().setOpacity(0);
		if (!isHidden) {
			return;
		}
		setPopupPosition(336, 88);

		FxUtil.doShow(this, null).play();

		isHidden = false;
	}

	@Override
	public void doClear() {
		// TODO Auto-generated method stub
	}

}
