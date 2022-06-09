package com.example.chapter11

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.SeekBar

/**
 * 设置饱和度
 * @author wangzhichao
 * @since 2022/5/31
 */
class I_ColorMatrixSetSaturationViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private val view: I_ColorMatrixSetSaturationView
    private val sb: SeekBar
    init {
        inflate(context, R.layout.viewgroup_color_matrix_set_saturation, this)
        view = findViewById(R.id.view)
        sb = findViewById(R.id.seekbar)

        sb.setOnSeekBarChangeListener(object: SimpleOnSeekBarChangeListener() {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                super.onProgressChanged(seekBar, progress, fromUser)
                view.saturation = progress.toFloat()
            }
        })
    }

    private open inner class SimpleOnSeekBarChangeListener : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {}
        override fun onStartTrackingTouch(seekBar: SeekBar) {}
        override fun onStopTrackingTouch(seekBar: SeekBar) {}
    }
}