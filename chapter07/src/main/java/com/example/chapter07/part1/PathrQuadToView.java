package com.example.chapter07.part1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author wangzhichao
 * @date 2019/09/19
 */
public class PathrQuadToView extends View {
    public PathrQuadToView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);

        Path path = new Path();
        path.moveTo(100, 300);
        path.rQuadTo(100, -100, 200, 0); // 相对的是 100, 300 这个点
        path.rQuadTo(100, 100, 200, 0); // 相对的是上一个的终点

        canvas.drawPath(path, paint);

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(100, 300, 5, paint);
        canvas.drawCircle(200, 200, 5, paint);
        canvas.drawCircle(300, 300, 5, paint);
        canvas.drawCircle(400, 400, 5, paint);
        canvas.drawCircle(500, 300, 5, paint);

        paint.setStyle(Paint.Style.STROKE);
        path.reset();
        path.moveTo(100, 300);
        path.rLineTo(100, -100);
        path.rLineTo(100, 100);
        path.rLineTo(100, 100);
        path.rLineTo(100, -100);
        canvas.drawPath(path, paint);

    }
}
