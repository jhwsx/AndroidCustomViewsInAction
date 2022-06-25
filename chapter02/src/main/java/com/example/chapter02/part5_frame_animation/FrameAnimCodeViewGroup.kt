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
 * 帧动画代码实现
 *
 * @author wangzhichao
 * @date 7/14/20
 */
class FrameAnimCodeViewGroup(context: Context, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private fun createAnimationDrawable(): AnimationDrawable {
        val animationDrawable = AnimationDrawable()
        for (i in 1..14) {
            val drawableId = resources.getIdentifier("list_icon_gif_playing$i",
                "drawable", context.packageName)
            val frame = resources.getDrawable(drawableId)
            animationDrawable.addFrame(frame, 60)
        }
        animationDrawable.isOneShot = false
        return animationDrawable
    }

    companion object {
        private const val TAG = "FrameAnimCodeViewGroup"
    }

    init {
        inflate(context, R.layout.frame_anim_code_viewgroup, this)
        // android:src 方式设置的, 要对应 getDrawable(), 否则取出的是 null
        val iv1 = findViewById<ImageView>(R.id.iv_1)
        val animationDrawable = createAnimationDrawable()
        iv1.setImageDrawable(animationDrawable)
        // android:background 方式设置的, 要对应 getBackground(),否则取出的是 null
        val iv2 = findViewById<ImageView>(R.id.iv_2)
        val animationDrawable2 = createAnimationDrawable()
        iv2.background = animationDrawable2
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
    }
}