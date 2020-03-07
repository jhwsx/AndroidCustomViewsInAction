package com.example.chapter01.part1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Rect 类的合并方法演示：
 * 与某个矩形合并
 *  public void union(int left, int top, int right, int bottom)
 *  public void union(Rect r)
 *  如果传入的矩形是空的，则什么都不做；
 *  如果传入的矩形不为空，则判断调用的矩形：
 *      如果调用的矩形不为空，则进行合并，合并规则是：
 *          取最小的左边，上边赋值给调用矩形；
 *          取最大的右边，下边赋值给调用矩形
 * 与某个点合并
 * public void union(int x, int y)
 * 合并规则： x 比左边小，则取x；x比右边大，则取x；
 * y 比上边小，则取 y；y 比下边大，则取y。
 * @author wangzhichao
 * @since 20-3-7
 */
public class RectUnionView extends View {
    public RectUnionView(Context context) {
        super(context);
    }

    public RectUnionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RectUnionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 合并矩形
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(5f);
        paint.setStyle(Paint.Style.STROKE);

        Rect rect1 = new Rect(50, 50, 100, 100);
        paint.setColor(0x44FF0000);
        canvas.drawRect(rect1, paint);

        Rect rect2 = new Rect(250, 250, 300, 300);
        paint.setColor(0x4400FF00);
        canvas.drawRect(rect2, paint);

        rect1.union(rect2);

        paint.setColor(0x440000FF);
        canvas.drawRect(rect1, paint);


        // 合并点
        paint.setColor(0x44FF00FF);
        paint.setStrokeWidth(5f);

        Rect rect3 = new Rect(50, 400, 300, 650);
        canvas.drawRect(rect3, paint);

        paint.setColor(0x4400ffff);
        paint.setStrokeWidth(10f);
        canvas.drawPoint(700, 700, paint);

        rect3.union(700, 700);

        paint.setColor(0x44ff0000);

        paint.setStrokeWidth(5f);
        canvas.drawRect(rect3, paint);

        Rect rect4 = new Rect(50, 800, 200, 900);
        paint.setColor(0x66ff0000);
        canvas.drawRect(rect4, paint);

        paint.setColor(0x6600ff00);
        paint.setStrokeWidth(10f);
        canvas.drawPoint(300,750, paint);
        rect4.union(300, 750);

        paint.setColor(0x66ff00ff);
        paint.setStrokeWidth(5f);
        canvas.drawRect(rect4, paint);
    }
}
