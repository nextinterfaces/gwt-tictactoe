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

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;

/**
 * This is a button class with two states MouseUp and MouseDown. Bear in mind tablets don't necessarily have two sates,
 * but one - MouseDown only.
 */
public class ImageButton extends YButton {

  private final Image defaultImage;

  public ImageButton(ImageResource resource) {
    this(new Image(resource));
  }

  public ImageButton(Image imgDefault) {

    addStyleName("yImageButton");
    setSize(imgDefault.getWidth() + "px", imgDefault.getHeight() + "px");

    this.defaultImage = imgDefault;
    // this.overImage = imgOver;

    // this.defaultImage.unsinkEvents(Event.ONCLICK | Event.MOUSEEVENTS);
    // this.overImage.unsinkEvents(Event.ONCLICK | Event.MOUSEEVENTS);

    setWidget(defaultImage);
    // sinkEvents(Event.ONCLICK | Event.MOUSEEVENTS);

    // ImageSwapper imgSwapper = new ImageSwapper();
    // addMouseDownHandler(new MouseDownHandler() {
    // @Override
    // public void onMouseDown(MouseDownEvent event) {
    // setWidget(overImage);
    // }
    // });
    //
    // addMouseUpHandler(new MouseUpHandler() {
    // @Override
    // public void onMouseUp(MouseUpEvent event) {
    // setWidget(defaultImage);
    // }
    // });

    // addMouseOutHandler(imgSwapper);
    // // addMouseOverHandler(imgSwapper);
    // addClickHandler(imgSwapper);
  }

  public int getWidth() {
    return defaultImage.getOffsetWidth();
  }

  public void setResource(ImageResource resource) {
    defaultImage.setResource(resource);
  }

}