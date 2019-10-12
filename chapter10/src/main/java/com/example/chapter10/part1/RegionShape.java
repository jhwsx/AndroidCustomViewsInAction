package com.example.chapter10.part1;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.graphics.drawable.shapes.Shape;

/**
 * @author wangzhichao
 * @date 2019/10/12
 */
public class RegionShape extends Shape {
    private Region mRegion;

    public RegionShape(Region region) {
        assert (region != null);
        this.mRegion = region;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        RegionIterator iterator = new RegionIterator(mRegion);
        Rect r = new Rect();

        while (iterator.next(r)) {
            canvas.drawRect(r, paint);
        }
    }
}
