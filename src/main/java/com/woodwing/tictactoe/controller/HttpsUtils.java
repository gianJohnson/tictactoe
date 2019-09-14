package com.woodwing.tictactoe.controller;

import com.woodwing.tictactoe.model.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

public class HttpsUtils {

    public static final String GAME_STATE = "gameState";
    public static final String VIEW_GAME = "tictactoe";
    public static final String USER = "user";

    private static final Logger log = LoggerFactory.getLogger(RestController.class);

    public static GameState getStateFromSession(HttpSession session)
    {
        GameState gameState = (GameState)session.getAttribute(GAME_STATE);
        if(gameState == null) {
            log.info("New GameState created and put in session");
            gameState = new GameState();
            putStateInSession(session, gameState);
        }
        return gameState;
    }


    public static void putStateInSession(HttpSession session, GameState gameState) {
        session.setAttribute(GAME_STATE, gameState);
    }
}
