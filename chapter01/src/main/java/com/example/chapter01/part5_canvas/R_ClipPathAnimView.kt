package com.example.chapter01.part5_canvas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.chapter01.R
import com.example.common.dp

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
class R_ClipPathAnimView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.scenery)
    private var clipWidth = 0f
    private var path = Path()
    private var rectF = RectF()
    private var mtx = Matrix()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 5f
        color = Color.RED
    }
    private val CLIP_HEIGHT = 20.dp
    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 获取控件的宽高
        path.reset()
        canvas.save()
        var i = 0
        // 构建 Path
        while (i * CLIP_HEIGHT <= height) {
            if (i % 2 == 0) {
                // 准备从左向右变宽的区域
                rectF.set(0f, i * CLIP_HEIGHT, clipWidth, (i + 1) * CLIP_HEIGHT)
            } else {
                // 准备从右向左变宽的区域
                rectF.set((width - clipWidth), i * CLIP_HEIGHT, width.toFloat(),
                    (i + 1) * CLIP_HEIGHT)
            }
            path.addRect(rectF, Path.Direction.CCW)
            i++
        }
        // 裁剪出新的画布,显示出来图片的区域就是裁剪区域
        canvas.clipPath(path)
        // 在裁剪出的画布上绘制图片，这里做了铺满控件的处理
        mtx.setScale(width * 1f / bitmap.width,
            height * 1f / bitmap.height)
        canvas.drawBitmap(bitmap, mtx, paint)
        canvas.restore()
        // 执行动画
        if (clipWidth > width) {
//            canvas.drawPath(path, paint);
            return
        }
        clipWidth += 5
        invalidate()
    }
}