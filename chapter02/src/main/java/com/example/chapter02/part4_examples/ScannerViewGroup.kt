package com.example.chapter02.part4_examples

import android.content.Context
import android.util.AttributeSet
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.example.chapter02.R

/**
 * @author wangzhichao
 * @since 20-4-9
 */
class ScannerViewGroup(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    init {
        inflate(context, R.layout.scanner_viewgroup, this)
        val animation1 = AnimationUtils.loadAnimation(context, R.anim.scale_alpha_anim)
        val animation2 = AnimationUtils.loadAnimation(context, R.anim.scale_alpha_anim)
        val animation3 = AnimationUtils.loadAnimation(context, R.anim.scale_alpha_anim)
        val animation4 = AnimationUtils.loadAnimation(context, R.anim.scale_alpha_anim)
        val circle1 = findViewById<ImageView>(R.id.circle1)
        val circle2 = findViewById<ImageView>(R.id.circle2)
        val circle3 = findViewById<ImageView>(R.id.circle3)
        val circle4 = findViewById<ImageView>(R.id.circle4)
        val tvStartScan = findViewById<TextView>(R.id.start_scan)
        tvStartScan.setOnClickListener {
            circle1.startAnimation(animation1)
            animation2.startOffset = 600
            circle2.startAnimation(animation2)
            animation3.startOffset = 1200
            circle3.startAnimation(animation3)
            animation4.startOffset = 1800
            circle4.startAnimation(animation4)
        }
    }
}