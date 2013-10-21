package com.alex.tetris.widget;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created with IntelliJ IDEA.
 * User: alexandreduhem
 * Date: 21/10/13
 * Time: 22:38
 * To change this template use File | Settings | File Templates.
 */
public class GestureListener extends GestureDetector.SimpleOnGestureListener {

    private GameBoardView gameBoardView;

    public GestureListener(GameBoardView gameBoardView) {
        this.gameBoardView = gameBoardView;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        gameBoardView.rotate();
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {

        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }


    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (distanceX >= gameBoardView.getBoxSize() / 2) {
            gameBoardView.translateLeft();
        } else if (distanceX <= -gameBoardView.getBoxSize() / 2){
            gameBoardView.translateRight();
        }
        if (distanceY <= -gameBoardView.getBoxSize()/2){
            gameBoardView.translateDown();
        }
            Log.e("distance", "" + distanceY);
        return true;
    }
}
