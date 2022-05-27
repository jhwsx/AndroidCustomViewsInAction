package com.example.chapter06.view

import android.content.Context
import android.graphics.*
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import kotlin.jvm.JvmOverloads
import com.example.chapter06.R
import kotlin.math.max
import kotlin.math.min

/**
 * 中国结背景文字
 *
 * 没有处理超出一行的情况
 *
 * @author wangzhichao
 * @since 2020/01/09
 */
class DiamondBackgroundTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    /**
     * 要展示的字符数组
     */
    private val characterArray: CharArray
    private val bounds = Rect()
    private val backgroundRect = Rect()
    private val path = Path()
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var baseLineX = 0
    private var baseLineY = 0

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.DiamondBackgroundTextView)
        val text = ta.getString(R.styleable.DiamondBackgroundTextView_dbv_text)
        val backgroundColor =
            ta.getColor(R.styleable.DiamondBackgroundTextView_dbv_background_color, -0x7f1a3738)
        val textColor = ta.getColor(R.styleable.DiamondBackgroundTextView_dbv_text_color, -0x80efe3)
        val textSize =
            ta.getDimensionPixelSize(R.styleable.DiamondBackgroundTextView_dbv_text_size, 100)
        val padding =
            ta.getDimensionPixelSize(R.styleable.DiamondBackgroundTextView_dbv_padding, 10)
        ta.recycle()
        textPaint.color = textColor
        textPaint.textSize = textSize.toFloat()
        textPaint.textAlign = Paint.Align.CENTER
        backgroundPaint.color = backgroundColor
        backgroundPaint.style = Paint.Style.FILL
        require(!TextUtils.isEmpty(text)) { "no text" }
        characterArray = text!!.toCharArray()
        initBackgroundRect(padding)
        initBaseline()
        initDiamondPath()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measureWidth(widthMeasureSpec)
        val height = measureHeight(heightMeasureSpec)
        setMeasuredDimension(width, height)
    }

    /**
     * 决定 view 的高度
     *
     * @param heightMeasureSpec
     * @return
     */
    private fun measureHeight(heightMeasureSpec: Int): Int {
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        var result: Int
        if (heightMode == MeasureSpec.EXACTLY) {
            result = heightSize
        } else {
            result = backgroundRect.height()
            if (heightMode == MeasureSpec.AT_MOST) {
                // 要求的高度不能超过父类给的高度
                result = min(result, heightSize)
            }
        }
        return result
    }

    /**
     * 决定 view 的宽度
     *
     * @param widthMeasureSpec
     * @return
     */
    private fun measureWidth(widthMeasureSpec: Int): Int {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        var result: Int
        if (widthMode == MeasureSpec.EXACTLY) {
            result = widthSize
        } else {
            val v = (characterArray.size - 1) * (1 - OFFSET_PERCENT) + 1
            result = (v * backgroundRect.width()).toInt()
            if (widthMode == MeasureSpec.AT_MOST) {
                result = min(result, widthSize)
            }
        }
        return result
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (i in characterArray.indices) {
            canvas.save()
            canvas.translate(backgroundRect.width() * (1 - OFFSET_PERCENT) * i, 0f)
            canvas.drawPath(path, backgroundPaint)
            canvas.drawText(characterArray[i].toString(), baseLineX.toFloat(), baseLineY.toFloat(), textPaint)
            canvas.restore()
        }
    }

    /**
     * 初始化菱形路径
     * 菱形是内嵌在正方形里面的。
     */
    private fun initDiamondPath() {
        path.reset()
        path.moveTo(0f, backgroundRect.centerY().toFloat())
        path.lineTo(backgroundRect.centerX().toFloat(), 0f)
        path.lineTo(backgroundRect.width().toFloat(), backgroundRect.centerY().toFloat())
        path.lineTo(backgroundRect.centerX().toFloat(), backgroundRect.height().toFloat())
        path.close()
    }

    /**
     * 初始化背景正方形
     */
    private fun initBackgroundRect(padding: Int) {
        backgroundRect.left = 0
        backgroundRect.top = 0
        backgroundRect.right = (padding * 2 + maxCharacterSide) * 2
        backgroundRect.bottom = (padding * 2 + maxCharacterSide) * 2
    }

    /**
     * 初始化绘制文字的原点
     */
    private fun initBaseline() {
        val fm = textPaint.fontMetricsInt
        baseLineX = backgroundRect.centerX()
        baseLineY = backgroundRect.centerY() - (fm.top + fm.bottom) / 2
    }

    /**
     * 获取背景正方形的边长
     */
    private val maxCharacterSide: Int
        get() {
            var result = 0
            for (character in characterArray) {
                // 包裹字符的最小矩形
                textPaint.getTextBounds(character.toString(), 0, 1, bounds)
                val max = max(bounds.width(), bounds.height())
                if (max > result) {
                    result = max
                }
            }
            return result
        }

    companion object {
        private const val OFFSET_PERCENT = 0.2f
    }

}