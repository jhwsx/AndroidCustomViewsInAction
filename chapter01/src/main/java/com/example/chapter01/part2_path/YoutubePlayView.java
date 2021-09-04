package com.example.chapter01.part2_path;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import java.util.ArrayList;
import java.util.List;

public class YoutubePlayView extends View {

    private final int height = 100;
    private final Path path1 = new Path();
    private final Path path2 = new Path();
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    // 存放左边部分起始点的集合
    private final List<PointF> pointList11 = new ArrayList<>();
    // 存放左边部分终止点的集合
    private final List<PointF> pointList12 = new ArrayList<>();
    // 存放左边部分进度点的集合
    private final List<PointF> pointList1Progress = new ArrayList<>();
    // 存放右边部分起始点的集合
    private final List<PointF> pointList21 = new ArrayList<>();
    // 存放右边部分终止点的集合
    private final List<PointF> pointList22 = new ArrayList<>();
    // 存放右边部分进度点的集合
    private final List<PointF> pointList2Progress = new ArrayList<>();
    private static final String TAG = "YoutubePlayView";
    // 集合数据是否填充的标记
    private boolean isListDataFilled = false;

    public YoutubePlayView(Context context) {
        this(context, null);
    }

    public YoutubePlayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        initPointList();
    }

    // 提前把点集合创建好，不在 onDraw 方法里面创建
    private void initPointList() {
        for (int i = 0; i < 4; i++) {
            pointList11.add(new PointF());
            pointList12.add(new PointF());
            pointList21.add(new PointF());
            pointList22.add(new PointF());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension((int) (height * Math.sqrt(3) / 2), height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        fillListData(width, height);
        path1.reset();
        path2.reset();
        path1.moveTo(pointList1Progress.get(0).x, pointList1Progress.get(0).y);
        path1.lineTo(pointList1Progress.get(1).x, pointList1Progress.get(1).y);
        path1.lineTo(pointList1Progress.get(2).x, pointList1Progress.get(2).y);
        path1.lineTo(pointList1Progress.get(3).x, pointList1Progress.get(3).y);
        path1.close();
        canvas.drawPath(path1, paint);
        path2.moveTo(pointList2Progress.get(0).x, pointList2Progress.get(0).y);
        path2.lineTo(pointList2Progress.get(1).x, pointList2Progress.get(1).y);
        path2.lineTo(pointList2Progress.get(2).x, pointList2Progress.get(2).y);
        path2.lineTo(pointList2Progress.get(3).x, pointList2Progress.get(3).y);
        path2.close();
        canvas.drawPath(path2, paint);
    }

    private void fillListData(int width, int height) {
        if (isListDataFilled) {
            return;
        }
        isListDataFilled = true;
        pointList11.get(0).set(0, 0);
        pointList11.get(1).set(width / 2f, height / 4f);
        pointList11.get(2).set(width / 2f, height * 3 / 4f);
        pointList11.get(3).set(0, height);
        // 这里不能使用 addAll 方法，因为 pointList11 的点是复用的点。
        for (PointF pointF : pointList11) {
            pointList1Progress.add(new PointF(pointF.x, pointF.y));
        }

        pointList12.get(0).set(0, 0);
        pointList12.get(1).set(width / 3f, 0);
        pointList12.get(2).set(width / 3f, height);
        pointList12.get(3).set(0, height);

        pointList21.get(0).set(width, height / 2f);
        pointList21.get(1).set(width / 2f, height / 4f);
        pointList21.get(2).set(width / 2f, height * 3 / 4f);
        pointList21.get(3).set(width, height / 2f);
        for (PointF pointF : pointList21) {
            pointList2Progress.add(new PointF(pointF.x, pointF.y));
        }

        pointList22.get(0).set(width, 0);
        pointList22.get(1).set(width * 2 / 3f, 0);
        pointList22.get(2).set(width * 2 / 3f, height);
        pointList22.get(3).set(width, height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (state == STATE_STOP) {
            play();
        } else if (state == STATE_PLAYING) {
            stop();
        }
        return super.onTouchEvent(event);
    }

    private int state = STATE_STOP;
    private static final int STATE_STOP = 0;
    private static final int STATE_PLAYING = 1;
    private boolean animPlaying = false;

    public void play() {
        if (animPlaying)
            return;
        if (state == STATE_PLAYING)
            return;
        state = STATE_PLAYING;
        ValueAnimator va = ValueAnimator.ofFloat(0, 1);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float percent = (float) animation.getAnimatedValue();
                // 从 11 -> 12, 21 -> 22
                for (int i = 0; i < 4; i++) {
                    pointList1Progress.get(i).set(
                            pointList11.get(i).x + percent * (pointList12.get(i).x - pointList11.get(i).x),
                            pointList11.get(i).y + percent * (pointList12.get(i).y - pointList11.get(i).y)
                    );
                    pointList2Progress.get(i).set(
                            pointList21.get(i).x + percent * (pointList22.get(i).x - pointList21.get(i).x),
                            pointList21.get(i).y + percent * (pointList22.get(i).y - pointList21.get(i).y)
                    );
                }
                invalidate();
            }
        });
        va.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animPlaying = false;
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                animPlaying = true;
            }
        });
        va.setDuration(500);
        va.setInterpolator(new AccelerateInterpolator());
        va.start();
    }

    public void stop() {
        if (animPlaying)
            return;
        if (state == STATE_STOP)
            return;
        state = STATE_STOP;
        ValueAnimator va = ValueAnimator.ofFloat(0, 1);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float percent = (float) animation.getAnimatedValue();
                // 从 12 -> 11, 22 -> 21
                for (int i = 0; i < 4; i++) {
                    pointList1Progress.get(i).set(
                            pointList12.get(i).x + percent * (pointList11.get(i).x - pointList12.get(i).x),
                            pointList12.get(i).y + percent * (pointList11.get(i).y - pointList12.get(i).y)
                    );
                    pointList2Progress.get(i).set(
                            pointList22.get(i).x + percent * (pointList21.get(i).x - pointList22.get(i).x),
                            pointList22.get(i).y + percent * (pointList21.get(i).y - pointList22.get(i).y)
                    );
                }
                invalidate();
            }
        });
        va.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animPlaying = false;
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                animPlaying = true;
            }
        });
        va.setDuration(500);
        va.setInterpolator(new AccelerateInterpolator());
        va.start();
    }
}
