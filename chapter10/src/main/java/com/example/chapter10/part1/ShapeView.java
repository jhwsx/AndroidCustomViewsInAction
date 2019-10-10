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
