package com.alex.tetris.model;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: alexandreduhem
 * Date: 21/10/13
 * Time: 19:17
 * To change this template use File | Settings | File Templates.
 */
public class Line {

    public int y;
    public int size;

    ArrayList<Coordinate> coordinates;

    public Line(int y, int size) {
        this.y = y;
        this.size = size;
        coordinates = new ArrayList<Coordinate>();
    }

    public void setCoordinates(ArrayList<Coordinate> coordinates) {
        this.coordinates.clear();
        this.coordinates.addAll(coordinates);
        for (Coordinate coordinate : this.coordinates) {
            coordinate.y = y;
        }
    }

    public void addCoordinate(Coordinate coordinate) {
        coordinates.add(coordinate);
    }

    public boolean isFull() {
        return (coordinates != null && coordinates.size() == size);
    }

    public boolean isEmpty() {
        return (coordinates == null || coordinates.isEmpty());
    }

    public ArrayList<Coordinate> getCoordinates() {
        return coordinates;
    }
}
