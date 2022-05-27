package com.example.chapter08.part2

import android.content.Context
import android.graphics.*
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.example.chapter08.part2.A_PaintSetXfermodePorterDuffXfermodeViewGroup.ModeModel
import com.example.common.dp

/**
 * 这里演示的效果与 google 文档一致
 * https://developer.android.com/reference/android/graphics/PorterDuff.Mode
 * @author wangzhichao
 * @date 2019/09/22
 */
class A_PaintSetXfermodePorterDuffXfermodeView(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {
    private val w = 150f.dp.toInt()
    private val h = 150f.dp.toInt()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
    private val dstBmp: Bitmap
    private val srcBmp: Bitmap
    private var porterDuffXfermode: PorterDuffXfermode? = null
    private var desc: String = ""
    private val dstColor = Color.parseColor("#D81B60")
    private val srcColor = Color.parseColor("#2196F3")

    init {
        textPaint.color = Color.GREEN
        textPaint.textSize = 40f
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        dstBmp = makeDst(w, h)
        srcBmp = makeSrc(w, h)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (porterDuffXfermode == null) {
            return
        }
        // 使用离屏缓存
        // 新建图层
        val layerId = canvas.saveLayer(10f.dp, 10f.dp, 10f.dp + w, 10f.dp + h, paint)
        // 这里是核心绘制代码
        // 绘制目标图像，圆形的，在底部
        canvas.drawBitmap(dstBmp, 10f.dp, 10f.dp, paint)
        // 设置图像混合模式，注意的是：这行代码之前画布上所有的内容都作为目标图像
        paint.xfermode = porterDuffXfermode
        // 绘制源图像，方形的，在上面
        canvas.drawBitmap(srcBmp, 10f.dp, 10f.dp, paint)
        paint.xfermode = null
        // 还原图层
        canvas.restoreToCount(layerId)

        canvas.save()
        canvas.translate(0f, 700f)
        with(StaticLayout.Builder.obtain(desc,
            0,
            desc.length,
            textPaint,
            (width * 2f / 3).toInt())) {
            build().draw(canvas)
        }
        canvas.restore()
    }

    fun setModeModel(modeModel: ModeModel) {
        porterDuffXfermode = PorterDuffXfermode(modeModel.mode)
        desc = modeModel.desc
        invalidate()
    }

    private fun makeDst(w: Int, h: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = dstColor
        canvas.drawOval(RectF(w / 3f, 0f, w.toFloat(), 2 * h / 3f), paint)
        return bitmap
    }

    private fun makeSrc(w: Int, h: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = srcColor
        canvas.drawRect(RectF(0f, h / 3f, 2 * w / 3f, h.toFloat()), paint)
        return bitmap
    }

}