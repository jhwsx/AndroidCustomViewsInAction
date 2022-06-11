package com.example.chapter01.part5_canvas

import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.chapter01.R
import com.example.common.ImageUtils
import com.example.common.dp

/**
 *
 * @author wangzhichao
 * @since 2022/6/11
 */
class H_Camera3DTransformationView @JvmOverloads constructor(
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

    init {
        camera.rotateX(30f)
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
        // 默认 Camera 旋转操作是以原点为中心的，现在改为以图片的中心为中心。
        val dx = width / 2f
        val dy = avatarTop + AVATAR_SIZE / 2f
        canvas.translate(dx, dy)
        camera.applyToCanvas(canvas)
        canvas.translate(-dx, -dy)
        canvas.drawBitmap(avatar, avatarLeft, avatarTop, paint)
    }
}