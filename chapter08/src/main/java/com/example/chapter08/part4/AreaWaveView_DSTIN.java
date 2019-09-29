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
 * 区域波纹效果：在不规则区域中显示波纹效果
 *
 * @author wangzhichao
 * @date 2019/09/27
 */
public class AreaWaveView_DSTIN extends View {
    private Bitmap srcBmp;
    private Bitmap dstBmp;
    private Path path = new Path();
    private Paint paint = new Paint();
    private float waveLength;
    private float halfWaveLength;
    private float height;
    private float amplitude;

    public AreaWaveView_DSTIN(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        // 不规则区域用作源图像
        srcBmp = BitmapFactory.decodeResource(getResources(), R.drawable.text_shade);
        dstBmp = Bitmap.createBitmap(srcBmp.getWidth(), srcBmp.getHeight(), Bitmap.Config.ARGB_8888);

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);

        waveLength = srcBmp.getWidth() * 1f / 3;
        halfWaveLength = waveLength / 2;
        height = 0.5f * srcBmp.getHeight();
        amplitude = 50;

        startAnimation();
    }

    private void startAnimation() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, waveLength);
        valueAnimator.setDuration(2000L);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float curr = (float) animation.getAnimatedValue();
                updateWavePath(curr);
            }
        });
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.start();
    }

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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        // 先清空 dstBmp 上的图像，再绘制 path
        Canvas c = new Canvas(dstBmp);
        c.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR);
        c.drawPath(path, paint);

        // 绘制src图像，才能显示文字全貌
        canvas.drawBitmap(srcBmp, 0, 0, paint);
        // 离屏缓冲
        // 新建图层
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), paint, Canvas.ALL_SAVE_FLAG);
        // 这里是核心代码
        // 绘制目标图像，在下层，水波纹图像
        canvas.drawBitmap(dstBmp, 0, 0, paint);
        // 给画笔添加混合模式DST_IN
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        // 绘制源图像，在上层，不规则区域图像
        canvas.drawBitmap(srcBmp, 0, 0, paint);
        // 清除画笔的混合模式
        paint.setXfermode(null);
        // 还原图层
        canvas.restoreToCount(layerId);
    }

    private void updateWavePath(float progress) {
        path.reset();
        path.moveTo(-waveLength + progress, height);
        for (float i = -waveLength + progress; i <= getWidth() + waveLength; i += waveLength) {
            // 这里要注意的是相对的是起始点后者是上一个控制点。
            path.rQuadTo(halfWaveLength / 2, amplitude, halfWaveLength, 0);
            path.rQuadTo(halfWaveLength / 2, -amplitude, halfWaveLength, 0);
        }
        path.lineTo(getWidth(), getHeight());
        path.lineTo(0, getHeight());
        path.close();
        invalidate();
    }
}
