package com.example.chapter01.part3_text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.view.View;

/**
 * Canvas drawPosText() 逐个指定文字位置
 * 这个方法已经废弃了
 *
 * public void drawPosText(@NonNull String text, @NonNull @Size(multiple = 2) float[] pos,
 *             @NonNull Paint paint)
 * @since 20-3-11
 */
public class CanvasDrawPosTextView extends View {
    public CanvasDrawPosTextView(Context context) {
        super(context);
    }

    public CanvasDrawPosTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasDrawPosTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        float[] pos = {
                16, 100,
                48, 200,
                80,300,
                112, 400,
                144, 500
        };
        canvas.drawPosText(text, pos, paint);
    }
}
