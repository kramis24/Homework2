package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SquaresController squaresController = new SquaresController();

        SquaresView squaresView = findViewById(R.id.squaresView);
        squaresView.setOnTouchListener(squaresController);

        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(squaresController);
    }
}