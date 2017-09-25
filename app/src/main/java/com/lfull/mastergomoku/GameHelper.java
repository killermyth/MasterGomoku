package com.lfull.mastergomoku;

/**
 * 类或接口的描述信息
 * Created by LvYingBin on 2017/9/24.
 */

public class GameHelper {
    /**
     * 计算两点间的距离
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public static double distanceCalculate(final float x1, final float y1, final float x2, final float y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

}
