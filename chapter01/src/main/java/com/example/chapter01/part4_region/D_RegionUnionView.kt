package com.example.chapter01.part4_region

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * Region 最重要的操作在相交操作中。
 *
 * 用于与指定的矩形取并集
 * public final boolean union(Rect r)
 *
 * @author wangzhichao
 * @since 20-3-16
 */
class D_RegionUnionView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val region = Region()
    private val rect = Rect()
    private val path = Path()
    private val clip = Region()
    init {
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 创建区域
        path.reset()
        path.addCircle(325f,400f, 120f, Path.Direction.CCW)
        clip.set(0,0,800,800)
        region.setPath(path, clip)
        // 合并 3 个矩形
        rect.set(50, 50, 600, 200)
        region.union(rect)
        rect.set(500, 200, 600, 600)
        region.union(rect)
        rect.set(50, 200, 150, 600)
        region.union(rect)

        // 绘制区域
        drawRegion(canvas, region, paint)
    }

    private fun drawRegion(canvas: Canvas, region: Region, paint: Paint) {
        val regionIterator = RegionIterator(region)
        val rect = Rect()
        while (regionIterator.next(rect)) {
            canvas.drawRect(rect, paint)
        }
    }
}