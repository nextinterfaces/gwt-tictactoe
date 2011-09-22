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
import ywb.c.di.Context;
import ywb.c.di.UiGinjector;
import ywb.c.di.UiComponents;
import ywb.c.event.BackButtonEvent;
import ywb.c.event.BackButtonHandler;
import ywb.c.event.MenuOptionEvent.Type;
import ywb.c.ui.ImageButton;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;

public class LeftMenuImpl extends LeftMenu {

  final static UiResources RES = UiResources.INSTANCE;
  private final static UiComponents UI = UiGinjector.INSTANCE.getUiComponents();

  final ImageButton btnAZ;
  final ImageButton btnSwimwear;
  final ImageButton btnRedcarpet;
  final ImageButton btnFeellucky;
  final ImageButton btnWallpaper;
  final ImageButton btnSearch;
  final ImageButton btnSettings;

	private Context ctx;

	public LeftMenuImpl(Context ctx_) {
    super(ctx_);
		this.ctx = ctx_;

    btnAZ = new ImageButton(RES.mAZ());
    btnWallpaper = new ImageButton(RES.mWallpaper());
    btnSwimwear = new ImageButton(RES.mSwimwear());
    btnRedcarpet = new ImageButton(RES.mRedcarpet());
    btnFeellucky = new ImageButton(RES.mFeellucky());
    btnSearch = new ImageButton(RES.mSearch());
    btnSettings = new ImageButton(RES.mSettings());

		HTML option0 = new HTML("<span class='yOption'>" + "AZ" + "</span>");
		HTML option1 = new HTML("<span class='yOption'>" + "Europe" + "</span>");
		
    FlowPanel panel = new FlowPanel();
    addButton(btnAZ);
    addButton(btnWallpaper);
    addButton(btnSwimwear);
    addButton(btnRedcarpet);
    addButton(btnFeellucky);
    addButton(btnSearch);
    addButton(btnSettings);

    panel.add(btnAZ);
    panel.add(btnWallpaper);
    panel.add(btnSwimwear);
    panel.add(btnRedcarpet);
    panel.add(btnFeellucky);
    panel.add(btnSearch);
    panel.add(btnSettings);

    setWidget(panel);

    listenEvents();

    slideOutButton(btnAZ, new Command() {
      @Override
      public void execute() {
        resetButtons(btnAZ);
      }
    }, null, Type.AZ);

    slideOutButton(btnSwimwear, new Command() {
      @Override
      public void execute() {
        resetButtons(btnSwimwear);
      }
    }, null, Type.SWIMWEAR);

    slideOutButton(btnRedcarpet, new Command() {
      @Override
      public void execute() {
        resetButtons(btnRedcarpet);
      }
    }, null, Type.RED_CARPET);

    slideOutButton(btnFeellucky, new Command() {
      @Override
      public void execute() {
        resetButtons(btnFeellucky);
      }
    }, null, Type.FEEL_LUCKY);

    slideOutButton(btnWallpaper, new Command() {
      @Override
      public void execute() {
        resetButtons(btnWallpaper);
      }
    }, null, Type.WALLPAPER);
    
    slideOutButton(btnSearch, new Command() {
      @Override
      public void execute() {
        resetButtons(btnSearch);
      }
    }, null, Type.SEARCH);
    
    slideOutButton(btnSettings, new Command() {
      @Override
      public void execute() {
        resetButtons(btnSettings);
      }
    }, null, Type.SETTINGS);

  }

  @Override
  Type getType() {
    return Type.AZ;
  }

  private void listenEvents() {
    ctx.getEventBus().addHandler(BackButtonEvent.TYPE, new BackButtonHandler() {
      @Override
      public void onExecute(final BackButtonEvent e) {
        if (e.getType() == BackButtonEvent.Type.RED_CARPET) {
          resetButtons(btnRedcarpet);

        } else if (e.getType() == BackButtonEvent.Type.SWIMWEAR) {
          resetButtons(btnSwimwear);

        } else if (e.getType() == BackButtonEvent.Type.AZ) {
          resetButtons(btnAZ);

        } else if (e.getType() == BackButtonEvent.Type.ANIME) {
          resetButtons(btnWallpaper);

        } else if (e.getType() == BackButtonEvent.Type.FEELLUCKY) {
          resetButtons(btnFeellucky);

        } else if (e.getType() == BackButtonEvent.Type.SEARCH) {
          resetButtons(btnSearch);
          
        } else if (e.getType() == BackButtonEvent.Type.SETTINGS) {
          resetButtons(btnSettings);

        } else if (e.getType() == BackButtonEvent.Type.FRONT_PAGE) {
          resetButtons(null); // all buttons
          UI.getFrontPage().doShow();
          UI.getFrontTopMenu().doShow();

        } else {
          resetButtons(null); // all buttons
        }

      }
    });
  }

}
