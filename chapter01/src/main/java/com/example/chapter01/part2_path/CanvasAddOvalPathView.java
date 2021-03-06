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
 * 演示 Path 的 addOval 方法
 * public void addOval(RectF oval, Direction dir)
 * public void addOval(float left, float top, float right, float bottom, Direction dir)
 *
 * @author wangzhichao
 * @since 20-3-8
 */
public class CanvasAddOvalPathView extends View {
    public CanvasAddOvalPathView(Context context) {
        super(context);
    }

    public CanvasAddOvalPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasAddOvalPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        RectF rectF = new RectF(100, 100, 400, 300);
        ccwPath.addOval(rectF, Path.Direction.CCW);
        canvas.drawPath(ccwPath, paint);

        Path cwPath = new Path();
        RectF rectF1 = new RectF(500, 100, 800, 300);
        cwPath.addOval(rectF1, Path.Direction.CW);
        canvas.drawPath(cwPath, paint);

        // 生成的方向决定了依据路径进行排版的文字方向
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);

        ccwPath = new Path();
        rectF = new RectF(100, 500, 400, 700);
        ccwPath.addOval(rectF, Path.Direction.CCW);
        canvas.drawPath(ccwPath, paint);

        cwPath = new Path();
        rectF1 = new RectF(500, 500, 800, 700);
        cwPath.addOval(rectF1, Path.Direction.CW);
        canvas.drawPath(cwPath, paint);

        paint.setTextSize(40f);
        paint.setStrokeWidth(3f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(0x44ff0000);
        String text = "好好学习，天天向上";
        // 起点是在 3 点钟位置.
        canvas.drawTextOnPath(text, ccwPath, 0, 18, paint);
        canvas.drawTextOnPath(text, cwPath, 50, 9, paint);
    }
}
