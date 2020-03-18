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
 * 演示 Path 的 addRoundRect 方法
 * // 四个圆角一起设置的方法
 * public void addRoundRect(RectF rect, float rx, float ry, Direction dir)
 * public void addRoundRect(float left, float top, float right, float bottom, float rx, float ry,
 * Direction dir)
 * // 四个圆角可以分别设置的方法
 * public void addRoundRect(RectF rect, float[] radii, Direction dir)
 * public void addRoundRect(float left, float top, float right, float bottom, float[] radii,
 * Direction dir)
 *
 * @author wangzhichao
 * @since 20-3-8
 */
public class CanvasAddRoundRectPathView extends View {
    public CanvasAddRoundRectPathView(Context context) {
        super(context);
    }

    public CanvasAddRoundRectPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasAddRoundRectPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        ccwPath.addRoundRect(rectF, 40, 20, Path.Direction.CCW);
        canvas.drawPath(ccwPath, paint);

        Path cwPath = new Path();
        RectF rectF1 = new RectF(500, 100, 800, 300);
        float[] radii = {
                0, 0,
                0, 0,
                0, 0,
                100, 20,
        };
        cwPath.addRoundRect(rectF1, radii, Path.Direction.CW);
        canvas.drawPath(cwPath, paint);

        // 生成的方向决定了依据路径进行排版的文字方向
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);

        ccwPath = new Path();
        rectF = new RectF(100, 500, 400, 700);
        ccwPath.addRoundRect(rectF, 40,20,Path.Direction.CCW);
        canvas.drawPath(ccwPath, paint);

        cwPath = new Path();
        rectF1 = new RectF(500, 500, 800, 700);
        cwPath.addRoundRect(rectF1, radii, Path.Direction.CW);
        canvas.drawPath(cwPath, paint);

        paint.setTextSize(40f);
        paint.setStrokeWidth(3f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(0x44ff0000);
        String text = "好好学习，天天向上";
        canvas.drawTextOnPath(text, ccwPath, 0, 18, paint);
        // 这个顺时针的带圆角的文字起点有些不同：
        // 如果有圆角，那么起点就是左下角；
        // 如果没有圆角，那么起点就是左上角。
        canvas.drawTextOnPath(text, cwPath, 0, 9, paint);
    }
}
