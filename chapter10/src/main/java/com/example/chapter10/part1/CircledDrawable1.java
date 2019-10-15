package com.example.chapter10.part1;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.chapter10.Utils;

/**
 * 圆角 Drawable
 * 思路：
 * 1，传入 bitmap 对象
 * 2，把这个 bitmap 设置为 Shader
 * 3，绘制和印章一样大小的圆角矩形
 * @author wangzhichao
 * @date 2019/10/15
 */
public class CircledDrawable1 extends Drawable {
    private static final String TAG = "CircledDrawable";
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bitmap;
    private RectF rect;
    public CircledDrawable1(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        BitmapShader shader = new BitmapShader(Bitmap.createScaledBitmap(bitmap, right - left, bottom - top, true),
                Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        rect = new RectF(left, top, right, bottom);
        Log.d(TAG, "rect = " + rect.toShortString());
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawRoundRect(rect, Utils.dp2px(10), Utils.dp2px(10), paint);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicWidth() {
        return -1;
    }

    @Override
    public int getIntrinsicHeight() {
        return -1;
    }
}
