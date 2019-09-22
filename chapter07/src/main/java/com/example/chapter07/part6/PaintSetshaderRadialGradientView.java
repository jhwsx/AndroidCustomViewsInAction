package com.example.chapter07.part6;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.chapter07.R;

/**
 * @author wangzhichao
 * @date 2019/09/22
 */
public class PaintSetshaderRadialGradientView extends View {
    private static final String TAG = "RadialGradientView";
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float radius = 200;
    private RadialGradient radialGradient;

    public PaintSetshaderRadialGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "onSizeChanged");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!multiColor) {
            radialGradient = new RadialGradient(getWidth() / 2, getHeight() / 2, radius,
                    0xffff0000, 0xff00ff00, tileMode);
        } else {
            int[] colors = {0xffff0000, 0xff00ff00, 0xff0000ff, 0xffffff00};
            float[] stops = {0f, 0.2f, 0.5f, 1f};
            radialGradient = new RadialGradient(getWidth() / 2, getHeight() / 2, radius, colors, stops, tileMode);
        }
        paint.setShader(radialGradient);
        Log.d(TAG, "onDraw");
        if (drawRect) {
            if (smallRect) {
                canvas.drawRect(getWidth() / 4, getHeight() / 4, getWidth() /2, getHeight() /2, paint);
            } else {
                canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
            }
        } else {
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, paint);
        }
    }

    private boolean multiColor;

    public void setMultiColor(boolean multiColor) {
        this.multiColor = multiColor;
        invalidate();
    }

    private boolean drawRect;

    public void setDrawRect(boolean drawRect) {
        this.drawRect = drawRect;
        invalidate();
    }

    private Shader.TileMode tileMode = Shader.TileMode.REPEAT;

    public void setTileMode(Shader.TileMode tileMode) {
        this.tileMode = tileMode;
        invalidate();
    }

    private boolean smallRect;

    public void setSmallRect(boolean smallRect) {
        this.smallRect = smallRect;
        invalidate();
    }
}
