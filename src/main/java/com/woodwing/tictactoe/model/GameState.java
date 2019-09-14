package com.woodwing.tictactoe.model;

import org.springframework.stereotype.Component;

@Component
public class GameState {

	
	public enum GameStage {
		IN_GAME,
		POST_GAME
	}
	
	private String gameMessage;
	private Board.Cell turn;
	private GameStage gameStage;
	private Board board;

	public User getUser() {
		return user;
	}

	private User user;

	public void setUser(User user) {
		this.user = user;
	}

	public GameState()
	{
		board = new Board();
		startNewGame();
	}

	public void startNewGame()
	{
		board.clear();
		setGameMessage("");
		setTurn(Board.Cell.X);
		setGameStage(GameStage.IN_GAME);
	}
	


	public String getGameMessage() {
		return gameMessage;
	}

	public void setGameMessage(String playMessage) {
		this.gameMessage = playMessage;
	}

	public Board.Cell getTurn() {
		return turn;
	}

	public void setTurn(Board.Cell turn) {
		this.turn = turn;
	}

	public GameStage getGameStage() {
		return gameStage;
	}
	
	public void setGameStage(GameStage gameStage) {
		this.gameStage = gameStage;
	}

	public Board getBoard() {
		return board;
	}

		
}
