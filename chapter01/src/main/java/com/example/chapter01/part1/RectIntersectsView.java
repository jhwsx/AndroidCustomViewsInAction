package com.example.chapter01.part1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


/**
 * 判断两个矩形是否相交的方法
 * 1，Rect 类中的静态方法 intersects(Rect a, Rect b)方法演示
 * 2，Rect 类中的成员方法 intersects(int left, int top, int right, int bottom)
 * @author wangzhichao
 * @since 20-3-7
 */
public class RectIntersectsView extends View {
    public RectIntersectsView(Context context) {
        super(context);
    }

    public RectIntersectsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RectIntersectsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);
        Rect rect1 = new Rect(10, 10, 200, 200);
        Rect rect2 = new Rect(190, 10, 400, 200);
        Rect rect3 = new Rect(10, 210, 200, 300);

        paint.setColor(Color.RED);
        canvas.drawRect(rect1, paint);
        paint.setColor(Color.GREEN);
        canvas.drawRect(rect2, paint);
        paint.setColor(Color.YELLOW);
        canvas.drawRect(rect3, paint);

        Log.d("wzc", "Rect.intersects(rect1, rect2) =" + Rect.intersects(rect1, rect2)); // true
        Log.d("wzc", "Rect.intersects(rect1, rect3) =" + Rect.intersects(rect1, rect3)); // false

        Log.d("wzc", "rect1.intersects(rect2.left, rect2.top, rect2.right, rect2.bottom) = " +
                rect1.intersects(rect2.left, rect2.top, rect2.right, rect2.bottom)); // true
        Log.d("wzc", "rect1.intersects(rect3.left, rect3.top, rect3.right, rect3.bottom) = " +
                rect1.intersects(rect3.left, rect3.top, rect3.right, rect3.bottom)); // false
    }
}
