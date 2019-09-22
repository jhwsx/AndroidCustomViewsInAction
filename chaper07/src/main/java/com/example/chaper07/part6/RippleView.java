package com.example.chaper07.part6;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.LinearInterpolator;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * @author wangzhichao
 * @date 2019/09/22
 */
public class RippleView extends AppCompatImageView {
    public static final String TAG = "RippleView";
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float centerX;
    private float centerY;
    private float radius;
    private ObjectAnimator objectAnimator;
    private float maxRadius;
    private static final float DEFAULT_RADIUS = 20f;
    public RippleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (maxRadius == 0f) {
            maxRadius = (float) Math.sqrt(getWidth() * getWidth() + getHeight() * getHeight());
        }
        if (radius > 0) {
            canvas.drawCircle(centerX, centerY, radius, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent: ACTION_DOWN");
                if (objectAnimator != null && objectAnimator.isRunning()) {
                    Log.d(TAG, "onTouchEvent: cancel running anim");
                    objectAnimator.cancel();
                }
                // 手指按下时，绘制一个默认大小的圆圈
                centerX = event.getX();
                centerY = event.getY();
                setRadius(DEFAULT_RADIUS);
                return true;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent: ACTION_UP");
                // 手指抬起时，绘制扩散的圆圈
                if (objectAnimator == null) {
                    objectAnimator = ObjectAnimator.ofObject(this, "radius", new FloatEvaluator(), DEFAULT_RADIUS, maxRadius);
                    objectAnimator.setDuration(1000L);
                    objectAnimator.setInterpolator(new LinearInterpolator());
                    objectAnimator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            setRadius(0);
                        }
                    });
                }
                objectAnimator.start();
                break;
        }
        return super.onTouchEvent(event);
    }

    public void setRadius(float radius) {
        Log.d(TAG, "setRadius: radius = " + radius);
        this.radius = radius;
        if (radius > 0) {
            RadialGradient radialGradient = new RadialGradient(centerX, centerY, radius, 0x00ffffff, 0xff58faac, Shader.TileMode.CLAMP);
            paint.setShader(radialGradient);
        }
        invalidate();
    }
}
