package com.example.chapter01.part2_path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

/**
 * 蜘蛛网图
 *
 * @author wangzhichao
 * @since 20-3-8
 */
public class SpiderView extends View {
    private static final int netLineSpace = 50;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();
    public SpiderView(Context context) {
        super(context);
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth() / 2, getHeight() / 2);
        // 纬线数目
        int latitudeCount = 6;
        // 经线数目
        int longtitudeCount = 6;
        // 1,绘制网的纬线框
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3f);
        for (int i = 1; i <= latitudeCount; i++) {
            path.reset();
            for (int j = 0; j < longtitudeCount; j++) {
                Point point = getPoint(i, j);
                if (j == 0) {
                    path.moveTo(point.x, point.y);
                } else {
                    path.lineTo(point.x, point.y);
                }
            }
            path.close();
            canvas.drawPath(path, paint);
        }
        // 2,绘制网的经线
        for (int i = 0; i < longtitudeCount; i++) {
            Point point = getPoint(latitudeCount, i);
            canvas.drawLine(0,0, point.x, point.y, paint);
        }
        // 3,绘制点连成的区域
        List<Point> list = new ArrayList<>();
        list.add(getPoint(1, 0));
        list.add(getPoint(5, 1));
        list.add(getPoint(1, 2));
        list.add(getPoint(6, 3));
        list.add(getPoint(4, 4));
        list.add(getPoint(5, 5));
        path.reset();
        for (int i = 0; i < list.size(); i++) {
            Point point = list.get(i);
            if (i == 0) {
                path.moveTo(point.x, point.y);
            } else {
                path.lineTo(point.x, point.y);
            }
        }
        path.close();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#44ff0000"));
        canvas.drawPath(path, paint);
        // 4,绘制点
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(20f);
        paint.setStrokeCap(Paint.Cap.ROUND);
        for (Point point : list) {
            canvas.drawPoint(point.x, point.y, paint);
        }
    }

    private Point getPoint(int latitudePosition, int longtitudePosition) {
        int radius = latitudePosition * netLineSpace;
        int x = (int) (radius * Math.cos(Math.toRadians(longtitudePosition * 60)));
        int y = (int) (radius * Math.sin(Math.toRadians(longtitudePosition * 60)));
        return new Point(x, y);
    }
}
