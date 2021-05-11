package com.example.chapter03.part2_custom_interpolator_evaluator;

import android.animation.TypeEvaluator;

/**
 * 倒序转换器
 *
 * @author wangzhichao
 * @since 2021/5/11
 */
public class ReverseEvaluator implements TypeEvaluator<Integer> {
    @Override
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        int startInt = startValue;
        return (int) (endValue - fraction * (endValue - startInt));
    }
}
