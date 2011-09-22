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
package ywb.c.model;

public class Key {

	public final static Key SWIMWEAR = new Key("Swimwear", "celeb swimwear victoria secret");
	public final static Key WALLPAPER = new Key("Wallpaper", "wallpaper celebs");
	public final static Key OSCARS = new Key("Oscars", "oscars red carpet dress");
	public final static Key GOLDEN_GLOBE = new Key("Golden", "golden globe red carpet dress");
	public final static Key GRAMMY = new Key("Grammy", "grammy red carpet dress");
	public final static Key CANNES = new Key("Cannes", "cannes red carpet dress");

	private final String name;
	private final String searchQuery;

	public Key(String name, String searchQuery) {
		this.name = name;
		this.searchQuery = searchQuery;
	}

	public String getName() {
		return name;
	}

	public String getSearchQuery() {
		return searchQuery;
	}

	@Override
	public String toString() {
		return getName() + ":" + getSearchQuery();
	}

}
