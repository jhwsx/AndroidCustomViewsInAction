package com.example.chapter01.part1_basic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Rect 是否包含某个点 contains(int x, int y) 方法演示
 *
 * 绘制一个矩形,当手指在这个矩形区域内
 * 时,矩形变为绿色;否则是红色的。
 *
 * 学习 View 中的重绘方法 invalidate 函数和 postInvalidate 函数的区别：
 * invalidate 必须在主线程调用，否则会报错；postInvalidate 可以在主线程调用，也可以
 * 在子线程调用；
 * invalidate 比 postInvalidate 效率要高些，因为前者是直接重绘界面的，而后者是通过 Handler 发消息
 * 到主线程再进行重绘界面的。
 * @author wangzhichao
 * @since 20-3-6
 */
public class RectPointerView extends View {
    public RectPointerView(Context context) {
        this(context, null);
    }

    public RectPointerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RectPointerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        if (rect.contains(downX, downY)) {
            paint.setColor(Color.GREEN);
        } else {
            paint.setColor(Color.RED);
        }
        canvas.drawRect(rect, paint);
    }

    private boolean isRectContainsPoint(Rect rect, int x, int y) {
        // 首先判断是否是个正常的矩形
        if (!(rect.left < rect.right && rect.top < rect.bottom)) {
            return false;
        }
        // 点的横坐标位于矩形左边和矩形右边之间
        // 点的纵坐标位于矩形顶边和矩形底边之间
        if ((x > rect.left && x < rect.right)
        && (y > rect.top && y < rect.bottom)) {
            return true;
        }
        return false;
    }

    private int downX;
    private int downY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            downX = Math.round(event.getX());
            downY = Math.round(event.getY());
            invalidate();
            // 这里需要返回 true，因为只有返回 true，表示当前控件已经在拦截这个消息了,后续的 MOVE，UP 才会传递到当前控件中。
            // 如果返回 false,表示当前控件不需要这个消息,后续的 MOVE,UP 就不会再传入当前控件了.
            // TODO 2020-7-6 为什么呢?
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            downX = -1;
            downY = -1;
            postInvalidate();
        }
        return super.onTouchEvent(event);
    }
}
