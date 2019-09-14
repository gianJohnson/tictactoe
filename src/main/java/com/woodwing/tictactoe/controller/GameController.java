package com.woodwing.tictactoe.controller;

import com.woodwing.tictactoe.model.Board;
import com.woodwing.tictactoe.model.GameState;

public class GameController {

    public static void evaluate(GameState gameState) {
        Board board = gameState.getBoard();

        if(board.isWinner(gameState.getTurn())) {
            if(gameState.getTurn().equals(Board.Cell.O)) {
                gameState.setGameMessage("Computer Wins!");
            }
            else {
                if(gameState.getUser()!=null)
                    gameState.setGameMessage(gameState.getUser().userName+" Wins!");
                else
                    gameState.setGameMessage("Please set a name Wins!");

            }
            gameState.setGameStage(GameState.GameStage.POST_GAME);
        }
        else
        {
            if(gameState.getTurn() == Board.Cell.X)
                gameState.setTurn(Board.Cell.O);
            else
                gameState.setTurn(Board.Cell.X);


        }
    }
}
