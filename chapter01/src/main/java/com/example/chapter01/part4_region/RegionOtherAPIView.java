package com.example.chapter01.part4_region;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Region 其他函数
 *
 * @author wangzhichao
 * @since 20-3-16
 */
public class RegionOtherAPIView extends View {
    public RegionOtherAPIView(Context context) {
        super(context);
    }

    public RegionOtherAPIView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RegionOtherAPIView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(40);

        canvas.drawText("看代码的日志打印", 10,300, paint);
        Region region = new Region(10, 10, 100, 100);
        Log.d("RegionOtherAPIView", "region.isEmpty() = " + region.isEmpty());
        Log.d("RegionOtherAPIView", "region.isRect() = " + region.isRect());
        Log.d("RegionOtherAPIView", "region.isComplex() = " + region.isComplex());
        Log.d("RegionOtherAPIView", "region.getBounds() = " + region.getBounds());
        boolean contains = region.contains(50, 50);
        Log.d("RegionOtherAPIView", "contains = " + contains);
        boolean quickContains = region.quickContains(new Rect(20, 20, 50, 50));
        Log.d("RegionOtherAPIView", "quickContains = " + quickContains);
        boolean quickReject = region.quickReject(new Rect(0, 0, 5, 5));
        Log.d("RegionOtherAPIView", "quickReject = " + quickReject);

        region.translate(50, 50);
        Log.d("RegionOtherAPIView", "after translate: " + region);
    }
}
