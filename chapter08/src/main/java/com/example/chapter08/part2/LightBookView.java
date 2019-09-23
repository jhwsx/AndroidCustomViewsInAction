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
 * @date 2019/09/23
 */
public class LightBookView extends View {
    private Bitmap dstBmp;
    private Bitmap srcBmp;
    private Paint paint;
    public LightBookView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dstBmp = BitmapFactory.decodeResource(getResources(), R.drawable.book_bg);
        srcBmp = BitmapFactory.decodeResource(getResources(), R.drawable.book_light);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 使用离屏缓存
        // 新建图层
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), paint, Canvas.ALL_SAVE_FLAG);
        // 这里是核心代码
        // 绘制目标图像，在底部
        canvas.drawBitmap(dstBmp, 0, 0, paint);
        // 设置混合模式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN));
        // 绘制源图像，在顶部
        canvas.drawBitmap(srcBmp, 0, 0, paint);
        // 清空混合模式的设置
        paint.setXfermode(null);
        // 还原图层
        canvas.restoreToCount(layerId);
    }
}
