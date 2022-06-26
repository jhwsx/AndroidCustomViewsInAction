package com.example.chapter10.part1

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.chapter10.R

/**
 * @author wangzhichao
 * @date 2019/10/10
 */
class B_GradientDrawableShapeViewGroup2(context: Context, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    companion object {
        private const val TAG = "wzc"
    }

    init {
        inflate(context, R.layout.layout_gradientdrawable_shape_viewgroup2, this)
        val tv = findViewById<TextView>(R.id.tv)
        val btnAddCornerRadius = findViewById<Button>(R.id.btn_add_corner_radius)
        btnAddCornerRadius.setOnClickListener {
            val drawable = tv.background as GradientDrawable
            drawable.cornerRadius = 20f

//                drawable.setCornerRadii(new float[]{5, 5, 10, 10, 5, 10, 15, 30});

//                drawable.setStroke(Utils.dp2px(2), Color.YELLOW,Utils.dp2px(5),Utils.dp2px(5));

//                drawable.setGradientCenter(0.5f, 0.5f);
//                drawable.setGradientRadius(Utils.dp2px(20));
//                drawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
//                drawable.setColors(new int[] {0xffff0000,0xff0000ff});
        }
    }
}