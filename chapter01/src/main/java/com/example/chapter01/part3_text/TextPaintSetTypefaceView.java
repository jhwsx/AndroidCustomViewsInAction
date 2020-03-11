package com.example.chapter01.part3_text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Paint 的 setTypeface 方法，设置字体样式
 * setTypeface(Typeface typeface) 传入 null 就是清除之前的字体样式了。
 *
 * @author wangzhichao
 * @since 20-3-11
 */
public class TextPaintSetTypefaceView extends View {
    public TextPaintSetTypefaceView(Context context) {
        super(context);
    }

    public TextPaintSetTypefaceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TextPaintSetTypefaceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setTextSize(80f);


        String text = "床前明月光, How is it going?";
        canvas.drawText(text, 16, 100, paint);

        paint.setTypeface(Typeface.SANS_SERIF);
        canvas.drawText(text, 16, 200, paint);

        paint.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        canvas.drawText(text, 16, 300, paint);

        paint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/ff.ttf"));
        canvas.drawText(text, 16, 400, paint);
    }
}
