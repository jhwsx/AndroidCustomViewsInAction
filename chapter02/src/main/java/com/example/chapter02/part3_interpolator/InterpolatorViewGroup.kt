package com.example.chapter02.part3_interpolator;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.chapter02.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 插值器
 * AccelerateDecelerateInterpolator: 加速减速插值器
 * AccelerateInterpolator 加速插值器
 * LinearInterpolator 线性插值器
 * DecelerateInterpolator 减速插值器
 * BounceInterpolator 弹跳插值器, 模拟了物体自由落体后回弹的效果
 * AnticipateInterpolator 初值偏移插值器
 * OvershootInterpolator 结束偏移插值器
 * AnticipateOvershootInterpolator
 * CycleInterpolator 循环插值器
 * @author wangzhichao
 * @since 20-4-9
 */
public class InterpolatorViewGroup extends ConstraintLayout {

    private Interpolator interpolator = new AccelerateDecelerateInterpolator();

    public InterpolatorViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.interpolator_viewgroup, this);
        Spinner spinner = findViewById(R.id.spinner);
        final LinkedHashMap<String, Interpolator> map = new LinkedHashMap<>();
        map.put(getResources().getString(R.string.title_interpolator_accelerate_decelerate), new AccelerateDecelerateInterpolator());
        map.put(getResources().getString(R.string.title_interpolator_accelerate), new AccelerateInterpolator());
        map.put(getResources().getString(R.string.title_interpolator_decelerate), new DecelerateInterpolator());
        map.put(getResources().getString(R.string.title_interpolator_linear), new LinearInterpolator());
        map.put(getResources().getString(R.string.title_interpolator_bounce), new BounceInterpolator());
        map.put(getResources().getString(R.string.title_interpolator_anticipate_tension_2), new AnticipateInterpolator());
        map.put(getResources().getString(R.string.title_interpolator_anticipate_tension_point5), new AnticipateInterpolator(0.5f));
        map.put(getResources().getString(R.string.title_interpolator_anticipate_tension_4), new AnticipateInterpolator(4));
        map.put(getResources().getString(R.string.title_interpolator_overshoot_tension_2), new OvershootInterpolator());
        map.put(getResources().getString(R.string.title_interpolator_overshoot_tension_point5), new OvershootInterpolator(0.5f));
        map.put(getResources().getString(R.string.title_interpolator_overshoot_tension_4), new OvershootInterpolator(4));
        map.put(getResources().getString(R.string.title_interpolator_anticipate_overshoot_tension_2), new AnticipateOvershootInterpolator());
        map.put(getResources().getString(R.string.title_interpolator_anticipate_overshoot_tension_point5), new AnticipateOvershootInterpolator(0.5f));
        map.put(getResources().getString(R.string.title_interpolator_anticipate_overshoot_tension_4), new AnticipateOvershootInterpolator(4));
        map.put(getResources().getString(R.string.title_interpolator_cycle_cycles_1), new CycleInterpolator(1));
        map.put(getResources().getString(R.string.title_interpolator_cycle_cycles_point5), new CycleInterpolator(0.5f));
        map.put(getResources().getString(R.string.title_interpolator_cycle_cycles_4), new CycleInterpolator(4));
        Set<String> integers = map.keySet();
        final List<String> list = new ArrayList<>(integers);
        spinner.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, list));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String key = list.get(position);
                interpolator = map.get(key);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        final TextView tv = findViewById(R.id.tv);
        findViewById(R.id.btn_translate).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.translateanim_delta_50_percent);
                animation.setInterpolator(interpolator);
                tv.startAnimation(animation);
            }
        });
        findViewById(R.id.btn_rotate).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.rotateanim_pivot_50_percent);
                animation.setInterpolator(interpolator);
                tv.startAnimation(animation);
            }
        });
        findViewById(R.id.btn_scale).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.scaleanim_pivot_50_percent);
                animation.setInterpolator(interpolator);
                tv.startAnimation(animation);
            }
        });
        findViewById(R.id.btn_alpha).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.alphaanim);
                animation.setInterpolator(interpolator);
                tv.startAnimation(animation);
            }
        });
        findViewById(R.id.btn_reset).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.clearAnimation();
            }
        });
    }
}
