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

import org.adamtacy.client.ui.effects.events.EffectCompletedEvent;
import org.adamtacy.client.ui.effects.events.EffectCompletedHandler;
import org.adamtacy.client.ui.effects.impl.Fade;
import org.adamtacy.client.ui.effects.impl.NShow;

import ywb.c.Globals;
import ywb.c.api.API.Response;
import ywb.c.di.Context;
import ywb.c.model.Key;
import ywb.c.model.Photo;
import ywb.c.model.PhotoMap;
import ywb.c.ui.OverlayPopup;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SearchImageWidget extends Composite {

	private VerticalPanel mainPanel = new VerticalPanel();
	private FlowPanel imagePanel = new FlowPanel();
	private Context ctx;

	final ArrayList<Photo> photos = new ArrayList<Photo>();

	public SearchImageWidget(Key searchKey, final int maxRows, int numberPages, Context ctx_) {
		initWidget(mainPanel);
		this.ctx = ctx_;

		ScrollPanel scrollPanel = new ScrollPanel(imagePanel);
		scrollPanel.setSize(Globals.WRAPPER_WIDTH + "px", Globals.WRAPPER_HEIGHT + "px");
		// scrollPanel.getElement().getStyle().setProperty("overflowX", "hidden");
		mainPanel.add(scrollPanel);

		ctx.getPhotoDao().getPhotos(searchKey, numberPages, new Response<PhotoMap>() {
			@Override
			public void read(PhotoMap data) {
				photos.clear();

				for (String id : data.keySet()) {
					final Photo p = data.get(id);
					photos.add(p);
					// // show vertical images only
					// if (p.getThumbHeight() < 130 || p.getThumbWidth() > 120) {
					// continue;
					// }
					Image img = new Image(p.getThumbUrl());
					img.setStyleName("yThumb");

					img.addClickHandler(new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							showOverlay(p);
						}
					});
					imagePanel.add(img);
				}
			}
		});
	}

	void showOverlay(final Photo p) {
		final OverlayPopup popup = new OverlayPopup();
		final FlowPanel wrapper = new FlowPanel();
		wrapper.setStyleName("yPhotoWrapper");

		// HTML btnPlay = new HTML("Play");
		initBigImage(p, wrapper, popup);

		// HorizontalPanel playPanel = new HorizontalPanel();
		// final Timer t = new Timer() {
		// public void run() {
		// System.out.println("before click");
		// invokeClick(img, p, wrapper, popup);
		// }
		// };
		//		
		// btnPlay.addClickHandler(new ClickHandler() {
		// @Override
		// public void onClick(ClickEvent event) {
		// System.out.println("btnPlay click");
		// t.scheduleRepeating(2000);
		// }
		// });
		//		
		//
		// HTML btnStop = new HTML("Stop");
		// btnStop.addClickHandler(new ClickHandler() {
		// @Override
		// public void onClick(ClickEvent event) {
		// t.cancel();
		// }
		// });

		// playPanel.add(btnPlay);
		// playPanel.add(wrapper);
		// playPanel.add(new HTML("Stop"));
		popup.setWidget(wrapper);
		popup.show();
	}

	void setPopupPosition(Photo p, OverlayPopup popup) {
		int w = p.getWidth() > 1024 ? 1024 : p.getWidth();
		int h = p.getHeight() > 550 ? 550 : p.getHeight();
		int left = (Window.getClientWidth() - w) >> 1;
		int top = (Window.getClientHeight() - h) >> 1;
		popup.setPopupPosition(Math.max(Window.getScrollLeft() + left, 0), Math.max(Window.getScrollTop() + top, 0));
	}

	Image initBigImage(final Photo p, final FlowPanel wrapper, final OverlayPopup popup) {
		setPopupPosition(p, popup);
		final Image urlImg = new Image(p.getUrl());
		urlImg.getElement().getStyle().setOpacity(0);

		// VerticalPanel fp = new VerticalPanel();
		// fp.setStyleName("yImageHolder");
		// fp.add(urlImg);

		// Anchor a = new Anchor(p.getOriginalContextUrl());
		// a.setTarget("_blank");
		// a.setHref(p.getOriginalContextUrl());
		// fp.add(a);

		wrapper.add(urlImg);
		wrapper.add(new HTML(p.getOriginalContextUrl()));
		NShow show = new NShow(urlImg.getElement());
		show.setDuration(0.3);
		show.play();

		// urlImg.addClickHandler(new ClickHandler() {
		// @Override
		// public void onClick(ClickEvent event) {
		// invokeClick(urlImg, p, wrapper, popup);
		// }
		// });

		return urlImg;
	}

	void invokeClick(final Image urlImg, final Photo p, final FlowPanel wrapper, final OverlayPopup popup) {
		Fade fade = new Fade(urlImg.getElement());
		fade.setDuration(0.3);
		fade.addEffectCompletedHandler(new EffectCompletedHandler() {
			@Override
			public void onEffectCompleted(EffectCompletedEvent event) {
				// urlImg.removeFromParent();
				wrapper.clear();
				int inx = photos.indexOf(p);
				inx++;
				if (inx >= photos.size()) {
					inx = 0;
				}
				initBigImage(photos.get(inx), wrapper, popup);
			}
		});
		fade.play();
	}

	class Table {

		int sum;
		int row = 0;

		void add(Photo p) {
			// sum += p.getThumbWidth();
			// sum += 10; // padding
			// double d = (double) sum / (double) 640;
			// int i = (int) Math.round(d);
		}

		int getCurrentRow() {
			double d = (double) sum / (double) 640;
			int i = (int) Math.round(d);
			// System.out.println("sum=" + sum + ", i=" + i + ", d=" + d + ", " + p.getThumbUrl());
			return i;
		}
	}

}
