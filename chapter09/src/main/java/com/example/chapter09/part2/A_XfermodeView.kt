package com.example.chapter09.part2

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.common.dp

/**
 * 演示 saveLayer() 函数
 * @author wangzhichao
 * @date 2019/10/03
 */
class A_XfermodeView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val w = 150.dp
    private val h = 150.dp
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val dstBmp: Bitmap
    private val srcBmp: Bitmap
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        dstBmp = makeDst(w, h)
        srcBmp = makeSrc(w, h)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.GREEN)

        // 使用离屏缓存
        // 新建图层，这句代码调用后会生成一个全新的画布（bitmap），它是全透明的
        // 如果不写这行，那么就不会新建一个画布，而是直接在原画布上绘制圆形的，这样目标图像就是绿色的背景加上圆形了
        val layerId = canvas.saveLayer(0f, 0f, w, h, paint)
        // 这里是核心绘制代码
        // 绘制目标图像，圆形的，在底部
        canvas.drawBitmap(dstBmp, 0f, 0f, paint)
        // 设置图像混合模式
        paint.xfermode = xfermode
        // 绘制源图像，方形的，在上面
        canvas.drawBitmap(srcBmp, 0f, 0f, paint)
        paint.xfermode = null
        // 还原图层
        canvas.restoreToCount(layerId)
    }

    private fun makeDst(w: Float, h: Float): Bitmap {
        val bitmap = Bitmap.createBitmap(w.toInt(), h.toInt(), Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.parseColor("#FFFFCC44")
        canvas.drawOval(RectF(0f, 0f, w * 2f / 3, h * 2f / 3), paint)
        return bitmap
    }

    private fun makeSrc(w: Float, h: Float): Bitmap {
        val bitmap = Bitmap.createBitmap(w.toInt(), h.toInt(), Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.parseColor("#FF66AAFF")
        canvas.drawRect(RectF(w / 3f, h / 3f, w, h), paint)
        return bitmap
    }

}