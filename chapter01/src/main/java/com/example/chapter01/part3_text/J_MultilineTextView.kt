package com.example.chapter01.part3_text

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.text.LineBreaker
import android.os.Build
import android.text.Layout
import android.text.Layout.BREAK_STRATEGY_HIGH_QUALITY
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import android.text.StaticLayout
import android.text.TextPaint
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import com.example.common.dp

/**
 * 多行文本
 * @author wangzhichao
 * @since 2022/5/25
 */
class J_MultilineTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private var _expand: Boolean = false
        set(value) {
            field = value
            invalidate()
        }
    var expand: Boolean
        get() = _expand
        set(value) {
            _expand = value
        }
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 24.dp
    }
    private val text1 =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer fringilla finibus neque, feugiat varius nisi consectetur ut. Quisque metus sapien, tincidunt sed eros vel, tincidunt lacinia nisl. In erat elit, dictum vel felis non, luctus scelerisque risus. Suspendisse potenti. Aliquam ultricies lorem id risus tristique lobortis. Etiam ut tempus augue. Mauris cursus libero at leo pharetra cursus. Etiam tortor libero, tempor ac imperdiet ut, hendrerit at augue."

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        with(StaticLayout.Builder.obtain(text1,
            0,
            text1.length,
            textPaint,
            width)) {
            setEllipsize(TextUtils.TruncateAt.END)
//            setIndents(intArrayOf(24.dp.toInt()), null)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD)
//                setBreakStrategy(LineBreaker.BREAK_STRATEGY_HIGH_QUALITY)
//                setHyphenationFrequency(Layout.HYPHENATION_FREQUENCY_FULL)
            }
            setMaxLines(if (_expand) Int.MAX_VALUE else 3)
            build().draw(canvas)
        }
    }
}