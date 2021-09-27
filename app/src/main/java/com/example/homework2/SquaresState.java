package com.example.homework2;
/**
 * SquaresState
 * Game state/model of squares puzzle, stores game info.
 *
 * @author Dylan Kramis
 * @version 9/26/2021 Variable Size FINAL
 *
 * Enhancements: size seek bar added to control size, 4x4 to 10x10
 * grids available, default is 4x4
 * Issues: resets when screen rotates
 */

public class SquaresState {

    // win condition, generated in SquaresView
    public int winTiles[][];

    // game state variables
    public Square tiles[][] = null;

}
