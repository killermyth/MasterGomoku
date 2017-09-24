package com.lfull.mastergomoku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PlayActivity extends AppCompatActivity {
    private GameView mGameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        setContentView(R.layout.activity_play);
        initView();
    }

    private void initData() {
        GameSettingDO mGameSettingDO = new GameSettingDO(13);
        GameManager.getInstance().setGameSettingDO(mGameSettingDO);
    }

    private void initView() {
        mGameView = findViewById(R.id.view_game);
    }
}
