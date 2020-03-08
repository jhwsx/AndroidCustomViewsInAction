package com.example.chapter01.part1_basic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Rect 是否包含某个 Rect 方法演示
 * <p>
 * 手指在屏幕一个角开始点击，滑动到对角构成一个矩形，如果这个矩形包含在预定的矩形框内，
 * 预定的矩形框就是绿色，否则就是红色
 *
 * @author wangzhichao
 * @since 20-3-6
 */
public class RectContainsRectView extends View {
    public RectContainsRectView(Context context) {
        super(context);
    }

    public RectContainsRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RectContainsRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(5f);
        paint.setStyle(Paint.Style.STROKE);

        Rect rect = new Rect();
        rect.set(100, 100, 500, 500);
        if (action == MotionEvent.ACTION_DOWN) {
            Log.d("wzc", "onDraw if");
            paint.setColor(Color.RED);
        } else {
            Log.d("wzc", "onDraw else r = " + r.toShortString());
            if (rect.contains(r)) {
                Log.d("wzc", "onDraw else green");
                paint.setColor(Color.GREEN);
            } else {
                Log.d("wzc", "onDraw else red");
                paint.setColor(Color.RED);
            }
        }
        canvas.drawRect(rect, paint);
    }

    private Rect r = new Rect();
    private int action = MotionEvent.ACTION_DOWN;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            action = MotionEvent.ACTION_DOWN;
            Log.d("wzc", "down");
            invalidate();
            r.left = x;
            r.top = y;
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            action = MotionEvent.ACTION_UP;
            r.right = x;
            r.bottom = y;
            Log.d("wzc", "r = " + r.toShortString());
            r.sort();
            Log.d("wzc", "up");
            invalidate();
        }
        return super.onTouchEvent(event);
    }
}
