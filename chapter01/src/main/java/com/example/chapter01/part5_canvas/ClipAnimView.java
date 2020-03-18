package com.example.chapter01.part5_canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.chapter01.R;

/**
 * 裁剪动画示例(自己实现的)
 * 思路：
 * 1，先绘制图片出来,铺满屏幕是使用 Matrix 设置的缩放比例；
 * 2，在图片上遮盖一条一条的矩形条；
 * 3，遍历矩形条，偶数索引的向左缩放，奇数索引的向右缩放。
 *
 * @author wangzhichao
 * @since 20-3-18
 */
public class ClipAnimView extends View {

    private final Bitmap bitmap;
    private final Paint paint;
    private final Matrix matrix;
    private static final int RECT_HEIGHT = 40;
    private int width;

    public ClipAnimView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.scenery);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        matrix = new Matrix();
    }

    private Rect rect = new Rect();
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth();
        // 1, 绘制图片
        matrix.setScale(width * 1f / bitmap.getWidth(),
                getHeight() * 1f / bitmap.getHeight());
        canvas.drawBitmap(bitmap, matrix, paint);
        // 2, 绘制矩形条
        int count = Math.round(getHeight() * 1f / RECT_HEIGHT) + 1;
        int top = 0;
        for (int i = 0; i < count; i++) {
            if ((i & 1) == 0) {
                rect.set(0, top, getWidth() - progress, top + RECT_HEIGHT);
            } else {
                rect.set(progress, top, getWidth(), top + RECT_HEIGHT);
            }
            top += RECT_HEIGHT;
            canvas.drawRect(rect, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        post(new MyRunnable());
        invalidate();
        return super.onTouchEvent(event);
    }

    private int progress = 0;

    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            progress += 10;
            if (progress > width) {
                progress = width;
                invalidate();
            } else {
                invalidate();
                post(this);
            }
        }
    }

}
