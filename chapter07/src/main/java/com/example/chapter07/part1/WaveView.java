package com.example.chapter07.part1;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

/**
 * @author wangzhichao
 * @date 2019/09/19
 */
public class WaveView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int waveLength = 400;
    private int height = 300;
    private Path path = new Path();
    private int progress;

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, waveLength);
        valueAnimator.setDuration(1000L);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curr = (int) animation.getAnimatedValue();
                update(curr);
            }
        });
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int halfWaveLength = waveLength / 2;
        path.reset();
        path.moveTo(-waveLength + progress, height);
        for (int i = -waveLength; i <= getWidth() + waveLength; i += waveLength) {
            path.rQuadTo(halfWaveLength / 2, -50, halfWaveLength, 0);
            path.rQuadTo(halfWaveLength / 2, 50, halfWaveLength, 0);
        }
        path.lineTo(getWidth(), getHeight());
        path.lineTo(0, getHeight());
        path.close();
        canvas.drawPath(path, paint);
    }

    private void update(int progress) {
        this.progress = progress;
        invalidate();
    }
}
