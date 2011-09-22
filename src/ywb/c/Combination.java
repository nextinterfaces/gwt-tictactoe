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

import java.util.ArrayList;
import java.util.HashMap;

import ywb.c.tic.XY;

public class Combination {

	public static class Row {
		String key;
		String value;
		int numCells = 0;

		Row(String key_) {
			this.key = key_;
			this.value = "";
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void addXY(XY xy) {
			if (!this.value.contains(xy.getName())) {
				this.value += "-" + xy.getName();
				numCells++;
			}
		}

		public int numberCells() {
			return numCells;
		}

		public XY getLastSpot() {
			String[] usedSpots = value.split("-");
			String kkk = key;
			for (String s : usedSpots) {
				kkk = kkk.replace(s, "");
			}
			kkk = kkk.replace("_", "");
			return new XY(kkk);
		}

		@Override
		public String toString() {
			return key + "=" + value + ", " + numCells;
		}

	}

	private HashMap<String, Row> map = new HashMap<String, Row>();

	public Combination() {
		reset();
	}

	public void reset() {

		map.clear();
		Row r0 = new Row("00_01_02");
		Row r1 = new Row("10_11_12");
		Row r2 = new Row("20_21_22");

		Row r3 = new Row("00_10_20");
		Row r4 = new Row("01_11_21");
		Row r5 = new Row("02_12_22");

		Row r6 = new Row("00_11_22");
		Row r7 = new Row("02_11_20");

		map.put(r0.getKey(), r0);
		map.put(r1.getKey(), r1);
		map.put(r2.getKey(), r2);
		map.put(r3.getKey(), r3);
		map.put(r4.getKey(), r4);
		map.put(r5.getKey(), r5);
		map.put(r6.getKey(), r6);
		map.put(r7.getKey(), r7);
	}

	public ArrayList<Row> getRows(XY xy) {
		ArrayList<Row> arr = new ArrayList<Row>();

		for (String key : map.keySet()) {
			if (key.contains(xy.getName())) {
				arr.add(map.get(key));
			}
		}
		return arr;
	}

	public void addToRows(XY xy) {
		for (String key : map.keySet()) {
			String name = xy.getName();
			if (key.contains(name)) {
				Row r = map.get(key);
				r.addXY(xy);
				String blah = null;
			}
		}
	}

	public ArrayList<XY> getPotentialWin() {

		ArrayList<XY> potenialWins = new ArrayList<XY>();
		for (String key : map.keySet()) {
			Row r = map.get(key);
			if (r.numberCells() == 2) {
				potenialWins.add(r.getLastSpot());
			}
		}
		return potenialWins;
	}

	public Row getDoubleCellRow(XY xy) {
		for (String key : map.keySet()) {
			if (key.contains(xy.getName())) {
				Row r = map.get(key);
				if (r.numberCells() == 2) {
					return r;
				}
			}
		}
		return null;
	}

	void debug() {
		System.out.println("-------Combinations------");

		for (String key : map.keySet()) {
			System.out.println(map.get(key));
		}
	}

}
