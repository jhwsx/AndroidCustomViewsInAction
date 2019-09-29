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
public class IrregularWaveViewGroup_DSTIN extends LinearLayout {
    public IrregularWaveViewGroup_DSTIN(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_irregularwaveviewgroup_dstin, this);
    }
}
