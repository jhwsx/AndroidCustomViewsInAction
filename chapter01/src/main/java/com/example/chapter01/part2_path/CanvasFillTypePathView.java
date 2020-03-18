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
        path.addCircle(200, 200, 120, Path.Direction.CW);
        path.addCircle(400, 200, 120, Path.Direction.CW);
        path.setFillType(Path.FillType.WINDING);
        canvas.drawPath(path, paint);
        paint.setStyle(Paint.Style.FILL);

        path = new Path();
        path.addCircle(700, 200, 120, Path.Direction.CW);
        path.addCircle(900, 200, 120, Path.Direction.CW);
        path.setFillType(Path.FillType.EVEN_ODD);
        canvas.drawPath(path, paint);

        path = new Path();
        path.addCircle(200, 500, 120, Path.Direction.CW);
        path.addCircle(400, 500, 120, Path.Direction.CCW);
        path.setFillType(Path.FillType.WINDING);
        canvas.drawPath(path, paint);
        paint.setStyle(Paint.Style.FILL);

        path = new Path();
        path.addCircle(700, 500, 120, Path.Direction.CW);
        path.addCircle(900, 500, 120, Path.Direction.CCW);
        path.setFillType(Path.FillType.EVEN_ODD);
        canvas.drawPath(path, paint);
        canvas.drawPath(path, paint);

        path = new Path();
        path.addCircle(300, 800, 120, Path.Direction.CW);
        path.addCircle(300, 800, 100, Path.Direction.CW);
        path.setFillType(Path.FillType.WINDING);
        canvas.drawPath(path, paint);
        paint.setStyle(Paint.Style.FILL);

        path = new Path();
        path.addCircle(900, 800, 120, Path.Direction.CW);
        path.addCircle(900, 800, 100, Path.Direction.CW);
        path.setFillType(Path.FillType.EVEN_ODD);
        canvas.drawPath(path, paint);

        path = new Path();
        path.addCircle(300, 1200, 120, Path.Direction.CW);
        path.addCircle(300, 1200, 100, Path.Direction.CCW);
        path.setFillType(Path.FillType.WINDING);
        canvas.drawPath(path, paint);
        paint.setStyle(Paint.Style.FILL);

        path = new Path();
        path.addCircle(900, 1200, 120, Path.Direction.CW);
        path.addCircle(900, 1200, 100, Path.Direction.CCW);
        path.setFillType(Path.FillType.EVEN_ODD);
        canvas.drawPath(path, paint);
    }
}
