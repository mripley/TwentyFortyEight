package com.twofoureight.twentyfortyeight;

import com.twofoureight.twentyfortyeightgame.TwentyFortyEightGame;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

class GameGestureDetector extends GestureDetector.SimpleOnGestureListener {
	
	private TwentyFortyEightGame game;
	private TwentyFortyEightView view;
	public GameGestureDetector(TwentyFortyEightView view){
		this.view = view;
		this.game = view.getGame();
	}
	
	@Override
	public boolean onDown(MotionEvent e) {
		return true;
	}
	
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

		float xDiff = e1.getX() - e2.getX();;
		float yDiff = e1.getY() - e2.getY();;
	
		try {
		// are we doing x things
		if (Math.abs(xDiff) >= Math.abs(yDiff)) {
			// going left
			if (xDiff >= 0) {
				Log.i("MOVE", "Going left!");
				game.moveLeft();
			}
			else { // going right
				Log.i("MOVE", "Going right!");
				game.moveRight();
			}
		}
		else { // nope we are doing y things
			// going up
			if (yDiff >= 0) {
				Log.i("MOVE", "Going up!");
				game.moveUp();
			}
			else { // going down
				Log.i("MOVE", "Going down!");
				game.moveDown();
			}
		}
		view.reDraw();
		} catch (Exception e) {
			Log.i("ERROR", "Where are we going?");
			e.printStackTrace();
		}
		return true;
	}
}