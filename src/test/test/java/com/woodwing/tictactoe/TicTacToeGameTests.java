package com.woodwing.tictactoe;

import com.woodwing.tictactoe.controller.RestController;
import com.woodwing.tictactoe.model.Board;
import com.woodwing.tictactoe.model.GameState;
import com.woodwing.tictactoe.service.ApiClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TicTacToeGameTests {

	@LocalServerPort
	int randomServerPort;

	@Autowired
	private ApiClient apiClient;

	@Autowired
	private GameState gameState;

	private static final Logger log = LoggerFactory.getLogger(RestController.class);


	@Test
	public void testInitEndpoint()  {


		final String baseUrl = "http://localhost:" + randomServerPort + "/tictactoe";

		try {
			apiClient.callUrl(baseUrl);
			Assert.assertEquals(200, apiClient.callUrl(baseUrl).getStatusCodeValue());
		} catch (URISyntaxException e) {
			log.error(e.getMessage());
		}


	}

	@Test
	public void testMoveEndpoint()  {


		final String baseUrl = "http://localhost:" + randomServerPort + "/tictactoe/move?row=0&col=0";

		try {
			apiClient.callUrl(baseUrl);
			Assert.assertEquals(200, apiClient.callUrl(baseUrl).getStatusCodeValue());
		} catch (URISyntaxException e) {
			log.error(e.getMessage());
		}


	}

	//TODO add test for other endpoint
	//TODO if api with return type Map<String, GameState> the api test can go further to get the full body game state
	//TODO mock session can be used too



	//test computer move
	@Test
	public void testBoardMove_X_vs_ComputerO_on_0_0()
	{
		Board board = gameState.getBoard();
		try {

			board.move(0, 0, Board.Cell.X);
			gameState.setTurn(Board.Cell.O);
			//computer
			board.makeRandomMove(gameState);
			gameState.setTurn(Board.Cell.X);
			Assert.assertTrue(checkMove(0,0));

		}
		catch( Exception e )
		{
			log.error(e.getMessage());
		}


	}

	//check row,col for X and random cell for O
	private boolean checkMove(int row, int col) {
		Board board = gameState.getBoard();
		Board.Cell cell = board.board[row][col];

		if (gameState.getTurn().equals(cell))
		{
			for(int r = 0;  r < 3;  r++ ) {
				for(int c = 0;  c < 3;  c++) {
					//the game start by an X move followed by O move
					if(board.board[r][c] == Board.Cell.O)
						return true;
				}
			}
		}
		return false;
	}

	//TODO add test for isWinner and Evalute

}
