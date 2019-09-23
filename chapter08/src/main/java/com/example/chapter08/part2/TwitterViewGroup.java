package com.example.chapter08.part2;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.chapter08.R;

/**
 * @author wangzhichao
 * @date 2019/09/23
 */
public class TwitterViewGroup extends LinearLayout {
    public TwitterViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_twitter_viewgroup, this);
    }
}
