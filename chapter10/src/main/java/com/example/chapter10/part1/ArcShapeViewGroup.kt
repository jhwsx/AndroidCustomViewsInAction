package com.example.chapter10.part1

import android.content.Context
import com.example.chapter10.R
import android.widget.LinearLayout
import android.util.AttributeSet
/**
 * @author wangzhichao
 * @date 2019/10/11
 */
class ArcShapeViewGroup(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    init {
        inflate(context, R.layout.layout_arc_shape_viewgroup, this)
    }
}