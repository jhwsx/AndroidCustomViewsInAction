package com.example.chapter07.part4_bitmapshader

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.chapter07.R
import kotlin.math.max
import kotlin.math.min

/**
 * 圆形头像以及带圆角矩形头像控件
 *
 * @author wangzhichao
 * @date 2019/09/21
 */
class D_AvatarView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val type: Type
    private val rectRadius: Float
    private val bitmap: Bitmap
    private val mtx = Matrix()
    private val bitmapShader: BitmapShader
    private val rectF = RectF()
    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.D_AvatarView)
        val resId = ta.getResourceId(R.styleable.D_AvatarView_av_src, 0)
        require(resId != 0) { "Invalid av_src: $resId"}
        val typeInt = ta.getInt(R.styleable.D_AvatarView_av_type, -1)
        type = if (typeInt == 0) {
            Type.CIRCLE
        } else {
            Type.RECTANGLE
        }
        rectRadius = ta.getDimension(R.styleable.D_AvatarView_av_rectangle_radius, 0f)
        ta.recycle()
        bitmap = BitmapFactory.decodeResource(resources, resId)
        bitmapShader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val measuredWidth = if (widthMode == MeasureSpec.EXACTLY) widthSize else bitmap.width
        val measuredHeight = if (heightMode == MeasureSpec.EXACTLY) heightSize else bitmap.height
        if (type == Type.CIRCLE) {
            // 裁剪圆形头像，以最短边为准
            val side = min(measuredWidth, measuredHeight)
            setMeasuredDimension(side, side)
        } else {
            setMeasuredDimension(measuredWidth, measuredHeight)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 把图片缩放到与控件一样大小
        val scaleX = width * 1f / bitmap.width
        val scaleY = height * 1f / bitmap.height
        mtx.setScale(scaleX, scaleY)
        bitmapShader.setLocalMatrix(mtx)
        paint.shader = bitmapShader
        if (type == Type.CIRCLE) {
            canvas.drawCircle(width / 2f, width / 2f, width / 2f, paint)
        } else {
            rectF.set(0f, 0f, width.toFloat(), height.toFloat())
            canvas.drawRoundRect(rectF, rectRadius,rectRadius,paint)
        }
    }
    companion object {
        enum class Type {
            CIRCLE, RECTANGLE
        }
    }
}