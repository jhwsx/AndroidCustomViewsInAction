package com.example.chapter06.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 获取绘制字符串所占区域的高度，宽度和最小矩形。
 * @author wzc
 * @date 2019/9/10
 */
public class TextAreaView extends View {
    public TextAreaView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int baseLineX = 0;
        int baseLineY = 200;

        Paint paint = new Paint();
        paint.setTextSize(120);
        String text = "harvic\'s blog";
        canvas.drawText(text,baseLineX,baseLineY,paint);

        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        int top = baseLineY + fm.top;
        int bottom = baseLineY + fm.bottom;
        int height = bottom - top;
        Log.d("qijian", "所占区域的高度：" + height);
        int width = (int) paint.measureText(text);
        Log.d("qijian", "所占区域的宽度：" + width);
        int areaLeft = 0;
        int areaTop = top;
        int areaRight = areaLeft + width;
        int areaBottom = areaTop + height;
        Rect area = new Rect(areaLeft, areaTop, areaRight, areaBottom);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(area, paint);

        // 最小矩形
        Rect minRect = new Rect();
        // 注意：获取指定字符串所对应的的最小矩形，以（0,0）点所在位置为基线
        paint.getTextBounds(text, 0, text.length(), minRect);
        Log.e("qijian", "最小矩形：" + minRect.toShortString());

        Rect actualMinRect = new Rect();
        actualMinRect.left = minRect.left;
        actualMinRect.top = baseLineY + minRect.top;
        actualMinRect.right = minRect.right;
        actualMinRect.bottom = baseLineY + minRect.bottom;
        Log.e("qijian", "实际的最小矩形：" + actualMinRect.toShortString());
        paint.setColor(Color.GREEN);
        canvas.drawRect(actualMinRect, paint);
    }
}
