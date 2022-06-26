package com.example.chapter10.part1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.chapter10.R;
import com.example.chapter10.Utils;

/**
 * 放大镜效果
 * 思路：
 * 1，把图放大成和背景一样大，并绘制出来；
 * 2，跟手指移动的圆心；
 * 3，利用 ShapeDrawable setShader 画以上面的圆心以及一定半径的印章；
 * 4，把 ShapeDrawable 绘制到 canvas 上面。
 *
 * @author wangzhichao
 * @date 2019/10/13
 */
public class MagnifierView extends View {

    private final Bitmap sceneryBmp;
    private Bitmap bitmap;
    private int currX = -1;
    private int currY = -1;
    private ShapeDrawable drawable;
    private Matrix matrix = new Matrix();
    // 放大倍数
    private static final int FACTOR = 3;
    private static final int RADIUS = Utils.dp2px(40);

    public MagnifierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        sceneryBmp = BitmapFactory.decodeResource(getResources(), R.drawable.scenery);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (bitmap == null) {
            bitmap = Bitmap.createScaledBitmap(sceneryBmp, getWidth(), getHeight(), false);
            BitmapShader bitmapShader = new BitmapShader(Bitmap.createScaledBitmap(sceneryBmp, getWidth() * FACTOR,
                    getHeight() * FACTOR, true), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            drawable = new ShapeDrawable(new OvalShape());
            drawable.getPaint().setShader(bitmapShader);
            drawable.setBounds(0, 0, RADIUS * 2, RADIUS * 2);
        }
        canvas.drawBitmap(bitmap, 0, 0, null);
        drawable.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        // 这两行代码的作用很关键：移动 Shader 到要显示的地方。
        matrix.setTranslate(RADIUS - x * FACTOR, RADIUS - y * FACTOR);
        drawable.getPaint().getShader().setLocalMatrix(matrix);
        drawable.setBounds(x - RADIUS, y - RADIUS, x + RADIUS, y + RADIUS);
        invalidate();
        return true;
    }
}

/**
 * 总结：
 * 1，关键是记得把 Shader 移动到需要显示的位置，因为 Shader 默认是在 ShapeDrawable 的左上角开始绘制的。
 */
