package com.example.chapter01.part1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Paint setColor 方法的使用演示
 *
 * @author wangzhichao
 * @since 20-3-6
 */
public class BasicSetColorView extends View {
    public BasicSetColorView(Context context) {
        super(context);
    }

    public BasicSetColorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BasicSetColorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        paint.setColor(Color.parseColor("#ffff0000"));
        canvas.drawCircle(300, 300, 200, paint);

        paint.setColor(Color.parseColor("#ff00ffff"));
        canvas.drawCircle(300, 300, 100, paint);
    }
}
