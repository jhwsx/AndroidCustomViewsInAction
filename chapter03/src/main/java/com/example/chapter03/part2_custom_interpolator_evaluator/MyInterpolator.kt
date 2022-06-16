package com.example.chapter03.part2_custom_interpolator_evaluator

import android.animation.TimeInterpolator
import android.util.Log

/**
 * @author wangzhichao
 * @since 2021/5/10
 */
class MyInterpolator : TimeInterpolator {
    /**
     * input 参数与任何我们设定的值没有关系，只与时间有关 ，随着时间的推移，
     * 动画的进度也自然地增加， input 参数就代表了当前动画的进度，而返回值则表示动画的当前
     * 数值进度。
     *
     * @param input 取值范围是 0f 到 1f，而且是从 0f 慢慢变化到 1f。
     * @return 返回值未必是 0f 到 1f 之间。
     */
    override fun getInterpolation(input: Float): Float {
        Log.d("MyInterpolator", "getInterpolation: input = $input")
        return 1 - input
    }
}