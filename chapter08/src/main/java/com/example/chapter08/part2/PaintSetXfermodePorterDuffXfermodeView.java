package com.example.chapter08.part2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author wangzhichao
 * @date 2019/09/22
 */
public class PaintSetXfermodePorterDuffXfermodeView extends View {
    private int width = 400;
    private int height = 400;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap dstBmp;
    private Bitmap srcBmp;

    public PaintSetXfermodePorterDuffXfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        dstBmp = makeDst(width, height);
        srcBmp = makeSrc(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 使用离屏缓存
        // 新建图层
        int layerId = canvas.saveLayer(0, 0, width * 2, height * 2, paint, Canvas.ALL_SAVE_FLAG);
        // 这里是核心绘制代码
        // 绘制目标图像，圆形的，在底部
        canvas.drawBitmap(dstBmp, 0,0, paint);
        // 设置图像混合模式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        // 绘制源图像，方形的，在上面
        canvas.drawBitmap(srcBmp, width / 2, height / 2, paint);
        paint.setXfermode(null);
        // 还原图层
        canvas.restoreToCount(layerId);
    }

    private Bitmap makeDst(int w, int h) {
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(0xFFFFCC44);
        canvas.drawOval(new RectF(0, 0, w, h), paint);
        return bitmap;
    }

    private Bitmap makeSrc(int w, int h) {
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(0xFF66AAFF);
        canvas.drawRect(new RectF(0, 0, w, h), paint);
        return bitmap;
    }
}
