package com.alex.tetris.model;

import android.graphics.Color;

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
    public static final int NUMBER_OF_SHAPE = 7;

    private int[][] coordinates;
    private int shape;

    private static final int[][] COORD_0 = {{0, 0}, {1, 0}, {1, 1}, {0, 1}};
    private static final int[][] COORD_I = {{0, 0}, {0, 1}, {0, 2}, {0, 3}};
    private static final int[][] COORD_S = {{0, 0}, {1, 0}, {1, 1}, {2, 1}};
    private static final int[][] COORD_Z = {{0, 1}, {1, 1}, {1, 0}, {2, 0}};
    private static final int[][] COORD_L = {{0, 2}, {0, 1}, {0, 0}, {1, 0}};
    private static final int[][] COORD_J = {{0, 0}, {1, 0}, {1, 1}, {1, 2}};
    private static final int[][] COORD_T = {{0, 0}, {1, 0}, {2, 0}, {1, 1}};
    private static final int[][] COORD_NO_SHAPE = {{0, 0}};


    public Piece(int shape) {
        this.shape = shape;
        switch (shape) {
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
            default:
                coordinates = COORD_NO_SHAPE;
                break;
        }
    }

    public int getColor() {
        switch (shape) {
            case SHAPE_I:
                return Color.MAGENTA;
            case SHAPE_S:
                return Color.CYAN;
            case SHAPE_Z:
                return Color.GRAY;
            case SHAPE_L:
                return Color.BLUE;
            case SHAPE_J:
                return Color.GREEN;
            case SHAPE_T:
                return Color.YELLOW;
            default:
                return Color.BLACK;
        }
    }

    /**
     * Give the index of the coordinates used to be the center of the rotation
     *
     * @return
     */
    public int getRotationCoordinateIndex() {
        switch (shape) {
            case SHAPE_I:
                return 1;
            case SHAPE_S:
                return 1;
            case SHAPE_Z:
                return 1;
            case SHAPE_L:
                return 1;
            case SHAPE_J:
                return 2;
            case SHAPE_T:
                return 1;
            default:
                return 0;
        }
    }


    /**
     * Translate the shape to the given coordinate (with the rotation center point)
     * @param coordinate
     */
    public void translate(Coordinate coordinate){
        int[] centerCoordinatesForRotation = coordinates[getRotationCoordinateIndex()];
        int distanceX = coordinate.x - centerCoordinatesForRotation[0]  ;
        int distanceY = coordinate.y - centerCoordinatesForRotation[1]  ;
         for (int i = 0; i<coordinates.length; i++){
             coordinates[i][0] =  coordinates[i][0] + distanceX;
             coordinates[i][1] =  coordinates[i][1] + distanceY;
        }
    }

    public int[][] getCoordinatesAfterRotation() {
        int[][] coordinatesToReturn = new int[coordinates.length][2];
        int[] centerCoordinatesForRotation = coordinates[getRotationCoordinateIndex()];
        for (int i = 0; i < coordinates.length; i++) {
            int newX = coordinates[i][0] - centerCoordinatesForRotation[0];
            int newY = coordinates[i][1] - centerCoordinatesForRotation[1];
            coordinatesToReturn[i][0] = newY + centerCoordinatesForRotation[0];
            coordinatesToReturn[i][1] = -newX + centerCoordinatesForRotation[1];
        }
        return coordinatesToReturn;
    }

    public int[][] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int[][] coordinates) {
        this.coordinates = coordinates;
    }

    public int getShape() {
        return shape;
    }
}
