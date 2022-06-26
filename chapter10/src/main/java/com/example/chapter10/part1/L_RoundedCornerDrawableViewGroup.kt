package com.example.chapter10.part1

import android.content.Context
import android.graphics.BitmapFactory
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import com.example.chapter10.R

/**
 * 圆角 Drawable
 *
 * @author wangzhichao
 * @date 2019/10/15
 */
class L_RoundedCornerDrawableViewGroup(context: Context?, attrs: AttributeSet?) :
    ScrollView(context, attrs) {
    init {
        inflate(context, R.layout.layout_circled_drawable_viewgroup, this)
        val iv0 = findViewById<ImageView>(R.id.iv0)
        val iv1 = findViewById<ImageView>(R.id.iv1)
        val iv2 = findViewById<ImageView>(R.id.iv2)
        val iv3 = findViewById<ImageView>(R.id.iv3)
        val iv4 = findViewById<ImageView>(R.id.iv4)
        val iv5 = findViewById<ImageView>(R.id.iv5)
        val iv6 = findViewById<ImageView>(R.id.iv6)
        val iv7 = findViewById<ImageView>(R.id.iv7)
        val iv8 = findViewById<ImageView>(R.id.iv8)
        val tv = findViewById<TextView>(R.id.tv)
        val tv1 = findViewById<TextView>(R.id.tv1)
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.avatar)
        // 当使用 setlmageDrawable(drawable）函数来设 ImageView 数据源时，自定义 Drawab
        // 的位置和大小与 ImageView scaleType 关。
        iv0.setImageDrawable(L_RoundedCornerDrawable(bitmap))
        iv1.setImageDrawable(L_RoundedCornerDrawable(bitmap))
        iv2.setImageDrawable(L_RoundedCornerDrawable(bitmap))
        iv3.setImageDrawable(L_RoundedCornerDrawable(bitmap))
        iv4.setImageDrawable(L_RoundedCornerDrawable(bitmap))
        iv5.setImageDrawable(L_RoundedCornerDrawable(bitmap))
        iv6.setImageDrawable(L_RoundedCornerDrawable(bitmap))
        iv7.setImageDrawable(L_RoundedCornerDrawable(bitmap))
        iv8.setImageDrawable(L_RoundedCornerDrawable(bitmap))
        // 当使 setBackgroundDrawable(drawable 函数来设置 View 背景时，自定义 Drawable
        // 的宽、高与控件大小一致，控件的宽、高则选取本身宽、高和自定义 Drawable 宽、高
        // 中的最大值。见 getSuggestedMinimumHeight() 方法和 getSuggestedMinimumWidth() 方法。
        tv.background = L_RoundedCornerDrawable(bitmap)
        // 没有固有宽和高的 Drawable，这样 Drawable 会完全包裹文字
        tv1.background = L_RoundedCornerDrawable2(bitmap)
    }
}