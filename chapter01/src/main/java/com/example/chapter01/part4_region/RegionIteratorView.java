package com.example.chapter01.part4_region;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 学习 RegionIterator
 *
 * @author wangzhichao
 * @since 20-3-16
 */
public class RegionIteratorView extends View {
    public RegionIteratorView(Context context) {
        super(context);
    }

    public RegionIteratorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RegionIteratorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);

        Rect rect = new Rect(50, 50, 300, 800);
        // 绘制路径
        Path path = new Path();
        RectF rectF = new RectF(rect);
//        path.addOval(rectF, Path.Direction.CCW);
        path.addRoundRect(rectF, 50, 50, Path.Direction.CCW);
        // 绘制 clip
        Region clip = new Region(rect);

        // 获取最终的区域
        Region region = new Region();
        region.setPath(path, clip);

        drawRegion(canvas, region, paint);
    }

    /**
     * 绘制 Region
     * 在 Paint 使用 setStyle 为 STROKE 的情况下：
     * 如果区域就是一个矩形，那么绘制出来的就是一个矩形框；
     * 如果区域不是一个矩形，那么可以绘制出多个矩形来。
     *
     * @param canvas
     * @param region
     * @param paint
     */
    private void drawRegion(Canvas canvas, Region region, Paint paint) {
        RegionIterator regionIterator = new RegionIterator(region);
        Rect rect = new Rect();
        while (regionIterator.next(rect)) {
            canvas.drawRect(rect, paint);
        }
    }
}
