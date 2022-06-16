package com.example.chapter03.part4_objectanimator;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chapter03.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * @author wangzhichao
 * @since 2021/5/13
 */
public class M_ObjectAnimatorIntroViewGroup extends LinearLayout {
    private final TextView tv;
    private final Spinner spinner;
    private ObjectAnimator objectAnimator;

    public M_ObjectAnimatorIntroViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.m_object_animator_intro_viewgroup, this);
        tv = findViewById(R.id.tv);
        Button btnStartAnim = findViewById(R.id.btn_start_anim);
        final LinkedHashMap<String, ObjectAnimator> map = new LinkedHashMap<>();
        map.put("alpha", ObjectAnimator.ofFloat(tv, "alpha", 1, 0, 1));
        map.put("rotationX", ObjectAnimator.ofFloat(tv, "rotationX", 0, 270, 0));
        map.put("rotationY", ObjectAnimator.ofFloat(tv, "rotationY", 0, 270, 0));
        map.put("rotation", ObjectAnimator.ofFloat(tv, "rotation", 0, 270, 0));
        map.put("translationX", ObjectAnimator.ofFloat(tv, "translationX", 0, 200, -100, 0));
        map.put("translationY", ObjectAnimator.ofFloat(tv, "translationY", 0, 200, -100, 0));
        map.put("scaleX", ObjectAnimator.ofFloat(tv, "scaleX", 0, 3, 1));
        map.put("scaleY", ObjectAnimator.ofFloat(tv, "scaleY", 0, 3, 1));
        Set<String> integers = map.keySet();
        final List<String> list = new ArrayList<>(integers);
        spinner = findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, list));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String key = list.get(position);
                objectAnimator = map.get(key);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnStartAnim.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                objectAnimator.setDuration(2000L);
                objectAnimator.start();
            }
        });
        tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "click me", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    private void doAnimation() {
//        tv.setRotation();
        objectAnimator = ObjectAnimator.ofFloat(tv, "rotation", 0, 180, 0);
        objectAnimator.setDuration(2000L);
        objectAnimator.start();

//        objectAnimator2 = ObjectAnimator.ofFloat(tv2, "alpha", 1, 0, 1);
//        objectAnimator2.setDuration(2000L);
//        objectAnimator2.start();
//
//        objectAnimator3 = ObjectAnimator.ofFloat(tv3, "rotationX", 0, 270, 0);
//        objectAnimator3.setDuration(2000L);
//        objectAnimator3.start();
//
//        objectAnimator4 = ObjectAnimator.ofFloat(tv4, "rotationY", 0, 270, 0);
//        objectAnimator4.setDuration(2000L);
//        objectAnimator4.start();
    }
}
