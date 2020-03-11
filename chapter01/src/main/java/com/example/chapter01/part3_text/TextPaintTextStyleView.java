package com.example.chapter01.part3_text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Paint 的设置文字样式的方法演示
 *
 * @author wangzhichao
 * @since 20-3-11
 */
public class TextPaintTextStyleView extends View {
    public TextPaintTextStyleView(Context context) {
        super(context);
    }

    public TextPaintTextStyleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TextPaintTextStyleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(60f);
        paint.setStrokeWidth(5f);

        String text = "窗前明月光";
        canvas.drawText(text, 16, 100, paint);

        // 设置粗体
        paint.setFakeBoldText(true);
        canvas.drawText(text, 16, 200, paint);
        paint.setFakeBoldText(false);

        // 设置中间删除线
        paint.setStrikeThruText(true);
        canvas.drawText(text, 16, 300, paint);
        paint.setStrikeThruText(false);

        // 设置下划线
        paint.setUnderlineText(true);
        canvas.drawText(text, 16, 400, paint);
        paint.setUnderlineText(false);

        // 设置文字倾斜度
        // 注意：倾斜的时候是底部不动，顶部向右倾斜，或向左倾斜。
        paint.setTextSkewX(0.25f); // 负值表示向右倾斜
        canvas.drawText(text, 16, 500, paint);
        paint.setTextSkewX(-0.25f); // 正值表示向右倾斜
        canvas.drawText(text, 16, 600, paint);
        paint.setTextSkewX(0f);

        // 设置文字水平拉伸
        paint.setTextScaleX(2); // 拉伸为原来宽度的 2 倍
        canvas.drawText(text, 16, 700, paint);
        paint.setTextScaleX(0.5F);
        canvas.drawText(text, 16, 800, paint);
    }
}
