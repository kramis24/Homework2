package com.example.homework2;

import android.view.MotionEvent;
import android.view.View;

import androidx.core.view.TintableBackgroundView;

public class SquaresController implements View.OnTouchListener, View.OnClickListener {
    private SquaresView squaresView;
    private SquaresState gameState;

    public SquaresController(SquaresView sv) {
        squaresView = sv;
        gameState = squaresView.getSquaresState();
    }

    @Override
    public void onClick(View view) {

        // switch statement just in case
        switch (view.getId()) {
            case R.id.resetButton:
                gameState.tiles = null;
        }

        // indicates that the grid needs to be redrawn
        squaresView.invalidate();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        // searches grid to find tapped space
        for (int i = 0; i < gameState.tiles.length; i++) {
            for (int j = 0; j < gameState.tiles[i].length; j++) {
                if (motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN
                    && motionEvent.getX() > gameState.tiles[i][j].left
                    && motionEvent.getX() < gameState.tiles[i][j].right
                    && motionEvent.getY() > gameState.tiles[i][j].top
                    && motionEvent.getY() < gameState.tiles[i][j].bottom) {

                    // checks adjacent squares and swaps if adjacent is empty
                    checkAdjacent(i, j);
                    return true;
                }
            }
        }

        return false;
    }

    protected void checkAdjacent(int x, int y) {

        // method variable
        int temp;

        // aborts if selected square is empty
        if (gameState.tiles[x][y].number == Square.EMPTY) {
            return;
        }

        // check adjacent squares for empty if adjacencies exist,
        // swaps numbers if empty and invalidates
        // above selected square
        if (x > 0) {
            if (gameState.tiles[x-1][y].number == Square.EMPTY) {
                temp = gameState.tiles[x][y].number;
                gameState.tiles[x][y].number = gameState.tiles[x-1][y].number;
                gameState.tiles[x-1][y].number = temp;
                squaresView.invalidate();
                return;
            }
        }

        // right of selected square
        if (y > 0) {
            if (gameState.tiles[x][y-1].number == Square.EMPTY) {
                temp = gameState.tiles[x][y].number;
                gameState.tiles[x][y].number = gameState.tiles[x][y-1].number;
                gameState.tiles[x][y-1].number = temp;
                squaresView.invalidate();
                return;
            }
        }

        // below selected square
        if (x < gameState.tiles.length - 1) {
            if (gameState.tiles[x+1][y].number == Square.EMPTY) {
                temp = gameState.tiles[x][y].number;
                gameState.tiles[x][y].number = gameState.tiles[x+1][y].number;
                gameState.tiles[x+1][y].number = temp;
                squaresView.invalidate();
                return;
            }
        }

        // left of selected square
        if (y < gameState.tiles[x].length - 1) {
            if (gameState.tiles[x][y+1].number == Square.EMPTY) {
                temp = gameState.tiles[x][y].number;
                gameState.tiles[x][y].number = gameState.tiles[x][y+1].number;
                gameState.tiles[x][y+1].number = temp;
                squaresView.invalidate();
                return;
            }
        }
    }
}
