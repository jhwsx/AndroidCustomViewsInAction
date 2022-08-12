package com.example.chapter13.part1

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.chapter13.R

/**
 *
 * @author wangzhichao
 * @since 2022/8/12
 */
@SuppressLint("ResourceType")
class BottomNavigationItem(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private val ATTRS = intArrayOf(android.R.attr.text, android.R.attr.src)

    init {
        inflate(context, R.layout.bottom_navigation_view, this)
        val iv: ImageView = findViewById(R.id.iv)
        val tv: TextView = findViewById(R.id.tv)
        context.obtainStyledAttributes(attrs, ATTRS).use {
            val text = it.getString(0)
            val src = it.getDrawable(1)
            iv.setImageDrawable(src)
            tv.text = text
        }
    }
}