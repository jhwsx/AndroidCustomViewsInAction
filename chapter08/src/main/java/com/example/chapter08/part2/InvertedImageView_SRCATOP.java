package com.example.chapter08.part2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.chapter08.R;

/**
 * @author wangzhichao
 * @date 2019/09/26
 */
public class InvertedImageView_SRCATOP extends View {
    private Bitmap bitmap;
    private Bitmap srcBitmap;
    private Bitmap dstBitmap;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF rectF = new RectF();
    public InvertedImageView_SRCATOP(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
        dstBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog_invert_shade);
        Matrix matrix = new Matrix();
        matrix.postScale(1, -1);
        srcBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float bitmapHeight = getHeight() * 0.4f;
        float bitmapWidth = bitmapHeight * bitmap.getWidth() * 1f / bitmap.getHeight();
        rectF.left = 0;
        rectF.right = bitmapWidth;
        rectF.top = 0;
        rectF.bottom = bitmapHeight;

        // 画出正立的图片
        canvas.drawBitmap(bitmap, null, rectF, paint);

        // 离屏缓冲
        // 新建图层
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), paint, Canvas.ALL_SAVE_FLAG);
        // 这里是核心代码
        canvas.translate(0, bitmapHeight);
        // 绘制目标图像，在下层
        canvas.drawBitmap(dstBitmap, null, rectF, paint);
        // 给画笔设置混合模式 SRC_ATOP
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        // 绘制源图像，在上层
        canvas.drawBitmap(srcBitmap, null, rectF, paint);
        // 清除画笔的混合模式
        paint.setXfermode(null);
        // 还原图层
        canvas.restoreToCount(layerId);
    }
}
