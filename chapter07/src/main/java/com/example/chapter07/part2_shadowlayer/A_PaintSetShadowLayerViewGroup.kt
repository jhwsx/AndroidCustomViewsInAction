package com.example.chapter07.part2_shadowlayer

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.SeekBar
import com.example.chapter07.R

/**
 * @author wangzhichao
 * @date 2019/09/20
 */
class A_PaintSetShadowLayerViewGroup : LinearLayout {
    private var seekBarRadius: SeekBar? = null
    private var seekBarDx: SeekBar? = null
    private var seekBarDy: SeekBar? = null
    private var view: A_PaintSetShadowLayerView? = null
    private var btnShowShadow: View? = null
    private var btnClearShadow1: View? = null
    private var btnClearShadow2: View? = null

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context,
        attrs,
        defStyleAttr) {
        init()
    }

    private fun init() {
        inflate(context, R.layout.layout_paint_setshadowlayer_viewgroup, this)
        initViews()
        initData()
    }

    private fun initData() {
        view!!.setDx(0)
        view!!.setDy(0)
        seekBarRadius!!.setOnSeekBarChangeListener(object : SimpleOnSeekBarChangeListener() {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                super.onProgressChanged(seekBar, progress, fromUser)
                Log.d(TAG, "radius = $progress")
                view!!.setRadius(progress)
            }
        })
        seekBarDx!!.setOnSeekBarChangeListener(object : SimpleOnSeekBarChangeListener() {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                super.onProgressChanged(seekBar, progress, fromUser)
                val newProgress = progress - 20
                Log.d(TAG, "dx = $newProgress")
                view!!.setDx(newProgress)
            }
        })
        seekBarDy!!.setOnSeekBarChangeListener(object : SimpleOnSeekBarChangeListener() {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                super.onProgressChanged(seekBar, progress, fromUser)
                val newProgress = progress - 20
                Log.d(TAG, "dy = $newProgress")
                view!!.setDy(newProgress)
            }
        })
        btnShowShadow!!.setOnClickListener { view!!.showShadow() }
        btnClearShadow1!!.setOnClickListener { view!!.clearShadow1() }
        btnClearShadow2!!.setOnClickListener { view!!.clearShadow2() }
    }

    private fun initViews() {
        seekBarRadius = findViewById(R.id.seekbar_radius)
        seekBarDx = findViewById(R.id.seekbar_dx)
        seekBarDy = findViewById(R.id.seekbar_dy)
        view = findViewById(R.id.view)
        btnShowShadow = findViewById(R.id.btnShowShadow)
        btnClearShadow1 = findViewById(R.id.btnClearShadow1)
        btnClearShadow2 = findViewById(R.id.btnClearShadow2)
    }

    private open inner class SimpleOnSeekBarChangeListener : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {}
        override fun onStartTrackingTouch(seekBar: SeekBar) {}
        override fun onStopTrackingTouch(seekBar: SeekBar) {}
    }

    companion object {
        private val TAG = A_PaintSetShadowLayerViewGroup::class.java.simpleName
    }
}