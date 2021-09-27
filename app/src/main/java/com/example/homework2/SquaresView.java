package com.example.homework2;
/**
 * SquaresView
 * View of squares puzzle, draws the puzzle in its current state.
 *
 * @author Dylan Kramis
 * @version 9/26/2021 Variable Size FINAL
 *
 * Enhancements: size seek bar added to control size, 4x4 to 10x10
 * grids available, default is 4x4
 * Issues: resets when screen rotates
 */

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

    /**
     * SquaresView
     * Constructor for the SquaresView object, sets up variables
     * necessary for operation.
     *
     * @param context
     * @param attrs
     */
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

        // initialize game state
        gameState = new SquaresState();
    }

    /**
     * onDraw
     * Draws everything in the SquaresView object.
     *
     * @param canvas does the drawing
     */
    @SuppressLint("DrawAllocation")
    public void onDraw(Canvas canvas) {

        // setting win condition
        gameState.winTiles = generateWin();

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

        // setting text size
        numberPaint.setTextSize(gridHeight / (gridSize * 2));

        // initialize tiles if none are found
        if (gameState.tiles == null) {
            initializeTiles();
        }

        // setting background color, green if win condition met
        if (checkWin()) {
            setBackgroundColor(Color.GREEN);
        }

        // cyan otherwise
        else {
            setBackgroundColor(Color.CYAN);
        }

        // drawing grid outline
        canvas.drawRect(gridLeft, gridTop, gridRight, gridBottom, outlinePaint);

        // drawing tiles
        for (int i = 0; i < gameState.tiles.length; i++) {
            for (int j = 0; j < gameState.tiles[i].length; j++) {
                drawSquare(gameState.tiles[i][j], canvas);
            }
        }
    }

    /**
     * drawSquare
     * Helper method for onDraw, draws the numbered squares making up
     * the grid of tiles.
     *
     * @param sq Square object being drawn, holds boundary info
     * @param canvas does the drawing
     */
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
                          sq.bottom - (gridHeight / (gridSize * 4)), numberPaint);
        }
    }

    /**
     * initializeTiles
     * Helper method for onDraw, initializes numbers and boundaries
     * for each tile in game state.
     */
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

    /**
     * randomize
     * Helper method for initializeTiles, recursively generates a random number
     * that hasn't already been taken by another tile.
     *
     * @return unique randomized int
     */
    protected int randomize() {

        // sets number to a random value
        int num = (int) Math.round((Math.pow(gridSize, 2) - 1) * Math.random());

        // verifies that the number is not a repeat, recursively rerolls if it is
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

    /**
     * getSquaresState
     * Returns the game state of the squares puzzle.
     *
     * @return game state of the squares puzzle
     */
    public SquaresState getSquaresState() {
        return gameState;
    }

    /**
     * checkWin
     * Helper method for onDraw, checks for differences between the current grid
     * of squares and the game state's win condition.
     *
     * @return true if game won, false otherwise.
     */
    protected boolean checkWin() {

        // looks for tiles that don't match
        for (int i = 0; i < gameState.tiles.length; i++) {
            for (int j = 0; j < gameState.tiles[i].length; j++) {
                if (gameState.tiles[i][j].number != gameState.winTiles[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * generateWin
     * Generates the win condition for every possible size.
     *
     * @return new win condition
     */
    protected int[][] generateWin() {

        // method variables
        int win[][] = new int[gridSize][gridSize];
        int count = 1;

        // generation using for loops, sets each spot to count
        // unless count is equal to gridsize squared and iterates
        // count by 1 each time
        for (int i = 0; i < win.length; i++) {
            for(int j = 0; j < win[i].length; j++) {
                if (count == gridSize * gridSize) {
                    win[i][j] = 0;
                }
                else {
                    win[i][j] = count;
                }

                count++;
            }
        }

        // returns completed array
        return win;
    }
}
