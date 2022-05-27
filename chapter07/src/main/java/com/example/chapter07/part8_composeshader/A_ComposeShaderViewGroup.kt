package com.example.chapter07.part8_composeshader

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.chapter07.R

/**
 *
 * @author wangzhichao
 * @since 2022/5/27
 */
class A_ComposeShaderViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    init {
        inflate(context, R.layout.layout_composeshader_viewgroup, this)
    }
}