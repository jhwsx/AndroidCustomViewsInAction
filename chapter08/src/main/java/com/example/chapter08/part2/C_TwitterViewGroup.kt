package com.example.chapter08.part2

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.chapter08.R

/**
 * @author wangzhichao
 * @date 2019/09/23
 */
class C_TwitterViewGroup(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    init {
        inflate(context, R.layout.layout_twitter_viewgroup, this)
    }
}