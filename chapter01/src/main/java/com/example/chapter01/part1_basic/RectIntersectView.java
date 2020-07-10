package com.example.chapter01.part1_basic;

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
 * 判断两个矩形是否相交并且如果相交就把相交的矩形赋值给调用者，如果不相交则调用矩形不变。
 * 1，public boolean intersect(int left, int top, int right, int bottom)
 * 2，public boolean intersect(Rect r)
 * 觉得这种方法不纯粹，判断里面还有修改。
 *
 * @author wangzhichao
 * @since 20-3-7
 */
public class RectIntersectView extends View {
    private static final String TAG = "RectIntersectView";
    public RectIntersectView(Context context) {
        super(context);
    }

    public RectIntersectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RectIntersectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        boolean intersect12 = rect1.intersect(rect2);
        // intersect12:true,intersectRect=[190,10][200,200]
        Log.d(TAG, "intersect12:" + intersect12 + ",intersectRect=" + rect1.toShortString());
        Log.d(TAG, "isIntersect(rect1, rect2) = " + isIntersect(rect1, rect2));
        boolean intersect23 = rect2.intersect(rect3);
        // intersect23:false,intersectRect=[190,10][400,200]
        Log.d(TAG, "intersect23:" + intersect23 + ",intersectRect=" + rect2.toShortString());
        Log.d(TAG, "isIntersect(rect2, rect3) = " + isIntersect(rect2, rect3));
    }
    // 判断两个矩形相交的思路
    // 先考虑对立事件:不相交的情况有哪些
    private boolean isIntersect(Rect rect1, Rect rect2) {
        if (rect1.top > rect2.bottom) {
            return false;
        }
        if (rect1.bottom < rect2.top) {
            return false;
        }
        if (rect1.left > rect2.right) {
            return false;
        }
        if (rect1.right < rect2.left) {
            return false;
        }
        return true;
        // 等价于下面的写法
//        return rect1.bottom >= rect2.top && rect1.top <= rect2.bottom
//                && rect1.left <= rect2.right && rect1.right >= rect2.left;
    }
}
