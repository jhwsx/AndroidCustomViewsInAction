package com.example.chapter10.part2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.chapter10.R;
import com.example.chapter10.Utils;

/**
 * @author wangzhichao
 * @date 2019/10/21
 */
public class BitmapFactoryOptionsInSampleSizeView extends View {

    private final Bitmap bitmap;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int dstWidth = Utils.dp2px(200);
    private int dstHeight = Utils.dp2px(200);
    public BitmapFactoryOptionsInSampleSizeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 设置为true，表示只解析图片信息，不获取图片，不分配内存。可以看到 bitmap 是 null，而 outXXXX 都是有值的。
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.book, options);
        options.inJustDecodeBounds = false;
        // 这里直接写成 1，会报错：java.lang.RuntimeException: Canvas: trying to draw too large(143327232bytes) bitmap.
//        options.inSampleSize = 1;
//        options.inSampleSize = calSampleSize(options, dstWidth, dstHeight);
        options.inSampleSize = calculateInSampleSize(options, dstWidth, dstHeight);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.book, options);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (bitmap == null) {
            return;
        }
        canvas.drawBitmap(bitmap, null, new Rect(0, 0, dstWidth, dstHeight), paint);
    }

    private int calSampleSize(BitmapFactory.Options options, int dstWidth, int dstHeight) {
        int rawWidth = options.outWidth;
        int rawHeight = options.outHeight;
        int inSampleSize = 1;
        if (rawWidth > dstWidth || rawHeight > dstHeight) {
            float ratioHeight = (float) rawHeight / dstHeight;
            float ratioWidth = rawWidth * 1f / dstWidth;
            Log.d("wzc", "ratioHeight: " + ratioHeight + ", ratioWidth: " + ratioWidth);
            inSampleSize = (int) Math.min(ratioHeight, ratioWidth);
        }
        return inSampleSize;
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
