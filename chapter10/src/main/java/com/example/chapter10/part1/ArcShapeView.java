package com.example.chapter10.part1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.chapter10.Utils;

/**
 * @author wangzhichao
 * @date 2019/10/11
 */
public class ArcShapeView extends View {
    private ShapeDrawable drawable;
    public ArcShapeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        drawable = new ShapeDrawable(new ArcShape(0f,90f));
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
 * 1，ArcShape 是继承于 RectShape 的;
 * 2, startAngle 为 0 ，是在 x 轴正轴方向上。这个值不能过大，没有验证它的范围。
 * sweepAngle 的符号：正代表顺时针，负代表逆时针；数字代表扫描的范围有多大，大于360代表是满的。这个可以很大，不过没有必要。
 */