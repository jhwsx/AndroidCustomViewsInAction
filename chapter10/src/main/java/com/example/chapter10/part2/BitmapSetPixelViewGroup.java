package com.example.chapter10.part2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.chapter10.R;

/**
 * @author wangzhichao
 * @since 2019/12/10
 */
public class BitmapSetPixelViewGroup extends LinearLayout {
    public BitmapSetPixelViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_bitmap_setpixel_viewgroup, this);
        ImageView ivOriginal = findViewById(R.id.ivOriginal);
        ImageView ivNew = findViewById(R.id.ivNew);
        Bitmap srcBmp = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
        ivOriginal.setImageBitmap(srcBmp);
        // 变为可变的位图
        Bitmap desBmp = srcBmp.copy(Bitmap.Config.ARGB_8888, true);
        for (int w = 0; w < desBmp.getWidth(); w++) {
            for (int h = 0; h < desBmp.getHeight(); h++) {
                int originColor = desBmp.getPixel(w, h);
                int red = Color.red(originColor);
                int green = Color.green(originColor);
                int blue = Color.blue(originColor);
                int alpha = Color.alpha(originColor);

                if (green < 200) {
                    green += 30;
                }

                desBmp.setPixel(w,h,Color.argb(alpha, red, green, blue));
            }
        }
        ivNew.setImageBitmap(desBmp);
    }
}
