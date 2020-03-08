package com.example.chapter01.part2_path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Path 的填充模式
 * Path.FillType.WINDING
 * Path.FillType.EVEN_ODD
 * Path.FillType.INVERSE_WINDING
 * Path.FillType.INVERSE_EVEN_ODD
 * 查看；https://hencoder.com/ui-1-1/
 * @author wangzhichao
 * @since 20-3-8
 */
public class CanvasFillTypePathView extends View {
    public CanvasFillTypePathView(Context context) {
        super(context);
    }

    public CanvasFillTypePathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasFillTypePathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);

        Path path = new Path();
        path.addCircle(200, 200, 150, Path.Direction.CW);
        path.addCircle(400, 200, 150, Path.Direction.CW);
        path.setFillType(Path.FillType.WINDING);
        canvas.drawPath(path, paint);
        paint.setStyle(Paint.Style.FILL);

        path = new Path();
        path.addCircle(800, 200, 150, Path.Direction.CW);
        path.addCircle(1000, 200, 150, Path.Direction.CW);
        path.setFillType(Path.FillType.EVEN_ODD);
        canvas.drawPath(path, paint);

        path = new Path();
        path.addCircle(200, 600, 150, Path.Direction.CW);
        path.addCircle(400, 600, 150, Path.Direction.CCW);
        path.setFillType(Path.FillType.WINDING);
        canvas.drawPath(path, paint);
        paint.setStyle(Paint.Style.FILL);

        path = new Path();
        path.addCircle(800, 600, 150, Path.Direction.CW);
        path.addCircle(1000, 600, 150, Path.Direction.CCW);
        path.setFillType(Path.FillType.EVEN_ODD);
        canvas.drawPath(path, paint);
        canvas.drawPath(path, paint);

        path = new Path();
        path.addCircle(300, 1000, 150, Path.Direction.CW);
        path.addCircle(300, 1000, 100, Path.Direction.CW);
        path.setFillType(Path.FillType.WINDING);
        canvas.drawPath(path, paint);
        paint.setStyle(Paint.Style.FILL);

        path = new Path();
        path.addCircle(900, 1000, 150, Path.Direction.CW);
        path.addCircle(900, 1000, 100, Path.Direction.CW);
        path.setFillType(Path.FillType.EVEN_ODD);
        canvas.drawPath(path, paint);

        path = new Path();
        path.addCircle(300, 1400, 150, Path.Direction.CW);
        path.addCircle(300, 1400, 100, Path.Direction.CCW);
        path.setFillType(Path.FillType.WINDING);
        canvas.drawPath(path, paint);
        paint.setStyle(Paint.Style.FILL);

        path = new Path();
        path.addCircle(900, 1400, 150, Path.Direction.CW);
        path.addCircle(900, 1400, 100, Path.Direction.CCW);
        path.setFillType(Path.FillType.EVEN_ODD);
        canvas.drawPath(path, paint);
    }
}
