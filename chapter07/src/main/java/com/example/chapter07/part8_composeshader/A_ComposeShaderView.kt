package com.example.chapter07.part8_composeshader

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.chapter07.R
import com.example.common.dp

/**
 * 混合着色器
 *
 * @author wangzhichao
 * @since 2022/5/27
 */
class A_ComposeShaderView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val avatar = BitmapFactory.decodeResource(resources, R.drawable.img)
    private val shape = BitmapFactory.decodeResource(resources, R.drawable.tencent_class)
    private val avatarBitmapShader = BitmapShader(avatar, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
    private val shapeBitmapShader = BitmapShader(shape, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
    // Shader shaderA, 这是 dst
    // Shader shaderB, 这是 src
    private val composeShader = ComposeShader(avatarBitmapShader, shapeBitmapShader, PorterDuff.Mode.DST_IN)
    private val rectF = RectF()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        rectF.set(0f, 0f, avatar.width.toFloat(), avatar.height.toFloat())
        paint.shader = composeShader
        canvas.drawRect(rectF, paint)
        paint.shader = null
    }
}