package com.example.chapter01.part5_canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Canvas 的旋转操作
 *
 * // 没有指定旋转中心，就是原点
 * public void rotate(float degrees)
 *
 * public final void rotate(float degrees, float px, float py)
 *
 * 理解指定旋转点的文章: https://blog.csdn.net/iasxk/article/details/17411381
 *
 * @author wangzhichao
 * @since 20-3-17
 */
public class CanvasRotateView extends View {
    public CanvasRotateView(Context context) {
        super(context);
    }

    public CanvasRotateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasRotateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint redPaint = getPaint(Color.RED, Paint.Style.STROKE, 3);
        Paint greenPaint = getPaint(Color.GREEN, Paint.Style.FILL, 3);
        Rect rect = new Rect(300, 10, 500, 100);
        // 绘制一个空心矩形
        canvas.drawRect(rect, redPaint);
        switch (value) {
            case 1:
                // 旋转中心是原点
                canvas.rotate(30f);
                break;
            case 3:
                // 旋转中心是指定的点
                canvas.rotate(30f, getWidth() / 2, getHeight() / 2);
                break;
            default:
                break;
        }
        // 再绘制一个实现矩形
        canvas.drawRect(rect, greenPaint);
    }

    public Paint getPaint(@ColorInt int color, Paint.Style style, int strokeWidth) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(style);
        paint.setColor(color);
        paint.setStrokeWidth(strokeWidth);
        return paint;
    }

    private int value = 0;
    private int count = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        value = count % 4;
        count++;
        invalidate();
        return super.onTouchEvent(event);
    }
}
