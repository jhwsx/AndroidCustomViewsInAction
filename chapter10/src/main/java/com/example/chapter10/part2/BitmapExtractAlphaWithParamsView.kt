package com.example.chapter10.part2;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.chapter10.R;

/**
 * 这个例子展示 Bitmap 的带参数的 extractAlpha 的用法
 * 1，获取 Alpha Bitmap；
 * 2，创建 Bitmap；
 * 3，绘制到画布上。
 *
 * @author wangzhichao
 * @date 2019/12/02
 */
public class BitmapExtractAlphaWithParamsView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private final int[] offsetXY;
    private final Bitmap bitmap;

    public BitmapExtractAlphaWithParamsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 获取 Alpha Bitmap
        Bitmap srcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cat_dog);
        paint.setMaskFilter(new BlurMaskFilter(6, BlurMaskFilter.Blur.NORMAL));
        offsetXY = new int[2];
        Bitmap alphaBitmap = srcBitmap.extractAlpha(paint, offsetXY);
        Log.d("wzc", "offsetX = " + offsetXY[0] + ", offsetY = " + offsetXY[1]);
        // 创建 Bitmap
        bitmap = Bitmap.createBitmap(alphaBitmap.getWidth(), alphaBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmap);
        // 清楚之前设置的值
        paint.setMaskFilter(null);
        paint.setColor(Color.CYAN);
        c.drawBitmap(alphaBitmap, 0, 0, paint); // 这里给 alphaBitmap 填充天蓝色。
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawBitmap(bitmap, 0,0, paint);
        canvas.drawBitmap(bitmap, -offsetXY[0], -offsetXY[1], paint);
    }
}
