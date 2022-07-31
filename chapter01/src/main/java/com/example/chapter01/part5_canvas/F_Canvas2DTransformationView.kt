package com.example.chapter01.part5_canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.chapter01.R
import com.example.common.ImageUtils
import com.example.common.dp

/**
 * 几何变换的使用大概分为三类：
 *
 * 使用 Canvas 来做常见的二维变换；
 * 使用 Matrix 来做常见和不常见的二维变换；
 * 使用 Camera 来做三维变换。
 *
 * 多次几何变换
 * 参考：https://rengwuxian.com/ui-1-4/
 *
 * @author wangzhichao
 * @since 2022/6/10
 */
class F_Canvas2DTransformationView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val avatar = ImageUtils.decodeSampleBitmapFromResource(context.resources,
        R.drawable.avatar,
        100.dp.toInt())

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // OK 的代码
//        canvas.rotate(45f, 100.dp + avatar.width / 2f, avatar.height / 2f)
//        canvas.drawBitmap(avatar, 100.dp, 0.dp, paint)

        // 有问题的代码
//        canvas.translate(100.dp, 0f)
//        canvas.rotate(45f, 100.dp + avatar.width / 2f, avatar.height / 2f)
//        canvas.drawBitmap(avatar, 0.dp, 0.dp, paint)

        // OK 的代码 顺着看理解的思路，不好理解。以 Canvas 坐标系为变换的参照物。
//        canvas.translate(100.dp, 0f)
//        canvas.rotate(45f, avatar.width / 2f, avatar.height / 2f)
//        canvas.drawBitmap(avatar, 0.dp, 0.dp, paint)

        // OK 的代码，倒着看理解的思路，好理解。以图形为变换的参照物。
        canvas.rotate(45f, 100.dp + avatar.width / 2f, avatar.height / 2f)
        canvas.translate(100.dp, 0f)
        canvas.drawBitmap(avatar, 0.dp, 0.dp, paint)
    }
}