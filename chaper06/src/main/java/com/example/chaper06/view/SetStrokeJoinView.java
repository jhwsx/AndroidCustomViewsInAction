package com.example.chaper06.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 设置拐角的形状
 * @author wangzhichao
 * @date 2019/09/17
 */
public class SetStrokeJoinView extends View {

    private static final int X_END = 450;
    public SetStrokeJoinView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(40);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);

        Path path = new Path();
        path.moveTo(100, 100);
        path.lineTo(450, 100);
        path.lineTo(X_END, 300);
        paint.setStrokeJoin(Paint.Join.MITER); // 尖角，是默认的
        canvas.drawPath(path, paint);

        path.reset();
        path.moveTo(100, 400);
        path.lineTo(450, 400);
        path.lineTo(X_END, 600);
        paint.setStrokeJoin(Paint.Join.BEVEL); // 平角
        canvas.drawPath(path, paint);

        path.reset();
        path.moveTo(100, 700);
        path.lineTo(450, 700);
        path.lineTo(X_END, 900);
        paint.setStrokeJoin(Paint.Join.ROUND); // 圆角
        canvas.drawPath(path, paint);

    }
}
