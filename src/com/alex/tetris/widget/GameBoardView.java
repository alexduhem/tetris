package com.alex.tetris.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
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

    ArrayList<Piece> pieces;

    Paint paint = new Paint();

    int sideMargin;

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
        pieces = new ArrayList<Piece>();
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
        if (currentPiece != null){
            paint.setColor(currentPiece.getColor());
            for (int i = 0; i < currentPiece.getCoordinates().length; i++) {
                canvas.drawRect(getRectForCoordinate(currentPiece.getCoordinates()[i]), paint);
            }
        }
    }

    private Rect getRectForCoordinate(int[] coordinatesInGameBoard) {
        int x = coordinatesInGameBoard[0];
        //the origin of the plan is at the top left, to simulate a plan
        //with a bottom left origin (easier to imagine), with do the following operation to y
        int y = -coordinatesInGameBoard[1] + gameBoardHeight;
        int left = x * boxSize + (sideMargin / 2);
        int bottom = y * boxSize;
        return new Rect(left, bottom - boxSize, left + boxSize, bottom);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    public boolean translationDownIsAllowed() {
        if (currentPiece != null) {
            for (int i = 0; i < currentPiece.getCoordinates().length; i++) {
                int y = currentPiece.getCoordinates()[i][1];
                if (y == 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean translationRightIsAllowed() {
        if (currentPiece != null) {
            for (int i = 0; i < currentPiece.getCoordinates().length; i++) {
                int x = currentPiece.getCoordinates()[i][0];
                if (x == gameBoardLength - 1) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean translationLeftIsAllowed() {
        if (currentPiece != null) {
            for (int i = 0; i < currentPiece.getCoordinates().length; i++) {
                int x = currentPiece.getCoordinates()[i][0];
                if (x == 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean translateDown() {
        if (translationDownIsAllowed()) {
            for (int i = 0; i < currentPiece.getCoordinates().length; i++) {
                currentPiece.getCoordinates()[i][1]--;
            }
            invalidate();
            return true;
        }
        return false;
    }

    public void translateRight() {
        if (translationRightIsAllowed()) {
            for (int i = 0; i < currentPiece.getCoordinates().length; i++) {
                currentPiece.getCoordinates()[i][0]++;
            }
            invalidate();
        }
    }

    public void translateLeft() {
        if (translationLeftIsAllowed()) {
            for (int i = 0; i < currentPiece.getCoordinates().length; i++) {
                currentPiece.getCoordinates()[i][0]--;
            }
            invalidate();
        }
    }

    public void rotate() {
        if (currentPiece != null && currentPiece.getShape() != Piece.SHAPE_O) {
            int[][] newCoordinates = currentPiece.getCoordinatesAfterRotation();
            boolean rotationEnabled = true;
            for (int i = 0; i < newCoordinates.length; i++) {
                if (newCoordinates[i][0] >= gameBoardLength ||
                        newCoordinates[i][0] < 0 ||
                        newCoordinates[i][1] < 0) {
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

    public void performAction() {
        if (currentPiece == null) {
            Random random = new Random();
            int shape = random.nextInt(Piece.NUMBER_OF_SHAPE - 1);
            currentPiece = new Piece(shape);
            currentPiece.translate(new Coordinate(DEFAULT_BOARD_LENGTH/2, DEFAULT_BOARD_HEIGHT));
            invalidate();
        } else {
            if (!translateDown()) {
                pieces.add(currentPiece);
                 currentPiece = null;
            }
        }
    }
}
