package com.example.chapter10.part2

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.util.AttributeSet
import android.view.View
import com.example.chapter10.R

/**
 * 从小狗图片中裁剪出只有小狗的小矩形
 *
 * @author wangzhichao
 * @date 2019/10/29
 */
class O_BitmapCreateBitmapClipImageView(context: Context, attrs: AttributeSet?) :
    View(context, attrs) {
    private val bitmap: Bitmap
    private val source: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.dog)
    private val bitmap2: Bitmap
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(source, 0f, 0f, null)
        canvas.drawBitmap(bitmap, 0f, source.height.toFloat(), null)
        canvas.drawBitmap(bitmap2, 0f, (source.height + bitmap.height).toFloat(), null)
    }

    init {
        val srcWidth = source.width
        val srcHeight = source.height
        // Bitmap createBitmap(@NonNull Bitmap source, int x, int y, int width, int height)
        // source 源图像
        // x, y 从源图像的哪个位置开始裁剪
        // width, height 从指定位置裁剪多少像素
        bitmap = Bitmap.createBitmap(source, srcWidth / 3, srcHeight / 3, srcWidth / 3, srcHeight / 3)
        val matrix = Matrix()
        matrix.setScale(1f, 2f)
        // Bitmap createBitmap(@NonNull Bitmap source, int x, int y, int width, int height, @Nullable Matrix m, boolean filter)
        // Matrix，对裁剪后的图像进行矩阵变换
        // filter, 对应 paint.setFilterBitmap(filter), 是否给图像添加滤波效果。
        // 自己测验了为 true 时，把图片放到最大，最小的格子是正方形；为 false 时，把图片放大到最大，最小的格子是矩形。
        bitmap2 =
            Bitmap.createBitmap(source, srcWidth / 3, srcHeight / 3, srcWidth / 3, srcHeight / 3, matrix, true)
    }
}