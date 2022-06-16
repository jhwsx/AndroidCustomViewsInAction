package com.example.chapter03.part3_valueanimator_advanced_ofobject

import android.animation.TypeEvaluator

/**
 * @author wangzhichao
 * @since 2021/5/11
 */
class CharEvaluator : TypeEvaluator<Char> {
    override fun evaluate(fraction: Float, startValue: Char, endValue: Char): Char {
        val startInt = startValue.code
        val endInt = endValue.code
        return (startInt + fraction * (endInt - startInt)).toInt().toChar()
    }
}