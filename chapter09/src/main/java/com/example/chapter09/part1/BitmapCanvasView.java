package com.example.chapter09.part1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * 使用 Bitmap 创建 Canvas
 *
 * @author wzc
 * @date 2019/10/1
 */
public class BitmapCanvasView extends View {
    private Bitmap bitmap;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Canvas c;

    public BitmapCanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        c = new Canvas(bitmap);
        paint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setTextSize(50);
        c.drawText("欢迎光临", 0, 100, paint);
        // 不写下面这行，是不会画到 View 上的。
        canvas.drawBitmap(bitmap, 0, 0, paint);
    }
}
