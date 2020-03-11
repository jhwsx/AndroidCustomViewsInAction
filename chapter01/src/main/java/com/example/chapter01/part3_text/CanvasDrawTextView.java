package com.example.chapter01.part3_text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;

/**
 * Canvas drawText() 绘制文本
 * // 指定String类型的文本，以及起始点坐标
 * public void drawText(@NonNull String text, float x, float y, @NonNull Paint paint)
 * // 指定String类型的文本，起始点坐标，以及从文本中哪一个索引的字符开始绘制，到哪一个索引的字符为止，是左闭右开区间
 * public void drawText(@NonNull String text, int start, int end, float x, float y,
 *
 * @author wangzhichao
 * @NonNull Paint paint)
 * // 指定 CharSequence 类型的文本， 起始点坐标，以及从文本中哪一个索引的字符开始绘制，到哪一个索引的字符为止，是左闭右开区间
 * public void drawText(@NonNull CharSequence text, int start, int end, float x, float y,
 * @NonNull Paint paint)
 * // 指定字符数组类型的文本, 开始绘制的字符索引 是 index，count 是从指定索引开始绘制几个字符
 * public void drawText(@NonNull char[] text, int index, int count, float x, float y,
 * @NonNull Paint paint)
 * @since 20-3-11
 */
public class CanvasDrawTextView extends View {
    public CanvasDrawTextView(Context context) {
        super(context);
    }

    public CanvasDrawTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasDrawTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5f);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(60f);

        String text = "床前明月光";
        // 床前明月光
        canvas.drawText(text, 16, 100, paint);
        // 明月，start = 2， end = 4
        canvas.drawText(text, 2, 4, 16, 200, paint);
        //
        SpannableString ss = new SpannableString(text);
        canvas.drawText(ss, 0, ss.length(), 16, 300, paint);
        char[] chars = text.toCharArray();
        canvas.drawText(chars, 0, 2, 16, 400, paint);
    }
}
