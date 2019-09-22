package com.example.chapter07.part4;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.chapter07.R;

/**
 * @author wangzhichao
 * @date 2019/09/21
 */
public class AvatarViewGroup extends LinearLayout {
    public AvatarViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_avatar_viewgroup, this);
    }
}
