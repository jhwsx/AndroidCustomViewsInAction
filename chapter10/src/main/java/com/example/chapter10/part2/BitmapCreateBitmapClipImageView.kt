package com.example.chapter10.part2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.chapter10.R;

/**
 * 从小狗图片中裁剪出只有小狗的小矩形
 *
 * @author wangzhichao
 * @date 2019/10/29
 */
public class BitmapCreateBitmapClipImageView extends View {

    private final Bitmap bitmap;
    private final Bitmap source;
    private final Bitmap bitmap2;

    public BitmapCreateBitmapClipImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        source = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
        int width = source.getWidth();
        int height = source.getHeight();
        // Bitmap createBitmap(@NonNull Bitmap source, int x, int y, int width, int height)
        // source 源图像
        // x, y 从源图像的哪个位置开始裁剪
        // width, height 从指定位置裁剪多少像素
        bitmap = Bitmap.createBitmap(source, width / 3, height / 3, width / 3, height / 3);
        Matrix matrix = new Matrix();
        matrix.setScale(2, 1);
        // Bitmap createBitmap(@NonNull Bitmap source, int x, int y, int width, int height, @Nullable Matrix m, boolean filter)
        // Matrix，对裁剪后的图像进行矩阵变换
        // filter, 对应 paint.setFilterBitmap(filter), 是否给图像添加滤波效果。
        // 自己测验了为 true 时，把图片放到最大，最小的格子是正方形；为 false 时，把图片放大到最大，最小的格子是矩形。
        bitmap2 = Bitmap.createBitmap(source, width / 3, height / 3, width / 3, height / 3, matrix, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(source, 0,0, null);
        canvas.drawBitmap(bitmap,0,source.getHeight(), null);
        canvas.drawBitmap(bitmap2,0,source.getHeight() + bitmap.getHeight(), null);
    }
}

/**
 * 总结：
 * 1，Matrix 是一个 3x3 的矩阵，参考 https://mp.weixin.qq.com/s?__biz=MzA5MzI3NjE2MA==&mid=2650237467&idx=1&sn=abe2e534249ac5deb3e09efd412dd8e2&chksm=88639b74bf141262da5c170aa37cf9d1ff85a6835fc0f162172fc0801ebf26307f759b9b7fb6&scene=38#wechat_redirect
 */
