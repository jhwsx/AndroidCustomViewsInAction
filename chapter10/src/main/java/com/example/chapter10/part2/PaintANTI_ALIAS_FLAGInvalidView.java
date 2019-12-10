package com.example.chapter10.part2;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author wangzhichao
 * @since 2019/12/10
 */
public class PaintANTI_ALIAS_FLAGInvalidView extends View {
    private Paint paint1 = new Paint();
    private Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Bitmap bitmap;
    private final Bitmap bitmap2;

    public PaintANTI_ALIAS_FLAGInvalidView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint1.setColor(Color.RED);
        paint1.setStyle(Paint.Style.FILL);
        paint2.setColor(Color.RED);
        paint2.setStyle(Paint.Style.FILL);
        bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888);
        bitmap2 = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("paint_antialiasflag", "onDraw");
        canvas.drawCircle(250,300,200, paint1);

        canvas.drawCircle(700,300,200, paint2);

        Canvas c = new Canvas(bitmap);
        c.drawCircle(200,200, 200, paint1);
        canvas.drawBitmap(bitmap, 50, 700, paint1);

        Canvas c2 = new Canvas(bitmap2);
        c2.drawCircle(200,200, 200, paint2);

        canvas.drawBitmap(bitmap2, 500, 700, paint2);
    }


}

/**
 * 总结：
 * 1，该例子在重走多次调用 onDraw 方法后，会导致 bitmap2 锯齿化。
 */
