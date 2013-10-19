package com.alex.tetris.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

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


    public GameBoardView(Context context) {
        this(context, null);
    }

    public GameBoardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GameBoardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


}
