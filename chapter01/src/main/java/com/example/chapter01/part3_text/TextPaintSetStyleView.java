package com.example.chapter01.part3_text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Paint填充样式（setStyle）对文字的影响
 * 可以看到：
 * Paint.Style.FILL 是实心填充的；
 * Paint.Style.STROKE 是描边的，
 * Paint.Style.FILL_AND_STROKE 也是实心填充的，但是比 FILL 要大半个 strokeWidth，相当于前面两个的叠加。
 *
 * @author wangzhichao
 * @since 20-3-11
 */
public class TextPaintSetStyleView extends View {
    public TextPaintSetStyleView(Context context) {
        super(context);
    }

    public TextPaintSetStyleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TextPaintSetStyleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5f);
        paint.setTextSize(100f);

        String text = "床前明月光";
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(text + "-FILL", 16, 100, paint);

        paint.setStyle(Paint.Style.STROKE);
        canvas.drawText(text + "-STROKE", 16, 300, paint);

        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawText(text + "-FILL_AND_STROKE", 16, 500, paint);


        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(text + "-叠加 FILL 和 STROKE", 16, 700, paint);

        paint.setStyle(Paint.Style.STROKE);
        canvas.drawText(text + "-叠加 FILL 和 STROKE", 16, 700, paint);
    }
}
