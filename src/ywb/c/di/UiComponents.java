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
package ywb.c.di;

import ywb.c.event.SubMenuEvent;
import ywb.c.page.AzNamePage;
import ywb.c.page.AzPage;
import ywb.c.page.BrandingPage;
import ywb.c.page.FeelLuckyPage;
import ywb.c.page.FrontPage;
import ywb.c.page.FrontTopMenu;
import ywb.c.page.ImagesPage;
import ywb.c.page.LeftMenuImpl;
import ywb.c.page.PageManager;
import ywb.c.page.RedcarpetOptionPage;
import ywb.c.page.RedcarpetPage;
import ywb.c.page.SearchPage;
import ywb.c.page.SettingsPage;
import ywb.c.page.SubMenuPage;
import ywb.c.page.SwimwearPage;
import ywb.c.page.TopMenu;
import ywb.c.page.WallpaperPage;
import ywb.c.page.WikiPage;

import com.google.inject.Inject;

/**
 * This class is a workaround as GIN injector creates each time a new instance of the "injectable" classes. Yet for
 * classes such as FormPopup and KeyboardPopup we need to have ONLY one instance.
 */
public class UiComponents {

	private static LeftMenuImpl leftMenu;
	private static TopMenu topMenu;
	private static RedcarpetPage redcarpetPage;
	private static SearchPage searchPage;
	private static AzPage azPage;
	private static AzNamePage azNamePage;
	private static RedcarpetOptionPage oscarsPage;
	private static RedcarpetOptionPage goldenPage;
	private static RedcarpetOptionPage grammyPage;
	private static RedcarpetOptionPage cannesPage;
	private static ImagesPage searchResultPage;
	private static SwimwearPage swimwearPage;
	private static FeelLuckyPage feeluckyPage;
	private static WallpaperPage wallpaperPage;
	private static FrontPage frontPage;
	private static FrontTopMenu frontTopMenu;
	private static BrandingPage brandingPage;
	private static SettingsPage settingsPage;
	private static SubMenuPage subMenuPage;
	private static WikiPage wikiPage;

	private static PageManager pageManager;

	private Context ctx;

	@Inject
	public UiComponents(Context context) {
		this.ctx = context;
		this.ctx.initUi(this);
	}

	public PageManager getPageManager() {
		if (pageManager == null) {
			pageManager = new PageManager();
		}
		return pageManager;
	}

	public BrandingPage getBrandingPage() {
		if (brandingPage == null) {
			brandingPage = new BrandingPage(ctx);
		}
		return brandingPage;
	}

	public FrontPage getFrontPage() {
		if (frontPage == null) {
			frontPage = new FrontPage(ctx);
			getPageManager().bind(frontPage);
		}
		return frontPage;
	}

	public WikiPage getWikiPage() {
		if (wikiPage == null) {
			wikiPage = new WikiPage(ctx);
			getPageManager().bind(wikiPage);
		}
		return wikiPage;
	}

	public SubMenuPage getSubMenuPage() {
		if (subMenuPage == null) {
			subMenuPage = new SubMenuPage(ctx);
			getPageManager().bind(subMenuPage);
		}
		return subMenuPage;
	}

	public SearchPage getSearchPage() {
		if (searchPage == null) {
			searchPage = new SearchPage(ctx);
			getPageManager().bind(searchPage);
		}
		return searchPage;
	}

	public SettingsPage getSettingsPage() {
		if (settingsPage == null) {
			settingsPage = new SettingsPage(ctx);
			getPageManager().bind(settingsPage);
		}
		return settingsPage;
	}

	public RedcarpetPage getRedcarpetPage() {
		if (redcarpetPage == null) {
			redcarpetPage = new RedcarpetPage(ctx);
			getPageManager().bind(redcarpetPage);
		}
		return redcarpetPage;
	}

	public WallpaperPage getWallpaperPage() {
		if (wallpaperPage == null) {
			wallpaperPage = new WallpaperPage(ctx);
			getPageManager().bind(wallpaperPage);
		}
		return wallpaperPage;
	}

	public AzPage getAzPage() {
		if (azPage == null) {
			azPage = new AzPage(ctx);
			getPageManager().bind(azPage);
		}
		return azPage;
	}

	public AzNamePage getAzNamePage() {
		if (azNamePage == null) {
			azNamePage = new AzNamePage(ctx);
			getPageManager().bind(azNamePage);
		}
		return azNamePage;
	}

	public RedcarpetOptionPage getOscarsPage() {
		if (oscarsPage == null) {
			oscarsPage = new RedcarpetOptionPage(SubMenuEvent.Type.REDCARPET_OSCARS, ctx);
			getPageManager().bind(oscarsPage);
		}
		return oscarsPage;
	}

	public RedcarpetOptionPage getGoldenPage() {
		if (goldenPage == null) {
			goldenPage = new RedcarpetOptionPage(SubMenuEvent.Type.REDCARPET_GOLDEN, ctx);
			getPageManager().bind(goldenPage);
		}
		return goldenPage;
	}

	public RedcarpetOptionPage getGrammyPage() {
		if (grammyPage == null) {
			grammyPage = new RedcarpetOptionPage(SubMenuEvent.Type.REDCARPET_GRAMMY, ctx);
			getPageManager().bind(grammyPage);
		}
		return grammyPage;
	}

	public RedcarpetOptionPage getCannesPage() {
		if (cannesPage == null) {
			cannesPage = new RedcarpetOptionPage(SubMenuEvent.Type.REDCARPET_CANNES, ctx);
			getPageManager().bind(cannesPage);
		}
		return cannesPage;
	}

	public ImagesPage getSearchResultPage() {
		if (searchResultPage == null) {
			searchResultPage = new ImagesPage(ctx);
			getPageManager().bind(searchResultPage);
		}
		return searchResultPage;
	}

	public SwimwearPage getSwimwearPage() {
		if (swimwearPage == null) {
			swimwearPage = new SwimwearPage(ctx);
			getPageManager().bind(swimwearPage);
		}
		return swimwearPage;
	}

	public FeelLuckyPage getFeelLuckyPage() {
		if (feeluckyPage == null) {
			feeluckyPage = new FeelLuckyPage(ctx);
			getPageManager().bind(feeluckyPage);
		}
		return feeluckyPage;
	}

	public LeftMenuImpl getLeftMenu() {
		if (leftMenu == null) {
			leftMenu = new LeftMenuImpl(ctx);
		}
		return leftMenu;
	}

	public TopMenu getTopMenu() {
		if (topMenu == null) {
			topMenu = new TopMenu(ctx);
		}
		return topMenu;
	}

	public FrontTopMenu getFrontTopMenu() {
		if (frontTopMenu == null) {
			frontTopMenu = new FrontTopMenu(ctx);
		}
		return frontTopMenu;
	}

}
