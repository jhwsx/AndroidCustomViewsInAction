package com.example.chapter01.part5_canvas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import com.example.chapter01.R
import com.example.common.ImageUtils
import com.example.common.dp

/**
 * Canvas 的缩放操作
 * // 没有指定缩放中心，就是原点
 * public void scale(float sx, float sy)
 * public final void scale(float sx, float sy, float px, float py)
 *
 *
 * 参考文章：https://blog.csdn.net/tianjian4592/article/details/45234419
 * 关于 scale 指定缩放中心的文章, 查看: https://blog.csdn.net/u011451706/article/details/52473382
 *
 * @author wangzhichao
 * @since 20-3-17
 */
class D_CanvasScaleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val redPaint = getPaint(Color.RED)
    private val greenPaint = getPaint(Color.GREEN)
    private val rect = RectF()
    private val bitmap = ImageUtils.decodeSampleBitmapFromResource(resources, R.drawable.avatar, 100.dp.toInt())
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rect.set(0F, 0F, width.toFloat(), height.toFloat())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 绘制一个空心矩形
        canvas.drawRect(rect, redPaint)
        canvas.drawBitmap(bitmap, 0f, 0f, redPaint)
        canvas.save()
        // 缩放 0.5f
//        canvas.scale(0.5f, 0.5f);
        // 以绘图中心为缩放中心
//        canvas.scale(0.5f, 0.5f, getWidth() / 2, getHeight() / 2);
        // 上面的一行等价于下面的三行，好好体会一下 scale 的 px ， py：The x-coord for the pivot point (unchanged by the scale)
        // 正着看 canvas 变换过程：
        // 1, 先向右下平移控件大小的一半宽度和一半高度
        // 2, 再以平移后的坐标原点为缩放中心，把画布宽高缩小为原来的0.5倍
        // 3, 向左上平移当前画布大小的一半宽度和一半高度，实际x方向平移距离=sx * dx，y方向平移距离=sy * dy。
        // 4, 最后绘制矩形和图片
        // 倒着看 canvas 变换过程（这是便于理解的方式）：
        // 1，先绘制矩形和图片
        // 2，再把画布向左上平移控件大小的一半宽度和一半高度
        // 3，在绘图坐标原点缩放（还是控件的坐标原点）
        // 4，再把画布向右平移控件大小的一半宽度和一半高度
        canvas.translate(width / 2f, height / 2f)
        canvas.scale(0.5f, 0.5f)
        canvas.translate(-width / 2f, -height / 2f)
        // 再绘制一个实线矩形
        canvas.drawRect(rect, greenPaint)
        canvas.drawBitmap(bitmap, 0f, 0f, redPaint)
        canvas.restore()
    }

    private fun getPaint(@ColorInt color: Int): Paint {
        val paint = Paint()
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        paint.color = color
        return paint
    }

    companion object {
        private const val TAG = "CanvasScaleView"
    }
}