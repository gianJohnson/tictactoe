package com.woodwing.tictactoe.model;


import java.util.Random;

public class Board {
	public enum Cell
	{
		BLANK,
		X,
		O
	}
	
	//matrix of cells
	public Cell[][] board = new Cell[3][3];



	public void clear() {
		for(int r = 0;  r < 3;  r++ ) {
			for(int c = 0;  c < 3;  c++) {
				board[r][c] = Cell.BLANK;
			}
		}
	}
	
	//TODO  change enum to (BLANK,""), (X, "X")..

	public String markAt(int row, int col)
	{
		Cell cell = board[row][col];
		if(cell.equals(Cell.X)) {
			return "X";
		}
		else if(cell.equals(Cell.O)) {
			return "O";
		}
		else if(cell.equals(Cell.BLANK)) {
			return " ";
		}
		return "err";
	}

	public void makeRandomMove(GameState gameState) throws Exception {

		// Keep generating random positions until a blank spot is found
		boolean found = false;
		Random random = new Random();
		int r, c;
		while (!found) {
			r = random.nextInt(3);
			c = random.nextInt(3);
			if (gameState.getBoard().board[r][c].equals(Board.Cell.BLANK)) {
				gameState.getBoard().move(r, c, gameState.getTurn());
				found = true;

			}
		}

	}
	public void move(int row, int col, Cell cell) throws Exception {
		board[row][col] = cell;
	}
	
	//TODO
	//add method to check if game is draw


	public boolean isWinner(Cell cell) {
		// rows
		boolean isWinner;
		for(int r = 0; 3 > r;  r++) {
			isWinner = true;
			for(int c = 0; (isWinner && c < 3); c++) {
				if(board[r][c] != cell) {
					isWinner = false;
				}
			}
			if(isWinner) {
				return true;
			}
		}
		
		// cols
		for(int c = 0; c < 3;  c++) {
			isWinner = true;
			for(int r = 0; (isWinner && r < 3); r++) {
				if(board[r][c] != cell) {
					isWinner = false;
				}
			}
			if(isWinner) {
				return true;
			}
		}
		
		// diagonals
		if((board[0][0] == cell) && (board[1][1] == cell) && (board[2][2] == cell)) {
			return true;
		}
		if((board[2][0] == cell) && (board[1][1] == cell) && (board[0][2] == cell)) {
			return true;
		}
		
		return false;
	}

}
