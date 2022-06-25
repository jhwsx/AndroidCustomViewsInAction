package com.example.chapter03.part5_animatorset

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withSave
import com.example.chapter03.R
import com.example.common.ImageUtils
import com.example.common.dp

/**
 *
 * @author wangzhichao
 * @since 2022/6/23
 */
class J_FlipboardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val AVATAR_SIZE = 200.dp
    private val avatar = ImageUtils.decodeSampleBitmapFromResource(context.resources,
        R.drawable.avatar,
        AVATAR_SIZE.toInt())
    private var avatarLeft: Float = 0f
    private var avatarTop: Float = 0f

    private val camera = Camera()

    /**
     * 顶部翻起的角度
     */
    var topFlipAngle = 0f
        set(value) {
            field = value
            invalidate()
        }

    /**
     * 底部翻起的角度
     */
    var bottomFlipAngle = 30f
        set(value) {
            field = value
            invalidate()
        }

    /**
     * 折痕的角度
     */
    var creaseAngle = 0f
        set(value) {
            field = value
            invalidate()
        }
    private val animatorSet = AnimatorSet()

    init {
        // Sets the location of the camera. The default location is set at 0, 0, -8.
        // 这里用于修正糊脸效果
        camera.setLocation(0f, 0f, -8f * resources.displayMetrics.density)

        val bottomFlipAngleAnimator =
            ObjectAnimator.ofFloat(this, "bottomFlipAngle", 0f, 60f).apply {
                startDelay = 1000L
                duration = 1000L
            }
        val creaseAngleAnimator = ObjectAnimator.ofFloat(this, "creaseAngle", 0f, 270f).apply {
            startDelay = 200L
            duration = 1000L
        }
        val topFlipAngleAnimator = ObjectAnimator.ofFloat(this, "topFlipAngle", 0f, -60f).apply {
            startDelay = 200L
            duration = 1000L
        }
        // 先底部翻转，再改变折痕角度，最后顶部翻转
        animatorSet.playSequentially(bottomFlipAngleAnimator,
            creaseAngleAnimator,
            topFlipAngleAnimator)
        animatorSet.start()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        avatarLeft = width / 2f - avatar.width / 2f
        avatarTop = 100.dp
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val dx = width / 2f
        val dy = avatarTop + AVATAR_SIZE / 2f
        // 1，绘制上半部分
        canvas.withSave {
            translate(dx, dy)
            rotate(-creaseAngle)
            camera.save()
            camera.rotateX(topFlipAngle)
            camera.applyToCanvas(canvas)
            camera.restore()
            clipRect(-AVATAR_SIZE, -AVATAR_SIZE, AVATAR_SIZE, 0f)
            rotate(creaseAngle)
            translate(-dx, -dy)
            drawBitmap(avatar, avatarLeft, avatarTop, paint)
        }

        // 2，绘制下半部分
        canvas.withSave {
            translate(dx, dy)
            rotate(-creaseAngle)
            camera.save()
            camera.rotateX(bottomFlipAngle)
            camera.applyToCanvas(canvas)
            camera.restore()
            // 一定要在旋转之前裁切
            clipRect(-AVATAR_SIZE, 0f, AVATAR_SIZE, AVATAR_SIZE)
            rotate(creaseAngle)
            translate(-dx, -dy)
            drawBitmap(avatar, avatarLeft, avatarTop, paint)
        }
    }
}