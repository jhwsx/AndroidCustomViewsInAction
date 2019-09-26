package com.example.chapter08.part2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.chapter08.R;

/**
 * @author wangzhichao
 * @date 2019/09/24
 */
public class RoundImageView_SRCATOP extends View {
    private Bitmap dstBmp;
    private Bitmap srcBmp;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public RoundImageView_SRCATOP(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        dstBmp = BitmapFactory.decodeResource(getResources(), R.drawable.dog_shade);
        srcBmp = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 离屏缓冲
        // 新建图层
        int layer = canvas.saveLayer(0, 0, getWidth(), getHeight(), paint, Canvas.ALL_SAVE_FLAG);
        // 这里是核心代码
        // 绘制目标图像，在底部
        canvas.drawBitmap(dstBmp, 0, 0, paint);
        // 给画笔设置混合模式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        // 绘制源图像，在顶部
        canvas.drawBitmap(srcBmp, 0, 0, paint);
        // 清空画笔的混合模式
        paint.setXfermode(null);
        // 还原图层
        canvas.restoreToCount(layer);
    }
}
