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
 * 屏幕显示与 Canvas 的关系
 * 1，每次调用 drawXXX 函数来绘图时，都会产生一个全新的 Canvas 透明图层；
 * 2，如果在调用 drawXXX 函数前，调用平移、旋转等函数对 Canvas 进行操作，
 * 那么这个操作是不可逆的。每次产生的画布的最新位置都是执行这些操作后的位置。
 * 3，在 Canvas 图层与屏幕合成时,超出屏幕范围的图像是不会显示出来的。
 *
 * @author wangzhichao
 * @since 20-3-17
 */
public class CanvasScreenDisplayView extends View {
    public CanvasScreenDisplayView(Context context) {
        super(context);
    }

    public CanvasScreenDisplayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasScreenDisplayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint greenPaint = getPaint(Color.GREEN, Paint.Style.STROKE,3);
        Paint redPaint = getPaint(Color.RED, Paint.Style.STROKE,3);
        // 在平移之前绘制一个矩形
        Rect rect = new Rect(0, 0, getWidth(), 200);
        canvas.drawRect(rect, greenPaint);
        // 平移操作
        canvas.translate(100, 100);
        // 在平移之后再绘制一个矩形
        canvas.drawRect(rect, redPaint);
    }

    public Paint getPaint(@ColorInt int color, Paint.Style  style, int strokeWidth) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(style);
        paint.setColor(color);
        paint.setStrokeWidth(strokeWidth);
        return paint;
    }
}
