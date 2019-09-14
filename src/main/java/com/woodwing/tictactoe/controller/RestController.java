package com.woodwing.tictactoe.controller;

import com.woodwing.tictactoe.model.Board;
import com.woodwing.tictactoe.model.GameState;
import com.woodwing.tictactoe.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class RestController {


	private static final Logger log = LoggerFactory.getLogger(RestController.class);

	//TODO improve api with return type Map<String, GameState> in order to test the logic with end point calls


	@PostMapping("/tictactoe/setUserName")
	public String showPage(@ModelAttribute("someBean") User user, HttpSession session, Model model) {
		model.addAttribute(HttpsUtils.USER, user);
		GameState gameState = HttpsUtils.getStateFromSession(session);
		gameState.setUser(user);
		model.addAttribute(HttpsUtils.GAME_STATE, gameState);
		return HttpsUtils.VIEW_GAME;
	}
	//start a new game
	@RequestMapping(value = "/tictactoe", method = RequestMethod.GET)
	public String init(Model model) {

		GameState gameState = new GameState();
		gameState.startNewGame();
		model.addAttribute(HttpsUtils.GAME_STATE, gameState);
		return HttpsUtils.VIEW_GAME;
	}


	//start a new game
	@RequestMapping(value = "/tictactoe/new", method = RequestMethod.GET)
	public String gameNew(
			HttpSession session,
			Model model) {
		
		GameState gameState = HttpsUtils.getStateFromSession(session);
		if(gameState == null) {
			log.info("start new game");
			gameState = new GameState();
		}
		gameState.startNewGame();
		model.addAttribute(HttpsUtils.GAME_STATE, gameState);


		return HttpsUtils.VIEW_GAME;
	}

	@RequestMapping(value = "/tictactoe/move", method = RequestMethod.GET)
	public String playerMove(
			HttpSession session,
			@RequestParam(value = "row") Integer row,
			@RequestParam(value = "col") Integer col,
			Model model) {
		
		GameState gameState = HttpsUtils.getStateFromSession(session);
		model.addAttribute(HttpsUtils.GAME_STATE, gameState);


		if(!gameState.getGameStage().equals(GameState.GameStage.IN_GAME)) {
			log.info("Game not in progress");
			return HttpsUtils.VIEW_GAME;
		}
		
		Board board = gameState.getBoard();
		try {
			log.info("move=(" + row + ", " + col + ")");
			board.move(row, col, gameState.getTurn());
			GameController.evaluate(gameState);

			//computer
			board.makeRandomMove(gameState);
			GameController.evaluate(gameState);

		}
		catch( Exception e )
		{
			log.error("error", e);
		}
		
		return HttpsUtils.VIEW_GAME;
	}

}
	


