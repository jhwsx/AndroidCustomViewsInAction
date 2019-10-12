package com.example.chapter10.part1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.ShapeDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.chapter10.Utils;

/**
 * @author wangzhichao
 * @date 2019/10/12
 */
public class RegionShapeView extends View {
    private ShapeDrawable drawable;
    public RegionShapeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Rect vRect = new Rect(Utils.dp2px(50), Utils.dp2px(0), Utils.dp2px(90), Utils.dp2px(150));
        Rect hRect = new Rect(Utils.dp2px(0), Utils.dp2px(50), Utils.dp2px(250), Utils.dp2px(100));
        // 构建两个区域
        Region vRegion = new Region(vRect);
        Region hRegion = new Region(hRect);

        // 取两个区域的相交之外的区域
        vRegion.op(hRegion, Region.Op.XOR);
        RegionShape regionShape = new RegionShape(vRegion);
        drawable = new ShapeDrawable(regionShape);
        drawable.setBounds(new Rect(0, 0, Utils.dp2px(250), Utils.dp2px(150)));
        drawable.getPaint().setColor(Color.YELLOW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawable.draw(canvas);
    }
}
