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
 * 从小狗图片中裁剪出只有小狗的小矩形
 *
 * @author wangzhichao
 * @date 2019/10/29
 */
public class BitmapCreateBitmapClipImageView extends View {

    private final Bitmap bitmap;
    private final Bitmap source;

    public BitmapCreateBitmapClipImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        source = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
        int width = source.getWidth();
        int height = source.getHeight();
        // Bitmap createBitmap(@NonNull Bitmap source, int x, int y, int width, int height)
        // source 源图像
        // x, y 从源图像的哪个位置开始裁剪
        // width, height 从指定位置裁剪多少像素
        bitmap = Bitmap.createBitmap(source, width / 3, height / 3, width / 3, height / 3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(source, 0,0, null);
        canvas.drawBitmap(bitmap,0,source.getHeight(), null);
    }
}
