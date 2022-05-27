package com.example.chapter01.part5_canvas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.chapter01.R
import com.example.chapter01.part5_canvas.CanvasRestoreToCountView
import com.example.chapter01.part5_canvas.CanvasScaleView
import com.example.chapter01.part5_canvas.ClipAnimView
import com.example.chapter01.part5_canvas.ClipPathAnimView
import kotlin.math.min

/**
 * 圆形头像例子
 * 思路：
 * 1，在图片所在的位置先裁剪出一个圆形的画布；
 * 2，绘制图片，因为超出圆形画布区域的图片部分不会显示，因此就可以得到圆形头像。
 * 3, 因为要用到 clipPath，所以需要设置关闭硬件加速。
 *
 * @author wangzhichao
 * @since 20-3-18
 */
class CircleAvatarView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var bitmap = BitmapFactory.decodeResource(resources, R.drawable.avator)
    private val paint = Paint()
    private var path: Path? = null

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        path = Path()
        val halfWidth = bitmap.width * 0.5f
        val halfHeight = bitmap.height * 0.5f
        val radius = min(halfWidth, halfHeight)
        path!!.addCircle(halfWidth, halfHeight, radius, Path.Direction.CCW)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.save()
        canvas.clipPath(path!!)
        canvas.drawBitmap(bitmap!!, 0f, 0f, paint)
        canvas.restore()
    }
}