package com.example.chapter01.part4_region

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * 学习 RegionIterator
 *
 * @author wangzhichao
 * @since 20-3-16
 */
class C_RegionIteratorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rect = Rect()
    private val path = Path()
    private val rectF = RectF()
    private val clip = Region()
    private val region = Region()
    init {
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        rect.set(50, 50, 300, 800)
        // 绘制路径
        rectF.set(rect)
        path.addRoundRect(rectF, 50f, 50f, Path.Direction.CCW)
        // 绘制 clip
        clip.set(rect)
        // 获取最终的区域
        region.setPath(path, clip)
        drawRegion(canvas, region, paint)

        rect.set(400, 50, 700, 800)
        rectF.set(rect)
        path.addOval(rectF, Path.Direction.CCW)
        clip.set(rect)
        region.setPath(path, clip)
        drawRegion(canvas, region, paint)
    }

    /**
     * 绘制 Region
     * 在 Paint 使用 setStyle 为 STROKE 的情况下：
     * 如果区域就是一个矩形，那么绘制出来的就是一个矩形框；
     * 如果区域不是一个矩形，那么可以绘制出多个矩形来。
     *
     * @param canvas
     * @param region
     * @param paint
     */
    private fun drawRegion(canvas: Canvas, region: Region, paint: Paint) {
        // 创建区域对应的矩形集
        val regionIterator = RegionIterator(region)
        val rect = Rect()
        // next 方法获取下一个矩形，保存在容器参数里面
        while (regionIterator.next(rect)) {
            // 绘制那个矩形
            canvas.drawRect(rect, paint)
        }
    }

    companion object {
        private const val TAG = "RegionIteratorView"
    }
}