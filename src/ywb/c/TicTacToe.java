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

import ywb.c.tic.Logic;
import ywb.c.tic.XY;
import ywb.c.ui.ImageButton;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TicTacToe implements EntryPoint {

	public static int TYPE_AI_FIRST = 0;
	public static int TYPE_PLAYER_FIRST = 1;
	public static int TYPE_2PLAYERS = 2;

	/**
	 * <pre>
	 * 0 = 00,
	 * 1 = 01,
	 * 2 = 02,
	 * 3 = 10,
	 * 4 = 11,
	 * 5 = 12,
	 * 6 = 20,
	 * 7 = 21,
	 * 8 = 22
	 * </pre>
	 */

	/**
	 * 
	 */
	private Combination combinaton = new Combination();

	private final Logic logic = new Logic();

	public static enum Side {
		_, X, O
	}

	Side[][] cellValue = new Side[3][3];
	RootPanel[][] cellPanel = new RootPanel[3][3];
	PushButton[][] cellButton = new PushButton[3][3];
	Image[][] cellImg = new Image[3][3];
	final String X = "images/x.png";
	final String O = "images/o.png";
	final String _ = "images/blank.png";

	int type;
	int turn;
	boolean win = false;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				cellPanel[i][j] = RootPanel.get("cell" + i + j);
				cellImg[i][j] = new Image(_);
				cellButton[i][j] = new PushButton(cellImg[i][j], CLICKHANDLER);
				cellPanel[i][j].add(cellButton[i][j]);
			}
		}

		RootPanel controlPanel = RootPanel.get("control");
		VerticalPanel controlPanelH = new VerticalPanel();

		final UiResources RES = UiResources.INSTANCE;
		final ImageButton btn2Players = new ImageButton(RES.btn2PlayersOn());
		final ImageButton btnAiFirst = new ImageButton(RES.btnPlaybookFirst());
		final ImageButton btnPlayerFirst = new ImageButton(RES.btnYouFirst());

		btn2Players.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				btn2Players.setResource(RES.btn2PlayersOn());
				btnAiFirst.setResource(RES.btnPlaybookFirst());
				btnPlayerFirst.setResource(RES.btnYouFirst());
				reset(TYPE_2PLAYERS);
			}
		});
		controlPanelH.add(btn2Players);

		btnAiFirst.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				btn2Players.setResource(RES.btn2Players());
				btnAiFirst.setResource(RES.btnPlaybookFirstOn());
				btnPlayerFirst.setResource(RES.btnYouFirst());
				reset(TYPE_AI_FIRST);
			}
		});

		controlPanelH.add(btnAiFirst);

		btnPlayerFirst.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				btn2Players.setResource(RES.btn2Players());
				btnAiFirst.setResource(RES.btnPlaybookFirst());
				btnPlayerFirst.setResource(RES.btnYouFirstOn());
				reset(TYPE_PLAYER_FIRST);
			}
		});
		controlPanelH.add(btnPlayerFirst);
		controlPanel.add(controlPanelH);

		reset(TYPE_2PLAYERS);
	}

	void move(XY obj, Side side, boolean isAiMove) {
		if (win) {
			return;
		}
		if (!isEmpty(obj.x, obj.y)) {
			return;
		}
		cellValue[obj.x][obj.y] = side;
		if (!isAiMove) {
			combinaton.addToRows(obj);
			combinaton.debug();
		}

		if (side == Side.X) {
			// if (side == 1) {
			cellImg[obj.x][obj.y].setUrl(X);

		} else if (side == Side.O) {
			// } else if (side == 2) {
			cellImg[obj.x][obj.y].setUrl(O);
		}
		XY xyWinner = winner();
		win = (xyWinner != null);
		if (win) {
			// ArrayList<Row> rows = combinaton.getRows(xyWinner);
			ArrayList<XY> winningRow = getWinningRow(xyWinner);
			System.out.println("We have a WINNER @" + xyWinner + ", row " + winningRow + ". Do action here.");
			repaintOnWin(winningRow);
		}
		++turn;
		if (isAiMove()) {
			aiMove();
		}
	}
	
	private void repaintOnWin(ArrayList<XY> winningRow){
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				cellImg[i][j].getElement().getStyle().setOpacity(0.4);
			}
		}
		
		for (XY xy : winningRow) {
			cellImg[xy.x][xy.y].getElement().getStyle().setOpacity(1);
		}
	}

	private ArrayList<XY> getWinningRow(XY w) {

		Side side = cellValue[w.x][w.y];

		ArrayList<XY> reslt = new ArrayList<XY>();
		for (int i = 0; i < 3; i++) {
			if (cellValue[i][w.y] == side) {
				reslt.add(new XY(i, w.y));
			}
		}

		if (reslt.size() == 3) {
			return reslt;
		}

		reslt.clear();
		for (int i = 0; i < 3; i++) {
			if (cellValue[w.x][i] == side) {
				reslt.add(new XY(w.x, i));
			}
		}
		if (reslt.size() == 3) {
			return reslt;
		}

		reslt.clear();
		if (cellValue[0][0] == side && cellValue[1][1] == side && cellValue[2][2] == side) {
			reslt.add(new XY(0, 0));
			reslt.add(new XY(1, 1));
			reslt.add(new XY(2, 2));
			return reslt;
		}
		
		reslt.clear();
		if (cellValue[0][2] == side && cellValue[1][1] == side && cellValue[2][0] == side) {
			reslt.add(new XY(0, 2));
			reslt.add(new XY(1, 1));
			reslt.add(new XY(2, 0));
			return reslt;
		}

		return null;
	}

	void reset(int type) {
		combinaton.reset();
		this.type = type;
		this.turn = 0;
		this.win = false;
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				cellValue[i][j] = Side._;
				cellImg[i][j].setUrl(_);
				cellImg[i][j].getElement().getStyle().setOpacity(1);
			}
		}
		if (isAiMove()) {
			aiMove();
		}
	}

	boolean isAiMove() {
		if (win) {
			return false;

		} else if ((type == TYPE_AI_FIRST) && ((turn & 1) == 0)) {
			return true;

		} else if ((type == TYPE_PLAYER_FIRST) && ((turn & 1) != 0)) {
			return true;

		} else {
			return false;
		}
	}

	void debug() {
		System.out.println("-------------");
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				System.out.print(i + "," + j + "=" + cellValue[i][j] + "\t");
			}
			System.out.println();
		}
	}

	XY randomSpot(int retryCount) {

		int x = Random.nextInt(3);
		int y = Random.nextInt(3);

		if (isEmpty(x, y)) {
			return new XY(x, y);

		} else if (retryCount < 5) {
			return randomSpot(++retryCount);

		} else {
			boolean iterateReverse = Random.nextBoolean();

			if (iterateReverse) {

				for (int i = 2; i > -1; --i) {
					for (int k = 2; k > -1; --k) {
						if (isEmpty(i, k)) {
							return new XY(i, k);
						}
					}
				}
			} else {

				for (int i = 0; i < 3; ++i) {
					for (int k = 0; k < 3; ++k) {
						if (isEmpty(i, k)) {
							return new XY(i, k);
						}
					}
				}
			}
		}
		throw new RuntimeException("all spots are filled");
	}

	void aiMove() {
		// XY xy = logic.move(turnToSide(turn), cellValue);
		// move(xy.x, xy.y, turnToSide(turn));

		debug();

		Timer t = new Timer() {
			public void run() {
				XY xy = null;
				ArrayList<XY> potentialWins = combinaton.getPotentialWin();
				System.out.println("getPotentialWin=" + potentialWins);
				for (XY p : potentialWins) {
					if (isEmpty(p.x, p.y)) {
						xy = p;
						break;
					}
				}
				System.out.println("SINGLE PotentialWin=" + xy);
				if (xy == null) {
					xy = randomSpot(0);
				}
				move(xy, turnToSide(turn), true);
			}
		};
		t.schedule(400);
	}

	private boolean isEmpty(int x, int y) {
		return (cellValue[x][y] == Side._);
	}

	XY winner() {
		Side side;
		for (int i = 0; i < 3; ++i) {
			side = winTest(i, 0, 0, 1);
			if (side != Side._) {
				XY xy = new XY(i, 0);
				return xy;
			}
			side = winTest(0, i, 1, 0);
			if (side != Side._) {
				XY xy = new XY(0, i);
				return xy;
			}
		}

		side = winTest(0, 0, 1, 1);
		if (side != Side._) {
			XY xy = new XY(0, 0);
			return xy;
		}

		side = winTest(0, 2, 1, -1);
		if (side != Side._) {
			XY xy = new XY(0, 2);
			return xy;
		}

		return null;
	}

	Side winTest(int x, int y, int dx, int dy) {
		if (cellValue[x][y] != cellValue[x + dx][y + dy]) {
			return Side._;
		}
		if (cellValue[x][y] != cellValue[x + dx + dx][y + dy + dy]) {
			return Side._;
		}
		return cellValue[x][y];
	}

	final ClickHandler CLICKHANDLER = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			if (isAiMove()) {
				return;
			}
			PushButton button = (PushButton) event.getSource();
			int myI = -1, myJ = -1;
			for (int i = 0; i < 3; ++i) {
				for (int j = 0; j < 3; ++j) {
					if (button == cellButton[i][j]) {
						myI = i;
						myJ = j;
					}
				}
			}
			move(new XY(myI, myJ), turnToSide(turn), false);
		}
	};

	Side turnToSide(int turn) {
		return ((turn & 1) == 0) ? Side.X : Side.O;
		// return ((turn & 1) == 0) ? 1 : 2;
	}

}
