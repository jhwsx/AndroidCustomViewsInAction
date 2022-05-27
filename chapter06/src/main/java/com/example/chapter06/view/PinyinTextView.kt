package com.example.chapter06.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import kotlin.jvm.JvmOverloads
import com.example.chapter06.R
import kotlin.math.min

class PinyinTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var pinyinArr: Array<String?>? = null
    private var hanziArr: Array<String?>? = null
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var row = 1
    var density = 0f
    private var fontMetrics: Paint.FontMetrics? = null
    private var lineHeight = 0f
    private var pinyinHeight = 0f
    private var hanziHeight = 0f
    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.PinyinTextView)
        val textColor = ta.getColor(R.styleable.PinyinTextView_ptv_text_color, 0x333333)
        val textSize = ta.getDimensionPixelSize(R.styleable.PinyinTextView_ptv_text_size, 20)
        ta.recycle()
        textPaint.color = textColor
        textPaint.textSize = textSize.toFloat()
        density = resources.displayMetrics.density
        fontMetrics = textPaint.fontMetrics
        pinyinHeight = fontMetrics!!.bottom - fontMetrics!!.top
        hanziHeight = fontMetrics!!.bottom - fontMetrics!!.top
        lineHeight = pinyinHeight + hanziHeight
        paint.color = Color.RED
        paint.strokeWidth = 1f
        paint.style = Paint.Style.STROKE
    }

    fun setPinyinAndHanzi(pinyin: Array<String?>?, hanzi: Array<String?>?) {
        pinyinArr = pinyin
        hanziArr = hanzi
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = measureWidth(widthMeasureSpec)
        val height = measureHeight(heightMeasureSpec, width)
        setMeasuredDimension(width, height)
    }

    var baselineX = 0f
    override fun onDraw(canvas: Canvas) {
        if (pinyinArr == null || pinyinArr!!.size == 0) {
            return
        }
        baselineX = 0f
        var pinyinRowWidth: Float
        row = 1
        //        for (int i = 0; i < 58; i++) {
//            paint.setColor(Color.MAGENTA);
//            int row1 = i + 1;
//            canvas.drawLine(0, getRowLine(row1), getWidth(), getRowLine(row1), paint);
//            paint.setTextSize(40);
//            canvas.drawText(String.valueOf(row1), 0, getRowLine(row1), paint);
//        }
        for (index in pinyinArr!!.indices) {
            if (!TextUtils.equals(pinyinArr!![index], "null") && !TextUtils.equals(
                    pinyinArr!![index], " ")
            ) {
                val pinyin = getPinyinByIndex(index)
                pinyinRowWidth = baselineX + textPaint.measureText(pinyin)
                if (pinyinRowWidth > width) {
                    row++
                    baselineX = 0f
                }
                drawPinyin(canvas, pinyin)
                drawTone(canvas, baselineX, pinyinArr!![index])
                drawHanzi(canvas, baselineX, index)
                updateBaselineX(textPaint.measureText(getPinyinByIndex(index)))
            } else if (TextUtils.equals(pinyinArr!![index], "null")) {
                if (TextUtils.equals(hanziArr!![index], BREAK_TAG)) {
                    row++
                    baselineX = 0f
                } else if (TextUtils.equals(hanziArr!![index], PARAGRAPH_TAG)) {
                    row += 2
                    baselineX = 0f
                } else {
                    drawPunctuationOrSpace(canvas, index)
                    updateBaselineX(textPaint.measureText(hanziArr!![index]))
                }
            }
        }
    }

    private fun drawPinyin(canvas: Canvas, pinyin: String) {
        val pinyinBaselineY = getPinyinBaselineY(row)
        canvas.drawText(pinyin, baselineX, pinyinBaselineY, textPaint)
        val width = textPaint.measureText(pinyin)
        val fontMetricsInt = textPaint.fontMetricsInt
        paint.color = Color.RED
        val rect = RectF()
        rect.left = baselineX
        rect.right = baselineX + width
        rect.top = fontMetricsInt.top + pinyinBaselineY
        rect.bottom = fontMetricsInt.bottom + pinyinBaselineY
        canvas.drawRect(rect, paint)
    }

    private fun drawPunctuationOrSpace(canvas: Canvas, index: Int) {
        // 绘制标点符号,或者空格
        val text = hanziArr!![index]
        val hanziWidth = baselineX + textPaint.measureText(text)
        if (hanziWidth > width) {
            row++
            baselineX = 0f
        }
        val baselineY = getHanziBaselineY(row)
        canvas.drawText(text!!, baselineX, baselineY, textPaint)
        val width = textPaint.measureText(text)
        val fontMetricsInt = textPaint.fontMetricsInt
        paint.color = Color.BLUE
        val rect = RectF()
        rect.left = baselineX
        rect.right = baselineX + width
        rect.top = fontMetricsInt.top + baselineY
        rect.bottom = fontMetricsInt.bottom + baselineY
        canvas.drawRect(rect, paint)
    }

    private fun getRowLine(row: Int): Float {
        return row * lineHeight + (row - 1) * textPaint.fontSpacing
    }

    private fun getPinyinBaselineY(row: Int): Float {
        val rowLine = getRowLine(row)
        return rowLine - hanziHeight - fontMetrics!!.bottom
    }

    private fun getHanziBaselineY(row: Int): Float {
        val rowLine = getRowLine(row)
        return rowLine - fontMetrics!!.bottom
    }

    private fun drawHanzi(canvas: Canvas, baselineX: Float, index: Int) {
        // 由于拼音长度固定，采用居中显示策略，计算拼音实际长度不需要去掉拼音后面空格
        val hanzi = hanziArr!![index]
        val hanziBaselineX = baselineX + (textPaint.measureText(getPinyinByIndex(index))
                - textPaint.measureText(hanzi)) / 2 - moveHalfIfNeed(getPinyinByIndex(index),
            textPaint)
        val hanziBaselineY = getHanziBaselineY(row)
        canvas.drawText(hanzi!!, hanziBaselineX, hanziBaselineY, textPaint)
        val width = textPaint.measureText(hanzi)
        val fontMetricsInt = textPaint.fontMetricsInt
        paint.color = Color.BLUE
        val rect = RectF()
        rect.left = hanziBaselineX
        rect.right = hanziBaselineX + width
        rect.top = fontMetricsInt.top + hanziBaselineY
        rect.bottom = fontMetricsInt.bottom + hanziBaselineY
        canvas.drawRect(rect, paint)
    }

    private fun updateBaselineX(width: Float) {
        baselineX += width
    }

    private fun drawTone(canvas: Canvas, baselineX: Float, pinyinWithTone: String?) {
        var tone = " "
        when (pinyinWithTone!![pinyinWithTone.length - 1]) {
            '1' -> tone = "ˉ"
            '2' -> tone = "ˊ"
            '3' -> tone = "ˇ"
            '4' -> tone = "ˋ"
            else -> {}
        }
        // 去掉数字和空格符
        var toneIndex = pinyinWithTone.length - 3
        var stateIndex = -1
        while (toneIndex >= 0) {
            if (pinyinWithTone[toneIndex] == 'a' || pinyinWithTone[toneIndex] == 'e' || pinyinWithTone[toneIndex] == 'i' || pinyinWithTone[toneIndex] == 'o' || pinyinWithTone[toneIndex] == 'u' || pinyinWithTone[toneIndex] == 'v') {
                if (stateIndex == -1 || pinyinWithTone[toneIndex] < pinyinWithTone[stateIndex]) {
                    stateIndex = toneIndex
                }
            }
            toneIndex--
        }
        // iu同时存在规则: i,u 若是连在一起，谁在后面就标谁
        if (pinyinWithTone.contains("u")
            && pinyinWithTone.contains("i")
            && !pinyinWithTone.contains("a")
            && !pinyinWithTone.contains("o")
            && !pinyinWithTone.contains("e")
        ) {
            stateIndex =
                if (pinyinWithTone.indexOf("u") > pinyinWithTone.indexOf("i")) pinyinWithTone.indexOf(
                    "u") else pinyinWithTone.indexOf("i")
        }
        val baselineY = getPinyinBaselineY(row)
        if (stateIndex != -1) {
            // 没有声母存在时，stateIndex一直为-1 （'嗯' 转成拼音后变成 ng,导致没有声母存在，stateIndex一直为-1，数组越界crash）
            val toneBaselineX =
                (baselineX + textPaint.measureText(pinyinWithTone.substring(0, stateIndex))
                        + (textPaint.measureText(pinyinWithTone[stateIndex].toString() + "") - textPaint.measureText(
                    tone + "")) / 2)
            canvas.drawText(tone, toneBaselineX, baselineY, textPaint)
        }
    }

    private fun measureHeight(heightMeasureSpec: Int, width: Int): Int {
        var result = 0
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        if (heightMode == MeasureSpec.EXACTLY) {
            result = heightSize
        } else {
            if (pinyinArr != null && pinyinArr!!.isNotEmpty()) {
                var calculatedRowWidth = 0f
                var rowTotal = 1
                for (index in pinyinArr!!.indices) {
                    if (TextUtils.equals(pinyinArr!![index], "null")) {
                        if (TextUtils.equals(hanziArr!![index], BREAK_TAG) || TextUtils.equals(
                                hanziArr!![index], PARAGRAPH_TAG)
                        ) {
                            // 没有对应的拼音，对应汉字部分是换行符，段落标签
                            rowTotal += if (TextUtils.equals(hanziArr!![index], BREAK_TAG)) 1 else 2
                            calculatedRowWidth = 0f
                            continue
                        } else {
                            // 没有对应的拼音，则需要加上汉字部分的宽度(指的是标点符号,空格等)
                            calculatedRowWidth += textPaint.measureText(hanziArr!![index])
                        }
                    } else {
                        // 有对象的拼音，则加上拼音的宽度（注意不带最后的声调标志1,2,3,4）
                        calculatedRowWidth += textPaint.measureText(getPinyinByIndex(index))
                    }
                    if (calculatedRowWidth > width) {
                        // 当前已经宽度超过控件的宽度
                        rowTotal++
                        calculatedRowWidth = resetCalculatedRowWidth(index)
                    }
                }
                result = getRowLine(rowTotal).toInt()
            }
            if (heightMode == MeasureSpec.AT_MOST) {
                result = min(result, heightSize)
            }
        }
        return result
    }

    /**
     * 重置宽度,初始宽度要么是 null 的宽度，要么是对应拼音的宽度。
     */
    private fun resetCalculatedRowWidth(index: Int): Float {
        return if (TextUtils.equals(pinyinArr!![index], "null")) textPaint.measureText(
            pinyinArr!![index]) else textPaint.measureText(getPinyinByIndex(index))
    }

    /**
     * 按索引获取拼音部分，不带声调
     *
     * @param index
     * @return
     */
    private fun getPinyinByIndex(index: Int): String {
        return pinyinArr!![index]!!.substring(0, pinyinArr!![index]!!.length - 1)
    }

    private fun measureWidth(widthMeasureSpec: Int): Int {
        var result: Int
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        if (widthMode == MeasureSpec.EXACTLY) {
            result = widthSize
        } else {
            result = 200
            if (widthMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, widthSize)
            }
        }
        return result
    }

    private fun moveHalfIfNeed(pinyinUnit: String, paint: Paint): Float {
        return if (pinyinUnit.trim { it <= ' ' }.length % 2 == 0) {
            paint.measureText(" ") / 2
        } else {
            0f
        }
    }

    companion object {
        private const val TAG = "PinyinTextView"

        // 对应 <br>
        const val BREAK_TAG = "#"

        // 对应 <p>
        const val PARAGRAPH_TAG = "&"
    }
}