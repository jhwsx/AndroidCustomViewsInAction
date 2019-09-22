package com.example.chapter07.part6;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.chapter07.R;

/**
 * @author wangzhichao
 * @date 2019/09/22
 */
public class RippleViewGroup extends LinearLayout {
    public RippleViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_ripple_viewgroup, this);
    }
}
