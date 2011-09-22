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
package ywb.c;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface UiResources extends ClientBundle {

	public static final UiResources INSTANCE = GWT.create(UiResources.class);

	@Source("resources/btn2Players.png")
	public ImageResource btn2Players();

	@Source("resources/btnPlaybookFirst.png")
	public ImageResource btnPlaybookFirst();

	@Source("resources/btnYouFirst.png")
	public ImageResource btnYouFirst();

	@Source("resources/btn2PlayersOn.png")
	public ImageResource btn2PlayersOn();

	@Source("resources/btnPlaybookFirstOn.png")
	public ImageResource btnPlaybookFirstOn();

	@Source("resources/btnYouFirstOn.png")
	public ImageResource btnYouFirstOn();

	@Source("resources/btnClear.png")
	public ImageResource btnClear();

	@Source("resources/btnClearCache.png")
	public ImageResource btnClearCache();

	@Source("resources/hBack.png")
	public ImageResource btnBack();

	@Source("resources/wiki.png")
	public ImageResource wiki();

	@Source("resources/wikiTxt.png")
	public ImageResource wikiTxt();

	@Source("resources/btnArticle.png")
	public ImageResource btnArticle();

	@Source("resources/btnClose.png")
	public ImageResource btnClose();

	@Source("resources/btnDone.png")
	public ImageResource btnDone();

	@Source("resources/hBlank.png")
	public ImageResource hBlank();

	@Source("resources/mAZ.png")
	public ImageResource mAZ();

	@Source("resources/mSwimwear.png")
	public ImageResource mSwimwear();

	@Source("resources/mRedcarpet.png")
	public ImageResource mRedcarpet();

	@Source("resources/mFeellucky.png")
	public ImageResource mFeellucky();

	@Source("resources/mWallpaper.png")
	public ImageResource mWallpaper();

	@Source("resources/mSearch.png")
	public ImageResource mSearch();

	@Source("resources/mSettings.png")
	public ImageResource mSettings();

	@Source("resources/hSwimwear.png")
	public ImageResource hSwimwear();

	@Source("resources/hOscar.png")
	public ImageResource hOscar();

	@Source("resources/hRedcarpet.png")
	public ImageResource hRedcarpet();

	@Source("resources/hFeellucky.png")
	public ImageResource hFeellucky();

	@Source("resources/hGolden.png")
	public ImageResource hGoldenglobe();

	@Source("resources/hGrammy.png")
	public ImageResource hGrammy();

	@Source("resources/hWallpaper.png")
	public ImageResource hWallpaper();

	@Source("resources/hSearch.png")
	public ImageResource hSearch();

	@Source("resources/hSettings.png")
	public ImageResource hSettings();

	@Source("resources/hAz.png")
	public ImageResource hAz();

	@Source("resources/hCannes.png")
	public ImageResource hCannes();

	@Source("resources/hBack.png")
	public ImageResource hBackImg();

	@Source("resources/hBackSearch.png")
	public ImageResource hBackSearch();

	@Source("resources/hBackFront.png")
	public ImageResource hBackFront();

	@Source("resources/hBackRedcarpet.png")
	public ImageResource hBackRedcarpet();

	@Source("resources/hBackSwimwear.png")
	public ImageResource hBackSwimwear();

	@Source("resources/hBackAz.png")
	public ImageResource hBackAz();

	@Source("resources/aOscar.png")
	public ImageResource aOscar();

	@Source("resources/aGolden.png")
	public ImageResource aGolden();

	@Source("resources/aGrammy.png")
	public ImageResource aGrammy();

	@Source("resources/aCannes.png")
	public ImageResource aCannes();

	@Source("resources/hMovies.png")
	public ImageResource hMovies();

	@Source("resources/hModels.png")
	public ImageResource hModels();

	@Source("resources/hGirlDay.png")
	public ImageResource hGirlDay();

	@Source("resources/continentEurope.png")
	public ImageResource continentEurope();

	@Source("resources/continentNorthAmerica.png")
	public ImageResource continentNorthAmerica();

	@Source("resources/continentLatinAmerica.png")
	public ImageResource continentLatinAmerica();

	@Source("resources/continentAsia.png")
	public ImageResource continentAsia();

	@Source("resources/continentAustralia.png")
	public ImageResource continentAustralia();

	@Source("resources/continentAfrica.png")
	public ImageResource continentAfrica();

	@Source("resources/continentAntarctica.png")
	public ImageResource continentAntarctica();

	@Source("resources/frontModel0.png")
	public ImageResource frontModel0();

	@Source("resources/frontModel1.png")
	public ImageResource frontModel1();

	@Source("resources/frontModel2.png")
	public ImageResource frontModel2();

	@Source("resources/frontModel3.png")
	public ImageResource frontModel3();

	//
	@Source("resources/frontModel4.png")
	public ImageResource frontModel4();

	@Source("resources/gameCover.jpg")
	public ImageResource gameCover();

}
