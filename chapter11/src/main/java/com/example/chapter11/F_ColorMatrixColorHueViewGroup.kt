package com.example.chapter11

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.SeekBar

/**
 * 色相调节
 * @author wangzhichao
 * @since 2022/5/31
 */
class F_ColorMatrixColorHueViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private val view: F_ColorMatrixColorHueView
    private val sbRed: SeekBar
    private val sbGreen: SeekBar
    private val sbBlue: SeekBar
    init {
        inflate(context, R.layout.viewgroup_color_matrix_color_hue, this)
        view = findViewById(R.id.view)
        sbRed = findViewById(R.id.seekbar_red)
        sbGreen = findViewById(R.id.seekbar_green)
        sbBlue = findViewById(R.id.seekbar_blue)
        sbRed.setOnSeekBarChangeListener(object: SimpleOnSeekBarChangeListener() {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                super.onProgressChanged(seekBar, progress, fromUser)
                view.redHueDegrees = (progress - 180).toDouble()
            }
        })
        sbGreen.setOnSeekBarChangeListener(object: SimpleOnSeekBarChangeListener() {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                super.onProgressChanged(seekBar, progress, fromUser)
                view.greenHueDegrees = (progress - 180).toDouble()
            }
        })
        sbBlue.setOnSeekBarChangeListener(object: SimpleOnSeekBarChangeListener() {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                super.onProgressChanged(seekBar, progress, fromUser)
                view.blueHueDegrees = (progress - 180).toDouble()
            }
        })
    }

    private open inner class SimpleOnSeekBarChangeListener : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {}
        override fun onStartTrackingTouch(seekBar: SeekBar) {}
        override fun onStopTrackingTouch(seekBar: SeekBar) {}
    }
}