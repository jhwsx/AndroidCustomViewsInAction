package com.example.chapter10.part2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.chapter10.R;
import com.example.chapter10.Utils;

/**
 * 演示从图像中取出 Alpha 通道，并把它染成天蓝色.
 * 思路：
 * 1，加载源图像；
 * 2，调用 bitmap.extractAlpha()，得到只有透明通道的图像；
 * 3, 以创建的空白图像作为画布，使用天蓝色画笔把 2 得到的图像绘制。
 * 4，把最终的 bitmap 绘制到 canvas 上。
 *
 * @author wangzhichao
 * @date 2019/10/31
 */
public class BitmapExtractAlphaView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private final Bitmap src;
    private final Bitmap bitmap;

    public BitmapExtractAlphaView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        src = BitmapFactory.decodeResource(getResources(), R.drawable.cat_dog);
        bitmap = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmap);
        paint.setColor(Color.CYAN);
        c.drawBitmap(src.extractAlpha(), 0, 0, paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(src, null, new Rect(0, 0, getWidth(), getHeight() / 2), null);

        canvas.drawBitmap(bitmap, null, new Rect(0, getHeight() / 2, getWidth(), getHeight()), null);
    }
}
/**
 * 总结：
 * 1，canvas.drawBitmap 的 paint 参数：
 * 对于 ARGB 信息完整的 Bitmap 而言，paint 参数对绘制的 Bitmap 没有影响；
 * 对于只有 Alpha 通道的 Bitmap 而言，paint 参数意义重大：paint 的颜色就是用来填充 Bitmap 颜色的。
 */
