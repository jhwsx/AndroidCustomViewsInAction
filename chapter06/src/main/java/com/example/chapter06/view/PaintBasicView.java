package com.example.chapter06.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author wzc
 * @date 2019/9/10
 */
public class PaintBasicView extends View {
    public PaintBasicView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(50);
        // 设置线帽样式
        paint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawLine(100, 100, 400, 100, paint);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawLine(100, 200, 400, 200, paint);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(100, 300, 400, 300, paint);

        // 设置路径的转角样式
        Path path = new Path();
        paint.setStrokeCap(Paint.Cap.BUTT);
        path.moveTo(100, 400);
        path.lineTo(400, 400);
        path.lineTo(100, 600);
        // paint.setStrokeJoin(Paint.Join.MITER); // 拐角处是尖角
        // paint.setStrokeJoin(Paint.Join.ROUND); // 拐角处是圆角
        paint.setStrokeJoin(Paint.Join.BEVEL); // 拐角处是平角
        canvas.drawPath(path, paint);

        // 设置路径样式
        path.reset();
        paint.setStrokeWidth(2);
        path.moveTo(100, 700);
        path.lineTo(200, 900);
        path.lineTo(300, 700);
        paint.setColor(Color.GREEN);
        canvas.drawPath(path, paint);
        paint.setColor(Color.YELLOW);
        paint.setPathEffect(new CornerPathEffect(20));
        canvas.drawPath(path, paint);
        paint.setColor(Color.RED);
        paint.setPathEffect(new CornerPathEffect(50));
        canvas.drawPath(path, paint);

        paint.setColor(Color.GREEN);
        path.reset();
        // paint.setPathEffect(new DashPathEffect(new float[]{20, 10, 30, 20}, 0));
        // paint.setPathEffect(new DiscretePathEffect(2, 5));
        path.moveTo(100, 950);
        path.lineTo(200, 1000);
        path.lineTo(400, 800);
        path.lineTo(600, 950);
        path.lineTo(800, 900);
        canvas.drawPath(path, paint);

    }
}
