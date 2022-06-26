package com.example.chapter10.part2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.chapter10.R;

/**
 * 缩放图片
 * @author wangzhichao
 * @date 2019/10/29
 */
public class BitmapCreateScaledBitmapView extends View {

    private final Bitmap scaledBitmap;
    private final Bitmap source;

    public BitmapCreateScaledBitmapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        source = BitmapFactory.decodeResource(getResources(), R.drawable.scenery);
        // Bitmap createScaledBitmap(@NonNull Bitmap src, int dstWidth, int dstHeight, boolean filter)
        // dstWidth, dstHeight, 缩放到多少尺寸
        // filter 是否给图像添加滤波效果
        scaledBitmap = Bitmap.createScaledBitmap(source, 300, 200, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(source, 0, 0, null);
        canvas.drawBitmap(scaledBitmap, 0, source.getHeight(), null);
    }
}
