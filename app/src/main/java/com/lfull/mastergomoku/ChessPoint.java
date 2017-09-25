package com.lfull.mastergomoku;


import android.support.annotation.NonNull;

/**
 * 代表棋盘上某个点
 * 0，0 、1，9等等
 * Created by LvYingBin on 2017/9/24.
 */
public class ChessPoint {
    public final int x;
    public final int y;

    /**
     * 相邻的类型分别为横向相邻，纵向相邻，斜杠方向/相邻，反斜杠方向\相邻
     */
    public enum NextType {
        NoNext, NextHorizontal, NextVertical, NextSlash, NextBackSlash
    }

    public static final int NoNext = 0;
    public static final int NextHorizontal = 1;
    public static final int NextVertical = 2;
    public static final int NextSlash = 3;
    public static final int NextBackSlash = 4;

    public ChessPoint(int x, int y) {
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

    /**
     * 判断两点是否为相邻点并返回相邻类型
     *
     * @return
     */
    public NextType isNextTo(@NonNull ChessPoint chessPoint) {
        NextType nextType = NextType.NoNext;
        if (chessPoint.x == x - 1) {
            if (chessPoint.y == y - 1) {
                nextType = NextType.NextBackSlash;
            } else if (chessPoint.y == y) {
                nextType = NextType.NextHorizontal;
            } else if (chessPoint.y == y + 1) {
                nextType = NextType.NextSlash;
            }
        } else if (chessPoint.x == x) {
            if (chessPoint.y == y - 1) {
                nextType = NextType.NextVertical;
            } else if (chessPoint.y == y + 1) {
                nextType = NextType.NextVertical;
            }
        } else if (chessPoint.x == x + 1) {
            if (chessPoint.y == y - 1) {
                nextType = NextType.NextSlash;
            } else if (chessPoint.y == y) {
                nextType = NextType.NextHorizontal;
            } else if (chessPoint.y == y + 1) {
                nextType = NextType.NextBackSlash;
            }
        }
        return nextType;
    }
}
