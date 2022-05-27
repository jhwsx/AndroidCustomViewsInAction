package com.example.chapter08.part4

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.example.chapter08.R

/**
 * @author wangzhichao
 * @date 2019/09/29
 */
class D_HeartMapView_DSTIN(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val heartMapDstBmp: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.heartmap)
    private val pathSrcBmp: Bitmap = Bitmap.createBitmap(heartMapDstBmp.width, heartMapDstBmp.height, Bitmap.Config.ARGB_8888)
    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
    private lateinit var va: ValueAnimator
    private val pathCanvas = Canvas(pathSrcBmp)
    init {
        paint.color = Color.GREEN
        paint.style = Paint.Style.FILL
        startAnim()
    }

    private fun startAnim() {
        va = ValueAnimator.ofFloat(0f, 1f)
        va.duration = 3000L
        va.interpolator = LinearInterpolator()
        va.repeatMode = ValueAnimator.RESTART
        va.repeatCount = ValueAnimator.INFINITE
        va.addUpdateListener { animation ->
            val curr = animation.animatedValue as Float
            updatePath(curr)
        }
        va.start()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val defaultWidth = heartMapDstBmp.width
        val defaultHeight = heartMapDstBmp.height
        val width = if (widthMode == MeasureSpec.EXACTLY) widthSize else defaultWidth
        val height = if (heightMode == MeasureSpec.EXACTLY) heightSize else defaultHeight
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        pathCanvas.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR) // 这里写什么颜色都不要紧
        pathCanvas.drawPath(path, paint)
        // 离屏缓冲
        // 新建图层
        val layerId =
            canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null)
        // 这里是核心代码
        // 绘制目标图像，在下层，心电图图像
        canvas.drawBitmap(heartMapDstBmp, 0f, 0f, paint)
        // 给画笔设置混合模式DST_IN
        paint.xfermode = xfermode
        // 绘制源图像，在上层，是透明+不透明图像
        canvas.drawBitmap(pathSrcBmp, 0f, 0f, paint)
        // 清空画笔的混合模式
        paint.xfermode = null
        // 还原图层
        canvas.restoreToCount(layerId)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        va.cancel()
    }

    private fun updatePath(progress: Float) {
        path.reset()
        val left = (1 - progress) * width
        path.moveTo(width.toFloat(), 0f)
        path.lineTo(width.toFloat(), height.toFloat())
        path.lineTo(left, height.toFloat())
        path.lineTo(left, 0f)
        path.close()
        invalidate()
    }
}