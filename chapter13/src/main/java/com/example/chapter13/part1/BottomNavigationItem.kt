package com.example.chapter13.part1

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.chapter13.R

/**
 *
 * @author wangzhichao
 * @since 2022/8/12
 */
@SuppressLint("ResourceType")
class BottomNavigationItem(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private val ATTRS = intArrayOf(android.R.attr.text, R.attr.srcCompat)
    private val tv: TextView
    init {
        inflate(context, R.layout.bottom_navigation_view, this)
        val iv: ImageView = findViewById(R.id.iv)
        tv = findViewById(R.id.tv)
        context.obtainStyledAttributes(attrs, ATTRS).use {
            val text = it.getString(0)
            val src = it.getDrawable(1)
            iv.setImageDrawable(src)
            tv.text = text
        }
//        setOnClickListener {
//            Toast.makeText(context, tv.text, Toast.LENGTH_SHORT).show()
//        }
    }
}