package com.example.chapter10.part1

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.chapter10.R
import com.example.common.dp

/**
 * @author wangzhichao
 * @date 2019/10/10
 */
class B_GradientDrawableShapeViewGroup2(context: Context, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {

    init {
        inflate(context, R.layout.layout_gradientdrawable_shape_viewgroup2, this)
        val tv = findViewById<TextView>(R.id.tv)
        val btnAddCornerRadius = findViewById<Button>(R.id.btn_add_corner_radius)
        btnAddCornerRadius.setOnClickListener {
            val drawable = tv.background as GradientDrawable
            // 设置同样的圆角
            drawable.cornerRadius = 20f

            // 设置不同的圆角
//            drawable.cornerRadii = floatArrayOf(5f, 5f, 10f, 10f, 5f, 10f, 15f, 30f)

            // 设置描边
//            drawable.setStroke(2.dp.toInt(), Color.BLUE, 5.dp, 5.dp)

            // 设置渐变
//            drawable.setGradientCenter(0.5f, 0.5f)
//            drawable.gradientRadius = 20.dp
//            drawable.gradientType = GradientDrawable.RADIAL_GRADIENT;
//            drawable.colors = intArrayOf(0xffff0000.toInt(), 0xff0000ff.toInt())
        }
    }
}