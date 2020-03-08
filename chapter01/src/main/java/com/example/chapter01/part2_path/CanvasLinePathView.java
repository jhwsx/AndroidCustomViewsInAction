package com.example.chapter01.part2_path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Canvas.drawPath 方法演示直线路径
 *
 * @author wangzhichao
 * @since 20-3-8
 */
public class CanvasLinePathView extends View {
    public CanvasLinePathView(Context context) {
        super(context);
    }

    public CanvasLinePathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasLinePathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(5f);
        // 注意这里的 setStyle 对绘制路径有影响：若为 STROKE, 则只是描边；若为 FILL 或 FILL_AND_STROKE, 则会填充。
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);

        // 1, 创建 Path 对象
        Path path = new Path();
        // 2, 指定起始点,如果设置起始点，那么起始点就是（0, 0）
        path.moveTo(10, 10);
        // 3, 指定第一条直线的终点；也是第二条直线的起点
        path.lineTo(10, 100);
        // 4, 指定第二条直线的终点
        path.lineTo(300, 100);
        // 5, 闭环: 这会在第二条直线的终点和第一条线的起点创建一根连线
        path.close();
        // 6, 绘制路径
        canvas.drawPath(path, paint);
    }
}
