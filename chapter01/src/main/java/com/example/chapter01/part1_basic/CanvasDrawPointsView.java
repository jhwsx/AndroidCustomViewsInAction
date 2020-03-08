package com.example.chapter01.part1_basic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Canvas 的 画多点方法演示
 *
 * @author wangzhichao
 * @since 20-3-6
 */
public class CanvasDrawPointsView extends View {
    public CanvasDrawPointsView(Context context) {
        this(context, null);
    }

    public CanvasDrawPointsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasDrawPointsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(20);
        paint.setColor(Color.RED);

        float[] pts = new float[16]; // 2 个值确定一个点，总共是 8 个点
        for (int i = 0; i < 8; i++) {
            pts[i * 2] = (float) (200 + Math.cos(Math.toRadians(i * 45)) * 100);
            pts[i * 2 + 1] = (float) (200 + Math.sin(Math.toRadians(i * 45)) * 100);
        }
        canvas.drawPoints(pts, paint);

        float[] pts2 = new float[16];
        for (int i = 0; i < 8; i++) {
            pts2[i * 2] = (float) (200 + Math.cos(Math.toRadians(i * 45)) * 100);
            pts2[i * 2 + 1] = (float) (600 + Math.sin(Math.toRadians(i * 45)) * 100);
        }
        canvas.drawPoints(pts2, 4, 12, paint);
    }
}
