package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

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