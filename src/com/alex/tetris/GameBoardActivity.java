package com.alex.tetris;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.alex.tetris.widget.GameBoardView;

public class GameBoardActivity extends Activity {

    private GameBoardView gameBoardView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_board_activity);
        init();
    }

    private void init() {
        gameBoardView = (GameBoardView) findViewById(R.id.view_gameboard);
    }

    public void onButtonLeftClick(View view) {
        gameBoardView.translateLeft();
    }

    public void onButtonRotateClick(View view) {
          gameBoardView.rotate();
    }

    public void onButtonDownClick(View view) {
         gameBoardView.translateDown();
    }

    public void onButtonRightClick(View view) {
        gameBoardView.translateRight();
    }
}
