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

import java.util.ArrayList;

import com.google.gwt.user.client.Random;


public class AI {

//	Random ran = new Random();

	public XY move(int[][] thinker) {
		ArrayList<XY> ret = listMove(thinker);

		int retSize = ret.size();
		int retIdx = Random.nextInt(retSize);

		return ret.get(retIdx);
	}

	public ArrayList<XY> listMove(int[][] thinker) {
		ArrayList<XY> ret = new ArrayList<XY>();
		int maxScore = -20;//-200;
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				if (thinker[i][j] != 0) {
					continue;
				}
				int[][] c = invBoard(thinker);
				c[i][j] = -1;
				int score = -max(c);
				if (score > maxScore) {
					maxScore = score;
					ret.clear();
				}
				if (score == maxScore) {
					XY xy = new XY(i, j);
					ret.add(xy);
				}
			}
		}
		return ret;
	}

	public int max(int[][] board) {
		int ret = -20;//-200;

		int winner = winner(board);
		if (winner > 0) {
			return 10;//100;
		} else if (winner < 0) {
			return -10;//-100;
		}

		boolean noEmpty = true;
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				if (board[i][j] != 0) {
					continue;
				}
				noEmpty = false;
				int[][] c = invBoard(board);
				c[i][j] = -1;
				int score = -max(c);
				if (score > ret) {
					ret = score;
				}
			}
		}
		if (noEmpty) {
			return 0;
		} else if (ret == 0) {
			return 0;
		} else if (ret < 0) {
			return ret + 1;
		} else {
			return ret - 1;
		}

	}

	public int[][] invBoard(int[][] in) {
		int[][] ret = new int[3][3];
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				ret[i][j] = -in[i][j];
			}
		}
		return ret;
	}

	int winner(int board[][]) {
		int t;
		for (int i = 0; i < 3; ++i) {
			t = winTest(board, i, 0, 0, 1);
			if (t != 0) {
				return t;
			}
			t = winTest(board, 0, i, 1, 0);
			if (t != 0) {
				return t;
			}
		}
		t = winTest(board, 0, 0, 1, 1);
		if (t != 0) {
			return t;
		}
		t = winTest(board, 0, 2, 1, -1);
		if (t != 0) {
			return t;
		}
		return 0;
	}

	int winTest(int board[][], int x, int y, int dx, int dy) {
		if (board[x][y] != board[x + dx][y + dy]) {
			return 0;
		}
		if (board[x][y] != board[x + dx + dx][y + dy + dy]) {
			return 0;
		}
		return board[x][y];
	}

}
