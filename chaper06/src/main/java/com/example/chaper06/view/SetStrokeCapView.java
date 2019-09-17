package com.example.chaper06.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author wangzhichao
 * @date 2019/09/17
 */
public class SetStrokeCapView extends View {
    public SetStrokeCapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(40);

        paint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawLine(50, 100, 300, 100, paint);

        paint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawLine(50, 200, 300, 200, paint);

        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(50, 300, 300, 300, paint);

        paint.setColor(Color.RED);
        paint.setStrokeWidth(1);
        canvas.drawLine(50,0,50,400, paint);
        canvas.drawLine(300,0,300,400, paint);
    }
}
