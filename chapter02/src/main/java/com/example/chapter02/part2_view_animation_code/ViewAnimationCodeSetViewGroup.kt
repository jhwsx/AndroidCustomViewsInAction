package com.example.chapter02.part2_view_animation_code

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.*
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.chapter02.R

/**
 * 视图动画的集合动画，使用代码来实现
 *
 * @author wangzhichao
 * @since 20-3-26
 */
class ViewAnimationCodeSetViewGroup(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {
    init {
        inflate(context, R.layout.view_animation_code_set_viewgroup, this)
        val tv = findViewById<TextView>(R.id.tv)
        findViewById<View>(R.id.btn_start).setOnClickListener {
            /*
                        <set xmlns:android="http://schemas.android.com/apk/res/android"
                            android:duration="3000"
                            android:fillAfter="true">
                            <alpha
                                android:fromAlpha="0.0"
                                android:toAlpha="1.0" />
                            <scale
                                android:fromXScale="0.0"
                                android:fromYScale="0.0"
                                android:pivotX="50%"
                                android:pivotY="50%"
                                android:toXScale="1.4"
                                android:toYScale="1.4" />
                            <rotate
                                android:fromDegrees="0"
                                android:pivotX="50%"
                                android:pivotY="50%"
                                android:toDegrees="-600" />
                        </set>
                         */
            val animationSet = AnimationSet(true)
            val alphaAnimation = AlphaAnimation(0.0f, 1.0f)
            val scaleAnimation = ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f)
            val rotateAnimation = RotateAnimation(0f, -600f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f)
            animationSet.addAnimation(alphaAnimation)
            animationSet.addAnimation(scaleAnimation)
            animationSet.addAnimation(rotateAnimation)
            animationSet.duration = 3000L
            animationSet.fillAfter = true
            tv.startAnimation(animationSet)
            //                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.setanim);
//                tv.startAnimation(animation);
        }
        val tvBounce1 = findViewById<TextView>(R.id.tv_bounce1)
        findViewById<View>(R.id.btn_start_bounce1).setOnClickListener { v ->
            val animation = AnimationUtils.loadAnimation(v.context, R.anim.setanim_bounce1)
            tvBounce1.startAnimation(animation)
        }
        val tvBounce2 = findViewById<TextView>(R.id.tv_bounce2)
        findViewById<View>(R.id.btn_start_bounce2).setOnClickListener {
            /*
                        <?xml version="1.0" encoding="utf-8"?>
        <set xmlns:android="http://schemas.android.com/apk/res/android">
            <!--效果: 控件弹跳三次, 一次比一次跳得低的效果.-->
            <!--先向上偏移控件高度的 40%，时间是 350 毫秒-->
            <translate
                android:duration="350"
                android:fillBefore="false"
                android:fillEnabled="true"
                android:fromYDelta="0%"
                android:toYDelta="-40%" />
            <!--350 毫秒后，向下偏移控件高度的 40%，时间是 200 毫秒-->
            <translate
                android:duration="200"
                android:fillBefore="false"
                android:fillEnabled="true"
                android:fromYDelta="-40%"
                android:startOffset="350"
                android:toYDelta="0%" />
            <!--550 毫秒后，向上偏移控件高度的 25%，时间是 300 毫秒-->
            <translate
                android:duration="300"
                android:fillBefore="false"
                android:fillEnabled="true"
                android:fromYDelta="0%"
                android:startOffset="550"
                android:toYDelta="-25%" />
            <!--850 毫秒后，向下偏移控件高度的 25%，时间是 200 毫秒-->
            <translate
                android:duration="200"
                android:fillBefore="false"
                android:fillEnabled="true"
                android:fromYDelta="-25%"
                android:startOffset="850"
                android:toYDelta="0%" />
            <!--1050 毫秒后，向上偏移控件高度的 8%，时间是 200 毫秒-->
            <translate
                android:duration="200"
                android:fillBefore="false"
                android:fillEnabled="true"
                android:fromYDelta="0%"
                android:startOffset="1050"
                android:toYDelta="-8%" />
            <!--1250 毫秒后，向下偏移控件高度的 8%，时间是 150 毫秒-->
            <translate
                android:duration="150"
                android:fillBefore="false"
                android:fillEnabled="true"
                android:fromYDelta="-8%"
                android:startOffset="1250"
                android:toYDelta="0%" />
        </set>
                        */
            val animationSet = AnimationSet(true)
            val translateAnimation1 = TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, -0.4f)
            translateAnimation1.isFillEnabled = true
            translateAnimation1.fillBefore = false
            translateAnimation1.duration = 350L
            val translateAnimation2 = TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, -0.4f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f)
            translateAnimation2.isFillEnabled = true
            translateAnimation2.fillBefore = false
            translateAnimation2.startOffset = 350L
            translateAnimation2.duration = 200L
            val translateAnimation3 = TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, -0.25f)
            translateAnimation3.isFillEnabled = true
            translateAnimation3.fillBefore = false
            translateAnimation3.startOffset = 550L
            translateAnimation3.duration = 300L
            val translateAnimation4 = TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, -0.25f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f)
            translateAnimation4.isFillEnabled = true
            translateAnimation4.fillBefore = false
            translateAnimation4.startOffset = 850L
            translateAnimation4.duration = 200L
            val translateAnimation5 = TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, -0.08f)
            translateAnimation5.isFillEnabled = true
            translateAnimation5.fillBefore = false
            translateAnimation5.startOffset = 1050L
            translateAnimation5.duration = 200L
            val translateAnimation6 = TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, -0.08f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f)
            translateAnimation6.isFillEnabled = true
            translateAnimation6.fillBefore = false
            translateAnimation6.startOffset = 1250L
            translateAnimation6.duration = 150L
            animationSet.addAnimation(translateAnimation1)
            animationSet.addAnimation(translateAnimation2)
            animationSet.addAnimation(translateAnimation3)
            animationSet.addAnimation(translateAnimation4)
            animationSet.addAnimation(translateAnimation5)
            animationSet.addAnimation(translateAnimation6)
            tvBounce2.startAnimation(animationSet)
            //                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.setanim_bounce2);
//                tvBounce2.startAnimation(animation);
        }
    }
}