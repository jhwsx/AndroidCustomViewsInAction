package com.example.chapter01.part5_canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 裁剪画布
 * 在使用 clip 系列函数时，需要禁用硬件加速。
 * 特别注意，裁剪出来的就是最新画布的区域。
 *
 * @author wangzhichao
 * @since 20-3-17
 */
public class CanvasClipOperationView extends View {
    public CanvasClipOperationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
        // 裁剪出来的就是最新画布的区域。
//        canvas.clipRect(100, 100, 400, 300);
        Path path = new Path();
        path.addCircle(200, 200, 100, Path.Direction.CCW);
        canvas.clipPath(path);
        canvas.drawColor(Color.GREEN);
        // TODO 现在绘制文字，就是在裁剪出来的区域，怎么不显示了？
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(4f);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(20f);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("哈哈", 100, 100, paint);
    }
}
