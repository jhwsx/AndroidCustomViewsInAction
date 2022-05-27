package com.example.chapter07.part3_maskfilter

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.chapter07.R
import kotlin.math.abs

/**
 * 封装一个可以给图片添加阴影的控件
 *
 * @author wangzhichao
 * @date 2019/09/20
 */
class C_BitmapShadowView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val bitmap: Bitmap
    private val shadowBitmap: Bitmap
    private val shadowDx: Int
    private val shadowDy: Int
    private val shadowColor: Int
    private val shadowRadius: Int
    private val blurMaskFilter: BlurMaskFilter?
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rect = Rect()

    /**
     * 默认的控件宽度
     */
    private val defaultWidth: Int

    /**
     * 默认的控件高度
     */
    private val defaultHeight: Int

    /**
     * 图片需要的宽度
     */
    private var reqImgWidth: Int = 0

    /**
     * 图片需要的高度
     */
    private var reqImgHeight: Int = 0

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        val ta = context.obtainStyledAttributes(attrs, R.styleable.C_BitmapShadowView)
        val srcId = ta.getResourceId(R.styleable.C_BitmapShadowView_bsv_src, -1)
        shadowDx = ta.getInt(R.styleable.C_BitmapShadowView_bsv_shadowDx, 0)
        shadowDy = ta.getInt(R.styleable.C_BitmapShadowView_bsv_shadowDy, 0)
        shadowColor = ta.getColor(R.styleable.C_BitmapShadowView_bsv_shadowColor, Color.BLACK)
        shadowRadius = ta.getInt(R.styleable.C_BitmapShadowView_bsv_shadowRadius, 0)
        ta.recycle()
        require(srcId != -1) { "Unknown src" }
        require(shadowRadius >= 0) { "Invalid shadowRadius: $shadowRadius" }
        bitmap = BitmapFactory.decodeResource(resources, srcId)
        defaultWidth =
            if (shadowRadius == 0) bitmap.width else bitmap.width + abs(shadowDx) + 2 * shadowRadius
        defaultHeight =
            if (shadowRadius == 0) bitmap.height else bitmap.height + abs(shadowDy) + 2 * shadowRadius
        shadowBitmap = bitmap.extractAlpha()
        blurMaskFilter = if (shadowRadius > 0) {
            BlurMaskFilter(shadowRadius.toFloat(), BlurMaskFilter.Blur.NORMAL)
        } else {
            null
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val measuredWidth: Int
        val measuredHeight: Int
        if (widthMode == MeasureSpec.EXACTLY) {
            reqImgWidth = if (shadowRadius == 0) widthSize else widthSize - abs(shadowDx) - 2 * shadowRadius
            measuredWidth = widthSize
        } else {
            reqImgWidth = bitmap.width
            measuredWidth = defaultWidth
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            reqImgHeight = if (shadowRadius == 0) heightSize else heightSize - abs(shadowDx) - 2 * shadowRadius
            measuredHeight = heightSize
        } else {
            reqImgHeight = bitmap.height
            measuredHeight = defaultHeight
        }
        setMeasuredDimension(measuredWidth, measuredHeight)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = shadowColor
        // 不需要阴影，直接绘制原图片即可
        if (blurMaskFilter == null) {
            rect.set(0, 0, reqImgWidth, reqImgHeight)
            canvas.drawBitmap(bitmap, null, rect, paint)
            return
        }
        paint.maskFilter = blurMaskFilter
        val shadowLeft: Int
        val shadowRight: Int
        val left: Int
        if (shadowDx < 0) {
            shadowLeft = shadowRadius
            shadowRight = shadowRadius + reqImgWidth
            left = -shadowDx + 2 * shadowRadius
        } else {
            shadowLeft = shadowDx + shadowRadius
            shadowRight = shadowDx + shadowRadius + reqImgWidth
            left = 0
        }

        val shadowTop: Int
        val shadowBottom: Int
        val top: Int
        if (shadowDy < 0) {
            shadowTop = shadowRadius
            shadowBottom = shadowRadius + reqImgHeight
            top = -shadowDy + 2 * shadowRadius
        } else {
            shadowTop = shadowDy + shadowRadius
            shadowBottom = shadowDy + shadowRadius + reqImgHeight
            top = 0
        }
        rect.set(shadowLeft, shadowTop, shadowRight, shadowBottom)
        canvas.drawBitmap(shadowBitmap, null, rect, paint)
        paint.maskFilter = null
        // 绘制原图片
        rect.set(left, top, left + reqImgWidth, top + reqImgHeight)
        canvas.drawBitmap(bitmap, null, rect, paint)

    }
}