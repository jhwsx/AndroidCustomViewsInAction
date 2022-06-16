package com.example.chapter03.part2_custom_interpolator_evaluator

import android.animation.TypeEvaluator
import com.example.chapter03.part2_custom_interpolator_evaluator.MyInterpolator
import com.example.chapter03.part2_custom_interpolator_evaluator.B_CustomEvaluatorViewGroup
import com.example.chapter03.part2_custom_interpolator_evaluator.MyEvaluator
import com.example.chapter03.part2_custom_interpolator_evaluator.C_ReverseEvaluatorViewGroup
import com.example.chapter03.part2_custom_interpolator_evaluator.ReverseEvaluator
import com.example.chapter03.part2_custom_interpolator_evaluator.D_ArgbEvaluatorViewGroup

/**
 * 倒序转换器
 *
 * @author wangzhichao
 * @since 2021/5/11
 */
class ReverseEvaluator : TypeEvaluator<Int> {
    override fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {
        return (endValue - fraction * (endValue - startValue)).toInt()
    }
}