package com.example.chapter03.part5_animatorset

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import com.example.chapter03.R
import com.example.common.dp
import kotlin.math.cos
import kotlin.math.sin

/**
 *
 * @author wangzhichao
 * @since 2022/6/16
 */
class I_PathMenuViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
) : FrameLayout(context, attrs), View.OnClickListener {
    private val menu: Button
    private val circle1: Button
    private val circle2: Button
    private val circle3: Button
    private val circle4: Button
    private val circle5: Button
    private var menuOpen: Boolean = false
    private val RADIUS = 150.dp
    init {
        inflate(context, R.layout.layout_path_menu_viewgroup, this)
        menu = findViewById(R.id.menu)
        circle1 = findViewById(R.id.item1)
        circle2 = findViewById(R.id.item2)
        circle3 = findViewById(R.id.item3)
        circle4 = findViewById(R.id.item4)
        circle5 = findViewById(R.id.item5)
        menu.setOnClickListener(this)
        circle1.setOnClickListener(this)
        circle2.setOnClickListener(this)
        circle3.setOnClickListener(this)
        circle4.setOnClickListener(this)
        circle5.setOnClickListener(this)
    }

    private fun openMenu() {
        openItem(circle1, 0, 5, RADIUS)
        openItem(circle2, 1, 5, RADIUS)
        openItem(circle3, 2, 5, RADIUS)
        openItem(circle4, 3, 5, RADIUS)
        openItem(circle5, 4, 5, RADIUS)
    }

    private fun closeMenu() {
        closeItem(circle1, 0, 5, RADIUS)
        closeItem(circle2, 1, 5, RADIUS)
        closeItem(circle3, 2, 5, RADIUS)
        closeItem(circle4, 3, 5, RADIUS)
        closeItem(circle5, 4, 5, RADIUS)
    }

    private fun openItem(item: View, index: Int, total: Int, radius: Float) {
        item.visibility = View.VISIBLE
        val degrees = index * 90.0 / (total - 1)
        val translationX = -radius * sin(Math.toRadians(degrees))
        val translationY = -radius * cos(Math.toRadians(degrees))
        AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(item, "translationX", 0f, translationX.toFloat()),
                ObjectAnimator.ofFloat(item, "translationY", 0f, translationY.toFloat()),
                ObjectAnimator.ofFloat(item, "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(item, "scaleY", 0f, 1f),
                ObjectAnimator.ofFloat(item, "alpha", 0f, 1f),
            )
            duration = 500L
            start()
        }
    }

    private fun closeItem(item: View, index: Int, total: Int, radius: Float) {
        item.visibility = View.VISIBLE
        val degrees = index * 90.0 / (total - 1)
        val translationX = -radius * sin(Math.toRadians(degrees))
        val translationY = -radius * cos(Math.toRadians(degrees))
        AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(item, "translationX",  translationX.toFloat(), 0f),
                ObjectAnimator.ofFloat(item, "translationY", translationY.toFloat(), 0f),
                ObjectAnimator.ofFloat(item, "scaleX", 1f, 0f),
                ObjectAnimator.ofFloat(item, "scaleY", 1f, 0f),
                ObjectAnimator.ofFloat(item, "alpha", 1f, 0f),
            )
            duration = 500L
            start()
        }
    }

    override fun onClick(v: View) {
        if (menuOpen) {
            Toast.makeText(context, "click $v", Toast.LENGTH_SHORT).show()
            closeMenu()
        } else {
            openMenu()
        }
        menuOpen = !menuOpen
    }
}