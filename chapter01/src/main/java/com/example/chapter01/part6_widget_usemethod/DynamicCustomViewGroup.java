package com.example.chapter01.part6_widget_usemethod;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.RelativeLayout;

import com.example.chapter01.R;

/**
 * @author wangzhichao
 * @since 20-3-24
 */
public class DynamicCustomViewGroup extends RelativeLayout {
    public DynamicCustomViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.viewgroup_dynamic_custom_view, this);
        RelativeLayout root = findViewById(R.id.root);
        CustomView customView = new CustomView(context);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.text);
        layoutParams.setMargins(10,20,30,40);
        customView.setLayoutParams(layoutParams);
        customView.setBackgroundColor(Color.GREEN);
        root.addView(customView);
        root.setGravity(Gravity.CENTER);
    }
}
