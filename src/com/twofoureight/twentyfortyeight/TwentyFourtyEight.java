package com.twofoureight.twentyfortyeight;

import com.twofoureight.twentyfortyeightgame.TwentyFortyEightGame;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class TwentyFourtyEight extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_twenty_fourty_eight);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.twenty_fourty_eight, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_twenty_fourty_eight, container, false);
			
			TwentyFortyEightView twentyFortyEightView = (TwentyFortyEightView)rootView.findViewById(R.id.twnetyfortyeightview);
			twentyFortyEightView.setClickable(true);
			twentyFortyEightView.setFocusable(true);
			
			final GestureDetector detector = new GestureDetector(twentyFortyEightView.getContext(),
					                                             new GameGestureDetector(twentyFortyEightView));
			twentyFortyEightView.setOnTouchListener(new View.OnTouchListener() {
	            @Override
	            public boolean onTouch(View view, MotionEvent motionEvent) {
	            	detector.onTouchEvent(motionEvent);
	                return false;
	            }
	        });
			return rootView;
		}
	}

}
