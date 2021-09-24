package com.example.homework2;
/**
 * SquaresState
 * Game state/model of squares puzzle, stores game info.
 *
 * @author Dylan Kramis
 * @version 9/24/2021 Basic Version
 *
 * Enhancements: none yet, size slider planned
 * Issues: resets when screen rotates
 */

public class SquaresState {

    // win condition, currently hardcoded to 4x4 layout
    final public int WIN_TILES[][] = {{1,  2,  3,  4},
                                      {5,  6,  7,  8},
                                      {9,  10, 11, 12},
                                      {13, 14, 15, 0}};

    // game state variables
    public Square tiles[][] = null;

}
