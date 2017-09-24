package com.lfull.mastergomoku;

import java.util.HashMap;
import java.util.Map;

/**
 * 游戏管理类
 * Created by LvYingBin on 2017/9/24.
 */
public class GameManager {
    private static GameManager mGameManager = new GameManager();
    private GameSettingDO mGameSettingDO;
    //落子过程记录
    private HashMap<ChessPoint, Boolean> mGameHashMap = new HashMap<>();
    private int playCount;

    public static GameManager getInstance() {
        return mGameManager;
    }

    private GameManager() {
    }

    /**
     * 落子后调用此处
     */
    public void go(ChessPoint point) {
        mGameHashMap.put(point, isBlackNow());
        playCount++;
    }

    /**
     * 下一步是否该黑棋走了
     *
     * @return
     */
    public boolean isBlackNow() {
        return playCount % 2 == 0;
    }

    /**
     * 判断该点是否可以下子
     */
    public boolean isCanAddChess(ChessPoint point) {
        return !mGameHashMap.containsKey(point);
    }

    /**
     * 胜负判断
     */
    public void winJudge() {
        if (mGameHashMap.size() < 9) {
            return;
        }
        for (Map.Entry<ChessPoint, Boolean> entry : mGameHashMap.entrySet()) {

        }



    }

    public GameSettingDO getGameSettingDO() {
        return mGameSettingDO;
    }

    public void setGameSettingDO(GameSettingDO gameSettingDO) {
        mGameSettingDO = gameSettingDO;
    }

    public HashMap<ChessPoint, Boolean> getGameHashMap() {
        return mGameHashMap;
    }
}
