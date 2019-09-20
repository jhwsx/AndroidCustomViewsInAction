package com.example.chaper07.part2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.chaper07.R;

/**
 * @author wangzhichao
 * @date 2019/09/20
 */
public class PaintSetMaskFilterView extends View {
    private final BlurMaskFilter innerBlurMaskFilter;
    private final BlurMaskFilter solidBlurMaskFilter;
    private final BlurMaskFilter normalBlurMaskFilter;
    private final BlurMaskFilter outerBlurMaskFilter;
    private final BlurMaskFilter textSolidBlurMaskFilter;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap pic;
    private Bitmap dog;

    public PaintSetMaskFilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 发光效果不支持硬件加速，所以下面这行代码必须要写上
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        /**
         * 参数一：模糊半径
         * 参数二：发光样式
         * BlurMaskFilter.Blur.INNER 内发光
         * BlurMaskFilter.Blur.SOLID 在边界内部填实，外发光
         * BlurMaskFilter.Blur.NORMAL 内外发光
         * BlurMaskFilter.Blur.OUTER 在边界内部什么都不填，外发光
         */
        innerBlurMaskFilter = new BlurMaskFilter(50, BlurMaskFilter.Blur.INNER);
        solidBlurMaskFilter = new BlurMaskFilter(50, BlurMaskFilter.Blur.SOLID);
        normalBlurMaskFilter = new BlurMaskFilter(50, BlurMaskFilter.Blur.NORMAL);
        outerBlurMaskFilter = new BlurMaskFilter(50, BlurMaskFilter.Blur.OUTER);
        textSolidBlurMaskFilter = new BlurMaskFilter(5, BlurMaskFilter.Blur.SOLID);

        pic = BitmapFactory.decodeResource(getResources(), R.drawable.pic);
        dog = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);

        paint.setMaskFilter(innerBlurMaskFilter);
        canvas.drawCircle(200, 200, 100, paint);

        paint.setMaskFilter(solidBlurMaskFilter);
        canvas.drawCircle(500, 200, 100, paint);

        paint.setMaskFilter(normalBlurMaskFilter);
        canvas.drawCircle(200, 500, 100, paint);

        paint.setMaskFilter(outerBlurMaskFilter);
        canvas.drawCircle(500, 500, 100, paint);

        paint.setMaskFilter(textSolidBlurMaskFilter);
        paint.setTextSize(40);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("我的边缘是什么颜色，就发什么光", 100, 700, paint);

        paint.setMaskFilter(solidBlurMaskFilter);
        RectF dst = new RectF(100, 800, 100 + pic.getWidth(), 800 + pic.getHeight());
        canvas.drawBitmap(pic, null, dst, paint);
        RectF dogDst = new RectF(dst.right + 100, 800, getWidth() - 100, dst.bottom);
        canvas.drawBitmap(dog, null, dogDst, paint);
    }
}

/**
 * 总结：
 * 1，边缘是什么颜色，就发什么光；
 */
