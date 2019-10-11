package com.example.chapter10.part1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.chapter10.Utils;

/**
 * @author wangzhichao
 * @date 2019/10/10
 */
public class ShapeView extends View {

    private final ShapeDrawable drawable;

    public ShapeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        drawable = new ShapeDrawable(new RectShape());
        // 这里所设置的矩形位置是指在所在控件中的位置，而不是以屏幕上左上角点为坐标的。
        drawable.setBounds(new Rect(Utils.dp2px(50), Utils.dp2px(50), Utils.dp2px(200), Utils.dp2px(100)));
        drawable.getPaint().setColor(Color.YELLOW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawable.draw(canvas);
    }
}

/**
 * 总结：
 * 1，为什么 drawable.getPaint().setColor(Color.YELLOW); 设置的黄色最终能作用到矩形上呢？
 * 可以查看 ShapeDrawable 的 draw 方法里的
 * final int count = canvas.save();
 * canvas.translate(r.left, r.top);
 * onDraw(state.mShape, canvas, paint);
 * canvas.restoreToCount(count);
 * 而 onDraw 方法如下：
 * protected void onDraw(Shape shape, Canvas canvas, Paint paint) {
 *     shape.draw(canvas, paint);
 * }
 * RectShape 的 draw 方法如下：
 * public void draw(Canvas canvas, Paint paint) {
 *     canvas.drawRect(mRect, paint);
 * }
 * 2，为什么 setBounds 方法所设置的矩形位置是指在所在控件中的位置，而不是以屏幕上左上角点为坐标的？
 * 还是查看 ShapeDrawable 的 draw 方法里的
 * final int count = canvas.save();
 * // 平移画布
 * canvas.translate(r.left, r.top);
 * // 再进行绘制
 * onDraw(state.mShape, canvas, paint);
 * canvas.restoreToCount(count);
 * 3，RectShape 的 mRect 变量是如何 drawable.setBounds 设置的矩形信息的？
 * ShapeDrawable.setBounds -> Drawable.setBounds
 * 在基类的 setBounds 方法里，调用了 onBoundsChange 方法，它是一个空方法，实现是在 ShapeDrawable 里面，
 * protected void onBoundsChange(Rect bounds) {
 *     super.onBoundsChange(bounds);
 *     updateShape();
 * }
 * private void updateShape() {
 *     if (mShapeState.mShape != null) {
 *         final Rect r = getBounds();
 *         final int w = r.width();
 *         final int h = r.height();
 *         mShapeState.mShape.resize(w, h);
 *         if (mShapeState.mShaderFactory != null) {
 *             mShapeState.mPaint.setShader(mShapeState.mShaderFactory.resize(w, h));
 *         }
 *     }
 *     invalidateSelf();
 * }
 * 而 mShapeState.mShape 就是 RectShape 对象，查看它的 resize 方法：
 * 是在基类 Shape 里面：
 * public final void resize(float width, float height) {
 *     if (width < 0) {
 *         width = 0;
 *     }
 *     if (height < 0) {
 *         height =0;
 *     }
 *     if (mWidth != width || mHeight != height) {
 *         mWidth = width;
 *         mHeight = height;
 *         onResize(width, height);
 *     }
 * 继续查看 onResize 方法，是一个空方法，实现 RectShape 里面：
 * protected void onResize(float width, float height) {
 *     mRect.set(0, 0, width, height);
 * }
 */
