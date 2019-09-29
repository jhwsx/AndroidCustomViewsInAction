package com.example.chapter08.part4;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.chapter08.R;

/**
 * @author wangzhichao
 * @date 2019/09/29
 */
public class HeartMapViewGroup_DSTIN extends LinearLayout {
    public HeartMapViewGroup_DSTIN(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_hearmap_viewgroup_dstin, this);
    }
}
