package com.example.chapter01.part1_basic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Paint.setStyle 方法的使用
 *
 * @author wangzhichao
 * @since 20-3-6
 */
public class BasicSetStyleView extends View {
    Paint paint = new Paint();
    Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public BasicSetStyleView(Context context) {
        super(context);
    }

    public BasicSetStyleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BasicSetStyleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(24f);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(30);
        paint.setColor(Color.RED);

        // 设置为 FILL，绘制出的半径不包括线宽
        // setStrokeWidth 的值，对于 FILL 的效果没有影响
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(200, 200, 70, paint);
        canvas.drawText("FILL", 400, 200, textPaint);

        // 设置为 STROKE，绘制出的是圆环，圆环的宽度是线宽，圆环宽度一半的位置距离圆心是半径
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(200, 500, 70, paint);
        canvas.drawText("STROKE", 400, 500, textPaint);

        // 设置为 FILL_AND_STROKE，绘制出的比 FILL，要多出半个线宽的区域
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(200, 800, 70, paint);
        canvas.drawText("FILL_AND_STROKE", 400, 800, textPaint);

        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(0F);
        canvas.drawLine(130, 0, 130, 1000, paint);
        canvas.drawLine(270, 0, 270, 1000, paint);
    }
}
