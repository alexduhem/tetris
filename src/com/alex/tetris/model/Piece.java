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

    private Coordinate[] coordinates;
    private int shape;

    private static final Coordinate[] COORD_0 = {new Coordinate(0, 0), new Coordinate(1, 0), new Coordinate(1, 1), new Coordinate(0, 1)};
    private static final Coordinate[] COORD_I = {new Coordinate(0, 0), new Coordinate(0, 1), new Coordinate(0, 2), new Coordinate(0, 3)};
    private static final Coordinate[] COORD_S = {new Coordinate(0, 0), new Coordinate(1, 0), new Coordinate(1, 1), new Coordinate(2, 1)};
    private static final Coordinate[] COORD_Z = {new Coordinate(0, 1), new Coordinate(1, 1), new Coordinate(1, 0), new Coordinate(2, 0)};
    private static final Coordinate[] COORD_L = {new Coordinate(0, 2), new Coordinate(0, 1), new Coordinate(0, 0), new Coordinate(1, 0)};
    private static final Coordinate[] COORD_J = {new Coordinate(0, 0), new Coordinate(1, 0), new Coordinate(1, 1), new Coordinate(1, 2)};
    private static final Coordinate[] COORD_T = {new Coordinate(0, 0), new Coordinate(1, 0), new Coordinate(2, 0), new Coordinate(1, 1)};
    private static final Coordinate[] COORD_NO_SHAPE = {new Coordinate(0, 0)};


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
        Coordinate centerCoordinatesForRotation = coordinates[getRotationCoordinateIndex()];
        int distanceX = coordinate.x - centerCoordinatesForRotation.x  ;
        int distanceY = coordinate.y - centerCoordinatesForRotation.y  ;
         for (int i = 0; i<coordinates.length; i++){
             coordinates[i].x =  coordinates[i].x + distanceX;
             coordinates[i].y =  coordinates[i].y + distanceY;
        }
    }

    public Coordinate[] getCoordinatesAfterRotation() {
        Coordinate[] coordinatesToReturn = new Coordinate[coordinates.length];
        Coordinate centerCoordinatesForRotation = coordinates[getRotationCoordinateIndex()];
        for (int i = 0; i < coordinates.length; i++) {
            int newX = coordinates[i].x - centerCoordinatesForRotation.x;
            int newY = coordinates[i].y - centerCoordinatesForRotation.y;
            coordinatesToReturn[i] = new Coordinate(newY + centerCoordinatesForRotation.x, -newX + centerCoordinatesForRotation.y) ;
        }
        return coordinatesToReturn;
    }

    public Coordinate[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinate[] coordinates) {
        this.coordinates = coordinates;
    }

    public int getShape() {
        return shape;
    }
}
