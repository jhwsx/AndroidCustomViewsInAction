package com.example.chapter07.part2_shadowlayer

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.chapter07.R
import com.example.common.ImageUtils
import com.example.common.dp

/**
 * 给图片或者文字添加阴影效果
 *
 * @author wangzhichao
 * @date 2019/09/20
 */
class A_PaintSetShadowLayerView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val avatar: Bitmap = ImageUtils.decodeSampleBitmapFromResource(context.resources, R.drawable.avatar, 100.dp.toInt())
    private var radius = 0
    private var dx = 0
    private var dy = 0
    init {
        // 只有文字支持硬件加速，其他都不支持硬件加速，所以这里禁用了硬件加速。
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        paint.color = Color.GREEN
        paint.textSize = 24.dp
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (showShadow) {
            paint.setShadowLayer(radius.toFloat(), dx.toFloat(), dy.toFloat(), Color.GRAY)
        }
        if (clearShadow1) {
            paint.clearShadowLayer()
            /*
            public void clearShadowLayer() {
            setShadowLayer(0, 0, 0, 0);
            }
            */
        }
        if (clearShadow2) {
            // 没有 radius，就没有了阴影了。
            paint.setShadowLayer(0f, dx.toFloat(), dy.toFloat(), Color.GRAY)
        }
        canvas.drawText("willwaywang6", 100f, 100f, paint)
        canvas.drawCircle(200f, 200f, 50f, paint)
        canvas.drawBitmap(avatar, 200f, 300f, paint)
    }

    fun setRadius(radius: Int) {
        this.radius = radius
        invalidate()
    }

    fun setDx(dx: Int) {
        this.dx = dx
        invalidate()
    }

    fun setDy(dy: Int) {
        this.dy = dy
        invalidate()
    }

    private var showShadow = true
    fun showShadow() {
        showShadow = true
        clearShadow1 = false
        clearShadow2 = false
        invalidate()
    }

    private var clearShadow1 = false
    fun clearShadow1() {
        clearShadow1 = true
        showShadow = false
        invalidate()
    }

    private var clearShadow2 = false
    fun clearShadow2() {
        clearShadow2 = true
        showShadow = false
        invalidate()
    }
}