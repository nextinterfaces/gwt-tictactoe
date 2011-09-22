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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.PopupPanel;

class OverlayWrapper {

	class OverlayPanel extends PopupPanel implements HasClickHandlers {
		OverlayPanel() {
			super(false, true);
			addStyleName("overlay-bg");
			sinkEvents(Event.ONCLICK);
		}

		@Override
		public HandlerRegistration addClickHandler(ClickHandler handler) {
			return addHandler(handler, ClickEvent.getType());
		}
	}

	private OverlayPanel overlay;

	private PopupPanel popup;

	public OverlayWrapper(PopupPanel popupPanel) {

		this.popup = popupPanel;
		popup.addStyleName("overlay-popup");

		popupPanel.addCloseHandler(new CloseHandler<PopupPanel>() {
			@Override
			public void onClose(CloseEvent<PopupPanel> event) {
				overlay.hide();
			}
		});

		overlay = new OverlayPanel();
		overlay.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				overlay.hide();
				popup.hide();
			}
		});
	}

	public void showOverlay() {
		// IE Window.getClientHeight() might not work.
		// JavaScriptObject pageSize = JSInvoker.getPageSize();
		// String[] array = pageSize.toString().split(",");
		// DOM.setStyleAttribute(overlay.getElement(), "height", array[1] + "px");
		DOM.setStyleAttribute(overlay.getElement(), "height", Window.getClientHeight() + "px");
		overlay.show();
	}
}