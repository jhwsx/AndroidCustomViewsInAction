package com.example.chapter07.part6_radialgradient

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.chapter07.R

/**
 * @author wangzhichao
 * @date 2019/09/22
 */
class B_RippleViewGroup(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    init {
        inflate(context, R.layout.layout_ripple_viewgroup, this)
    }
}