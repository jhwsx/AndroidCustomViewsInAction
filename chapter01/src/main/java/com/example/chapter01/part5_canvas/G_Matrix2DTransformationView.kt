package com.example.chapter01.part5_canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Matrix
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
 *  TODO 折叠布局：https://blog.csdn.net/lmj623565791/article/details/44278417
 * 使用 Camera 来做三维变换。
 *
 * 多次几何变换
 * 参考：https://rengwuxian.com/ui-1-4/
 *
 * @author wangzhichao
 * @since 2022/6/10
 */
class G_Matrix2DTransformationView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val avatar = ImageUtils.decodeSampleBitmapFromResource(context.resources,
        R.drawable.avatar,
        100.dp.toInt())
    private val mtx = Matrix()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // OK 的代码
//        canvas.rotate(45f, 100.dp + avatar.width / 2f, avatar.height / 2f)
//        canvas.drawBitmap(avatar, 100.dp, 0.dp, paint)
        // 相当于 canvas.rotate 的代码。
//        mtx.preRotate(45f, 100.dp + avatar.width / 2f, avatar.height / 2f)
//        canvas.concat(mtx) // 把几何变换应用到 Canvas，用 Canvas 当前的变换矩阵和 Matrix 相乘，即基于 Canvas 当前的变换，叠加上 Matrix 中的变换。建议使用。
//        canvas.drawBitmap(avatar, 100.dp, 0.dp, paint)

        // OK 的代码 顺着看理解的思路，不好理解
//        canvas.translate(100.dp, 0f)
//        canvas.rotate(45f, avatar.width / 2f, avatar.height / 2f)
//        canvas.drawBitmap(avatar, 0.dp, 0.dp, paint)
        // 与上面的代码相对应
//        mtx.preTranslate(100.dp, 0f)
//        mtx.preRotate(45f, avatar.width / 2f, avatar.height / 2f)
//        canvas.setMatrix(mtx)
//        canvas.drawBitmap(avatar, 0.dp, 0.dp, paint)

        // OK 的代码，倒着看理解的思路，好理解
//        canvas.rotate(45f, 100.dp + avatar.width / 2f, avatar.height / 2f)
//        canvas.translate(100.dp, 0f)
//        canvas.drawBitmap(avatar, 0.dp, 0.dp, paint)
        // 与上面的代码相对应，但是是正着写
        mtx.postTranslate(100.dp, 0f)
        mtx.postRotate(45f, 100.dp + avatar.width / 2f, avatar.height / 2f)
        canvas.setMatrix(mtx) // 把几何变换应用到 Canvas，这是用 Matrix 直接替换 Canvas 当前的变换矩阵，即抛弃 Canvas 当前的变换，改用 Matrix 的变换。不建议使用。
        canvas.drawBitmap(avatar, 0.dp, 0.dp, paint)
    }
}