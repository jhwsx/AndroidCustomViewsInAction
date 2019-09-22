package com.example.chapter07.part2;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.chapter07.R;

/**
 * @author wangzhichao
 * @date 2019/09/20
 */
public class TextViewSetShadowLayerViewGroup extends LinearLayout {
    public TextViewSetShadowLayerViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_textview_setshadowlayer_viewgroup, this);
        TextView textView =  findViewById(R.id.text);
        textView.setShadowLayer(4F, 4F,4F, Color.RED);
    }
}
