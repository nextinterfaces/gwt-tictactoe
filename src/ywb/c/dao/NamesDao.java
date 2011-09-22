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
package ywb.c.dao;

import java.util.ArrayList;
import java.util.HashMap;

import ywb.c.api.API;
import ywb.c.di.Context;
import ywb.c.di.Storage;
import ywb.c.model.Continent;

public class NamesDao {

	private Storage storage;
	private API api;
	private Context ctx;
	
	public static void main(String[] args) {
		System.out.println("start");
	}

	private HashMap<Continent, ArrayList<String>> countries = new HashMap<Continent, ArrayList<String>>();

	// public static enum Continent {
	// Europe, Nort_America, Latin_America, Asia, Africa, Australia, Antarctica
	// }

	public ArrayList<String> getNames(Continent continent) {
		return countries.get(continent);
	}

	public String getRandomName() {
		// int i = Math.abs(Random.nextInt(names.size() - 1));
		// String n = names.get(i);
		// if (!n.startsWith("<div")) {
		// return n;
		// } else {
		// return getRandomName();
		// }

		return null;
	}

	public void init(Context ctx) {
		this.storage = ctx.getStorage();
		this.api = ctx.getApi();
		this.ctx = ctx;

		ArrayList<String> europeList = new ArrayList<String>();
		ArrayList<String> northamerList = new ArrayList<String>();
		ArrayList<String> latinamList = new ArrayList<String>();
		ArrayList<String> asiaList = new ArrayList<String>();
		ArrayList<String> africaList = new ArrayList<String>();
		ArrayList<String> australiaList = new ArrayList<String>();
		ArrayList<String> antarcticaList = new ArrayList<String>();

		countries.put(Continent.EUROPE, europeList);
		countries.put(Continent.NORTH_AMERICA, northamerList);
		countries.put(Continent.SOUTH_AMERICA, latinamList);
		countries.put(Continent.ASIA, asiaList);
		countries.put(Continent.AFRICA, africaList);
		countries.put(Continent.AUSTRALIA, australiaList);
		countries.put(Continent.ANTARCTICA, antarcticaList);

		initEuropeList(europeList);
		initNorthAmericaList(northamerList);
		initSouthAmericaList(latinamList);
		initAfricaList(africaList);
		initAsiaList(asiaList);
		initAustraliaList(australiaList);

		antarcticaList.add("Antarctica");
	}

	void initEuropeList(ArrayList<String> list) {

		list.add("<div class='yAZ'>A</div>");
		list.add("Albania");
		list.add("Andorra");
		list.add("Armenia");
		list.add("Austria");

		list.add("<div class='yAZ'>B</div>");
		list.add("Belarus");
		list.add("Belgium");
		list.add("Bosnia and Herzegovina");
		list.add("Bulgaria");

		list.add("<div class='yAZ'>C</div>");
		list.add("Croatia");
		list.add("Cyprus");
		list.add("Czech Republic");

		list.add("<div class='yAZ'>D</div>");
		list.add("Denmark");

		list.add("<div class='yAZ'>E</div>");
		list.add("Estonia");

		list.add("<div class='yAZ'>F</div>");
		list.add("Finland");
		list.add("France");

		list.add("<div class='yAZ'>G</div>");
		list.add("Georgia");
		list.add("Germany");
		list.add("Greece");

		list.add("<div class='yAZ'>H</div>");
		list.add("Hungary");

		list.add("<div class='yAZ'>I</div>");
		list.add("Iceland");
		list.add("Ireland");
		list.add("Italy");

		list.add("<div class='yAZ'>L</div>");
		list.add("Latvia");
		list.add("Liechtenstein");
		list.add("Lithuania");
		list.add("Luxembourg");

		list.add("<div class='yAZ'>M</div>");
		list.add("Macedonia");
		list.add("Malta");
		list.add("Moldova");
		list.add("Monaco");
		list.add("Montenegro");

		list.add("<div class='yAZ'>N</div>");
		list.add("Netherlands");
		list.add("Norway");

		list.add("<div class='yAZ'>P</div>");
		list.add("Poland");
		list.add("Portugal");

		list.add("<div class='yAZ'>R</div>");
		list.add("Romania");
		list.add("Russia");

		list.add("<div class='yAZ'>S</div>");
		list.add("San Marino");
		list.add("Serbia");
		list.add("Slovakia");
		list.add("Slovenia");
		list.add("Spain");
		list.add("Sweden");
		list.add("Switzerland");

		list.add("<div class='yAZ'>T</div>");
		list.add("Turkey");

		list.add("<div class='yAZ'>U</div>");
		list.add("Ukraine");
		list.add("United Kingdom");

		list.add("<div class='yAZ'>V</div>");
		list.add("Vatican City");
	}

	void initNorthAmericaList(ArrayList<String> list) {

		list.add("<div class='yAZ'>A</div>");
		list.add("Antigua and Barbuda");

		list.add("<div class='yAZ'>B</div>");
		list.add("The Bahamas");
		list.add("Barbados");
		list.add("Belize");

		list.add("<div class='yAZ'>C</div>");
		list.add("Canada");
		list.add("Costa Rica");
		list.add("Cuba");

		list.add("<div class='yAZ'>D</div>");
		list.add("Dominica");
		list.add("Dominican Republic");

		list.add("<div class='yAZ'>E</div>");
		list.add("El Salvador");

		list.add("<div class='yAZ'>G</div>");
		list.add("Greenland (Kalaallit Nunaat)");
		list.add("Grenada");
		list.add("Guatemala");

		list.add("<div class='yAZ'>H</div>");
		list.add("Haiti");
		list.add("Honduras");

		list.add("<div class='yAZ'>J</div>");
		list.add("Jamaica");

		list.add("<div class='yAZ'>M</div>");
		list.add("Mexico");

		list.add("<div class='yAZ'>N</div>");
		list.add("Nicaragua");

		list.add("<div class='yAZ'>P</div>");
		list.add("Panama");

		list.add("<div class='yAZ'>S</div>");
		list.add("Saint Kitts and Nevis");
		list.add("Saint Lucia");
		list.add("Saint Vincent and Grenadines");

		list.add("<div class='yAZ'>T</div>");
		list.add("Trinidad and Tobago");

		list.add("<div class='yAZ'>U</div>");
		list.add("United States of America");
	}

