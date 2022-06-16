package com.example.chapter03.part2_custom_interpolator_evaluator;

import android.animation.TypeEvaluator;

/**
 * 自定义转换器
 *
 * @author wangzhichao
 * @since 2021/5/10
 */
public class MyEvaluator implements TypeEvaluator<Integer> {
    @Override
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        int startInt = startValue;
        return (int) (200 + startInt + fraction * (endValue - startInt));
    }
}
