package com.example.chapter08.part4;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.example.chapter08.R;

/**
 * @author wangzhichao
 * @date 2019/09/29
 */
public class IrregularWaveView_DSTIN extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap dstBmp;
    private Bitmap srcBmp;

    public IrregularWaveView_DSTIN(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        dstBmp = BitmapFactory.decodeResource(getResources(), R.drawable.wave_bg);
        srcBmp = BitmapFactory.decodeResource(getResources(), R.drawable.circle_shape);
    }

    private int position;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int defaultWidth = srcBmp.getWidth();
        int defaultHeight = srcBmp.getHeight();
        int width = widthMode == MeasureSpec.EXACTLY ? widthSize : defaultWidth;
        int height = heightMode == MeasureSpec.EXACTLY ? heightSize : defaultHeight;
        setMeasuredDimension(width, height);

        startAnim();
    }

    private void startAnim() {
        ValueAnimator va = ValueAnimator.ofInt(0, dstBmp.getWidth());
        va.setDuration(3000L);
        va.setInterpolator(new LinearInterpolator());
        va.setRepeatMode(ValueAnimator.RESTART);
        va.setRepeatCount(ValueAnimator.INFINITE);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curr = (int) animation.getAnimatedValue();
                position = curr;
                invalidate();
            }
        });
        va.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // canvas.drawColor(Color.RED);
        // 先画上圆形
        canvas.drawBitmap(srcBmp, 0, 0, paint);

        // 离屏缓冲
        // 新建图层
        int layer = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        // 这里是核心代码
        // 绘制目标图像，在下层，不规则波纹
        // 这句代码是取出 dstBmp 的这个矩形 new Rect(position, 0, position + srcBmp.getWidth(), srcBmp.getHeight()) 位置，
        // 绘制在目标矩形 new RectF(0, 0, srcBmp.getWidth(), srcBmp.getHeight()) 的地方。
        canvas.drawBitmap(dstBmp, new Rect(position, 0, position + srcBmp.getWidth(), srcBmp.getHeight()),
                new RectF(0, 0, srcBmp.getWidth(), srcBmp.getHeight()), paint);
        // 给画笔设置混合模式DST_IN
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        // 绘制源图像，在上层，是圆
        canvas.drawBitmap(srcBmp, 0, 0, paint);
        // 清空画笔设置的混合模式
        paint.setXfermode(null);
        // 还原图层
        canvas.restoreToCount(layer);
    }
}
