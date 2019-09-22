package com.example.chapter06.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 给定中间线找基线
 *
 * @author wzc
 * @date 2019/9/10
 */
public class Center2BaseLineView extends View {
    public Center2BaseLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        Rect rect = new Rect(50, 50, 800, 400);
        canvas.drawRect(rect, paint);

        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(120);
        paint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        int baseLineX = rect.centerX();
        int baseLineY = rect.centerY() - (fm.top + fm.bottom) / 2;
        canvas.drawText("harvic\'s blog", baseLineX, baseLineY, paint);
    }
}
