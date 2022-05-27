package com.example.chapter01.part4_region

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

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
 * DIFFERENCE(0), // 最终区域为 region1 与 region2 不同的区域
 * INTERSECT(1), // 最终区域为 region1 与 region2 相同的区域
 * UNION(2), // 最终区域为 region1 与 region2 组合在一起的区域
 * XOR(3), // 最终区域为 region1 与 region2 相交之外的部分组成的区域
 * REVERSE_DIFFERENCE(4), // 最终区域为 region2 与 region1 不同的区域
 * REPLACE(5); // 最终区域为 region2 的区域
 * Op(int nativeInt) {
 * this.nativeInt = nativeInt;
 * }
 *
 * public final int nativeInt;
 * }
 * @author wangzhichao
 * @since 20-3-16
 */
class E_RegionOperationView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rect1 = Rect()
    private val rect2 = Rect()
    private val region1 = Region()
    private val region2 = Region()
    private val region = Region()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        // 绘制两个 stroke 的矩形
        rect1.set(100, 100, 400, 200)
        rect2.set(200, 0, 300, 300)
        canvas.drawRect(rect1, paint)
        canvas.drawRect(rect2, paint)
        // 创建两个 Region
        region1.set(rect1)
        region2.set(rect2)
        paint.color = Color.GREEN
        paint.style = Paint.Style.FILL

        // 对两个区域进行操作，下面两种方法是等价的。
        // 第一种方法：public boolean op(Region region, Op op)
//        region1.op(region2, currOp);
//        drawRegion(canvas, region1, paint);

        // 第二种方法：public boolean op(Region region1, Region region2, Op op)
        region.op(region1, region2, currOp)
        drawRegion(canvas, region, paint)
    }

    private fun drawRegion(canvas: Canvas, region: Region, paint: Paint) {
        val regionIterator = RegionIterator(region)
        val rect = Rect()
        while (regionIterator.next(rect)) {
            canvas.drawRect(rect, paint)
        }
    }

    var currOp = Region.Op.DIFFERENCE
        set(value) {
            field = value
            invalidate()
        }
}