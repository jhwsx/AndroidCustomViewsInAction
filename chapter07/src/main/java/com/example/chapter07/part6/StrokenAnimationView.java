package com.example.chapter07.part6;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.example.chapter07.R;

import java.util.Timer;
import java.util.TimerTask;

public class StrokenAnimationView extends View {
    private Paint paint;
    private final RectF rectF;
    private final float defaultStrokeWidth;
    private final float defaultRadius;
    private float strokeWidth;
    private float radius;
    private int rotateStart = 225;
    private int currDegree;
    private boolean animStarted;
    private boolean isAutoStart = true;
    private final int[] colors;
    private final float[] positions;
    private SweepGradient sweepGradient;
    private final Matrix matrix = new Matrix();
    private ValueAnimator valueAnimator;

    public StrokenAnimationView(Context context) {
        this(context, (AttributeSet) null);
    }

    public StrokenAnimationView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StrokenAnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rectF = new RectF();
        defaultStrokeWidth = (float) dp2px(5.0F);
        defaultRadius = (float) dp2px(10.0F);
        strokeWidth = defaultStrokeWidth;
        radius = defaultRadius;
        colors = new int[13];
        colors[0] = Color.parseColor("#FF000000");
        colors[1] = Color.parseColor("#FFFBC85B");
        colors[2] = Color.parseColor("#FFFBC85B");
        colors[3] = Color.parseColor("#FFF1448A");
        colors[4] = Color.parseColor("#FF8925FF");
        colors[5] = Color.parseColor("#FF6DCDFC");
        colors[6] = Color.parseColor("#FF000000");
        colors[7] = Color.parseColor("#FF000000");
        colors[8] = Color.parseColor("#FFFBC85B");
        colors[9] = Color.parseColor("#FFFBC85B");
        colors[10] = Color.parseColor("#FF6DCDFC");
        colors[11] = Color.parseColor("#FF8925FF");
        colors[12] = Color.parseColor("#FFF1448A");
        positions = new float[]{0.25F, 0.251F, 0.37F, 0.41F, 0.46F, 0.5F, 0.501F, 0.75F, 0.751F, 0.87F, 0.91F, 0.96F, 1.0F};
        float cx = (float) (getMeasuredWidth() / 2);
        float cy = (float) (getMeasuredHeight() / 2);
        sweepGradient = new SweepGradient(cx, cy, colors, positions);
        parseTypeArray(context, attrs);
        initPaint();
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        float cx = (float) (getMeasuredWidth() / 2);
        float cy = (float) (getMeasuredHeight() / 2);
        sweepGradient = new SweepGradient(cx, cy, colors, positions);
        paint.setShader(sweepGradient);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        rectF.left = strokeWidth / 2;
        rectF.top = strokeWidth / 2;
        rectF.right = (float) getMeasuredWidth() - strokeWidth / 2;
        rectF.bottom = (float) getMeasuredHeight() - strokeWidth / 2 ;
        if (isAutoStart) {
            startAnim();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        matrix.reset();
        matrix.setRotate(currDegree, (float) (getMeasuredWidth() / 2), (float) (getMeasuredHeight() / 2));
        sweepGradient.setLocalMatrix(matrix);
        canvas.drawRoundRect(rectF, radius, radius, paint);
    }

    public void startAnim() {
        if (!animStarted) {
            animStarted = true;
            valueAnimator = ValueAnimator.ofInt(rotateStart, 360 + rotateStart);
            valueAnimator.setDuration(2000L);
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    currDegree = (int) animation.getAnimatedValue();
                    invalidate();
                }
            });
            valueAnimator.start();
        }
    }

    public void parseTypeArray(Context var1, AttributeSet var2) {
        TypedArray ta = var1.obtainStyledAttributes(var2, R.styleable.StrokenAnimationView);
        isAutoStart = ta.getBoolean(R.styleable.StrokenAnimationView_is_auto_start, true);
        rotateStart = ta.getInteger(R.styleable.StrokenAnimationView_rotate_start, 225);
        strokeWidth = ta.getDimension(R.styleable.StrokenAnimationView_stroke_width, defaultStrokeWidth);
        radius = ta.getDimension(R.styleable.StrokenAnimationView_stroke_radius, defaultRadius);
        ta.recycle();
    }

    public void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(this.strokeWidth);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (valueAnimator != null) {
            valueAnimator.removeAllUpdateListeners();
            valueAnimator.cancel();
            valueAnimator = null;
        }
    }

    private int dp2px(float dp) {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
    }
}
