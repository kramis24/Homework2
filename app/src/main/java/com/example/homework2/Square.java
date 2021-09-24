package com.example.homework2;
/**
 * Square
 * Defines variables for the squares in the grid as objects.
 *
 * @author Dylan Kramis
 * @version 9/24/2021 Basic Version
 *
 * Enhancements: none yet, size slider planned
 * Issues: resets when screen rotates
 */

public class Square {

    // define zero as the empty space
    public static final int EMPTY = 0;

    // public variables for each square, number and positions
    public int number;
    public float left;
    public float top;
    public float right;
    public float bottom;

    /**
     * Square
     * Constructor for Square object, information required.
     *
     * @param number number assigned to square
     * @param left left edge
     * @param top top edge
     * @param right right edge
     * @param bottom bottom edge
     */
    public Square(int number, float left, float top, float right, float bottom) {

        this.number = number;
        this.left   = left;
        this.top    = top;
        this.right  = right;
        this.bottom = bottom;
    }
}
