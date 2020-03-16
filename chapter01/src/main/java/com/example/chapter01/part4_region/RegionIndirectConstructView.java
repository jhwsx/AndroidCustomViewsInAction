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
 * Region 的间接构造
 * 清空区域
 * public void setEmpty()
 * 设置矩形区域
 * public boolean set(int left, int top, int right, int bottom)
 * 设置矩形区域
 * public boolean set(Rect r)
 * 设置指定区域
 * public boolean set(Region region)
 * 参数一：用来构造区域的路径；参数二：与参一的 path 构成的路径取交集，并将该交集设置为最终的区域
 * public boolean setPath(Path path, Region clip)
 *
 * @author wangzhichao
 * @since 20-3-16
 */
public class RegionIndirectConstructView extends View {
    public RegionIndirectConstructView(Context context) {
        super(context);
    }

    public RegionIndirectConstructView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RegionIndirectConstructView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        // 创建椭圆路径
        Path path = new Path();
        path.addOval(new RectF(50, 50, 300, 600), Path.Direction.CCW);
        // 创建 clip 区域
        Region clip = new Region(50, 50, 300, 300);

        // 创建空白区域
        Region region = new Region();
        region.setPath(path, clip);

        // 绘制 Region
        drawRegion(canvas, region, paint);

    }

    /**
     * 绘制 Region
     * 需要注意的是，如果 Paint 的 setStyle 为 STROKE，那么只会绘制出一个外围的框。
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
