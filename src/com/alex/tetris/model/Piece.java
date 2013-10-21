package com.alex.tetris.model;

import android.graphics.Color;

import java.util.Arrays;

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



    public Piece(int shape) {
        this.shape = shape;
        switch (shape) {
            case SHAPE_O:
                coordinates = new Coordinate[]{new Coordinate(0, 0), new Coordinate(1, 0), new Coordinate(1, 1), new Coordinate(0, 1)};
                break;
            case SHAPE_I:
                coordinates = new Coordinate[]{new Coordinate(0, 0), new Coordinate(0, 1), new Coordinate(0, 2), new Coordinate(0, 3)};
                break;
            case SHAPE_S:
                coordinates = new Coordinate[]{new Coordinate(0, 0), new Coordinate(1, 0), new Coordinate(1, 1), new Coordinate(2, 1)};
                break;
            case SHAPE_Z:
                coordinates = new Coordinate[]{new Coordinate(0, 1), new Coordinate(1, 1), new Coordinate(1, 0), new Coordinate(2, 0)};
                break;
            case SHAPE_L:
                coordinates = new Coordinate[]{new Coordinate(0, 2), new Coordinate(0, 1), new Coordinate(0, 0), new Coordinate(1, 0)};
                break;
            case SHAPE_J:
                coordinates = new Coordinate[]{new Coordinate(0, 0), new Coordinate(1, 0), new Coordinate(1, 1), new Coordinate(1, 2)};
                break;
            case SHAPE_T:
                coordinates = new Coordinate[]{new Coordinate(0, 0), new Coordinate(1, 0), new Coordinate(2, 0), new Coordinate(1, 1)};
                break;
            default:
                coordinates = new Coordinate[]{new Coordinate(0, 0)};
                break;
        }
        for (Coordinate coord : coordinates) {
            coord.setColor(getColor());
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
            case SHAPE_O:
                return Color.DKGRAY;
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
     *
     * @param coordinate
     */
    public void translate(Coordinate coordinate) {
        Coordinate centerCoordinatesForRotation = coordinates[getRotationCoordinateIndex()];
        int distanceX = coordinate.x - centerCoordinatesForRotation.x;
        int distanceY = coordinate.y - centerCoordinatesForRotation.y;
        for (int i = 0; i < coordinates.length; i++) {
            coordinates[i].x = coordinates[i].x + distanceX;
            coordinates[i].y = coordinates[i].y + distanceY;
        }
    }

    public Coordinate[] getCoordinatesAfterRotation() {
        Coordinate[] coordinatesToReturn = new Coordinate[coordinates.length];
        Coordinate centerCoordinatesForRotation = coordinates[getRotationCoordinateIndex()];
        for (int i = 0; i < coordinates.length; i++) {
            int newX = coordinates[i].x - centerCoordinatesForRotation.x;
            int newY = coordinates[i].y - centerCoordinatesForRotation.y;
            coordinatesToReturn[i] = new Coordinate(newY + centerCoordinatesForRotation.x, -newX + centerCoordinatesForRotation.y);
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

    public boolean isOnTheBottom() {
        for (int i = 0; i < coordinates.length; i++) {
            if (coordinates[i].y == 0) {
                return true;
            }
        }
        return false;
    }

}
