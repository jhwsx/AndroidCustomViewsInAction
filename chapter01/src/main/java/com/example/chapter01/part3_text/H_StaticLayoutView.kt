package com.example.chapter01.part3_text

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.StaticLayout
import android.text.TextPaint
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View

/**
 * StaticLayout 多行文字的绘制
 * @author wangzhichao
 * @since 2022/5/22
 */
class H_StaticLayoutView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
    private val text1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer fringilla finibus neque, feugiat varius nisi consectetur ut. Quisque metus sapien, tincidunt sed eros vel, tincidunt lacinia nisl. In erat elit, dictum vel felis non, luctus scelerisque risus. Suspendisse potenti. Aliquam ultricies lorem id risus tristique lobortis. Etiam ut tempus augue. Mauris cursus libero at leo pharetra cursus. Etiam tortor libero, tempor ac imperdiet ut, hendrerit at augue."
    private val text2 = "静夜思\n床前明月光，疑是地上霜。\n举头望明月，低头思故乡。"
    init {
        textPaint.textSize = 48f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.save()
        canvas.translate(50f, 100f)
        // 演示多行
        with(StaticLayout.Builder.obtain(text1,
            0,
            text1.length,
            textPaint,
            (width * 0.6).toInt())) {
            setMaxLines(3)
            setEllipsize(TextUtils.TruncateAt.END)
            build().draw(canvas)
        }

        canvas.translate(0f, 400f)
        // 演示 \n 可以识别
        with(StaticLayout.Builder.obtain(text2,
            0,
            text2.length,
            textPaint,
            (width * 0.6).toInt())) {
            setEllipsize(TextUtils.TruncateAt.END)
            build().draw(canvas)
        }
        canvas.restore()



    }
}