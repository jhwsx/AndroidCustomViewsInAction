package com.example.chaper07.part5;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.chaper07.R;

/**
 * @author wangzhichao
 * @date 2019/09/22
 */
public class ShimmerTextViewGroup extends LinearLayout {
    public ShimmerTextViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_shimmer_text_viewgroup, this);
    }
}
