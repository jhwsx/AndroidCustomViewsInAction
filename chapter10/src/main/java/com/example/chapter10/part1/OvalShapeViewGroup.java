package com.example.chapter10.part1;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.example.chapter10.R;

/**
 * @author wangzhichao
 * @date 2019/10/11
 */
public class OvalShapeViewGroup extends LinearLayout {
    public OvalShapeViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_oval_shape_viewgroup, this);
    }
}