	void initSouthAmericaList(ArrayList<String> list) {
		list.add("Argentina");
		list.add("Bolivia");
		list.add("Brazil");
		list.add("Chile");
		list.add("Colombia");
		list.add("Ecuador");
		list.add("French Guiana");
		list.add("Guyana");
		list.add("Paraguay");
		list.add("Peru");
		list.add("Suriname");
		list.add("Uruguay");
		list.add("Venezuela");
	}

	void initAfricaList(ArrayList<String> list) {

		addLetter("A", list);
		list.add("Algeria");
		list.add("Angola");

		addLetter("B", list);
		list.add("Benin");
		list.add("Botswana");
		list.add("Burkina Faso");
		list.add("Burundi");

		addLetter("C", list);
		list.add("Cameroon");
		list.add("Cape Verde");
		list.add("Central African Republic");
		list.add("Chad");
		list.add("Comoros");
		list.add("Republic of Congo");
		list.add("Democratic Republic of Congo");
		list.add("Cote d'Ivoire");

		addLetter("D", list);
		list.add("Djibouti");

		addLetter("E", list);
		list.add("Egypt");
		list.add("Equatorial Guinea");
		list.add("Eritrea");
		list.add("Ethiopia");

		addLetter("G", list);
		list.add("Gabon");
		list.add("The Gambia");
		list.add("Ghana");
		list.add("Guinea");
		list.add("Guinea-Bissau");

		addLetter("I", list);
		list.add("Ivory Coast");

		addLetter("K", list);
		list.add("Kenya");

		addLetter("L", list);
		list.add("Lesotho");
		list.add("Liberia");
		list.add("Libya");

		addLetter("M", list);
		list.add("Madagascar");
		list.add("Malawi");
		list.add("Mali");
		list.add("Mauritania");
		list.add("Mauritius");
		list.add("Morocco");
		list.add("Mozambique");

		addLetter("N", list);
		list.add("Namibia");
		list.add("Niger");
		list.add("Nigeria");

		addLetter("R", list);
		list.add("Rwanda");

		addLetter("S", list);
		list.add("Sao Tome and Principe");
		list.add("Senegal");
		list.add("Seychelles");
		list.add("Sierra Leone");
		list.add("Somalia");
		list.add("South Africa");
		list.add("Sudan");
		list.add("Swaziland");

		addLetter("T", list);
		list.add("Tanzania");
		list.add("Togo");
		list.add("Tunisia");

		addLetter("U", list);
		list.add("Uganda");

		addLetter("Z", list);
		list.add("Zambia");
		list.add("Zimbabwe");
	}

	void initAsiaList(ArrayList<String> list) {

		addLetter("A", list);
		list.add("Afghanistan");
		list.add("Azerbaijan");

		addLetter("B", list);
		list.add("Bahrain");
		list.add("Bangladesh");
		list.add("Bhutan");
		list.add("Brunei");
		list.add("Burma (Myanmar)");

		addLetter("C", list);
		list.add("Cambodia");
		list.add("China");

		addLetter("H", list);
		list.add("Hong Kong");

		addLetter("I", list);
		list.add("India");
		list.add("Indonesia");
		list.add("Iran");
		list.add("Iraq");
		list.add("Israel");

		addLetter("J", list);
		list.add("Japan");
		list.add("Jordan");

		addLetter("K", list);
		list.add("Kazakhstan");
		list.add("Korea, North");
		list.add("Korea, South");
		list.add("Kuwait");
		list.add("Kyrgyzstan");

		addLetter("L", list);
		list.add("Laos");
		list.add("Lebanon");

		addLetter("M", list);
		list.add("Malaysia");
		list.add("Maldives");
		list.add("Mongolia");
		list.add("Myanmar");

		addLetter("N", list);
		list.add("Nepal");

		addLetter("O", list);
		list.add("Oman");

		addLetter("P", list);
		list.add("Pakistan");
		list.add("Philippines");

		addLetter("Q", list);
		list.add("Qatar");

		addLetter("R", list);
		list.add("Russia");

		addLetter("S", list);
		list.add("Saudi Arabia");
		list.add("Singapore");
		list.add("Sri Lanka");
		list.add("Syria");

		addLetter("T", list);
		list.add("Taiwan");
		list.add("Tajikistan");
		list.add("Thailand");
		list.add("Turkey");
		list.add("Turkmenistan");

		addLetter("U", list);
		list.add("United Arab Emirates");
		list.add("Uzbekistan");

		addLetter("V", list);
		list.add("Vietnam");

		addLetter("Y", list);
		list.add("Yemen");
	}

	private void addLetter(String letter, ArrayList<String> list) {
		list.add("<div class='yAZ'>" + letter + "</div>");
	}

	void initAustraliaList(ArrayList<String> list) {
		list.add("Australia");
		list.add("Fiji");
		list.add("Kiribati");
		list.add("Marshall Islands");
		list.add("Micronesia");
		list.add("Nauru");
		list.add("New Zealand");
		list.add("Palau");
		list.add("Papua New Guinea");
		list.add("Samoa");
		list.add("Solomon Islands");
		list.add("Tonga");
		list.add("Tuvalu");
		list.add("Vanuatu");
	}

}
