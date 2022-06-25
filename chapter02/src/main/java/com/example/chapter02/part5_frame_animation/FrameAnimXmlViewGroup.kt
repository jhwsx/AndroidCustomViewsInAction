package com.example.chapter02.part5_frame_animation

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.chapter02.R

/**
 * 帧动画 xml 实现
 *
 * xml 资源放在 drawable 下面,
 * 根节点是 animation-list,
 * oneshot 属性为 false, 表示无限循环; 为 true, 表示就播放一次.
 * 播放需要 AnimationDrawable 类的支持
 *
 * @author wangzhichao
 * @date 7/14/20
 */
class FrameAnimXmlViewGroup(context: Context, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    companion object {
        private const val TAG = "FrameAnimXmlViewGroup"
    }

    init {
        inflate(context, R.layout.frame_anim_xml_viewgroup, this)
        // android:src 方式设置的, 要对应 getDrawable(), 否则取出的是 null
        val iv1 = findViewById<ImageView>(R.id.iv_1)
        val animationDrawable = iv1.drawable as AnimationDrawable
        // android:background 方式设置的, 要对应 getBackground(),否则取出的是 null
        val iv2 = findViewById<ImageView>(R.id.iv_2)
        val animationDrawable2 = iv2.background as AnimationDrawable
        val cbOneShot = findViewById<CheckBox>(R.id.cb_oneshot)
        findViewById<View>(R.id.start).setOnClickListener {
            animationDrawable.isOneShot = cbOneShot.isChecked
            animationDrawable.start()
            animationDrawable2.isOneShot = cbOneShot.isChecked
            animationDrawable2.start()
            Log.d(TAG, "onClick: isRunning=" + animationDrawable.isRunning)
        }
        findViewById<View>(R.id.stop).setOnClickListener {
            animationDrawable.stop()
            animationDrawable2.stop()
        }
        // AnimationDrawable 的一些 api
        Log.d(TAG, "FrameAnimXmlViewGroup: 第 0 帧的时长=" + animationDrawable.getDuration(0))
        Log.d(TAG, "FrameAnimXmlViewGroup: 第 0 帧的drawable=" + animationDrawable.getFrame(0))
        Log.d(TAG, "FrameAnimXmlViewGroup: 总共多少帧=" + animationDrawable.numberOfFrames)
        Log.d(TAG, "FrameAnimXmlViewGroup: isOneShot=" + animationDrawable.isOneShot)
    }
}