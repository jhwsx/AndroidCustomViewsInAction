package com.example.chapter04.part2

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.chapter04.R
import com.example.common.dp

/**
 *
 * @author wangzhichao
 * @since 2022/6/22
 */
class A_ViewPropertyAnimatorIntroViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    private val iv: ImageView

    init {
        inflate(context, R.layout.layout_viewpropertyanimator_intro_viewgroup, this)
        val btnStartAnim = findViewById<Button>(R.id.btn_start_anim)
        iv = findViewById(R.id.iv)
        btnStartAnim.setOnClickListener { doAnimation() }
    }

    private fun doAnimation() {
        // 不用显式地调用 start() 函数
        // 可以多个动画一起执行
        // 链式编程
        iv.animate()
            .scaleX(2f)
            .scaleY(2f)
            .translationX(100.dp)
            .translationY(100.dp)
            .rotation(45f)
            .alpha(0.5f)
            .setInterpolator(LinearInterpolator())
            .setStartDelay(300L)
            .setListener(object: Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) {
                    Log.d(TAG, "onAnimationStart: ")
                }

                override fun onAnimationEnd(animation: Animator?) {
                    Log.d(TAG, "onAnimationEnd: ")
                }

                override fun onAnimationCancel(animation: Animator?) {
                    Log.d(TAG, "onAnimationCancel: ")
                }

                override fun onAnimationRepeat(animation: Animator?) {
                    Log.d(TAG, "onAnimationRepeat: ")
                }

            })
            .setUpdateListener {
                Log.d(TAG, "setUpdateListener: ")
            }
            .withStartAction {
                Log.d(TAG, "withStartAction: ")
            }
            .withEndAction {
                Log.d(TAG, "withEndAction: ")
            }
    }

    companion object {
        private const val TAG = "A_ViewPropertyAnimatorI"
    }

}