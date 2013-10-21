package com.alex.tetris;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.alex.tetris.widget.GameBoardView;

import java.util.Timer;
import java.util.TimerTask;

public class GameBoardActivity extends Activity {

    private GameBoardView gameBoardView;
    boolean pause;
    Timer timer;
    Button buttonPause;

    private static final int DEFAULT_SPEED = 600;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_board_activity);
        init();
    }

    private void init() {
        gameBoardView = (GameBoardView) findViewById(R.id.view_gameboard);
        buttonPause = (Button) findViewById(R.id.button_start_pause);
        pause = true;
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

    public void onButtonStartClick(View view) {
        if (pause) {
            start(DEFAULT_SPEED);

        } else {
            pause();
        }
        pause = !pause;
    }

    @Override
    protected void onPause() {
        pause();
        super.onPause();
    }

    private void pause() {
        if (!pause) {
            timer.cancel();
            timer = null;
            buttonPause.setText(getString(R.string.start));
        }
    }

    private void start(int speed) {
        buttonPause.setText(getString(R.string.pause));
        if (timer == null) {
            timer = new Timer("tetrisTimer", false);
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        gameBoardView.performAction();
                    }
                });
            }
        }, 0, speed);
    }
}

