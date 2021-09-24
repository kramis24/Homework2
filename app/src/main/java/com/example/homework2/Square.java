package com.example.homework2;

public class Square {

    public static final int EMPTY = 0;

    // public variables for each square, number and positions
    public int number;
    public float left;
    public float top;
    public float right;
    public float bottom;

    // constructor
    public Square(int number, float left, float top, float right, float bottom) {

        this.number = number;
        this.left   = left;
        this.top    = top;
        this.right  = right;
        this.bottom = bottom;
    }
}
