package com.lfull.mastergomoku;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.HashMap;
import java.util.Map;

/**
 * 游戏主界面View
 * Created by LvYingBin on 2017/9/24.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = "GameView";
    private GameManager mGameManager;
    private int boardSize;
    // 定义SurfaceHolder对象
    private SurfaceHolder mHolder;
    // 棋盘背景画笔
    private Paint bgPaint = new Paint();
    // 棋盘画笔
    private Paint boardPaint = new Paint();
    // 棋子画笔
    private Paint chessPaint = new Paint();
    private float rowWidth = 0.0f;
    private float columnWidth = 0.0f;
    private int mChessboardWidth = 0;
    private int mChessboardHeight = 0;


    public GameView(Context context) {
        super(context);
        init();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(21)
    public GameView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {

        mHolder = this.getHolder();
        mHolder.addCallback(this);
        // 设置透明
        mHolder.setFormat(PixelFormat.TRANSLUCENT);
        setZOrderOnTop(true);
        chessPaint.setAntiAlias(true);
        boardPaint.setStrokeWidth(2.0f);
        boardPaint.setColor(getResources().getColor(R.color.colorBlack));
        bgPaint.setColor(getResources().getColor(R.color.colorWood));
//        clear.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawChessBoard();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (right - left > 0 && bottom - top > 0) {
            mChessboardWidth = right - left;
            mChessboardHeight = bottom - top;
            rowWidth = mChessboardHeight / boardSize;
            columnWidth = mChessboardWidth / boardSize;
        }
    }

    /**
     * 画棋盘
     */
    private void drawChessBoard() {
        Canvas canvas = mHolder.lockCanvas();
        if (canvas == null) {
            return;
        }
        drawChessBoard(canvas);
        mHolder.unlockCanvasAndPost(canvas);
    }

    private void drawChessBoard(@NonNull Canvas canvas) {
        canvas.drawRect(0, 0, mChessboardWidth, mChessboardHeight, bgPaint);
        for (int i = 0; i <= boardSize; i++) {
            // draw 横线
            canvas.drawLine(0, rowWidth * i, mChessboardWidth, rowWidth * i, boardPaint);
            // draw 竖线
            canvas.drawLine(columnWidth * i, 0, columnWidth * i, mChessboardHeight, boardPaint);
        }
    }

    private void drawChess(@NonNull Canvas canvas) {
        HashMap<ChessPoint, Boolean> map = mGameManager.getGameHashMap();
        for (Map.Entry<ChessPoint, Boolean> entry : map.entrySet()) {
            drawChess(canvas, entry.getKey(), entry.getValue());
        }
    }

    /**
     * 画棋子
     *
     * @param point
     */
    private void drawChess(@NonNull Canvas canvas, @NonNull ChessPoint point, boolean isBlack) {
        chessPaint.setColor(getResources().getColor(isBlack ? R.color.colorBlack : R.color.colorWhite));
        canvas.drawCircle(rowWidth * point.x, columnWidth * point.y, rowWidth / 4, chessPaint);
    }

    private void drawGame() {
        Canvas canvas = mHolder.lockCanvas();
        if (canvas == null) {
            return;
        }
        drawChessBoard(canvas);
        drawChess(canvas);
        mHolder.unlockCanvasAndPost(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final float x = event.getX();
        final float y = event.getY();
        final int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                ChessPoint point = addChess(x, y);
                if (point != null) {
                    mGameManager.go(point);
                    drawGame();
                }
                break;
        }
        return true;
    }

    /**
     * 找到最近的一个点
     *
     * @param x
     * @param y
     * @return
     */
    private ChessPoint addChess(final float x, final float y) {
        int preX = (int) (x / columnWidth);
        int preY = (int) (y / rowWidth);

        float startX = columnWidth * preX;
        float startY = rowWidth * preY;
        float endX = columnWidth * (preX + 1);
        float endY = columnWidth * (preY + 1);

        double distance1 = GameHelper.distanceCalculate(x, y, startX, startY);
        double distance2 = GameHelper.distanceCalculate(x, y, startX, endY);
        double distance3 = GameHelper.distanceCalculate(x, y, endX, startY);
        double distance4 = GameHelper.distanceCalculate(x, y, endX, endY);

        double minDistance = Math.min(Math.min(Math.min(distance1, distance2), distance3), distance4);

        ChessPoint chessPoint;
        if (minDistance == distance1) {
            chessPoint = new ChessPoint(preX, preY);
        } else if (minDistance == distance2) {
            chessPoint = new ChessPoint(preX, preY + 1);
        } else if (minDistance == distance3) {
            chessPoint = new ChessPoint(preX + 1, preY);
        } else {
            chessPoint = new ChessPoint(preX + 1, preY + 1);
        }
        return mGameManager.isCanAddChess(chessPoint) ? chessPoint : null;
    }

    public void setGameManager(GameManager gameManager) {
        mGameManager = gameManager;
        boardSize = mGameManager.getGameSettingDO().boardSize;
    }
}
