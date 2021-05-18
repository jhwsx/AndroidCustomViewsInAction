package com.example.chapter06.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

/**
 * @author wangzhichao
 * @date 2019/09/18
 */
public class PathEffectAnimView extends View {
    public PathEffectAnimView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 60); // 这个 75 是下面的 intervals 数组元素的和，不然会有一跳一跳的效果出现，不好。
        valueAnimator.setDuration(500L);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int current = (int) animation.getAnimatedValue();
                setPhase(current);
                postInvalidate();
            }
        });
        valueAnimator.start();
    }
    private int phase;
    Path path1 = new Path();
    Path path2 = new Path();
    Paint paint = new Paint();
    RectF rect;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (rect == null) {
            rect = new RectF(getWidth()* 0.2f, getHeight() * 0.3f, getWidth() * 0.8f, getHeight() * 0.7f );
        }
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10f);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setLinearText(false);
        path1.reset();
        path1.addRect(rect, Path.Direction.CCW);
        paint.setPathEffect(new DashPathEffect(new float[]{40, 20}, phase));
        canvas.drawPath(path1, paint);

        paint.setAntiAlias(true);
        paint.setStrokeWidth(10f);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setLinearText(false);
        path2.reset();
        path2.addRect(rect, Path.Direction.CCW);
        paint.setPathEffect(new DashPathEffect(new float[]{0, 40, 20, 0}, phase));
        canvas.drawPath(path2, paint);
    }

    private void setPhase(int phase) {
        this.phase = phase;
    }
}
