package com.example.chapter03.part3_valueanimator_advanced_ofobject

import android.animation.TypeEvaluator
import android.graphics.Point

/**
 * @author wangzhichao
 * @since 2021/5/11
 */
class FallingBallEvaluator : TypeEvaluator<Point> {
    override fun evaluate(fraction: Float, startValue: Point, endValue: Point): Point {
        val x = (startValue.x + fraction * (endValue.x - startValue.x)).toInt()
        val y: Int
        y = if (fraction * 2 <= 1) {
            (startValue.y + fraction * 2 * (endValue.y - startValue.y)).toInt()
        } else {
            endValue.y
        }
        return Point(x, y)
    }
}