package com.example.chapter01.part2_path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Canvas.drawPath 方法演示弧形路径
 *
 * @author wangzhichao
 * @since 20-3-8
 */
public class CanvasArcPathView extends View {
    public CanvasArcPathView(Context context) {
        super(context);
    }

    public CanvasArcPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasArcPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        RectF rectF = new RectF(100, 10, 200, 100);
        // 3, 绘制圆弧:水平向右是 0 度，顺时针为正方向。
        // arc 的起点跟 Path 的最后一个点不同时，会在两者间连线；如果没有上一个点，则 arc 的起点就是 Path 的起点
        path.arcTo(rectF, 0, 90);
        // 4, 绘制路径
        canvas.drawPath(path, paint);

        paint.setStrokeWidth(3f);
        paint.setColor(0x4400FF00);
        canvas.drawOval(rectF, paint);
        paint.setColor(0x440000FF);
        canvas.drawRect(rectF, paint);

        // ---------------------演示强制将弧的起点起点作为 Path 的起始位置-------
        // 1, 创建 Path 对象
        path.reset();
        // 2, 指定起始点,如果设置起始点，那么起始点就是（0, 0）
        path.moveTo(10, 210);
        rectF = new RectF(100, 210, 200, 300);
        // 3, 绘制圆弧:水平向右是 0 度，顺时针为正方向。
        // 最后一个参数 forceMove true 表示总是以 arc 作为一个新的轮廓。
        path.arcTo(rectF, 0, 90, true);
        // 4, 绘制路径
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5f);
        canvas.drawPath(path, paint);

        paint.setStrokeWidth(3f);
        paint.setColor(0x4400FF00);
        canvas.drawOval(rectF, paint);
        paint.setColor(0x440000FF);
        canvas.drawRect(rectF, paint);
    }
}
