package com.alex.tetris;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.alex.tetris.util.Params;
import com.alex.tetris.widget.GameBoardView;

import java.util.Timer;
import java.util.TimerTask;

public class GameBoardActivity extends Activity implements GameBoardView.Listener {

    private GameBoardView gameBoardView;
    boolean pause;
    Timer timer;
    ImageButton buttonPause;
    View viewGameOverContainer;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_board_activity);
        init();
    }

    private void init() {
        gameBoardView = (GameBoardView) findViewById(R.id.view_gameboard);
        buttonPause = (ImageButton) findViewById(R.id.button_start_pause);
        pause = true;
        gameBoardView.setListener(this);
        viewGameOverContainer = findViewById(R.id.gameover_container);
    }

    public void onButtonLeftClick(View view) {
        if (!pause) {
            gameBoardView.translateLeft();
        }
    }

    public void onButtonRotateClick(View view) {
        if (!pause) {
            gameBoardView.rotate();
        }
    }

    public void onButtonDownClick(View view) {
        if (!pause) {
            gameBoardView.translateDown();
        }
    }

    public void onButtonRightClick(View view) {
        if (!pause) {
            gameBoardView.translateRight();
        }
    }

    public void onButtonRestartClick(View view) {
        gameBoardView.clear();
        start(Params.DEFAULT_SPEED);
        viewGameOverContainer.setVisibility(View.GONE);
    }

    public void onButtonStartClick(View view) {
        if (pause) {
            start(Params.DEFAULT_SPEED);
        } else {
            pause();
        }

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
            buttonPause.setImageResource(R.drawable.play);
            gameBoardView.setTouchable(false);
        }
        pause = true;
    }

    private void start(int speed) {
        buttonPause.setImageResource(R.drawable.pause);
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
        pause = false;
        gameBoardView.setTouchable(true);
    }

    @Override
    public void onGameOver() {
        pause();
        viewGameOverContainer.setVisibility(View.VISIBLE);
    }
}

