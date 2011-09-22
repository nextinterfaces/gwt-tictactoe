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
import java.util.Date;

import ywb.c.api.API;
import ywb.c.di.Context;
import ywb.c.di.Storage;

public class GirlOfDayNamesDao {

	private Storage storage;
	private API api;
	private Context ctx;

	ArrayList<String> names = new ArrayList<String>();

	public String getTodayName() {
		Date d = new Date();
		int date = d.getDate();
		System.out.println("today is the " + date);
		return names.get(date);
	}

	public void init(Context ctx) {
		this.storage = ctx.getStorage();
		this.api = ctx.getApi();
		this.ctx = ctx;
		names.add("Adriana Lima");
		names.add("Alessandra Ambrosio");
		names.add("Charlize Theron");
		names.add("Bar Refaeli");
		names.add("Eva Mendes");
		names.add("Megan Fox");
		names.add("Marisa Miller");
		names.add("Abbey Clancy");
		names.add("Kristen Bell");
		names.add("Kristen Stewart");
		names.add("Kristin Kreuk");
		names.add("Veronica Varekova");
		names.add("Jessica Simpson");
		names.add("Jessica Van Der Steen");
		names.add("Selita Ebanks");
		names.add("Keira Knightley");
		names.add("Adriana Lima");
		names.add("Megan Fox");
		names.add("Petra Nemcova");
		names.add("Molly Sims");
		names.add("Marisa Miller");
		names.add("Bar Refaeli");
		names.add("Gisele Bundchen");
		names.add("Heidi Klum");
		names.add("Halle Berry");
		names.add("Kim Kardashian");
		names.add("Shakira");
		names.add("Jessica Alba");
		names.add("Jessica Simpson");
		names.add("Julianne Moore");
		names.add("Adriana Lima");
		names.add("Alessandra Ambrosio");
		names.add("Charlize Theron");
		names.add("Bar Refaeli");
		names.add("Eva Mendes");
		names.add("Megan Fox");
	}

}
