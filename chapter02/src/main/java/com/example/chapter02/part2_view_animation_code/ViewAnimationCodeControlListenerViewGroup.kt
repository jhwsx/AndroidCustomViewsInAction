package com.example.chapter02.part2_view_animation_code

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.chapter02.R

/**
 * 动画监听结束回调是不可靠的
 * 参考阿里巴巴 Android 开发手册
 * 在有强依赖 onAnimationEnd 回调的交互时,如动画播放完毕才能操作页
 * 面 , onAnimationEnd 可 能 会 因 各 种 异 常 没 被 回 调 ( 参 考 :
 * https://stackoverflow.com/questions/5474923/onanimationend-is-not-getting-called-onanimationstart-works-fine ), 建 议 加 上 超 时 保 护 或 通 过 postDelay 替 代
 * onAnimationEnd。
 *
 * @author wangzhichao
 * @since 20-3-26
 */
class ViewAnimationCodeControlListenerViewGroup(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {
    private var scaleAnimation: ScaleAnimation? = null

    companion object {
        private const val TAG = "wzc"
    }

    init {
        inflate(context, R.layout.view_animation_code_control_listener_viewgroup, this)
        val tv = findViewById<TextView>(R.id.tv)
        findViewById<View>(R.id.btn_start).setOnClickListener {
            if (scaleAnimation == null) {
                scaleAnimation = ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f)
                scaleAnimation!!.duration = 7000L
                scaleAnimation!!.isFillEnabled = false
                scaleAnimation!!.fillBefore = false
                scaleAnimation!!.fillAfter = true
                scaleAnimation!!.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {
                        Log.d(TAG, "onAnimationStart: ")
                    }

                    override fun onAnimationEnd(animation: Animation) {
                        Log.d(TAG, "onAnimationEnd: ")
                    }

                    override fun onAnimationRepeat(animation: Animation) {
                        Log.d(TAG, "onAnimationRepeat: ")
                    }
                })
            }
            tv.startAnimation(scaleAnimation)
        }
        findViewById<View>(R.id.btn_cancel).setOnClickListener {
            if (scaleAnimation != null) {
                scaleAnimation!!.cancel()
            }
        }
        findViewById<View>(R.id.btn_reset).setOnClickListener {
            if (scaleAnimation != null) {
                scaleAnimation!!.reset()
            }
        }
    }
}