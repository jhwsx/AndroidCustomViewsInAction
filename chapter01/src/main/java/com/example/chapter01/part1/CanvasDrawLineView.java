package com.example.chapter01.part1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Canvas 的 画直线方法演示
 * Paint 的 setStyle 设置情况与线绘制的效果无关。
 * Paint 的 setStrokeWidth() 决定了绘制出直线的线宽。
 *
 * @author wangzhichao
 * @since 20-3-6
 */
public class CanvasDrawLineView extends View {
    public CanvasDrawLineView(Context context) {
        this(context, null);
    }

    public CanvasDrawLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasDrawLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(30);
        paint.setColor(Color.RED);

        paint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(100, 100, 300, 100, paint);


        paint.setStyle(Paint.Style.FILL);
        canvas.drawLine(100, 200, 300, 200, paint);

        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawLine(100, 300, 300, 300, paint);

        paint.setStrokeWidth(100);
        canvas.drawLine(100, 500, 300, 500, paint);

        paint.setStrokeWidth(0f);
        canvas.drawLine(100, 600, 300, 600, paint);

    }
}
