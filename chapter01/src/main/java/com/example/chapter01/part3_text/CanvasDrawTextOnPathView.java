package com.example.chapter01.part3_text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Canvas 绘制文字：沿路径绘制
 * // 参数一：String text 要绘制的文本，
 * // 参数二：Path path 作为文字沿着为基线的路径(这个是重点)
 * // 参数三：float hOffset 沿着路径添加到文本起始位置的距离, 沿路径方向是正方向
 * // 参数四：float vOffset 用来定位文本的在路径之上（-）或路径之下（+）的距离，也就是在基线之下为正，在基线之上为负。
 * public void drawTextOnPath(@NonNull String text, @NonNull Path path, float hOffset,
 *             float vOffset, @NonNull Paint paint)
 * // 这个方法可以指定文本中的起始点以及绘制字符的个数。
 * public void drawTextOnPath(@NonNull char[] text, int index, int count, @NonNull Path path,
 *             float hOffset, float vOffset, @NonNull Paint paint)
 *
 * @author wangzhichao
 * @since 20-3-11
 */
public class CanvasDrawTextOnPathView extends View {
    public CanvasDrawTextOnPathView(Context context) {
        super(context);
    }

    public CanvasDrawTextOnPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasDrawTextOnPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(60f);
        paint.setStrokeWidth(5f);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);

        Path path = new Path();
        path.addCircle(300, 300, 200, Path.Direction.CCW);
        canvas.drawPath(path, paint);

        Path path2 = new Path();
        path2.addCircle(800, 300, 200, Path.Direction.CCW);
        canvas.drawPath(path2, paint);

        String text = "床前明月光，疑是地上霜。";
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(0f);

        canvas.drawTextOnPath(text, path, 0,0, paint);
        // 沿路径方向是 hOffset 的正方向；
        // vOffset：在路径之下是正方向，在路径之下是负方向。
        canvas.drawTextOnPath(text, path2, 80, 30, paint);


        paint.setStrokeWidth(5f);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);

        Path path3 = new Path();
        path3.addCircle(300, 800, 200, Path.Direction.CW);
        canvas.drawPath(path3, paint);

        Path path4 = new Path();
        path4.addCircle(800, 800, 200, Path.Direction.CW);
        canvas.drawPath(path4, paint);

        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(0f);

        canvas.drawTextOnPath(text, path3, 0,0, paint);
        // 沿路径方向是 hOffset 的正方向；
        // vOffset：在路径之下是正方向，在路径之下是负方向。
        canvas.drawTextOnPath(text, path4, 80, 30, paint);
    }
}
