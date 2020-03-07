package com.example.chapter01.part1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Canvas 绘制背景的方法演示
 *
 * @author wangzhichao
 * @since 20-3-6
 */
public class CanvasDrawBackgroundView extends View implements View.OnClickListener {
    public CanvasDrawBackgroundView(Context context) {
        this(context, null);
    }

    public CanvasDrawBackgroundView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasDrawBackgroundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (remainder) {
            case 0:
                // 需要传入必须是 8 位的 0xAARRGGBB 的形式。
                // 如果传入的是 0xRRGGBB，那么没有效果。
                // 还可以传入 Color.RED 等预定义的一些值。
                canvas.drawColor(0xFFFF0000);
                break;
            case 1:
                canvas.drawARGB(100, 0, 255, 0);
                break;
            case 2:
                canvas.drawRGB(255, 255, 0);
                break;
            default:
        }
    }

    private int count = 0;
    private int remainder = 0;

    @Override
    public void onClick(View v) {
        remainder = count % 3;
        invalidate();
        count++;
    }
}
