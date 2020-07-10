package com.example.chapter01.part4_region;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.support.annotation.Nullable;
import android.support.v7.widget.DialogTitle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Region 的直接构造
 * public Region(Region region)
 * public Region(Rect r)
 * public Region(int left, int top, int right, int bottom)
 *
 * @author wangzhichao
 * @since 20-3-16
 */
public class RegionDirectConstructView extends View {
    private static final String TAG = "RegionDirectConstructVi";

    public RegionDirectConstructView(Context context) {
        super(context);
    }

    public RegionDirectConstructView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RegionDirectConstructView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);

        Region region = new Region(new Rect(20, 100, 300, 200));
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
            Log.d(TAG, "drawRect: "); // 只会打印一次
            canvas.drawRect(rect, paint);
        }
    }
}
