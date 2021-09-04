package com.example.chapter01.part1_basic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author wangzhichao
 * @since 20-3-6
 */
public class BasicView extends View {
    public BasicView(Context context) {
        super(context);
    }

    public BasicView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BasicView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        // 设置画笔的颜色
        paint.setColor(Color.RED);
        // 设置画笔的填充样式
        // 设置 STROKE，为红色的圆环
        paint.setStyle(Paint.Style.STROKE);
        // 设置画笔的宽度
        // Pass 0 to stroke in hairline mode. 输入负数，还是头发丝模式。
        // 发现传入 Float.MAX_VALUE，运行后屏幕变黑了。
        paint.setStrokeWidth(50f);

        // 画圆
        canvas.drawCircle(190, 200, 150, paint);
    }
}
