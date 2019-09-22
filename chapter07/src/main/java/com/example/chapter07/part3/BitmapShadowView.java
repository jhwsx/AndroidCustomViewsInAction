package com.example.chapter07.part3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.chapter07.R;

/**
 * 封装一个可以给图片添加阴影的控件
 *
 * @author wangzhichao
 * @date 2019/09/20
 */
public class BitmapShadowView extends View {
    private Bitmap bitmap;
    private Bitmap shadowBitmap;
    private int shadowDx;
    private int shadowDy;
    private int shadowColor;
    private int shadowRadius;
    private BlurMaskFilter blurMaskFilter;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public BitmapShadowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BitmapShadowView);
        int srcId = ta.getResourceId(R.styleable.BitmapShadowView_bsv_src, -1);
        if (srcId == -1) {
            throw new IllegalArgumentException("Unknown src");
        }
        shadowDx = ta.getInt(R.styleable.BitmapShadowView_bsv_shadowDx, 0);
        shadowDy = ta.getInt(R.styleable.BitmapShadowView_bsv_shadowDy, 0);
        shadowColor = ta.getColor(R.styleable.BitmapShadowView_bsv_shadowColor, Color.BLACK);
        shadowRadius = ta.getInt(R.styleable.BitmapShadowView_bsv_shadowRadius, 0);
        ta.recycle();

        bitmap = BitmapFactory.decodeResource(getResources(), srcId);
        shadowBitmap = bitmap.extractAlpha();
        blurMaskFilter = new BlurMaskFilter(shadowRadius, BlurMaskFilter.Blur.NORMAL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int defaultHeight = bitmap.getHeight();
        int defaultWidth = bitmap.getWidth();
        int measuredWidth = widthMode == MeasureSpec.EXACTLY ? widthSize : defaultWidth;
        int measuredHeight = heightMode == MeasureSpec.EXACTLY ? heightSize : defaultHeight;
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth() - shadowDx - shadowRadius;
        int height = width * getHeight() / getWidth();

        paint.setColor(shadowColor);
        paint.setMaskFilter(blurMaskFilter);
        canvas.drawBitmap(shadowBitmap, null, new Rect(shadowDx, shadowDy, width, height), paint);

        paint.setMaskFilter(null);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setColor(Color.RED);
//        canvas.drawRect(new Rect(shadowDx, shadowDy, width, height), paint);
//        paint.setColor(Color.GREEN);
//        canvas.drawRect(new Rect(0, 0, width, height), paint);
//        paint.setColor(Color.YELLOW);
//        canvas.drawRect(0,0,getWidth(), getHeight(),paint);
        canvas.drawBitmap(bitmap, null, new Rect(0, 0, width, height), paint);
    }
}
