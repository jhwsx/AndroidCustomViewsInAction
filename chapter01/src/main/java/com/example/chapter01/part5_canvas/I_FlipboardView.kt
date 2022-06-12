package com.example.chapter01.part5_canvas

import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withSave
import com.example.chapter01.R
import com.example.common.ImageUtils
import com.example.common.dp

/**
 *
 * @author wangzhichao
 * @since 2022/6/11
 */
class I_FlipboardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val AVATAR_SIZE = 200.dp
    private val avatar = ImageUtils.decodeSampleBitmapFromResource(context.resources,
        R.drawable.avatar,
        AVATAR_SIZE.toInt())
    private var avatarLeft: Float = 0f
    private var avatarTop: Float = 0f

    private val camera = Camera()

    init {
        // Sets the location of the camera. The default location is set at 0, 0, -8.
        // 这里用于修正糊脸效果
        camera.setLocation(0f, 0f, -8f * resources.displayMetrics.density)
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
            rotate(-30f)
            clipRect(-AVATAR_SIZE, -AVATAR_SIZE, AVATAR_SIZE, 0f)
            rotate(30f)
            translate(-dx, -dy)
            drawBitmap(avatar, avatarLeft, avatarTop, paint)
        }

        // 2，绘制下半部分
        canvas.withSave {
            translate(dx, dy)
            rotate(-30f)
            camera.save()
            camera.rotateX(30f)
            camera.applyToCanvas(canvas)
            camera.restore()
            // 一定要在旋转之前裁切
            clipRect(-AVATAR_SIZE, 0f, AVATAR_SIZE, AVATAR_SIZE)
            rotate(30f)
            translate(-dx, -dy)
            drawBitmap(avatar, avatarLeft, avatarTop, paint)
        }
    }
}