package com.example.chaper07.part5;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * @author wangzhichao
 * @date 2019/09/22
 */
public class ShimmerTextView extends AppCompatTextView {
    private static final String TAG = "ShimmerTextView";
    private Paint paint;
    private int dx;
    private LinearGradient linearGradient;
    private Matrix matrix = new Matrix();

    public ShimmerTextView(Context context) {
        super(context);
        init();
    }

    public ShimmerTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShimmerTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = getPaint(); // 注意这里要获取 TextView 里的 paint，而不能去 new Paint().
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d(TAG, "onLayout");
        ValueAnimator va = ValueAnimator.ofInt(0, getMeasuredWidth() * 2);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.d(TAG, "onAnimationUpdate");
                dx = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        va.setRepeatMode(ValueAnimator.RESTART);
        va.setRepeatCount(ValueAnimator.INFINITE);
        va.setDuration(2000L);
        va.start();
        // 初始位置是在文字的左侧，宽度与文字宽度一样，使用的是边缘填充模式
        linearGradient = new LinearGradient(-getMeasuredWidth(), 0, 0, 0, new int[]{getCurrentTextColor(), 0xff00ff00, getCurrentTextColor()},
                new float[]{0f, 0.5f, 1f}, Shader.TileMode.CLAMP);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw");
        // 移动 LinearGradient
        matrix.reset();
        matrix.setTranslate(dx, 0);
        linearGradient.setLocalMatrix(matrix);
        paint.setShader(linearGradient);
        super.onDraw(canvas);
    }
}
