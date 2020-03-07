package com.example.chapter01.part1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Paint.setAntiAlias 方法演示
 * 也可以通过 Paint 的构造函数 Paint(int flags) 传入 Paint.ANTI_ALIAS_FLAG 来打开设置
 * @author wangzhichao
 * @since 20-3-6
 */
public class BasicAntiAliasView extends View {
    public BasicAntiAliasView(Context context) {
        super(context);
    }

    public BasicAntiAliasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BasicAntiAliasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(50f);

        // 画圆
        paint.setAntiAlias(false); // 关闭抗锯齿，边缘有些毛糙
        canvas.drawCircle(190, 200, 150, paint);

        paint.setAntiAlias(true); // 打开抗锯齿，边缘更加光滑
        canvas.drawCircle(600, 200, 150, paint);
    }
}
