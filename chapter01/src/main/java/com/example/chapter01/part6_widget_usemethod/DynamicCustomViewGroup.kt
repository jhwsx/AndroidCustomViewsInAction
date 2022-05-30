package com.example.chapter01.part6_widget_usemethod

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.widget.RelativeLayout
import com.example.chapter01.R
import com.example.chapter01.part6_widget_usemethod.CustomView

/**
 * @author wangzhichao
 * @since 20-3-24
 */
class DynamicCustomViewGroup(context: Context, attrs: AttributeSet?) :
    RelativeLayout(context, attrs) {
    init {
        inflate(context, R.layout.viewgroup_dynamic_custom_view, this)
        val root = findViewById<RelativeLayout>(R.id.root)
        val customView = CustomView(context)
        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        layoutParams.addRule(RIGHT_OF, R.id.text)
        layoutParams.setMargins(10, 20, 30, 40)
        customView.layoutParams = layoutParams
        customView.setBackgroundColor(Color.GREEN)
        root.addView(customView)
        root.gravity = Gravity.CENTER
    }
}