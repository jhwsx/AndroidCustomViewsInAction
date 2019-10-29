package com.example.chapter10.part2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 演示指定色彩创建对象的 Bitmap.createBitmap 方法
 *
 * @author wangzhichao
 * @date 2019/10/29
 */
public class BitmapCreateBitmapColorView extends View {

    private final Bitmap bitmap;

    public BitmapCreateBitmapColorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        int width = 300;
        int height = 200;
        int[] colors = initColors(width, height);
        bitmap = Bitmap.createBitmap(colors, width, height, Bitmap.Config.ARGB_8888);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, null);
    }

    private int[] initColors(int width, int height) {
        int[] colors = new int[width * height];
        for (int y = 0; y < height; y++) { // 列
            for (int x = 0; x < width; x++) { // 行
                int r = x * 255 / (width - 1);
                int g = y * 255 / (width - 1);
                int b = 255 - Math.min(r, g);
                int a = Math.max(r, g);
                colors[y * width + x] = Color.argb(a, r, g, b);
            }
        }
        return colors;
    }
}
