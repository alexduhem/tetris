package com.alex.tetris.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.alex.tetris.model.Coordinate;
import com.alex.tetris.model.Piece;
import com.alex.tetris.util.Keys;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: alexandreduhem
 * Date: 19/10/13
 * Time: 16:40
 * To change this template use File | Settings | File Templates.
 */
public class GameBoardView extends View implements View.OnTouchListener {

    private static final int DEFAULT_BOARD_HEIGHT = 18;
    private static final int DEFAULT_BOARD_LENGTH = 10;

    //abcsisse maximale
    private int gameBoardLength;
    //ordonnée maximale
    private int gameBoardHeight;
    //taille en pixel d'une case
    private int boxSize;

    private Context context;

    private Piece currentPiece;

    Paint paint = new Paint();

    int sideMargin;

    ArrayList<Coordinate> usedCoordinates;

    public GameBoardView(Context context) {
        this(context, null);
    }

    public GameBoardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GameBoardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        setOnTouchListener(this);
        usedCoordinates = new ArrayList<Coordinate>();

    }

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);
        SharedPreferences pref = context.getSharedPreferences(Keys.PREF_KEY_GENERAL, Context.MODE_PRIVATE);
        gameBoardLength = pref.getInt(Keys.PREF_KEY_GAMEBOARD_LENGTH, DEFAULT_BOARD_LENGTH);
        gameBoardHeight = pref.getInt(Keys.PREF_KEY_GAMEBOARD_HEIGHT, DEFAULT_BOARD_HEIGHT);
        boxSize = height / gameBoardHeight;
        sideMargin = width - gameBoardLength * boxSize;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawGameBoundaries(canvas);
        if (currentPiece != null) {
            for (Coordinate coordinate : currentPiece.getCoordinates()) {
                drawBox(canvas, coordinate);
            }
        }
        for (Coordinate usedCoord : usedCoordinates) {
            drawBox(canvas, usedCoord);
        }
    }

    private void drawGameBoundaries(Canvas canvas){
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(0);
        canvas.drawRect(sideMargin/2, 0, getWidth()-sideMargin/2, DEFAULT_BOARD_HEIGHT*boxSize, paint);
    }

    private void drawBox(Canvas canvas, Coordinate coordinate){
        Rect rect = getRectForCoordinate(coordinate);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(1);
        canvas.drawRect(rect, paint);
        paint.setStrokeWidth(0);
        paint.setColor(coordinate.getColor());
        canvas.drawRect(rect.left+1, rect.top+1, rect.right-1, rect.bottom-1,  paint);
    }

    private Rect getRectForCoordinate(Coordinate coordinatesInGameBoard) {
        int x = coordinatesInGameBoard.x;
        //the origin of the plan is at the top left, to simulate a plan
        //with a bottom left origin (easier to imagine), with do the following operation to y
        int y = -coordinatesInGameBoard.y + gameBoardHeight;
        int left = x * boxSize + (sideMargin / 2);
        int bottom = y * boxSize;
        return new Rect(left, bottom - boxSize, left + boxSize, bottom);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }


    public boolean translationRightIsAllowed() {
        if (currentPiece != null) {
            for (Coordinate coord : currentPiece.getCoordinates()) {
                if (coord.x == gameBoardLength - 1) {
                    return false;
                }
                for (Coordinate existCoord : usedCoordinates) {
                    if (existCoord.x == coord.x+1 && existCoord.y == coord.y){
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean translationLeftIsAllowed() {
        if (currentPiece != null) {
            for (Coordinate coord : currentPiece.getCoordinates()) {
                if (coord.x == 0) {
                    return false;
                }
                for (Coordinate existCoord : usedCoordinates) {
                    if (existCoord.x == coord.x-1 && existCoord.y == coord.y){
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean translationDownIsAllowed() {
        if (currentPiece != null && !currentPiece.isOnTheBottom()) {
            for (Coordinate coord : currentPiece.getCoordinates()) {
                for (Coordinate existCoord : usedCoordinates) {
                    if (existCoord.x == coord.x && existCoord.y == coord.y - 1){
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean translateDown() {
        if (translationDownIsAllowed()) {
            for (int i = 0; i < currentPiece.getCoordinates().length; i++) {
                currentPiece.getCoordinates()[i].y--;
            }
            invalidate();
            return true;
        }
        return false;
    }

    public void translateRight() {
        if (translationRightIsAllowed()) {
            for (int i = 0; i < currentPiece.getCoordinates().length; i++) {
                currentPiece.getCoordinates()[i].x++;
            }
            invalidate();
        }
    }

    public void translateLeft() {
        if (translationLeftIsAllowed()) {
            for (int i = 0; i < currentPiece.getCoordinates().length; i++) {
                currentPiece.getCoordinates()[i].x--;
            }
            invalidate();
        }
    }

    public void rotate() {
        if (currentPiece != null && currentPiece.getShape() != Piece.SHAPE_O) {
            Coordinate[] newCoordinates = currentPiece.getCoordinatesAfterRotation();
            boolean rotationEnabled = true;
            for (int i = 0; i < newCoordinates.length; i++) {
                if (newCoordinates[i].x >= gameBoardLength ||
                        newCoordinates[i].x < 0 ||
                        newCoordinates[i].y < 0) {
                    rotationEnabled = false;
                    break;
                }
            }
            if (rotationEnabled) {
                currentPiece.setCoordinates(newCoordinates);
                invalidate();
            }
        }
    }

    private void addNewPiece() {
        Random random = new Random();
        int shape = random.nextInt(Piece.NUMBER_OF_SHAPE - 1);
        currentPiece = new Piece(shape);
        currentPiece.translate(new Coordinate(DEFAULT_BOARD_LENGTH / 2, DEFAULT_BOARD_HEIGHT));
        invalidate();
    }

    public void performAction() {
        if (currentPiece == null) {
            addNewPiece();
        } else {
            if (!translateDown()) {
                for (int i = 0; i < currentPiece.getCoordinates().length; i++) {
                    usedCoordinates.add(currentPiece.getCoordinates()[i]);
                }
                currentPiece = null;
            }
        }
    }

    private void removeCompleteLines(){

    }
}
