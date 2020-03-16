package com.example.chapter01.part4_region;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Region 最重要的操作在相交操作中。
 * 用于与指定的矩形取并集
 * public final boolean union(Rect r)
 *
 * @author wangzhichao
 * @since 20-3-16
 */
public class RegionUnionView extends View {
    public RegionUnionView(Context context) {
        super(context);
    }

    public RegionUnionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RegionUnionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);

        // 创建区域
        Region region = new Region(50, 50, 600, 200);
        // 创建矩形
        Rect rect = new Rect(500, 200, 600, 600);
        // 合并吧
        boolean union = region.union(rect);
        Log.d("wzc", "union = " + union);

        // 绘制区域
        drawRegion(canvas, region, paint);
    }

    private void drawRegion(Canvas canvas, Region region, Paint paint) {
        RegionIterator regionIterator = new RegionIterator(region);
        Rect rect = new Rect();

        while (regionIterator.next(rect)) {
            canvas.drawRect(rect, paint);
        }
    }
}
