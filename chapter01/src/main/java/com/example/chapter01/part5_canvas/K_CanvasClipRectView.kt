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
class K_CanvasClipRectView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val avatar = ImageUtils.decodeSampleBitmapFromResource(resources, R.drawable.avatar, 100.dp.toInt())
    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.RED)
        canvas.save()
        canvas.clipRect(0.dp, 0.dp, 50.dp, 50.dp)
        // clipOutRect 与 clipRect 是反着的。
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            canvas.clipOutRect(0.dp, 0.dp, 50.dp, 50.dp)
//        }
        canvas.drawBitmap(avatar, 0f, 0f, null)
        canvas.restore()
    }
}