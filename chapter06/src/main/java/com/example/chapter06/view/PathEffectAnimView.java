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
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 75); // 这个 75 是下面的 intervals 数组元素的和，不然会有一跳一跳的效果出现，不好。
        valueAnimator.setDuration(200L);
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
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5f);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setLinearText(false);
        Path path = new Path();
        RectF rect = new RectF(getWidth()* 0.2f, getHeight() * 0.3f, getWidth() * 0.8f, getHeight() * 0.7f ) ;
        path.addRect(rect, Path.Direction.CCW);
        paint.setPathEffect(new DashPathEffect(new float[]{40, 20, 10, 5}, phase));
        canvas.drawPath(path, paint);
    }

    private void setPhase(int phase) {
        this.phase = phase;
    }
}
