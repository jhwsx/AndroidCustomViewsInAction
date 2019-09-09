package com.example.chaper06.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author wzc
 * @date 2019/9/8
 */
public class DrawTextView extends View {
    public DrawTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int baseLineX = 0;
        int baseLineY = 200;

        // 画基线
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawLine(baseLineX, baseLineY, 3000, baseLineY, paint);

        // 写文字
        paint.setColor(Color.GREEN);
        paint.setTextSize(120);
        // paint.setTextAlign(Paint.Align.LEFT);
        // paint.setTextAlign(Paint.Align.CENTER);
        // paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("harvic\'s blog", baseLineX, baseLineY, paint);
//        canvas.drawText("A", baseLineX, baseLineY, paint);

        // 绘制 Text 四线格的各线位置
        Paint.FontMetrics fm = paint.getFontMetrics();
        float ascent = baseLineY + fm.ascent;
        float descent = baseLineY + fm.descent;
        float top = baseLineY + fm.top;
        float bottom = baseLineY + fm.bottom;
        // 画 top
        paint.setColor(Color.BLUE);
        canvas.drawLine(baseLineX, top, 3000, top, paint);
        // 画 ascent
        paint.setColor(Color.GREEN);
        canvas.drawLine(baseLineX, ascent, 3000, ascent, paint);
        // 画 descent
        paint.setColor(Color.GREEN);
        canvas.drawLine(baseLineX, descent, 3000, descent, paint);
        // 画 bottom
        paint.setColor(Color.RED);
        canvas.drawLine(baseLineX, bottom, 3000, bottom, paint);
    }
}
