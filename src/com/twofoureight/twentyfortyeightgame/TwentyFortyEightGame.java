package com.twofoureight.twentyfortyeightgame;
import java.util.ArrayList;
import java.util.Random;

public class TwentyFortyEightGame {

	public static final int BOARD_WIDTH = 4;
	public static final int BOARD_HEIGHT = 4;
	
	private static final int START_VALUE = 2;
	
	// start with 2 random locations filled with values
	private static final int NUM_START_VALUES = 2;
	
	private enum MoveDirection {
		UP,
		DOWN,
		LEFT,
		RIGHT
	}
	
	private Location[][] board;
	private Random rand;
	private ArrayList<Location> freeList;
	private ArrayList<Location> occupiedList;
	
	public TwentyFortyEightGame() {
		
		rand = new Random();
		
		// initialize our board to track state
		board = new Location[BOARD_WIDTH][BOARD_HEIGHT];
		freeList = new ArrayList<Location>();
		occupiedList = new ArrayList<Location>();
		
		// all locations are available to start
		for (int x = 0; x < BOARD_WIDTH; x++) {
			for (int y = 0; y < BOARD_HEIGHT; y++) {
				board[x][y] = new Location(x,y);
				freeList.add(board[x][y]);
			}
		}
		
		// initialize the start positions
		for (int count = 0; count < NUM_START_VALUES; count++) {
			// choose our two starting points
			Location startLoc = freeList.get(rand.nextInt(freeList.size()));
			// get the location and set its value then put it on the occupied list
			try {
				set(startLoc.x, startLoc.y, START_VALUE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
		
	public Location[][] getBoard() {
		return board;
	}
	
	private void checkBounds(int x, int y) throws Exception {
		if (x >= 0 && x <= BOARD_WIDTH && y >=0 && y <= BOARD_HEIGHT) {
		}
		else {
			throw new Exception("Invalid board location!");
		}
	}
	
	// @brief get the neighbor of a square. If the location is invalid it will throw a exception
	// @param x the x position on the board
	// @param y the y position on the board
	// @param dir the direction to check
	// @return either the value of the neighboring square
	private Location getNeighbor(int x, int y, MoveDirection dir) throws Exception {
		checkBounds(x, y);

		switch(dir){
		case UP:
			if (y-1 < 0) {
				return null;
			}
			return board[x][y-1];
		case DOWN:
			if (y+1 >= BOARD_HEIGHT) {
				return null;
			}
			return board[x][y+1];
		case LEFT:
			if (x-1 < 0) {
				return null;
			}
			return board[x-1][y];
		case RIGHT:
			if (x+1 >= BOARD_WIDTH) {
				return null;
			}
			return board[x+1][y];
		default:
			throw new Exception("Invalid Direction!!");
		}
	}
	
	public void set(int x, int y, int value) throws Exception {
		checkBounds(x, y);
		Location l = board[x][y];
		
		// old value...
		int oldValue = l.value;
		
		// set the new value
		l.value = value;
		
		// move the location from the free list to the occupied list if the values != 0
		// or move it from the occupied to the free list if we are setting the value to 0
		if (oldValue != 0 && value == 0) {
			occupiedList.remove(l);
			freeList.add(l);
		}
		else if (oldValue == 0 && value != 0){
			occupiedList.add(l);
			freeList.remove(l);
		}
	}
	
	public boolean isGameOver() {
		return false;
	}
	
	public void moveDown() throws Exception {
		// loop from the bottom up and combine elements as we go
		for (int x = 0; x < BOARD_WIDTH; x++) {
			for (int y = BOARD_HEIGHT; y >= 0; y--) {
				combine(x, y, MoveDirection.DOWN);
			}
		}
	}
	
	public void moveUp() throws Exception {
		for (int x = 0; x < BOARD_WIDTH; x++) {
			for (int y = 0; y < BOARD_HEIGHT; y++) {
				combine(x, y, MoveDirection.UP);
			}
		}
	}
	
	public void moveLeft() throws Exception {
		for (int y = 0; y < BOARD_HEIGHT; y++) {
			for (int x = 0; x < BOARD_HEIGHT; x++) {
				combine(x, y, MoveDirection.LEFT);
			}
		}
	}
	
	public void moveRight() throws Exception {
		for (int y = 0; y < BOARD_HEIGHT; y++) {
			for (int x = BOARD_WIDTH; x >= 0 ; x--) {
				combine(x, y, MoveDirection.RIGHT);
			}
		}
	}
	
	private void combine(int x, int y, MoveDirection dir) throws Exception {
		Location neighbor = null;
		Location thisLoc = board[x][y];

		neighbor = getNeighbor(x, y, dir);

		// we can only combine if the values are equal
		if (neighbor != null && (neighbor.value == thisLoc.value || neighbor.value == 0)) {
			set(neighbor.x, neighbor.y, neighbor.value + thisLoc.value);
			set(thisLoc.x, thisLoc.y, 0);	
		}
	}
}
