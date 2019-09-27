package com.example.chapter08.part3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.chapter08.R;

/**
 * @author wangzhichao
 * @date 2019/09/26
 */
public class EraserView_SRCOUT extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap srcBmp;
    private Bitmap dstBmp;
    private Bitmap eraserBmp;
    private Path path;

    public EraserView_SRCOUT(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        srcBmp = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
        dstBmp = Bitmap.createBitmap(srcBmp.getWidth(), srcBmp.getHeight(), Bitmap.Config.ARGB_8888);
        eraserBmp = BitmapFactory.decodeResource(getResources(), R.drawable.eraser);
        path = new Path();

        paint.setColor(Color.RED);
        paint.setStrokeWidth(40);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Canvas c = new Canvas(dstBmp);
        c.drawPath(path, paint);
        // 离屏缓冲
        // 新建图层
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), paint, Canvas.ALL_SAVE_FLAG);
        // 这里是核心代码
        // 绘制目标图像，在下层
        canvas.drawBitmap(dstBmp, 0, 0, paint);
        // 给画笔设置混合模式 SRCOUT
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        // 绘制源图像，在上层
        canvas.drawBitmap(srcBmp, 0, 0, paint);
        // 清除画笔的混合模式
        paint.setXfermode(null);
        // 还原图层
        canvas.restoreToCount(layerId);
        // 绘制橡皮擦
        canvas.drawBitmap(eraserBmp, lastX, lastY, paint);
    }

    private float lastX;
    private float lastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                path.moveTo(x, y);
                return true;
            case MotionEvent.ACTION_MOVE:
                float controlX = lastX;
                float controlY = lastY;
                float endX = (controlX + x) / 2;
                float endY = (controlY + y) / 2;
                path.quadTo(controlX, controlY, endX, endY);
                postInvalidate();
                lastX = x;
                lastY = y;
            default:
                return super.onTouchEvent(event);
        }
    }
}
