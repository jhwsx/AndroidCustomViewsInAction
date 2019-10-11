package com.example.chapter10.part1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.chapter10.Utils;

/**
 * RoundRectShape 实现中间带有镂空矩形的圆角矩形并且中间的镂空矩形也带有圆角
 *
 * @author wangzhichao
 * @date 2019/10/11
 */
public class RoundRectShapeView2 extends View {
    private ShapeDrawable drawable;

    public RoundRectShapeView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 外围矩形的各个角的圆角
        float[] outRadii = new float[]{
                Utils.dp2px(12), Utils.dp2px(12),
                Utils.dp2px(24), Utils.dp2px(24),
                0, 0,
                0, 0};
        // 内部矩形与外部矩形各边的间距
        RectF inset = new RectF(Utils.dp2px(10), Utils.dp2px(10), Utils.dp2px(10), Utils.dp2px(10));
        // 内部矩形的各个角的圆角
        float[] innerRadii = new float[]{
                0, 0,
                0, 0,
                Utils.dp2px(12), Utils.dp2px(12),
                Utils.dp2px(24), Utils.dp2px(24)
        };
        RoundRectShape roundRectShape = new RoundRectShape(outRadii, inset, innerRadii);
        drawable = new ShapeDrawable(roundRectShape);
        drawable.setBounds(new Rect(Utils.dp2px(50), Utils.dp2px(50), Utils.dp2px(200), Utils.dp2px(100)));
        drawable.getPaint().setColor(Color.YELLOW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawable.draw(canvas);
    }
}
