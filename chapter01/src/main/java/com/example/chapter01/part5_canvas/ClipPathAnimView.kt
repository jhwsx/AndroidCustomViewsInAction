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

/**
 * 剪切动画（启舰实现的）
 * Canvas.clipRegion 已经被移除了，看不到了。
 * 思路：
 * 1，使用路径，添加多个矩形；
 * 2，使用 Canvas.clipPath() 方法裁剪出路径包含的区域；
 * 3，在裁剪出的画布上，绘制图片，做铺满处理；
 * 4，执行动画。
 * @author wangzhichao
 * @since 20-3-18
 */
class ClipPathAnimView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var bitmap: Bitmap? = null
    private var clipWidth = 0
    private var path: Path? = null
    private var rectF: RectF? = null
    private var paint: Paint? = null
    private var matrix: Matrix? = null
    private fun init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.scenery)
        path = Path()
        rectF = RectF()
        paint = Paint()
        paint!!.style = Paint.Style.STROKE
        paint!!.strokeWidth = 5f
        paint!!.color = Color.RED
        matrix = Matrix()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 获取控件的宽高
        val width = width
        val height = height
        path!!.reset()
        canvas.save()
        var i = 0
        // 构建 Path
        while (i * CLIP_HEIGHT <= height) { //计算 clip 的区域
            if (i % 2 == 0) {
                rectF!![0f, (i * CLIP_HEIGHT).toFloat(), clipWidth.toFloat()] =
                    ((i + 1) * CLIP_HEIGHT).toFloat()
            } else {
                rectF!![(width - clipWidth).toFloat(), (i * CLIP_HEIGHT).toFloat(), width.toFloat()] =
                    ((i + 1) * CLIP_HEIGHT).toFloat()
            }
            path!!.addRect(rectF!!, Path.Direction.CCW)
            i++
        }
        // 裁剪出新的画布,显示出来图片的区域就是裁剪区域
        canvas.clipPath(path!!)
        // 在裁剪出的画布上绘制图片，这里做了铺满控件的处理
        matrix!!.setScale(width * 1f / bitmap!!.width,
            getHeight() * 1f / bitmap!!.height)
        canvas.drawBitmap(bitmap!!, matrix!!, paint)
        canvas.restore()
        // 执行动画
        if (clipWidth > width) {
//            canvas.drawPath(path, paint);
            return
        }
        clipWidth += 5
        invalidate()
    }

    companion object {
        private const val CLIP_HEIGHT = 30
    }

    init {
        init()
    }
}