package com.example.chapter10.part1

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import com.example.chapter10.R

/**
 * 演示 shape 标签对应的是 GradientDrawable，而不是 ShapeDrawable。
 *
 * @author wangzhichao
 * @date 2019/10/10
 */
class A_GradientDrawableShapeViewGroup(context: Context, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    companion object {
        private const val TAG = "wzc"
    }

    init {
        inflate(context, R.layout.layout_gradientdrawable_shape_viewgroup, this)
        val textView = findViewById<TextView>(R.id.tv)
        val textView1 = findViewById<TextView>(R.id.tv1)
        val background = textView.background
        textView1.text = background.toString()
        val gradientDrawable = background as GradientDrawable
        val textView2 = findViewById<TextView>(R.id.tv2)
        val textView3 = findViewById<TextView>(R.id.tv3)
        val background2 = textView2.background
        textView3.text = background2.toString()
    }
}