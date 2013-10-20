package com.alex.tetris.model;

/**
 * Created with IntelliJ IDEA.
 * User: alexandreduhem
 * Date: 20/10/13
 * Time: 15:02
 * To change this template use File | Settings | File Templates.
 */
public class Piece {

    public static final int SHAPE_O = 0;
    public static final int SHAPE_I = 1;
    public static final int SHAPE_S = 2;
    public static final int SHAPE_Z = 3;
    public static final int SHAPE_L = 4;
    public static final int SHAPE_J = 5;
    public static final int SHAPE_T = 6;

    private int[][] coordinates;

    private static final int[][] COORD_0 = {{0, 0},{1, 0},{1, 1},{0, 1}};
    private static final int[][] COORD_I = {{0, 0},{0, 1},{0, 2},{0, 3}};
    private static final int[][] COORD_S = {{0, 0},{1, 0},{1, 1},{2, 1}};
    private static final int[][] COORD_Z = {{0, 1},{1, 1},{1, 0},{2, 0}};
    private static final int[][] COORD_L = {{0, 2},{0, 1},{0, 0},{1, 0}};
    private static final int[][] COORD_J = {{0, 0},{1, 0},{1, 1},{1, 2}};
    private static final int[][] COORD_T = {{0, 0},{1, 0},{2, 0},{1, 1}};


    public Piece(int shape){
        switch (shape){
            case SHAPE_O:
                coordinates = COORD_0;
                break;
            case SHAPE_I:
                coordinates = COORD_I;
                break;
            case SHAPE_S:
                coordinates = COORD_S;
                break;
            case SHAPE_Z:
                coordinates = COORD_Z;
                break;
            case SHAPE_L:
                coordinates = COORD_L;
                break;
            case SHAPE_J:
                coordinates = COORD_J;
                break;
            case SHAPE_T:
                coordinates = COORD_T;
                break;
        }
    }

}