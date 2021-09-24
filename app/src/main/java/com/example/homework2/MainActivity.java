package com.example.homework2;
/**
 * MainActivity
 * Initializes design elements and sets listeners.
 *
 * @author Dylan Kramis
 * @version 9/24/2021 Basic Version
 *
 * Enhancements: none yet, size slider planned
 * Issues: resets when screen rotates
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    /**
     * onCreate
     * Main method, initializes and sets up widgets.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SquaresView squaresView = findViewById(R.id.squaresView);

        Button resetButton = findViewById(R.id.resetButton);

        SquaresController squaresController = new SquaresController(squaresView);
        squaresView.setOnTouchListener(squaresController);
        resetButton.setOnClickListener(squaresController);
    }
}