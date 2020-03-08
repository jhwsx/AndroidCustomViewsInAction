package com.example.chapter01.part2_path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 演示 Path 的 addCircle 方法
 * public void addCircle(float x, float y, float radius, Direction dir)
 *
 * @author wangzhichao
 * @since 20-3-8
 */
public class CanvasAddCirclePathView extends View {
    public CanvasAddCirclePathView(Context context) {
        super(context);
    }

    public CanvasAddCirclePathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasAddCirclePathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);

        Path ccwPath = new Path();
        ccwPath.addCircle(300, 300, 100, Path.Direction.CCW);
        canvas.drawPath(ccwPath, paint);

        Path cwPath = new Path();
        cwPath.addCircle(600, 300, 100, Path.Direction.CW);
        canvas.drawPath(cwPath, paint);

        ccwPath = new Path();
        ccwPath.addCircle(300, 600, 100, Path.Direction.CCW);
        canvas.drawPath(ccwPath, paint);

        cwPath = new Path();
        cwPath.addCircle(600, 600, 100, Path.Direction.CW);
        canvas.drawPath(cwPath, paint);


        paint.setTextSize(40f);
        paint.setStrokeWidth(3f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(0x44ff0000);

        String text = "好好学习，天天向上";
        canvas.drawTextOnPath(text, cwPath, 0, 9, paint);
        canvas.drawTextOnPath(text, ccwPath, 0, 9, paint);
    }
}
