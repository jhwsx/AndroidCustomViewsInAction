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
 * 比较 Path 的 reset() rewind() 方法
 * reset()
 *  Clear any lines and curves from the path, making it empty.
 *  This does NOT change the fill-type setting.
 * rewind()
 *
 *   Rewinds the path: clears any lines and curves from the path but
 *   keeps the internal data structure for faster reuse.
 *
 * @author wangzhichao
 * @since 20-3-8
 */
public class CanvasResetRewindPathView extends View {
    public CanvasResetRewindPathView(Context context) {
        super(context);
    }

    public CanvasResetRewindPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasResetRewindPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);

        Path path = new Path();
        path.setFillType(Path.FillType.INVERSE_WINDING);
        path.reset();
        path.addCircle(300,300,150, Path.Direction.CCW);
        canvas.drawPath(path, paint);

        Path path2 = new Path();
        path2.setFillType(Path.FillType.INVERSE_WINDING);
        path2.rewind();
        path2.addCircle(300,800,150, Path.Direction.CCW);
        canvas.drawPath(path2, paint);


    }
}
