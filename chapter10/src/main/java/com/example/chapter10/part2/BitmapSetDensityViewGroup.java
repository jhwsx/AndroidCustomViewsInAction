package com.example.chapter10.part2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.chapter10.R;

/**
 * @author wangzhichao
 * @date 2019/12/03
 */
public class BitmapSetDensityViewGroup extends LinearLayout {
    private static final String TAG = "BitmapSetDensity";
    public BitmapSetDensityViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_bitmap_setdensity_viewgroup, this);
        ImageView iv1 = findViewById(R.id.iv1);
        ImageView iv2 = findViewById(R.id.iv2);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cat_dog);
        iv1.setImageBitmap(bitmap);
        int density = bitmap.getDensity();
        Log.d(TAG, "density:" + density + ", width:" + bitmap.getWidth() + ", height:" + bitmap.getHeight());

        int scaledDensity = density * 2;
        bitmap.setDensity(scaledDensity);
        Log.d(TAG, "scaledDensity:" + scaledDensity + ", width:" + bitmap.getWidth() + ", height:" + bitmap.getHeight());
        iv2.setImageBitmap(bitmap);
    }
}

/**
 * 2019-12-04 22:36:11.351 28197-28197/com.example.chapter10 D/BitmapSetDensity: density:640, width:1476, height:1182
 * 2019-12-04 22:36:11.352 28197-28197/com.example.chapter10 D/BitmapSetDensity: scaledDensity:1280, width:1476, height:1182
 */

/**
 * 总结：
 * 1，Bitmap 的 setDensity()、getDensity() 函数对应的就是 inDensity。
 * 2，inDensity 表示该 Bitmap 适合的屏幕 dpi。当目标屏幕的 dpi（inTargetDensity）不等于它时，将会缩放图像以适应目标机器。
 * 缩放比例 scale = inTargetDensity / inDensity.
 * 3，在把 inDensity 增大两倍后，scale 就变为了原来的一半，所以显示出来的图像就缩小了一半。
 */
