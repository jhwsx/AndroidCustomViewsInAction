package com.example.chapter01.part3_text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Paint 的 setTextAlign 对文字的影响
 * 设置的是所要绘制的字符串与起始点的相对位置.
 *
 * 需要注意的是文字对齐相对的是绘制文字的起始点
 * Paint.Align.LEFT：表示文字整体在起始点的右边，可以理解为文字整体和起始点左对齐
 * Paint.Align.CENTER：表示文字整体水平方向的中心，就是起始点
 * Paint.Align.RIGHT：表示文字整体在起始点的左边。可以理解为文字整体和起始点右对齐
 *
 * @author wangzhichao
 * @since 20-3-11
 */
public class TextPaintSetTextAlignView extends View {
    public TextPaintSetTextAlignView(Context context) {
        super(context);
    }

    public TextPaintSetTextAlignView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TextPaintSetTextAlignView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(100f);
        paint.setStrokeWidth(5f);

        String text = "窗前明月光";

        float startX = getWidth() * 0.5f;
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(text, startX, 200, paint);

        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(text, startX, 400, paint);

        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(text, startX, 600, paint);

        paint.setColor(Color.GREEN);
        canvas.drawLine(startX, 0, startX, 800, paint);
    }
}
