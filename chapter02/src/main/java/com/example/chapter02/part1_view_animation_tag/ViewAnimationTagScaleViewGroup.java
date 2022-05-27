package com.example.chapter02.part1_view_animation_tag;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.chapter02.R;

/**
 * scale 标签动画文件放在 res/anim/ 下面
 * 需要注意的是
 * pivotX, pivotY 取值的含义:
 * 在默认情况下,动画的起始点是控件左上角的坐标原点.而 pivotX, pivotY 用于指定动画的起始点与坐标原点的相对位置.
 * 1,取值为数值时
 * android:pivotX="50" // 表示动画的起始点是在控件左上角的坐标原点的基础上在 X 轴正方向上加上 50px;
 * android:pivotY="50" // 表示动画的起始点是在控件左上角的坐标原点的基础上在 Y 轴正方向上加上 50px.
 * 2,取值为百分数时
 * android:pivotX="50%" // 表示动画的起始点是在控件左上角的坐标原点的基础上在 X 轴正方向上加上控件宽度的 50%;
 * android:pivotY="50%" // 表示动画的起始点是在控件左上角的坐标原点的基础上在 Y 轴正方向上加上控件高度的 50%;
 * 也就是说以控件的中心来进行缩放
 * 3,取值为百分数p时
 * android:pivotX="50%p" // 表示动画的起始点是在控件左上角的坐标原点的基础上在 X 轴正方向上加上父控件宽度的 50%;
 * android:pivotY="50%p" // 表示动画的起始点是在控件左上角的坐标原点的基础上在 Y 轴正方向上加上父控件高度的 50%;
 *
 * 不管 pivotX,pivotY 如何取值, 最终的结束位置是不变的.
 * fill 开头的三个属性
 *
 * 参考文章:
 * http://www.dss886.com/2017/07/05/01/
 * https://www.jianshu.com/p/ee0c1dda0275
 *
 * android:fillAfter          用于确定是否保持动画结束时的值，如果设置为true，控件动画结束时，将保持动画
 *                            最后时的状态，为false就是不保持；这个值默认是false，表示不保持动画最后的状态。
 *                            fillAfter属性的默认值是false。
 *                            控制动画播放完成时是否将 transformation 作用到视图上。
 *                            fillAfter 的设置是否有效, 与 fillEnabled 的设置无关.
 *
 * android:fillBefore         用于确定动画开始时，View的动画属性值；这里所说的动画开始不是指的调用
 *                            startAnimation 方法，而是界面中动画真正开始动的时候。从调用
 *                            startAnimation 到动画真正开始时，中间有一个 startOffset 阶段，若
 *                            fillBefore 为 true，则在 startOffset 阶段时，将动画属性设置为初始值，
 *                            为false，则为 View 本身的初始值。
 *                            fillBefore属性的默认值是 true。
 *                            控制动画开始之前是否将 transformation 作用到视图上。
 *                            fillBefore 的设置是否有效, 会受到 fillEnabled 的设置的影响.
 *
 * android:fillEnabled        用来控制 fillBefore 属性是否有效，若为 true，则 fillBefore 的设置有效果；若为 false
 *                            则不管设置 fillBefore 为 true 还是 false，都不起作用, 也就是说，当
 *                            fillEnabled 为 false 时，不管 fillBefore 是什么值，在 startOffset 阶段
 *                            都将初始值设置为动画属性的初始值。
 *                            fillEnabled 属性的值默认是 false，fillEnabled 属性是 fillBefore 属性的开关。
 * @author wangzhichao
 * @since 20-3-24
 */
public class ViewAnimationTagScaleViewGroup extends ConstraintLayout {

    public ViewAnimationTagScaleViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_animation_tag_scale_viewgroup, this);
        final TextView tv = findViewById(R.id.tv);
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.scaleanim);
                tv.startAnimation(animation);
            }
        });
        final TextView tvPivot50 = findViewById(R.id.tv_pivot_50);
        findViewById(R.id.btn_start_pivot_50).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.scaleanim_pivot_50);
                tvPivot50.startAnimation(animation);
            }
        });
        final TextView tvPivot50Percent = findViewById(R.id.tv_pivot_50_percent);
        findViewById(R.id.btn_start_pivot_50_percent).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.scaleanim_pivot_50_percent);
                tvPivot50Percent.startAnimation(animation);
            }
        });
        final TextView tvPivot50PercentP = findViewById(R.id.tv_pivot_50_percent_p);
        findViewById(R.id.btn_start_pivot_50_percent_p).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.scaleanim_pivot_50_percent_p);
                tvPivot50PercentP.startAnimation(animation);
            }
        });
    }

}
