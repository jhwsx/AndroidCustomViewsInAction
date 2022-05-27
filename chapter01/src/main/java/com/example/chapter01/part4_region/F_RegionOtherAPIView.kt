package com.example.chapter01.part4_region

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * Region 其他函数
 *
 * @author wangzhichao
 * @since 20-3-16
 */
class F_RegionOtherAPIView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val region = Region()
    private val rect = Rect()
    private val rect2 = Rect()

    init {
        paint.textSize = 40f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = Color.BLACK
        paint.style = Paint.Style.FILL
        canvas.drawText("看代码的日志打印", 10f, 100f, paint)
        region.set(10, 200, 300, 300)
        paint.color = Color.RED
        drawRegion(canvas, region, paint)
        Log.d(TAG, "region.isEmpty() = " + region.isEmpty)
        Log.d(TAG, "region.isRect() = " + region.isRect)
        Log.d(TAG, "region.isComplex() = " + region.isComplex)
        Log.d(TAG, "region.getBounds() = " + region.bounds)
        val contains = region.contains(50, 50)
        Log.d(TAG, "contains = $contains")
        rect.set(20, 210, 250, 250)
        paint.color = Color.GREEN
        canvas.drawRect(rect, paint)
        val quickContains = region.quickContains(rect)
        Log.d(TAG, "quickContains = $quickContains")
        rect2.set(280, 250, 350, 350)
        paint.color = Color.CYAN
        canvas.drawRect(rect2, paint)
        /*
        Return true if the region is empty, or if the specified rectangle does not intersect the region.
        Returning false is not a guarantee that they intersect, but returning true is a guarantee that they do not.
         */
        val quickReject = region.quickReject(rect2)
        Log.d(TAG, "quickReject = $quickReject")
        region.translate(40, 40)
        Log.d(TAG, "after translate: $region")
        paint.color = Color.MAGENTA
        paint.style = Paint.Style.STROKE
        drawRegion(canvas, region, paint)
    }

    /**
     * 绘制 Region
     *
     * @param canvas
     * @param region
     * @param paint
     */
    private fun drawRegion(canvas: Canvas, region: Region, paint: Paint) {
        val regionIterator = RegionIterator(region)
        val rect = Rect()
        while (regionIterator.next(rect)) {
            canvas.drawRect(rect, paint)
        }
    }

    companion object {
        private const val TAG = "RegionOtherAPIView"
    }
}