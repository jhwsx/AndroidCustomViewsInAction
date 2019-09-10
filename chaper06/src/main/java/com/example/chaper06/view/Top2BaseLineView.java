package com.example.chaper06.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 给定顶线找基线
 * @author wzc
 * @date 2019/9/10
 */
public class Top2BaseLineView extends View {
    public Top2BaseLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String text = "harvic\'s blog";
        int top = 200;
        int baseLineX = 0;

        // 设置 paint
        Paint paint = new Paint();
        paint.setTextSize(120);
        paint.setTextAlign(Paint.Align.LEFT);

        // 画 top
        paint.setColor(Color.YELLOW);
        canvas.drawLine(baseLineX, top, 3000, top, paint);

        // 计算出 baseline 线的位置
        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        // fm.top = top - baseLineY
        int baseLineY = top - fm.top;

        // 画基线
        paint.setColor(Color.RED);
        canvas.drawLine(baseLineX, baseLineY, 3000, baseLineY, paint);

        // 写文字
        paint.setColor(Color.GREEN);
        canvas.drawText(text, baseLineX, baseLineY, paint);
    }
}
