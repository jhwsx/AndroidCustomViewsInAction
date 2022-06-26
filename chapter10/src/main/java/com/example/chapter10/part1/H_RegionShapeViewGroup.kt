package com.example.chapter10.part1

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.chapter10.R

/**
 * @author wangzhichao
 * @date 2019/10/12
 */
class H_RegionShapeViewGroup(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    init {
        inflate(context, R.layout.layout_region_shape_viewgroup, this)
    }
}