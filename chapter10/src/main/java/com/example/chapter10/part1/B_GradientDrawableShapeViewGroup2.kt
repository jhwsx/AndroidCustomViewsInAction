package com.example.chapter10.part1;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chapter10.R;
import com.example.chapter10.Utils;

/**
 * @author wangzhichao
 * @date 2019/10/10
 */
public class GradientDrawableShapeViewGroup2 extends LinearLayout {
    private static final String TAG = "wzc";

    public GradientDrawableShapeViewGroup2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_gradientdrawable_shape_viewgroup2, this);
        final TextView tv = findViewById(R.id.tv);
        final Button btnAddCornerRadius = findViewById(R.id.btn_add_corner_radius);
        btnAddCornerRadius.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                GradientDrawable drawable = (GradientDrawable) tv.getBackground();
                drawable.setCornerRadius(20);

//                drawable.setCornerRadii(new float[]{5, 5, 10, 10, 5, 10, 15, 30});

//                drawable.setStroke(Utils.dp2px(2), Color.YELLOW,Utils.dp2px(5),Utils.dp2px(5));

//                drawable.setGradientCenter(0.5f, 0.5f);
//                drawable.setGradientRadius(Utils.dp2px(20));
//                drawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
//                drawable.setColors(new int[] {0xffff0000,0xff0000ff});
            }
        });
    }
}
