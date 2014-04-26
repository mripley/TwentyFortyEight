package com.twofoureight.twentyfortyeightgame;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TwentyFortyEightGameTest {

	
	private TwentyFortyEightGame game;
	
	private int placesSet(Location[][] board)
	{
		int count = 0;
		for (int x=0; x < TwentyFortyEightGame.BOARD_WIDTH; x++) {
			for (int y=0; y < TwentyFortyEightGame.BOARD_HEIGHT; y++) {
				if (board[x][y].value != 0) {
					count++;
				}
			}
		}
		return count;
	}
	
	
	@Before
	public void setup() {
		game = new TwentyFortyEightGame();
	}
	
	// test that the board is initialized correctly
	@Test
	public void initTest() {
		Location[][] board = game.getBoard();
		
		assertTrue(placesSet(board) == 2);
	}

	private void clearBoard(TwentyFortyEightGame game) {
		// clear the board
		for (int x = 0; x < TwentyFortyEightGame.BOARD_WIDTH; x++) {
			for (int y = 0; y < TwentyFortyEightGame.BOARD_WIDTH; y++) {
				try {
					game.set(x, y, 0);
				} catch (Exception e) {
					fail("Exception thrown!: " + e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	public void basicSetTest() {
		try {
			game.set(TwentyFortyEightGame.BOARD_WIDTH/2, TwentyFortyEightGame.BOARD_HEIGHT/2, 4);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		Location[][] board = game.getBoard();
		assertTrue(board[TwentyFortyEightGame.BOARD_WIDTH/2][TwentyFortyEightGame.BOARD_HEIGHT/2].value == 4);
		
	}
	
	// test that the basic up command is working
	@Test
	public void BasicUpTest() {

		clearBoard(game);
		try {
			game.set(0, 0, 2);
			game.set(0, 1, 2);
			
			game.moveUp();
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception thrown!: " + e.getMessage());
		}
		
		assertTrue(game.getBoard()[0][0].value == 4);
	}
	
	// test that the basic down command is working
	@Test
	public void BasicDownTest() {
		fail("Not implemented yet");
		clearBoard(game);
		try {
			game.set(0, 0, 2);
			game.set(0, 1, 2);
			
			game.moveDown();
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception thrown!: " + e.getMessage());
		}
		
		assertTrue(game.getBoard()[0][TwentyFortyEightGame.BOARD_HEIGHT].value == 4);
	}
	
	// test that the basic left command is working
	@Test
	public void BasicLeftTest() {
		fail("Not implemented yet");
	}
	
	// test that the basic left command is working
	@Test
	public void BasicRightTest() {
		fail("Not implemented yet");
	}

}
