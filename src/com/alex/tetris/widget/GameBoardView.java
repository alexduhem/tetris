package com.alex.tetris.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.alex.tetris.util.Keys;

/**
 * Created with IntelliJ IDEA.
 * User: alexandreduhem
 * Date: 19/10/13
 * Time: 16:40
 * To change this template use File | Settings | File Templates.
 */
public class GameBoardView extends View {

    private static final int DEFAULT_BOARD_HEIGHT = 18;
    private static final int DEFAULT_BOARD_LENGTH = 10;

    //abcsisse maximale
    private int gameBoardLength;
    //ordonnée maximale
    private int gameBoardHeight;
    //taille en pixel d'une case
    private int widthBox;

    private Context context;

    public GameBoardView(Context context) {
        this(context, null);
    }

    public GameBoardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GameBoardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        SharedPreferences pref = context.getSharedPreferences(Keys.PREF_KEY_GENERAL, Context.MODE_PRIVATE);
        gameBoardLength = pref.getInt(Keys.PREF_KEY_GAMEBOARD_LENGTH, DEFAULT_BOARD_LENGTH);
        gameBoardHeight = pref.getInt(Keys.PREF_KEY_GAMEBOARD_HEIGHT, DEFAULT_BOARD_HEIGHT);
        widthBox = w / gameBoardLength;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
