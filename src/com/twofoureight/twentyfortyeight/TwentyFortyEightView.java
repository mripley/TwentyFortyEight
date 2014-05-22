package com.twofoureight.twentyfortyeight;

import com.twofoureight.twentyfortyeightgame.TwentyFortyEightGame;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class TwentyFortyEightView extends View {

	// width and height for the board.
	private int boardWidth;  // how many cells in the X direction
	private int boardHeight; // how many cells in the Y direction
	private int cellPadding; // how much padding around each cell
	private int cellWidth;   // cell width in pixels
	private int cellHeight;  // cell height in pixels
	
	private int viewWidth;   // the width of the view
	private int viewHeight;  // the height of the view
	
	private Paint backgroundPaint; // the painter for background
	private Paint cellPaint;       // the painter for the cells
	private Paint textPaint;       // the painter for the text in the cells
	
	private TwentyFortyEightGame game;
	public TwentyFortyEightView(Context context, AttributeSet attrs) {
	    super(context, attrs);
	    init(context, attrs);
	}
	
	public TwentyFortyEightView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs);
	}
	
	private void init(Context c, AttributeSet attrs) {
		setWillNotDraw(false);
		TypedArray a = c.getTheme().obtainStyledAttributes(
		        attrs,
		        R.styleable.TwentyFortyEightView,
		        0, 0);

		   // grab the attributes for this view
		   try {
		       boardWidth = a.getInteger(R.styleable.TwentyFortyEightView_width, 0);
		       boardHeight = a.getInteger(R.styleable.TwentyFortyEightView_height, 0);
		       cellPadding = a.getInteger(R.styleable.TwentyFortyEightView_cellPadding, 0);
		   } finally {
		       a.recycle();
		   }

		   game = new TwentyFortyEightGame();
		   
		   // temp hack. Remove later
		   boardWidth = TwentyFortyEightGame.BOARD_WIDTH;
		   boardHeight = TwentyFortyEightGame.BOARD_HEIGHT;
		   
		   // set up all the paints needed for drawing
		   backgroundPaint = new Paint();
		   cellPaint = new Paint();
		   textPaint = new Paint();
		   backgroundPaint.setColor(Color.rgb(85, 85, 85));
		   backgroundPaint.setStyle(Paint.Style.FILL);
		   
		   cellPaint.setColor(Color.rgb(136, 136, 136));
		   cellPaint.setStyle(Paint.Style.FILL);
		   
		   textPaint.setColor(Color.WHITE);
		   textPaint.setStyle(Paint.Style.FILL);
		   textPaint.setTextSize(45);
		   textPaint.setTextAlign(Paint.Align.CENTER);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		// the width of the view is always the same. We only care about computing cell width and padding
	    viewWidth = MeasureSpec.getSize( widthMeasureSpec );
	    viewHeight = MeasureSpec.getSize( heightMeasureSpec );
	    
	    // we use the min dimension so we get the entire board on screen
	    int minDim = viewWidth > viewHeight ? viewHeight : viewWidth; 
	    
	    cellWidth = (minDim / boardWidth) - (cellPadding * boardWidth);
	    cellHeight = (minDim / boardHeight) - (cellPadding * boardHeight);
	    
	    setMeasuredDimension( viewWidth, viewHeight );
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		final int minDim = viewWidth > viewHeight ? viewHeight : viewWidth;
		
		// our start positions are known and pre computed
		final int startX = cellPadding * (boardWidth / 2);
		final int startY = cellPadding * (boardHeight / 2);
		
		final int textOffsetX = cellWidth / 2;  // precompute the offset
		final int textOffsetY = (cellHeight / 2) + 10;  // the y dimension needs a bit of help to handle the actual text.
		int cellX = 0;
		int cellY = 0;
		
		// draw the background rec
		canvas.drawRect(new RectF(0, 0, minDim, minDim), backgroundPaint);

		for (int x = startX; x < minDim; x += minDim/4) {
			for (int y = startY; y < minDim; y += minDim/4) {
				canvas.drawRect(new RectF(x, y, x+cellWidth, y+cellWidth), cellPaint);
				canvas.drawText(Integer.toString(game.getBoard()[cellX][cellY].value), x + textOffsetX, y + textOffsetY, textPaint);
				
				// wrap around so we stay within out array bounds
				cellY = (cellY+1) % boardHeight;
			}
			cellX++;
		}
	}
}
