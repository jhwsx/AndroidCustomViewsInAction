package com.example.chapter06.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.chapter06.R;

/**
 * @author wangzhichao
 * @since 2020/01/09
 */
public class DiamondBackgroundViewGroup extends LinearLayout {
    public DiamondBackgroundViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.practice_diamond_background_viewgroup, this);
    }
}
