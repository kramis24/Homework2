package com.example.homework2;

public class SquaresState {

    // win condition, currently hardcoded to 4x4 layout
    final public int WIN_TILES[][] = {{1,  2,  3,  4},
                                      {5,  6,  7,  8},
                                      {9,  10, 11, 12},
                                      {13, 14, 15, 0}};

    // game state variables
    public Square tiles[][] = null;

}
