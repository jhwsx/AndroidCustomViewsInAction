package com.example.chapter03.part3_valueanimator_advanced_ofobject;

import android.animation.TypeEvaluator;
import android.graphics.Point;

/**
 * @author wangzhichao
 * @since 2021/5/11
 */
public class FallingBallEvaluator implements TypeEvaluator<Point> {
    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        int x = (int) (startValue.x + fraction * (endValue.x - startValue.x));
        int y;
        if (fraction * 2 <= 1) {
            y = (int) (startValue.y + fraction * 2 * (endValue.y - startValue.y));
        } else {
            y = endValue.y;
        }
        return new Point(x, y);
    }
}
