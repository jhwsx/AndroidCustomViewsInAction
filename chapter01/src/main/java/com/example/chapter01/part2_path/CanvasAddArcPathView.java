package com.example.chapter01.part2_path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 演示 Path 的 addArc 方法
 * public void addArc(float left, float top, float right, float bottom, float startAngle,
 * float sweepAngle)
 * public void addArc(RectF oval, float startAngle, float sweepAngle)
 *
 * @author wangzhichao
 * @since 20-3-8
 */
public class CanvasAddArcPathView extends View {
    public CanvasAddArcPathView(Context context) {
        super(context);
    }

    public CanvasAddArcPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasAddArcPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);

        Path ccwPath = new Path();
        RectF rectF = new RectF(100, 100, 400, 300);
        ccwPath.addArc(rectF, 0, 90);
        canvas.drawPath(ccwPath, paint);

        Path cwPath = new Path();
        RectF rectF1 = new RectF(500, 100, 800, 300);
        cwPath.addArc(rectF1, 180, 180);
        canvas.drawPath(cwPath, paint);
    }
}
