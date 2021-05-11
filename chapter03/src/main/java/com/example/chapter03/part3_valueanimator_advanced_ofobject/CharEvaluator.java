package com.example.chapter03.part3_valueanimator_advanced_ofobject;

import android.animation.TypeEvaluator;

/**
 * @author wangzhichao
 * @since 2021/5/11
 */
public class CharEvaluator implements TypeEvaluator<Character> {
    @Override
    public Character evaluate(float fraction, Character startValue, Character endValue) {
        int startInt = startValue;
        int endInt = endValue;
        return ((char) (startInt + fraction * (endInt - startInt)));
    }
}
