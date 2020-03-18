package com.example.chapter01.part5_canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Canvas 画布的平移操作
 * 参一：x 方向上的平移距离，（+）right(-)left,
 * 参二：y 方向上的移动距离，(+)bottom(-)top
 * public void translate(float dx, float dy)
 *
 * @author wangzhichao
 * @since 20-3-17
 */
public class CanvasTranslateView extends View {

    public CanvasTranslateView(Context context) {
        super(context);
    }

    public CanvasTranslateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasTranslateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        if (translate) {
            canvas.translate(100, 100);
        }
        Rect rect = new Rect(0, 0, 400, 220);
        canvas.drawRect(rect, paint);
    }
    boolean translate = false;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        translate = !translate;
        invalidate();
        return super.onTouchEvent(event);
    }
}
