package com.example.chapter10.part1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.chapter10.Utils;

/**
 * RoundRectShape 实现单纯的带有圆角的矩形
 *
 * @author wangzhichao
 * @date 2019/10/11
 */
public class RoundRectShapeView1 extends View {
    private ShapeDrawable drawable;

    public RoundRectShapeView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        float[] outRadii = new float[]{
                Utils.dp2px(12), Utils.dp2px(12),
                Utils.dp2px(24), Utils.dp2px(24),
                0, 0,
                0, 0};
        RoundRectShape roundRectShape = new RoundRectShape(outRadii, null, null);
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
