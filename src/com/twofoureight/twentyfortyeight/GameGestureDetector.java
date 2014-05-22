package com.twofoureight.twentyfortyeight;

import com.twofoureight.twentyfortyeightgame.TwentyFortyEightGame;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

class GameGestureDetector extends GestureDetector.SimpleOnGestureListener {
	
	private TwentyFortyEightGame game;
	public GameGestureDetector(TwentyFortyEightGame game){
		this.game = game;
	}
	
	@Override
	public boolean onDown(MotionEvent e) {
		return true;
	}
	
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		Log.i("GESTURE", "Got a fling event");
		return true;
	}
}