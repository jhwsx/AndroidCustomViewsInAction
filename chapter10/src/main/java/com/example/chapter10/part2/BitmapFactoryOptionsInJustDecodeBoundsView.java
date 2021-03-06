package com.example.chapter10.part2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.chapter10.R;

/**
 * @author wangzhichao
 * @date 2019/10/21
 */
public class BitmapFactoryOptionsInJustDecodeBoundsView extends View {
    public BitmapFactoryOptionsInJustDecodeBoundsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 设置为true，表示只解析图片信息，不获取图片，不分配内存。可以看到 bitmap 是 null，而 outXXXX 都是有值的。
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.book, options);
        Log.d("wzc", "bitmap = " + bitmap);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d("wzc", "realWidth: " + options.outWidth + ", realHeight: " + options.outHeight
                    + ", mimeType: " + options.outMimeType + ", config: " + options.outConfig.toString() + ", colorSpace: " + options.outColorSpace);
        } else {
            Log.d("wzc", "realWidth: " + options.outWidth + ", realHeight: " + options.outHeight
                    + ", mimeType: " + options.outMimeType );
        }
    }
}
