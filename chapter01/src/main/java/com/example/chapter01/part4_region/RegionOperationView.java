package com.example.chapter01.part4_region;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Region区域操作
 * // 方式一：操作结果是赋值给调用者的，也就是调用的 Region 对象
 * public boolean op(Rect r, Op op)
 * public boolean op(int left, int top, int right, int bottom, Op op)
 * public boolean op(Region region, Op op)
 *
 * // 方式二：操作结果也是赋值给调用者，一般是我们另外创建的一个空的 Region 对象
 * public boolean op(Rect rect, Region region, Op op)
 * public boolean op(Region region1, Region region2, Op op)
 *
 * Op 是一个枚举
 * public enum Op {
 *     DIFFERENCE(0), // 最终区域为 region1 与 region2 不同的区域
 *     INTERSECT(1), // 最终区域为 region1 与 region2 相同的区域
 *     UNION(2), // 最终区域为 region1 与 region2 组合在一起的区域
 *     XOR(3), // 最终区域为 region1 与 region2 相交之外的部分组成的区域
 *     REVERSE_DIFFERENCE(4), // 最终区域为 region2 与 region1 不同的区域
 *     REPLACE(5); // 最终区域为 region2 的区域
 *     Op(int nativeInt) {
 *         this.nativeInt = nativeInt;
 *     }
 *
 *     public final int nativeInt;
 *}
 * @author wangzhichao
 * @since 20-3-16
 */
public class RegionOperationView extends View {
    public RegionOperationView(Context context) {
        super(context);
    }

    public RegionOperationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RegionOperationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        // 绘制两个 stroke 的矩形
        Rect rect1 = new Rect(100, 100, 400, 200);
        Rect rect2 = new Rect(200, 0, 300, 300);
        canvas.drawRect(rect1, paint);
        canvas.drawRect(rect2, paint);
        // 创建两个 Region
        Region region1 = new Region(rect1);
        Region region2 = new Region(rect2);

        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);

        // 对两个区域进行操作，下面两种方法是等价的。
        // 第一种方法：public boolean op(Region region, Op op)
//        region1.op(region2, currOp);
//        drawRegion(canvas, region1, paint);

        // 第二种方法：public boolean op(Region region1, Region region2, Op op)
        Region region = new Region();
        region.op(region1, region2, currOp);
        drawRegion(canvas, region, paint);




    }

    private void drawRegion(Canvas canvas, Region region, Paint paint) {
        RegionIterator regionIterator = new RegionIterator(region);
        Rect rect = new Rect();
        while (regionIterator.next(rect)) {
            canvas.drawRect(rect, paint);
        }
    }
    private Region.Op currOp = Region.Op.DIFFERENCE;
    public void setOp(Region.Op op) {
        currOp = op;
        invalidate();
    }
}
