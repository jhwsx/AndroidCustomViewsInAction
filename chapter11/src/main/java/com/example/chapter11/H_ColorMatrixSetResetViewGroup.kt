package com.example.chapter11

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.LinearLayout

/**
 * ColorMatrix 设置，重置函数
 * @author wangzhichao
 * @since 2022/6/3
 */
class H_ColorMatrixSetResetViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private val view: H_ColorMatrixSetResetView
    private val btnSet: Button
    private val btnReset: Button
    init {
        inflate(context, R.layout.viewgroup_colormatrix_setreset, this)
        view = findViewById(R.id.view)
        btnSet = findViewById(R.id.btn_set)
        btnReset = findViewById(R.id.btn_reset)
        btnSet.setOnClickListener {
            view.set()
        }
        btnReset.setOnClickListener {
            view.reset()
        }
    }
}