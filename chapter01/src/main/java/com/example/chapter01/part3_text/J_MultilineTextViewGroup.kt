package com.example.chapter01.part3_text

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.LinearLayout
import com.example.chapter01.R

/**
 * 多行文本
 * @author wangzhichao
 * @since 2022/5/25
 */
class J_MultilineTextViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    private val mtv: J_MultilineTextView
    private val btn: Button
    private var expand: Boolean = false
    init {
        inflate(context, R.layout.view_text_multilinetext, this)
        mtv = findViewById(R.id.mtv)
        btn = findViewById(R.id.button)
        btn.setOnClickListener {
            if (expand) {
                btn.text = "收起"
                mtv.expand = false
            } else {
                btn.text = "全文"
                mtv.expand = true
            }
            expand = !expand
        }
    }
}