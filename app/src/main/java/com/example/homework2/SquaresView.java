package com.example.homework2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class SquaresView extends SurfaceView {

    // paints
    Paint outlinePaint = new Paint();
    Paint squarePaint  = new Paint();
    Paint numberPaint  = new Paint();
    Paint emptyPaint   = new Paint();

    // dimensions, not hardcoding these
    public static float centerVertical;
    public static float centerHorizontal;
    public static float gridHeight;
    public static float gridWidth;
    public static float gridTop;
    public static float gridLeft;
    public static float gridBottom;
    public static float gridRight;

    // state/model
    private SquaresState gameState;

    public SquaresView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // letting tablet know things will be drawn
        setWillNotDraw(false);

        // color palette
        outlinePaint.setColor(Color.BLACK);
        outlinePaint.setStyle(Paint.Style.FILL);
        squarePaint.setColor(Color.WHITE);
        squarePaint.setStyle(Paint.Style.FILL);
        numberPaint.setColor(Color.BLUE);
        numberPaint.setStyle(Paint.Style.FILL);
        emptyPaint.setColor(0xFFBFBFBF);// light gray
        emptyPaint.setStyle(Paint.Style.FILL);

        setBackgroundColor(0xFF00FFFF);// electric cyan

        // initialize game state
        gameState = new SquaresState();
    }

    public void onDraw(Canvas canvas) {
        // setting dimensions
        centerVertical   = this.getHeight() / 2;
        centerHorizontal = this.getWidth()  / 2;

        if (this.getHeight() < this.getWidth()) {
            gridHeight = (3 * this.getHeight()) / 4;
            gridWidth  = (3 * this.getHeight()) / 4;
        }

        else {
            gridHeight = (3 * this.getWidth()) / 4;
            gridWidth  = (3 * this.getWidth()) / 4;
        }

        gridTop = centerVertical - (gridHeight / 2);
    }
}
