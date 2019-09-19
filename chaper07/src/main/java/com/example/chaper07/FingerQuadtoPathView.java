package com.example.chaper07;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author wangzhichao
 * @date 2019/09/19
 */
public class FingerQuadtoPathView extends View {

    private Path path = new Path();
    private Paint paint;
    private GestureDetector gestureDetector;

    public FingerQuadtoPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);

        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                reset();
                return super.onDoubleTapEvent(e);
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(path, paint);
    }

    public void reset() {
        path.reset();
        postInvalidate();
    }

    private float lastX;
    private float lastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                path.moveTo(x, y);
                return true;
            case MotionEvent.ACTION_MOVE:
//                path.lineTo(x, y);
                float endX = (lastX + x) / 2;
                float endY = (lastY + y) / 2;
                path.quadTo(lastX, lastY, endX, endY);
                postInvalidate();
                lastX = x;
                lastY = y;
                break;
        }

        return super.onTouchEvent(event);
    }

}
