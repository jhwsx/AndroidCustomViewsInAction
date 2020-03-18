package com.example.chapter01.part5_canvas;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * 使用 Canvas.translate 来绘制一把简易的尺子。
 *
 * @author wangzhichao
 * @since 20-3-17
 */
public class RulerView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public RulerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private int RULER_HEIGHT_DP = 50;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(40f);
        paint.setTextAlign(Paint.Align.CENTER);
        // 绘制尺子的轮廓
        Rect rect = new Rect(10, 10, getWidth() - 10,
                (int) (10 + dp2px(RULER_HEIGHT_DP)));
        canvas.drawRect(rect, paint);
        // 绘制刻度线
        int width = rect.width() - 20;
        int count = 40;
        int scale = width / count;
        int startY = rect.bottom - 20;
        for (int i = 0; i <= count; i++) {
            if (i % 10 == 0) {
                // 画长线
                canvas.drawLine(20, startY, 20, startY- dp2px(15), paint);
                int value = i / 10;
                canvas.drawText(String.valueOf(value), 20, startY - dp2px(20), paint);
            } else if (i % 5 == 0) {
                // 画中等长度的线
                canvas.drawLine(20, startY, 20, startY- dp2px(10), paint);
            } else {
                // 画短线
                canvas.drawLine(20, startY, 20, startY- dp2px(5), paint);
            }
            canvas.translate(scale, 0);
        }
    }

    private float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }
}
