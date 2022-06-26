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
class CircledDrawableViewGroup(context: Context?, attrs: AttributeSet?) :
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
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.avator)
        iv0.setImageDrawable(CircledDrawable(bitmap))
        iv1.setImageDrawable(CircledDrawable(bitmap))
        iv2.setImageDrawable(CircledDrawable(bitmap))
        iv3.setImageDrawable(CircledDrawable(bitmap))
        iv4.setImageDrawable(CircledDrawable(bitmap))
        iv5.setImageDrawable(CircledDrawable(bitmap))
        iv6.setImageDrawable(CircledDrawable(bitmap))
        iv7.setImageDrawable(CircledDrawable(bitmap))
        iv8.setImageDrawable(CircledDrawable(bitmap))
        tv.background = CircledDrawable(bitmap)
        tv1.background = CircledDrawable1(bitmap)
    }
}