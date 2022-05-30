package com.example.chapter01.part5_canvas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.chapter01.R
import com.example.common.ImageUtils
import com.example.common.dp
import kotlin.math.min

/**
 * 圆形头像例子
 * 思路：
 * 1，在图片所在的位置先裁剪出一个圆形的画布；
 * 2，绘制图片，因为超出圆形画布区域的图片部分不会显示，因此就可以得到圆形头像。
 * 3, 因为要用到 clipPath，所以需要设置关闭硬件加速。
 *
 * @author wangzhichao
 * @since 20-3-18
 */
class K_CircleAvatarView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val avatar = ImageUtils.decodeSampleBitmapFromResource(resources, R.drawable.avatar, 200.dp.toInt())
    private val rect = RectF()
    private val path = Path()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        rect.set(width / 2f - avatar.width / 2f, height / 2f - avatar.height / 2f, width / 2f + avatar.width / 2f, height / 2f + avatar.height / 2f)
        path.reset()
        path.addOval(rect, Path.Direction.CCW)
        canvas.clipPath(path)
        canvas.drawBitmap(avatar, width / 2f - avatar.width / 2f, height / 2f - avatar.height / 2f, paint)
    }
}