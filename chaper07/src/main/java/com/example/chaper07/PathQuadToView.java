package com.example.chaper07;

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
public class PathQuadToView extends View {
    public PathQuadToView(Context context, @Nullable AttributeSet attrs) {
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
        // 这个点就是起点了，如果不指定的话是0, 0
        path.moveTo(100, 300);
        // x1, y1 是控制点；x2, y2 是终点
        path.quadTo(200, 200, 300, 300);
        // 它的起点是 300, 300, 因为是连续调用 quadTo 方法的
        path.quadTo(400, 400, 500, 300);

        canvas.drawPath(path, paint);

    }
}
