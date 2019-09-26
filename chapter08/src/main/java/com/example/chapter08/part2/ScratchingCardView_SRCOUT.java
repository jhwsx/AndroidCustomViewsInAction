package com.example.chapter08.part2;

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
 * 刮刮卡效果
 * 思路：
 * 1，把卡绘制在最下面；
 * 2，构建目标图像：绘制一个和卡一样大小的透明板，把手指轨迹绘制以纯色绘制在这个透明板上；
 * 3，给画笔设置混合模式：SRC_OUT
 * 4，绘制源图像，就是刮层
 *
 * @author wangzhichao
 * @date 2019/09/26
 */
public class ScratchingCardView_SRCOUT extends View {
    private Bitmap cardBmp;
    private Bitmap srcBmp;
    private Bitmap dstBmp;
    private Path path;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public ScratchingCardView_SRCOUT(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        cardBmp = BitmapFactory.decodeResource(getResources(), R.drawable.guaguaka_text);
        srcBmp = BitmapFactory.decodeResource(getResources(), R.drawable.guaguaka_pic);
        dstBmp = Bitmap.createBitmap(srcBmp.getWidth(), srcBmp.getHeight(), Bitmap.Config.ARGB_8888);

        path = new Path();

        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(40);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制卡
        canvas.drawBitmap(cardBmp, 0, 0, paint);

        Canvas c = new Canvas(dstBmp);
        c.drawPath(path, paint);
        // 离屏缓冲
        // 新建图层
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), paint, Canvas.ALL_SAVE_FLAG);
        // 这里是核心代码
        // 绘制目标图像，在下层，也就是手指轨迹层
        canvas.drawBitmap(dstBmp, 0, 0, paint);
        // 给画笔设置混合模式SRC_OUT
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        // 绘制源图像，在上层，也就是刮刮层
        canvas.drawBitmap(srcBmp, 0, 0, paint);
        // 清除画笔的混合模式
        paint.setXfermode(null);
        // 还原图层
        canvas.restoreToCount(layerId);
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
                float endX = (lastX + x) / 2;
                float endY = (lastY + y) / 2;
                path.quadTo(controlX, controlY, endX, endY);
                invalidate();
                lastX = x;
                lastY = y;
            default:
                return super.onTouchEvent(event);
        }
    }
}
