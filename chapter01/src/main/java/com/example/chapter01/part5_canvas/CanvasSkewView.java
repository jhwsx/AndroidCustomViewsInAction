package com.example.chapter01.part5_canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Canvas 的扭曲操作,没有中心点的概念，就是原点
 * float sx:将画布在 X 轴方向上倾斜相应的角度,sx 为倾斜角度的正切值。
 * float sy:将画布在 Y 轴方向上倾斜 相应的角度,sy 为倾斜角度的正切值。
 * public void skew(float sx, float sy)
 * <p>
 * 参考文章：https://blog.csdn.net/tianjian4592/article/details/45234419
 *
 * @author wangzhichao
 * @since 20-3-17
 */
public class CanvasSkewView extends View {
    public CanvasSkewView(Context context) {
        super(context);
    }

    public CanvasSkewView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasSkewView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        Rect rect1 = new Rect(10, 10, 300, 500);
        canvas.drawRect(rect1, redPaint);
        // 在 x 方向上倾斜 60 度，则 tan60 = 1.732f
//        canvas.skew(1.732f, 0f);

        canvas.skew(0f, 1.732f);

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
