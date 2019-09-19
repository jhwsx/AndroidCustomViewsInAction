package com.example.chaper07.part1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author wangzhichao
 * @date 2019/09/19
 */
public class PathComputeBoundsView extends View {
    public PathComputeBoundsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(1f);
        paint.setStyle(Paint.Style.STROKE);

        Path path = new Path();
        path.moveTo(100, 100);
        path.lineTo(200, 200);
        path.lineTo(300, 200);
        path.lineTo(400, 400);
        canvas.drawPath(path, paint);

        RectF rectF = new RectF();
        path.computeBounds(rectF, true);

        paint.setColor(Color.BLUE);
        canvas.drawRect(rectF, paint);
    }
}
