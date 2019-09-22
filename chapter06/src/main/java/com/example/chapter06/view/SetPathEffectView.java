package com.example.chapter06.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.SumPathEffect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 设置路径样式
 *
 * @author wangzhichao
 * @date 2019/09/17
 */
public class SetPathEffectView extends View {
    public SetPathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.GRAY);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);

        Path path = new Path();
        path.moveTo(100, 400);
        path.lineTo(200, 100);
        path.lineTo(300, 400);

        paint.setColor(Color.GREEN);
        canvas.drawPath(path, paint);

        paint.setColor(Color.YELLOW);
        paint.setPathEffect(new CornerPathEffect(40));
        canvas.drawPath(path, paint);

        paint.setColor(Color.RED);
        paint.setPathEffect(new CornerPathEffect(100));
        canvas.drawPath(path, paint);

        path.reset();
        path.moveTo(0, 600);
        path.lineTo(getWidth() * 0.1f, 500);
        path.lineTo(getWidth() * 0.3f, 650);
        path.lineTo(getWidth() * 0.4f, 550);
        path.lineTo(getWidth() * 0.6f, 600);
        path.lineTo(getWidth() * 0.65f, 500);
        path.lineTo(getWidth() * 0.8f, 650);
        path.lineTo(getWidth() * 1f, 600);
        paint.setColor(Color.GREEN);
        CornerPathEffect cornerPathEffect = new CornerPathEffect(40);
        paint.setPathEffect(cornerPathEffect); // 圆形拐角效果
        canvas.drawPath(path, paint);

        path.reset();
        path.moveTo(0, 600 + 50);
        path.lineTo(getWidth() * 0.1f, 500 + 50);
        path.lineTo(getWidth() * 0.3f, 650 + 50);
        path.lineTo(getWidth() * 0.4f, 550 + 50);
        path.lineTo(getWidth() * 0.6f, 600 + 50);
        path.lineTo(getWidth() * 0.65f, 500 + 50);
        path.lineTo(getWidth() * 0.8f, 650 + 50);
        path.lineTo(getWidth() * 1f, 600 + 50);
        paint.setColor(Color.GREEN);
        DashPathEffect dashPathEffect = new DashPathEffect(new float[]{20, 10, 40, 20}, 0);
        paint.setPathEffect(dashPathEffect); // 虚线效果
        canvas.drawPath(path, paint);

        path.reset();
        path.moveTo(0, 600 + 100 );
        path.lineTo(getWidth() * 0.1f, 500 + 100);
        path.lineTo(getWidth() * 0.3f, 650 + 100);
        path.lineTo(getWidth() * 0.4f, 550 + 100);
        path.lineTo(getWidth() * 0.6f, 600 + 100);
        path.lineTo(getWidth() * 0.65f, 500 + 100);
        path.lineTo(getWidth() * 0.8f, 650 + 100);
        path.lineTo(getWidth() * 1f, 600 + 100);
        paint.setColor(Color.GREEN);
        paint.setPathEffect(new DiscretePathEffect(5,10)); // 离散路径效果
        canvas.drawPath(path, paint);

        path.reset();
        path.moveTo(0, 600 + 150 );
        path.lineTo(getWidth() * 0.1f, 500 + 150);
        path.lineTo(getWidth() * 0.3f, 650 + 150);
        path.lineTo(getWidth() * 0.4f, 550 + 150);
        path.lineTo(getWidth() * 0.6f, 600 + 150);
        path.lineTo(getWidth() * 0.65f, 500 + 150);
        path.lineTo(getWidth() * 0.8f, 650 + 150);
        path.lineTo(getWidth() * 1f, 600 + 150);
        paint.setColor(Color.GREEN);
//        paint.setPathEffect(new PathDashPathEffect(getStampPath(), 40,0, PathDashPathEffect.Style.TRANSLATE)); // 改变印章的位置来过渡，离散路径效果
        paint.setPathEffect(new PathDashPathEffect(getStampPath(), 40,0, PathDashPathEffect.Style.MORPH)); // 通过对印章进行变形来过渡，离散路径效果
//        paint.setPathEffect(new PathDashPathEffect(getStampPath(), 40,0, PathDashPathEffect.Style.ROTATE)); // 旋转印章来过渡，离散路径效果
        canvas.drawPath(path, paint);
        canvas.drawPath(path, paint);

        path.reset();
        path.moveTo(0, 600 + 200 );
        path.lineTo(getWidth() * 0.1f, 500 + 200);
        path.lineTo(getWidth() * 0.3f, 650 + 200);
        path.lineTo(getWidth() * 0.4f, 550 + 200);
        path.lineTo(getWidth() * 0.6f, 600 + 200);
        path.lineTo(getWidth() * 0.65f, 500 + 200);
        path.lineTo(getWidth() * 0.8f, 650 + 200);
        path.lineTo(getWidth() * 1f, 600 + 200);
        paint.setColor(Color.GREEN);
        paint.setPathEffect(new ComposePathEffect(cornerPathEffect, dashPathEffect));
        canvas.drawPath(path, paint);
        canvas.drawPath(path, paint);

        path.reset();
        path.moveTo(0, 600 + 250 );
        path.lineTo(getWidth() * 0.1f, 500 + 250);
        path.lineTo(getWidth() * 0.3f, 650 + 250);
        path.lineTo(getWidth() * 0.4f, 550 + 250);
        path.lineTo(getWidth() * 0.6f, 600 + 250);
        path.lineTo(getWidth() * 0.65f, 500 + 250);
        path.lineTo(getWidth() * 0.8f, 650 + 250);
        path.lineTo(getWidth() * 1f, 600 + 250);
        paint.setColor(Color.GREEN);
        paint.setPathEffect(new SumPathEffect(cornerPathEffect, dashPathEffect));
        canvas.drawPath(path, paint);
    }


    private Path getStampPath() {
        Path result = new Path();
        result.moveTo(0, 20);
        result.lineTo(10, 0);
        result.lineTo(20, 20);
        result.close();
        result.addCircle(0,0,3, Path.Direction.CCW);
        return result;
    }
}
