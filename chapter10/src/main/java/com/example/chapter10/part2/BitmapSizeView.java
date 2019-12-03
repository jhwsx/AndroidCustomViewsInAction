package com.example.chapter10.part2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.chapter10.R;

/**
 * @author wangzhichao
 * @date 2019/12/03
 */
public class BitmapSizeView extends View {
    public BitmapSizeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cat_dog);
        Log.d("wzc", "getBitmapSize(bitmap) = " + getBitmapSize(bitmap));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Log.d("wzc", "bitmap.getAllocationByteCount() = " + bitmap.getAllocationByteCount());
        }
        Log.d("wzc", "bitmap.getByteCount() = " + bitmap.getByteCount());
        Log.d("wzc", "bitmap.getRowBytes() * bitmap.getHeight() = " + (bitmap.getRowBytes() * bitmap.getHeight()));
    }

    private int getBitmapSize(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return bitmap.getAllocationByteCount();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            return bitmap.getByteCount();
        }
        return bitmap.getRowBytes() * bitmap.getHeight();
    }
}
