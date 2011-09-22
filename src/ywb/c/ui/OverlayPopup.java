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
package ywb.c.ui;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.PopupPanel;

public class OverlayPopup extends PopupPanel {

	private final OverlayWrapper overlayWrapper;

	public OverlayPopup() {
		this(false, false);
	}

	public OverlayPopup(boolean autoHide, boolean modal) {
		super(autoHide, modal);
		overlayWrapper = new OverlayWrapper(this);
		setAnimationEnabled(true);
	}

	public OverlayPopup(boolean autoHide) {
		this(autoHide, false);
	}

	@Override
	public void show() {
		overlayWrapper.showOverlay();
		super.show();
	}

	@Override
	public void hide() {
		super.hide();
	}

	public void doShow() {
		this.setPopupPositionAndShow(new PositionCallback() {
			@Override
			public void setPosition(int offsetWidth, int offsetHeight) {
				int winY = Window.getClientHeight();
				int y = (winY - offsetHeight) / 2;
				int x = (Window.getClientWidth() - offsetWidth) / 2;
				OverlayPopup.this.setPopupPosition(x, y);
			}
		});
	}
}
