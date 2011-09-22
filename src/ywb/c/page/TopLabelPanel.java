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

import ywb.c.UiResources;
import ywb.c.ui.ImageButton;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;

public class TopLabelPanel extends SimplePanel {

	private final ImageButton hTitleImg;
	private final HTML hTitleLabel;

	final static UiResources RES = UiResources.INSTANCE;

	public TopLabelPanel() {
		setStyleName("yTitle");
		hTitleImg = new ImageButton(RES.btnClear());
		hTitleLabel = new HTML();
		hTitleLabel.setStyleName("yTitleLabel");
		hTitleLabel.setWidth("663px");
		hTitleLabel.setHeight("60px");

		this.setWidget(hTitleImg);
	}

	public void setResource(ImageResource res) {
		hTitleLabel.removeFromParent();
		hTitleImg.setResource(res);
		this.setWidget(hTitleImg);
	}

	public void setText(String text) {
		hTitleImg.removeFromParent();
		hTitleLabel.setHTML("<p>" + text + "</p>");
		this.setWidget(hTitleLabel);
	}

}
