package com.example.chapter01.part4_region

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * Region 的直接构造
 * public Region(Region region)
 * public Region(Rect r)
 * public Region(int left, int top, int right, int bottom)
 *
 * @author wangzhichao
 * @since 20-3-16
 */
class A_RegionDirectConstructView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    // 通过一个矩形来构建一个 Region 对象。
    private val region = Region(Rect(20, 100, 300, 200))

    init {
        paint.style = Paint.Style.FILL
        paint.color = Color.RED
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawRegion(canvas, region, paint)
    }

    /**
     * 绘制 Region
     * 需要注意的是，如果 Paint 的 setStyle 为 STROKE，那么只会绘制出一个外围的框。
     *
     * @param canvas
     * @param region
     * @param paint
     */
    private fun drawRegion(canvas: Canvas, region: Region, paint: Paint) {
        val regionIterator = RegionIterator(region)
        val rect = Rect()
        while (regionIterator.next(rect)) {
            Log.d(TAG, "drawRect: ") // 只会打印一次
            canvas.drawRect(rect, paint)
        }
    }

    companion object {
        private const val TAG = "RegionDirectConstructVi"
    }
}