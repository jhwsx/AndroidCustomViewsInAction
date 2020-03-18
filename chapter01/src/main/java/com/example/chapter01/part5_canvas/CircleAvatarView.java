package com.example.chapter01.part5_canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.chapter01.R;

/**
 * 圆形头像例子
 * 思路：
 * 1，在图片所在的位置先裁剪出一个圆形的画布；
 * 2，绘制图片，因为超出圆形画布区域的图片部分不会显示，因此就可以得到圆形头像。
 * 3, 因为要用到 clipPath，所以需要设置关闭硬件加速。
 *
 * @author wangzhichao
 * @since 20-3-18
 */
public class CircleAvatarView extends View {

    private Bitmap bitmap;
    private Paint paint = new Paint();
    private Path path;

    public CircleAvatarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.avator);
        path = new Path();
        float halfWidth = bitmap.getWidth() * 0.5f;
        float halfHeight = bitmap.getHeight() * 0.5F;
        float radius = Math.min(halfWidth, halfHeight);
        path.addCircle(halfWidth, halfHeight, radius, Path.Direction.CCW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.restore();
    }
}
