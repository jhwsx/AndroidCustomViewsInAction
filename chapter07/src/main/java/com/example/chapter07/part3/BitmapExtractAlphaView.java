package com.example.chapter07.part3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.chapter07.R;

/**
 * 为 Bitmap 添加阴影，并且指定阴影的颜色
 * @author wangzhichao
 * @date 2019/09/20
 */
public class BitmapExtractAlphaView extends View {

    private Bitmap bitmap;
    private Bitmap alphaBitmap;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final int width;
    private final int height;
    private BlurMaskFilter blurMaskFilter;
    private RectF rect;
    private RectF rect2;
    private RectF rect2shift;

    public BitmapExtractAlphaView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.blog12);

        // 1, 绘制出一个一样大小的灰色图形
        alphaBitmap = bitmap.extractAlpha(); // 生成仅具有透明度的空白图像
        width = 400;
        height = width * alphaBitmap.getHeight() / alphaBitmap.getWidth();
        blurMaskFilter = new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL);
        rect = new RectF(0, 0, width, height);
        rect2 = new RectF(0, height, width, height * 2);
        rect2shift = new RectF(10, height + 10, width, height * 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 2, 对灰色图形应用BlurMaskFilter使其内外发光
        paint.setMaskFilter(blurMaskFilter);
        paint.setColor(Color.RED);
        canvas.drawBitmap(alphaBitmap, null, rect, paint);
        paint.setColor(Color.GREEN);

        // 3, 偏移原图形一段距离绘制出来
        canvas.drawBitmap(alphaBitmap, null, rect2shift, paint); // 在绘制仅具有透明度的空白图像时，图像的颜色是由画笔指定的。

        paint.setMaskFilter(null); // 清除发光效果
        canvas.drawBitmap(bitmap, null, rect, paint);
        canvas.drawBitmap(bitmap, null, rect2, paint);
    }
}
