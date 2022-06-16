package com.example.chapter03.part1_valueanimator

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.*
import com.example.chapter03.R
import java.lang.NumberFormatException

/**
 * 演示 ValueAnimator 常用的函数以及监听器
 * 注意：监听器是添加到集合里面的。
 * @author wangzhichao
 * @date 7/27/20
 */
class D_ValueAnimatorAPIGroup(context: Context?, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private var valueAnimator: ValueAnimator? = null
    // 记录初始的顶部位置，便于恢复到原来的位置。
    private var textTop = 0
    private val tv: TextView
    private var createdValueAnimator: ValueAnimator? = null
    private var clonedValueAnimator: ValueAnimator? = null

    init {
        inflate(context, R.layout.layout_value_animator_api_group, this)
        val rbRestart = findViewById<RadioButton>(R.id.rb_restart)
        tv = findViewById(R.id.tv)
        val etRepeatCount = findViewById<EditText>(R.id.et_repeat_count)
        val btnStart = findViewById<Button>(R.id.btn_start)
        val btnCancel = findViewById<Button>(R.id.btn_cancel)
        val btnPause = findViewById<Button>(R.id.btn_pause)
        val btnResume = findViewById<Button>(R.id.btn_resume)
        val btnReverse = findViewById<Button>(R.id.btn_reverse)
        val btnStartClone = findViewById<Button>(R.id.btn_start_clone)
        val btnCancelClone = findViewById<Button>(R.id.btn_cancel_clone)
        post { textTop = tv.top }
        btnStart.setOnClickListener {
            initValueAnimator(etRepeatCount, rbRestart)
            valueAnimator!!.start()
        }
        btnCancel.setOnClickListener { cancelAnim() }
        btnPause.setOnClickListener {
            if (valueAnimator != null) {
                valueAnimator!!.pause()
            }
        }
        btnResume.setOnClickListener {
            if (valueAnimator != null) {
                valueAnimator!!.resume()
            }
        }
        btnReverse.setOnClickListener {
            initValueAnimator(etRepeatCount, rbRestart)
            valueAnimator!!.reverse()
        }
        btnStartClone.setOnClickListener {
            createdValueAnimator = create()
            clonedValueAnimator = createdValueAnimator!!.clone()
            clonedValueAnimator!!.startDelay = 1000L
            clonedValueAnimator!!.start()
        }
        btnCancelClone.setOnClickListener {
            // 这种不可以停止动画
//                if (createdValueAnimator != null) {
//                    createdValueAnimator.removeAllUpdateListeners();
//                    createdValueAnimator.cancel();
//                }
            // 这种可以停止动画
            if (clonedValueAnimator != null) {
                clonedValueAnimator!!.removeAllUpdateListeners()
                clonedValueAnimator!!.cancel()
            }
        }
    }

    private fun initValueAnimator(etRepeatCount: EditText, rbRestart: RadioButton) {

        valueAnimator = ValueAnimator.ofInt(0, 400).apply {
            duration = 2000L
            addUpdateListener { animation ->
                val fraction = animation.animatedFraction
                val currValue = animation.animatedValue as Int
                tv.layout(tv.left, currValue + textTop, tv.right,
                    currValue + textTop + tv.height)
            }
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    Log.d(TAG, "onAnimationStart: ")
                }

                override fun onAnimationEnd(animation: Animator) {
                    Log.d(TAG, "onAnimationEnd: ")
                }

                override fun onAnimationCancel(animation: Animator) {
                    Log.d(TAG, "onAnimationCancel: ")
                }

                override fun onAnimationRepeat(animation: Animator) {
                    Log.d(TAG, "onAnimationRepeat: ")
                }
            })
            addPauseListener(object : Animator.AnimatorPauseListener {
                override fun onAnimationPause(animation: Animator) {
                    Log.d(TAG, "onAnimationPause: ")
                }

                override fun onAnimationResume(animation: Animator) {
                    Log.d(TAG, "onAnimationResume: ")
                }
            })
            var rc = 0
            try {
                rc = etRepeatCount.text.toString().toInt()
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            }
            repeatCount = rc
            repeatMode =
                if (rbRestart.isChecked) ValueAnimator.RESTART else ValueAnimator.REVERSE
        }
    }

    private fun create(): ValueAnimator {
        return ValueAnimator.ofInt(0, 400).apply {
            duration = 2000L
            repeatMode = ValueAnimator.RESTART
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener { animation ->
                val currValue = animation.animatedValue as Int
                tv.layout(tv.left, currValue + textTop, tv.right,
                    currValue + textTop + tv.height)
            }

        }
    }

    private fun cancelAnim() {
        if (valueAnimator != null) {
            // 移除所有的 AnimatorUpdateListener
            valueAnimator!!.removeAllUpdateListeners()
            // 移除指定的 AnimatorUpdateListener
            // valueAnimator.removeUpdateListener(ValueAnimator.AnimatorUpdateListener listener);
            // 移除所有的 AnimatorListener 和 AnimatorPauseListener
            valueAnimator!!.removeAllListeners()
            // 移除指定的 AnimatorListener
            // valueAnimator.removeListener(Animator.AnimatorListener listener);
            // 移除指定的 AnimatorPauseListener
            // valueAnimator.removePauseListener(Animator.AnimatorPauseListener listener);
            // 让控件回到初始的位置。
            tv.layout(tv.left, textTop, tv.right, textTop + tv.height)
            valueAnimator!!.cancel()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d(TAG, "onDetachedFromWindow: ")
        // 注意: 当不需要动画的时候,一定要移除动画,否则动画还在继续,从而导致 View 无法释放,进一步导致整个 Activity 无法释放,最终引起内存泄漏.
        cancelAnim()
    }

    companion object {
        private const val TAG = "D_ValueAnimatorAPIGroup"
    }

}