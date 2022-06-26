package com.example.chapter10.part2;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.example.chapter10.R;

/**
 * @author wangzhichao
 * @date 2019/12/02
 */
public class StrokeImageViewGroup extends LinearLayout {
    public StrokeImageViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_stroke_image_viewgroup, this);
    }
}
