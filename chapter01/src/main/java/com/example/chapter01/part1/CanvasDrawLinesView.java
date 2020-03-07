package com.example.chapter01.part1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Canvas 的 画多线 方法演示
 *
 * @author wangzhichao
 * @since 20-3-6
 */
public class CanvasDrawLinesView extends View {
    public CanvasDrawLinesView(Context context) {
        this(context, null);
    }

    public CanvasDrawLinesView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasDrawLinesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setColor(Color.RED);
        float[] pts = {
                100, 100, 100, 200, // 每 4 个值确定一条线，代表了线的起点和终点的坐标
                100, 200, 200, 200,
                200, 200, 200, 300}; // 假如有一组不能凑够 4 个值，那么就不能绘制出对应的线
        canvas.drawLines(pts, paint);


        float[] pts2 = {
                100, 400, 100, 500,
                100, 500, 200, 500,
                200, 500, 200, 600};
        int offset = 4;
        int count = 4;
        /**
         * 参数二 offset：开始绘制前要跳过的数组中的值的个数;
         * 参数三 count：在 offset 作用之后，设置处理数组中的几个值，这个数要设置成 4 的倍数才会有线绘制出来。
         */
        canvas.drawLines(pts2, offset, count, paint);



    }
}
