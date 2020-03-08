package com.example.chapter01.part2_path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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
        canvas.translate(getWidth()/2, getHeight()/2);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3f);

        Path path = new Path();
        // 遍历径向的网线数
        for (int i = 1; i <= 6; i++) {
            int radius = i * netLineSpace;
            // 遍历圆周向的节点数
            for (int j = 0; j <= 6; j++) {
                if (j == 0) {
                    path.reset();
                    path.moveTo(radius, 0);
                } else if (j == 6) {
                    path.close();
                    canvas.drawPath(path, paint);
                } else {
                    Pair<Float, Float> point = getPoint(i, j);
                    path.lineTo(point.first, point.second);
                }
            }
        }
        int maxRadius = 6 * netLineSpace;
        // 画径向节点的连线
        for (int i = 0; i < 6; i++) {
            canvas.drawLine(0, 0, (float) (maxRadius * cos(toRadians(60 * i))),
                    (float) (maxRadius * sin(toRadians(60 * i))), paint);
        }
        // 选取点的集合
        List<Pair<Float, Float>> pointList = new ArrayList<>();
        pointList.add(getPoint(2, 0));
        pointList.add(getPoint(5, 1));
        pointList.add(getPoint(1, 2));
        pointList.add(getPoint(6, 3));
        pointList.add(getPoint(4, 4));
        pointList.add(getPoint(5, 5));
        // 绘制这些点构成的区域
        paint.setColor(0x440000ff);
        paint.setStyle(Paint.Style.FILL);
        path.reset();
        for (int i = 0; i < pointList.size(); i++) {
            Pair<Float, Float> point = pointList.get(i);
            if (i == 0) {
                path.moveTo(point.first, point.second);
            } else {
                path.lineTo(point.first, point.second);
            }
        }
        path.close();
        canvas.drawPath(path, paint);
        // 绘制点
        paint.setColor(Color.BLUE);
        for (int i = 0; i < pointList.size(); i++) {
            Pair<Float, Float> point = pointList.get(i);
            canvas.drawCircle(point.first, point.second, netLineSpace / 3, paint);
        }
    }

    private Pair<Float, Float> getPoint(int netLine, int circlePoint) {
        int radius = netLine * netLineSpace;
        float x = (float) (radius * cos(toRadians(60 * circlePoint)));
        float y = (float) (radius * sin(toRadians(60 * circlePoint)));
        return new Pair<>(x, y);
    }
}
