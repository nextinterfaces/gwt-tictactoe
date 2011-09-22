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

public class qwe {

	public static void main(String[] args) {
		System.out.println(Math.round(0.3) + " [0.3] " + Math.floor(0.3));
		System.err.println("----");
		System.out.println(Math.round(0.9) + " [0.9] " + Math.floor(0.9));
		System.err.println("----");
		System.out.println(Math.round(0.75) + " [0.75] " + Math.floor(0.75));
		System.err.println("----");
		System.out.println(Math.round(0.45) + " [0.45] " + Math.floor(0.45));
		System.out.println(Math.round(0.55) + " [0.55] " + Math.floor(0.55));
		

		double d =  (double)1126/(double)635;
		System.err.println(d);
		int i = (int) Math.round( d);
		
		System.err.println(i);
	}
}
