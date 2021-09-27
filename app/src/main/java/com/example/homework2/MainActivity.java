package com.example.homework2;
/**
 * MainActivity
 * Initializes design elements and sets listeners.
 *
 * @author Dylan Kramis
 * @version 9/26/2021 Variable Size FINAL
 *
 * Enhancements: size seek bar added to control size, 4x4 to 10x10
 * grids available, default is 4x4
 * Issues: resets when screen rotates
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;

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

        SeekBar sizeSeekBar = findViewById(R.id.sizeSeekBar);

        SquaresController squaresController = new SquaresController(squaresView);
        squaresView.setOnTouchListener(squaresController);
        resetButton.setOnClickListener(squaresController);
        sizeSeekBar.setOnSeekBarChangeListener(squaresController);
    }
}