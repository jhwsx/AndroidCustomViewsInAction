package com.example.chapter08.part4;

import android.animation.ValueAnimator;
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
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.example.chapter08.R;

/**
 * @author wangzhichao
 * @date 2019/09/29
 */
public class HeartMapView_DSTIN extends View {
    private Bitmap dstBmp;
    private Bitmap srcBmp;
    private Paint paint;
    private Path path = new Path();
    public HeartMapView_DSTIN(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        dstBmp = BitmapFactory.decodeResource(getResources(), R.drawable.heartmap);
        srcBmp = Bitmap.createBitmap(dstBmp.getWidth(), dstBmp.getHeight(), Bitmap.Config.ARGB_8888);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);

        startAnim();
    }

    private void startAnim() {
        ValueAnimator va = ValueAnimator.ofFloat(0, 1);
        va.setDuration(3000L);
        va.setInterpolator(new LinearInterpolator());
        va.setRepeatMode(ValueAnimator.RESTART);
        va.setRepeatCount(ValueAnimator.INFINITE);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float curr = (float) animation.getAnimatedValue();
                updatePath(curr);
            }
        });
        va.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int defaultWidth = dstBmp.getWidth();
        int defaultHeight = dstBmp.getHeight();
        int width = widthMode == MeasureSpec.EXACTLY ? widthSize : defaultWidth;
        int height = heightMode == MeasureSpec.EXACTLY ? heightSize : defaultHeight;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Canvas c = new Canvas(srcBmp);
        c.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR); // 这里写什么颜色都不要紧
        c.drawPath(path, paint);
        // 离屏缓冲
        // 新建图层
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        // 这里是核心代码
        // 绘制目标图像，在下层，心电图图像
        canvas.drawBitmap(dstBmp, 0, 0, paint);
        // 给画笔设置混合模式DST_IN
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        // 绘制源图像，在上层，是透明+不透明图像
        canvas.drawBitmap(srcBmp, 0, 0, paint);
        // 清空画笔的混合模式
        paint.setXfermode(null);
        // 还原图层
        canvas.restoreToCount(layerId);
    }

    private void updatePath(float progress) {
        path.reset();
        float left = (1 - progress) * getWidth();
        path.moveTo(getWidth(), 0);
        path.lineTo(getWidth(), getHeight());
        path.lineTo(left, getHeight());
        path.lineTo(left, 0);
        path.close();
        invalidate();
    }

}
