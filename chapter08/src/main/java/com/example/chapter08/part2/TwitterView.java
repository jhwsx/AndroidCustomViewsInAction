package com.example.chapter08.part2;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.chapter08.R;

/**
 * @author wangzhichao
 * @date 2019/09/23
 */
public class TwitterView extends View {
    private Bitmap dstBmp;
    private Bitmap srcBmp;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public TwitterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        dstBmp = BitmapFactory.decodeResource(getResources(), R.drawable.twiter_bg);
        srcBmp = BitmapFactory.decodeResource(getResources(), R.drawable.twiter_light);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), paint, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(dstBmp, 0, 0, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
        canvas.drawBitmap(srcBmp, 0, 0, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }
}
