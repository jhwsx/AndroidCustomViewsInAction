package com.example.chapter01.part5_canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.example.chapter01.part5_canvas.CanvasRestoreToCountView
import com.example.chapter01.part5_canvas.CanvasScaleView
import com.example.chapter01.part5_canvas.ClipAnimView
import com.example.chapter01.part5_canvas.ClipPathAnimView

/**
 * 裁剪画布
 * 在使用 clip 系列函数时，需要禁用硬件加速。
 * 特别注意，裁剪出来的就是最新的画布形状。
 *
 * @author wangzhichao
 * @since 20-3-17
 */
class CanvasClipOperationView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.RED)
        // 裁剪出来的就是最新画布的区域。
//        canvas.clipRect(100, 100, 400, 300);
        val path = Path()
        path.addCircle(200f, 200f, 100f, Path.Direction.CCW)
        canvas.clipPath(path)
        canvas.drawColor(Color.GREEN)
        // 注意: 裁剪后,没有产生新的坐标系,还是原来的坐标系
        // TODO 不知道为什么?
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.strokeWidth = 4f
        paint.style = Paint.Style.FILL
        paint.color = Color.BLACK
        paint.textSize = 20f
        paint.textAlign = Paint.Align.CENTER
        canvas.drawText("哈哈", 200f, 200f, paint)
    }

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }
}