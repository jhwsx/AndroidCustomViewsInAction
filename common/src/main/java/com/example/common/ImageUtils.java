package com.example.common;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * 图片工具类
 *
 * @author wangzhichao
 * @since 2022/3/17
 */
public class ImageUtils {
    /**
     * 从资源文件中解析采用图像
     * @param res Resources 对象
     * @param resId 图片资源 id
     * @param reqWidth 期望的宽度
     * @param reqHeight 期望的高度
     * @return
     */
    public static Bitmap decodeSampleBitmapFromResource(
            Resources res, int resId, int reqWidth, int reqHeight) {
        // 1, 设置 inJustDecodeBounds = true, 进行 decode 来获取尺寸
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        // 2, 计算 inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 3, 使用 inSampleSize 来解码 bitmap
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static Bitmap decodeSampleBitmapFromByteArray(
            byte[] bytes, int reqWidth, int reqHeight) {
        // 1, 设置 inJustDecodeBounds = true, 进行 decode 来获取尺寸
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
        // 2, 计算 inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 3, 使用 inSampleSize 来解码 bitmap
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // 图片的原始宽和高
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize >= reqHeight)
            && (halfWidth / inSampleSize >= reqWidth)) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static Bitmap decodeSampleBitmapFromResource(Resources res, int resId, int requestSize) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inTargetDensity = requestSize;
        options.inDensity = options.outWidth;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
