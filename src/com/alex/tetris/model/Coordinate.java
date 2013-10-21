package com.alex.tetris.model;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created with IntelliJ IDEA.
 * User: alexandreduhem
 * Date: 20/10/13
 * Time: 23:32
 * To change this template use File | Settings | File Templates.
 */
public class Coordinate {
    public int x;
    public int y;
    public int color;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

}
