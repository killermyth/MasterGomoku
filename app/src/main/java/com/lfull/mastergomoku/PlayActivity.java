package com.lfull.mastergomoku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PlayActivity extends AppCompatActivity {
    private GameManager mGameManager;
    private GameView mGameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initData();
        initView();
    }

    private void initData() {
        GameSettingDO mGameSettingDO = new GameSettingDO(13);
        mGameManager = new GameManager(mGameSettingDO);
    }

    private void initView() {
        mGameView = findViewById(R.id.view_game);
        mGameView.setGameManager(mGameManager);
    }
}
