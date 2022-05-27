package com.example.chapter07.part4_bitmapshader

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.chapter07.R
import com.example.common.ImageUtils
import com.example.common.dp

/**
 * 圆形头像
 * @author wangzhichao
 * @date 2019/09/21
 */
class C_CircleAvatarView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap: Bitmap = ImageUtils.decodeSampleBitmapFromResource(resources, R.drawable.avatar, 100.dp.toInt())
    // 不存在多余区域，填充模式无所谓。
    private val bitmapShader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
    private val mtx = Matrix()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 把原图片缩放到和控件大小一致。
        val scale = (width / bitmap.width).toFloat()
        mtx.setScale(scale, scale)
        bitmapShader.setLocalMatrix(mtx)
        paint.shader = bitmapShader
        val half = (width / 2).toFloat()
        canvas.drawCircle(half, half, (width / 2).toFloat(), paint)
    }
}