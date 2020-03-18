package com.example.chapter01.part5_canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.chapter01.R;

import static android.media.MediaRecorder.MetricsConstants.HEIGHT;

/**
 * 剪切动画（启舰实现的）
 * Canvas.clipRegion 已经被移除了，看不到了。
 * 思路：
 * 1，使用路径，添加多个矩形；
 * 2，使用 Canvas.clipPath() 方法裁剪出路径包含的区域；
 * 3，在裁剪出的画布上，绘制图片，做铺满处理；
 * 4，执行动画。
 * @author wangzhichao
 * @since 20-3-18
 */
public class ClipPathAnimView extends View {

    private Bitmap bitmap;
    private static final int CLIP_HEIGHT = 30;
    private int clipWidth = 0;
    private Path path;
    private RectF rectF;
    private Paint paint;
    private Matrix matrix;

    public ClipPathAnimView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.scenery);
        path = new Path();
        rectF = new RectF();
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);
        paint.setColor(Color.RED);
        matrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 获取控件的宽高
        int width = getWidth();
        int height = getHeight();
        path.reset();
        canvas.save();
        int i = 0;
        // 构建 Path
        while (i * CLIP_HEIGHT <= height) {//计算 clip 的区域
            if (i % 2 == 0) {
                rectF.set(0, i * CLIP_HEIGHT, clipWidth, (i + 1) * CLIP_HEIGHT);
            } else {
                rectF.set(width - clipWidth, i * CLIP_HEIGHT, width, (i + 1) * CLIP_HEIGHT);
            }
            path.addRect(rectF, Path.Direction.CCW);
            i++;
        }
        // 裁剪出新的画布,显示出来图片的区域就是裁剪区域
        canvas.clipPath(path);
        // 在裁剪出的画布上绘制图片，这里做了铺满控件的处理
        matrix.setScale(width * 1f / bitmap.getWidth(),
                getHeight() * 1f / bitmap.getHeight());
        canvas.drawBitmap(bitmap, matrix, paint);
        canvas.restore();
        // 执行动画
        if (clipWidth > width) {
//            canvas.drawPath(path, paint);
            return;
        }
        clipWidth += 5;
        invalidate();
    }
}
