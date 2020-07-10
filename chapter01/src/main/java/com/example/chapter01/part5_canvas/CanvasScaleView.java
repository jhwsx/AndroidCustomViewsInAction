package com.example.chapter01.part5_canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Canvas 的缩放操作
 * // 没有指定缩放中心，就是原点
 * public void scale(float sx, float sy)
 * public final void scale(float sx, float sy, float px, float py)
 * <p>
 * 参考文章：https://blog.csdn.net/tianjian4592/article/details/45234419
 * 关于 scale 指定缩放中心的文章, 查看: https://blog.csdn.net/u011451706/article/details/52473382
 *
 * @author wangzhichao
 * @since 20-3-17
 */
public class CanvasScaleView extends View {
    private static final String TAG = "CanvasScaleView";
    public CanvasScaleView(Context context) {
        super(context);
    }

    public CanvasScaleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasScaleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint redPaint = getPaint(Color.RED, Paint.Style.STROKE, 3);
        Paint greenPaint = getPaint(Color.GREEN, Paint.Style.STROKE, 3);
        Rect rect = new Rect(0, 0, getWidth(), getHeight());
        // 绘制一个空心矩形
        canvas.drawRect(rect, redPaint);
        Rect rect1 = new Rect(10, 100, 300, 500);
        canvas.drawRect(rect1, redPaint);
        // 缩放 0.5f
//        canvas.scale(0.5f, 0.5f);
//        canvas.scale(0.5f, 0.5f, getWidth() / 2, getHeight() / 2);
        // 上面的一行等价于下面的三行，好好体会一下 scale 的 px ， py：The x-coord for the pivot point (unchanged by the scale)
        int dx = getWidth() / 2;
        int dy = getHeight() / 2;
        Log.d(TAG, "onDraw: 第一次 平移: dx =" + dx + ", dy = " + dy);
        canvas.translate(dx, dy);
        canvas.scale(0.5f, 0.5f);
        int dx1 = -getWidth() / 2;
        int dy1 = -getHeight() / 2;
        Log.d(TAG, "onDraw: 第二次 平移: dx1 =" + dx1 + ", dy1 = " + dy1);
        canvas.translate(dx1, dy1);

        // 再绘制一个实现矩形
        canvas.drawRect(rect, greenPaint);
        canvas.drawRect(rect1, greenPaint);
    }

    public Paint getPaint(@ColorInt int color, Paint.Style style, int strokeWidth) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(style);
        paint.setColor(color);
        paint.setStrokeWidth(strokeWidth);
        return paint;
    }
}
