package com.example.homework2;

import static java.lang.Math.pow;

import android.annotation.SuppressLint;
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
    public static float gridWidth;
    public static float gridHeight;
    public static float gridLeft;
    public static float gridTop;
    public static float gridRight;
    public static float gridBottom;

    // grid size, if grid is n*n slots, this is n
    public static int gridSize = 4;// this may change later

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
        numberPaint.setTextAlign(Paint.Align.CENTER);
        emptyPaint.setColor(0xFFBFBFBF);// light gray
        emptyPaint.setStyle(Paint.Style.FILL);

        setBackgroundColor(0xFF00FFFF);// electric cyan

        // initialize game state
        gameState = new SquaresState();
    }

    @SuppressLint("DrawAllocation")
    public void onDraw(Canvas canvas) {
        // setting center
        centerVertical   = this.getHeight() / 2;
        centerHorizontal = this.getWidth()  / 2;

        // setting grid dimensions based on height or width of this view,
        // whichever is shorter
        if (this.getHeight() < this.getWidth()) {
            gridWidth  = (3 * this.getHeight()) / 4;
            gridHeight = (3 * this.getHeight()) / 4;
        }

        else {
            gridWidth  = (3 * this.getWidth()) / 4;
            gridHeight = (3 * this.getWidth()) / 4;
        }

        // setting boundaries for grid based on center
        gridLeft = centerHorizontal - (gridWidth / 2);
        gridTop = centerVertical - (gridHeight / 2);
        gridRight = centerHorizontal + (gridWidth / 2);
        gridBottom = centerVertical + (gridHeight / 2);

        // setting tet size
        numberPaint.setTextSize(gridHeight / 8);

        // drawing grid outline
        canvas.drawRect(gridLeft, gridTop, gridRight, gridBottom, outlinePaint);

        // initialize tiles if none are found
        if (gameState.tiles == null) {
            initializeTiles();
        }

        // drawing tiles
        for (int i = 0; i < gameState.tiles.length; i++) {
            for (int j = 0; j < gameState.tiles[i].length; j++) {
                drawSquare(gameState.tiles[i][j], canvas);
            }
        }
    }

    protected void drawSquare(Square sq, Canvas canvas) {

        // draws square slightly smaller than given, gray if number is empty
        if (sq.number == Square.EMPTY) {
            canvas.drawRect(sq.left + 5, sq.top + 5, sq.right - 5,
                     sq.bottom - 5, emptyPaint);
        }

        // other
        else {
            canvas.drawRect(sq.left + 5, sq.top + 5, sq.right - 5,
                     sq.bottom - 5, squarePaint);

            canvas.drawText(Integer.toString(sq.number), (sq.left + sq.right) / 2,
                          sq.bottom - (gridHeight / 16), numberPaint);
        }
    }

    protected void initializeTiles() {

        // initializing tile array
        gameState.tiles = new Square[gridSize][gridSize];

        // assigning number and dimensions for every tile
        for (int i = 0; i < gameState.tiles.length; i++) {
            for (int j = 0; j < gameState.tiles[i].length; j++) {
                gameState.tiles[i][j] = new Square(randomize(),
                        gridLeft + ((j * gridWidth) / gridSize),
                        gridTop + ((i * gridHeight) / gridSize),
                        gridLeft + (((j + 1) * gridWidth) / gridSize),
                        gridTop + (((i + 1) * gridHeight) / gridSize));
            }
        }
    }

    protected int randomize() {
        // sets number to a random value
        int num = (int) Math.round((Math.pow(gridSize, 2) - 1) * Math.random());

        // verifies that the number is not a repeat, rerolls if it is
        for (int i = 0; i < gameState.tiles.length; i++) {
            for (int j = 0; j < gameState.tiles[i].length; j++) {
                if (gameState.tiles[i][j] != null) {
                    if (gameState.tiles[i][j].number == num) {
                        return randomize();
                    }
                }
            }
        }

        return num;
    }

    public SquaresState getSquaresState() {
        return gameState;
    }
}
