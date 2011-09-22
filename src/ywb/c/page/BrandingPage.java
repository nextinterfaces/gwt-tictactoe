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
import ywb.c.Log;
import ywb.c.UiResources;
import ywb.c.di.Context;
import ywb.c.ui.ImageButton;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PopupPanel;

public class BrandingPage extends PopupPanel {

	private boolean isHidden = true;
	final static UiResources RES = UiResources.INSTANCE;

	final int POSITION_X = 330;
	final int POSITION_Y = 555;

	public BrandingPage(Context ctx_) {
		setStyleName("yBrandingPage yPopupPage");

		this.getElement().getStyle().setOpacity(0);
		setPopupPosition(POSITION_X, POSITION_Y);
		setWidth("690px");
		// Frame frame = new Frame("zz_google.html");
		// frame.setSize("150px", "40px");
		// setWidget(frame);
		show();

	}

	public void setCountry(String country) {
		String filteredName = country.replace(" ", "_");
		HTML smallTxt = new HTML("All text is available under the terms of the GNU Free Documentation License. "
				+ "<p class='small'>Original content available at <u>en.wikipedia.org/wiki/" + filteredName + "</u></p>");
		smallTxt.setStyleName("ySmallTxt");
		ImageButton wikiBtn = new ImageButton(RES.wikiTxt());
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(wikiBtn);
		hp.add(smallTxt);

		hp.setCellVerticalAlignment(smallTxt, HasVerticalAlignment.ALIGN_MIDDLE);
		hp.setCellVerticalAlignment(wikiBtn, HasVerticalAlignment.ALIGN_MIDDLE);

		setWidget(hp);
	}

	public void doHide() {
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

	public void doShow() {
		if (!isHidden) {
			return;
		}
		// this.getElement().getStyle().setOpacity(0);

		setPopupPosition(POSITION_X, POSITION_Y);
		NMorphStyle eff = FxUtil.doShow(this, null);
		eff.play();

		isHidden = false;
	}

}
