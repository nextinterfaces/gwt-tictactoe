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

import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 * A overlay popup used to gray out the screen. Any popup which requires grey
 * out feauter on popup should derive from this class.
 */
public class OverlayPopupOLD extends PopupPanel {

  private final OverlayWrapperOLD overlayWrapperOLD;

  public OverlayPopupOLD() {
    this(false, false);
  }

  public OverlayPopupOLD(boolean autoHide, boolean modal) {
    super(autoHide, modal);
    overlayWrapperOLD = new OverlayWrapperOLD(this);
    // setAnimationEnabled(true);

    DOM.setStyleAttribute(this.getElement(), "position", "absolute");
  }

  // private OverlayPopupOLD(boolean autoHide) {
  // this(autoHide, false);
  // }

  // @Override
  public void showOverlay() {
    overlayWrapperOLD.showOverlay();
    // super.show();
  }

  public void hideOverlay() {
    overlayWrapperOLD.hideOverlay();
  }

  public void addOverlayMouseDownHandler(MouseDownHandler handler) {
    overlayWrapperOLD.addMouseDownHandler(handler);
  }

  // @Override
  // public void hide() {
  // super.hide();
  // // if (navigatable != null) {
  // // navigatable.onHide();
  // // }
  // }
}
