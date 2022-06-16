package com.example.chapter03.part1_valueanimator

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.chapter03.R

/**
 * 弹跳加载中动画
 * @author wangzhichao
 * @date 7/29/20
 */
class E_BounceLoadingViewGroup_My(context: Context?, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {

    init {
        inflate(context, R.layout.layout_bounce_loading_viewgroup_my, this)
    }

}