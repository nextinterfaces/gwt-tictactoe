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
import ywb.c.event.RedcarpetOptionEvent;
import ywb.c.ui.ImageButton;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PopupPanel;

public class RedcarpetPage extends PopupPanel implements IPage {

	final static UiResources RES = UiResources.INSTANCE;
	private boolean isHidden = true;

	private Context ctx;

	public RedcarpetPage(Context ctx_) {
		this.ctx = ctx_;
		setStyleName("yRedcarpetPage yPopupPage");
		setWidth("680px");

		setPopupPosition(336, 88);
		show();

		ImageButton btnOscar = new ImageButton(RES.aOscar());
		ImageButton btnGolden = new ImageButton(RES.aGolden());
		ImageButton btnGrammy = new ImageButton(RES.aGrammy());
		ImageButton btnCannes = new ImageButton(RES.aCannes());

		btnOscar.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				doHide();
				ctx.getEventBus().fireEvent(new RedcarpetOptionEvent(RedcarpetOptionEvent.Type.OSCARS));
			}
		});
		btnGolden.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				doHide();
				ctx.getEventBus().fireEvent(new RedcarpetOptionEvent(RedcarpetOptionEvent.Type.GOLDENGLOBE));
			}
		});
		btnGrammy.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				doHide();
				ctx.getEventBus().fireEvent(new RedcarpetOptionEvent(RedcarpetOptionEvent.Type.GRAMMY));
			}
		});
		btnCannes.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				doHide();
				ctx.getEventBus().fireEvent(new RedcarpetOptionEvent(RedcarpetOptionEvent.Type.CANNES));
			}
		});

		FlowPanel content = new FlowPanel();
		setWidget(content);

		content.add(btnOscar);
		content.add(btnGolden);
		content.add(btnGrammy);
		content.add(btnCannes);
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
		setPopupPosition(336, 88);

		NMorphStyle eff = FxUtil.doShow(this, null);
		eff.play();

		isHidden = false;
	}

	@Override
	public void doClear() {
		// TODO Auto-generated method stub
	}

}
