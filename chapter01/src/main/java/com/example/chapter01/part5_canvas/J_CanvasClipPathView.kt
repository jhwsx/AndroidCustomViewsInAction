package com.example.chapter01.part5_canvas

import android.content.Context
import android.graphics.*
import android.os.Build
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.example.chapter01.R
import com.example.common.ImageUtils
import com.example.common.dp

/**
 * 裁剪画布
 * 在使用 clip 系列函数时，需要禁用硬件加速。
 * 特别注意，裁剪出来的就是最新的画布形状。
 *
 * @author wangzhichao
 * @since 20-3-17
 */
class J_CanvasClipPathView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val path = Path()
    private val RADIUS = 100.dp
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 4f
        style = Paint.Style.FILL
        color = Color.BLACK
        textSize = 20.dp
        textAlign = Paint.Align.CENTER
    }
    private val bounds = Rect()
    private val text = "willwaywang6"
    private val avatar = ImageUtils.decodeSampleBitmapFromResource(resources, R.drawable.avatar, 100.dp.toInt())
    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.RED)
        canvas.save()
        path.reset()
        val centerX = 150.dp
        val centerY = 150.dp
        path.addCircle(centerX, centerY, RADIUS, Path.Direction.CCW)
        canvas.clipPath(path)
        // clipOutPath 与 clipPath 是反着的。
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            canvas.clipOutPath(path)
//        }
        canvas.drawColor(Color.GREEN)
        // 注意: 裁剪后,没有产生新的坐标系,还是原来的坐标系
        textPaint.getTextBounds(text, 0, text.length, bounds)
        val baseline = centerY - (bounds.top + bounds.bottom) / 2
        // 超出裁剪区域的绘制不能显示出来了。
        canvas.drawBitmap(avatar, 0f, 0f, null)
        canvas.drawText(text, centerX, baseline, textPaint)
        canvas.restore()

    }
}