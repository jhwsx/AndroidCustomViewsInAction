package com.example.chapter10.part1;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chapter10.R;

/**
 * 演示 shape 标签对应的是 GradientDrawable，而不是 ShapeDrawable。
 *
 * @author wangzhichao
 * @date 2019/10/10
 */
public class GradientDrawableShapeViewGroup extends LinearLayout {
    private static final String TAG = "wzc";
    public GradientDrawableShapeViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_gradientdrawable_shape_viewgroup, this);
        TextView textView = findViewById(R.id.tv);
        Drawable background = textView.getBackground();
        Log.d(TAG, "background = " + background); // 打印：background = android.graphics.drawable.GradientDrawable@42487368
        TextView textView2 = findViewById(R.id.tv2);
        Drawable background2 = textView2.getBackground();
        Log.d(TAG, "background2 = " + background2); // 打印：background2 = android.graphics.drawable.GradientDrawable@42487f88
    }
}
