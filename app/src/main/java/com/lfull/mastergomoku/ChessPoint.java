package com.lfull.mastergomoku;

/**
 * 代表棋盘上某个点
 * 0，0 、1，9等等
 * isBlack 代表色值 true为黑
 * Created by LvYingBin on 2017/9/24.
 */
public class ChessPoint {
    public final int x;
    public final int y;

    public ChessPoint(int x, int y, boolean isBlack) {
        this.x = x;
        this.y = y;
    }

    public final boolean equals(int x, int y) {
        return this.x == x && this.y == y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChessPoint point = (ChessPoint) o;

        if (x != point.x) return false;
        if (y != point.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
