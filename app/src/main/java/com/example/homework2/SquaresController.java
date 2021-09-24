package com.example.homework2;

import android.view.MotionEvent;
import android.view.View;

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

        squaresView.invalidate();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
