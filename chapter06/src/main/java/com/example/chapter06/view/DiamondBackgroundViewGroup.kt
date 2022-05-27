package com.example.chapter06.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.chapter06.R

/**
 * @author wangzhichao
 * @since 2020/01/09
 */
class DiamondBackgroundViewGroup(context: Context?, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    init {
        inflate(context, R.layout.practice_diamond_background_viewgroup, this)
    }
}