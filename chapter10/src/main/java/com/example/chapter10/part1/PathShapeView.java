package com.example.chapter10.part1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.chapter10.Utils;

/**
 * @author wangzhichao
 * @date 2019/10/11
 */
public class PathShapeView extends View {
    private ShapeDrawable drawable;
    private final Path path;

    public PathShapeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        path = new Path();
        path.moveTo(0, 0);
        path.lineTo(100, 0);
        path.lineTo(100, 100);
        path.lineTo(0, 100);
        path.close();

        PathShape pathShape = new PathShape(path, 100, 100);
//        PathShape pathShape = new PathShape(path, 100, 200);

        drawable = new ShapeDrawable();
        drawable.setShape(pathShape);
        drawable.setBounds(new Rect(0, 0, Utils.dp2px(250), Utils.dp2px(150)));
        drawable.getPaint().setColor(Color.YELLOW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawable.draw(canvas);
    }

    public void updateStdWidthAndStdHeight(int stdWidth, int stdHeight) {
        PathShape pathShape = new PathShape(path, stdWidth, stdHeight);
        drawable.setShape(pathShape);
        invalidate();
    }
}

/**
 * 总结：
 * 1，PathShape 是继承于 Shape 的。
 * 2, PathShape 的参数 stdWidth 和 stdHeight 理解成宽度分成多少份，高度分成多少份；而 Path 路径的宽，高也应该
 * 理解成份数，而不是 px。Path 的宽和高与 stdWidth 和 stdHeight 一致的话，就会填满整个控件。
 */
