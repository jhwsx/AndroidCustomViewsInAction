package com.example.chapter01.part1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Canvas 的 画点方法演示
 * Paint.setStyle 的值与绘制出的点效果无关
 * Paint.setStrokeWidth 的值决定了点的大小
 *
 * @author wangzhichao
 * @since 20-3-6
 */
public class CanvasDrawPointView extends View {
    public CanvasDrawPointView(Context context) {
        this(context, null);
    }

    public CanvasDrawPointView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasDrawPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        canvas.drawPoint(100, 100, paint);

        paint.setStyle(Paint.Style.FILL);
        canvas.drawPoint(100, 200, paint);

        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPoint(100, 300, paint);

        paint.setStrokeWidth(60);
        canvas.drawPoint(100, 400, paint);

        paint.setStrokeWidth(120);
        canvas.drawPoint(100, 500, paint);
    }
}
