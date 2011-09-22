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
package ywb.c.tic;

public class Logic {

	AI ai = new AI();

	public XY move(int side, int[][] board) {
		int[][] thinker = new int[3][3];
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				int b = board[i][j];
				thinker[i][j] = (b == side) ? 1 : (b == 0) ? 0 : -1;
			}
		}

		// ArrayList<int[]> ret = listMove(thinker);
		//
		// int retSize = ret.size();
		// Random ran = new Random();
		// int retIdx = ran.nextInt(retSize);
		//
		// return ret.get(retIdx);

		return ai.move(thinker);
	}

}
