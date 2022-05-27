package com.example.chapter01.part4_region

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * Region 的间接构造
 * 清空区域
 * public void setEmpty()
 * 设置矩形区域
 * public boolean set(int left, int top, int right, int bottom)
 * 设置矩形区域
 * public boolean set(Rect r)
 * 设置指定区域
 * public boolean set(Region region)
 * 参数一：用来构造区域的路径；参数二：与参一的 path 构成的路径取交集，并将该交集设置为最终的区域赋值给调用者
 * public boolean setPath(Path path, Region clip)
 *
 * @author wangzhichao
 * @since 20-3-16
 */
class B_RegionIndirectConstructView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()
    private val rectF = RectF()
    private var styleFlag = true
    // 创建 clip 区域
    private val clip = Region()
    // 创建空白区域
    private val region = Region()
    init {
        paint.color = Color.RED
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.style =
            if (styleFlag) Paint.Style.FILL else Paint.Style.STROKE
        // 创建椭圆路径
        path.reset()
        rectF.set(50f, 50f, 300f, 600f)
        path.addOval(rectF, Path.Direction.CCW)
        // 创建 clip 区域
        clip.set(50, 50, 300, 300)
        // 根据路径的区域与某区域的交集构造出新的区域
        region.setPath(path, clip) // 相交得到的是上半个椭圆
        // 绘制 Region
        drawRegion(canvas, region, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        styleFlag = !styleFlag
        postInvalidate()
        return super.onTouchEvent(event)
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
        private const val TAG = "RegionIndirectConstruct"
    }
}