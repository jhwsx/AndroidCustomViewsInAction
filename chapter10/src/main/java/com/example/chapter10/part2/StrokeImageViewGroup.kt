package com.example.chapter10.part2

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.chapter10.R

/**
 * @author wangzhichao
 * @date 2019/12/02
 */
class StrokeImageViewGroup(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    init {
        inflate(context, R.layout.layout_stroke_image_viewgroup, this)
    }
}