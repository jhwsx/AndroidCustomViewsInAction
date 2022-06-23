package com.example.chapter04.part1;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.chapter04.R;

/**
 * @author wangzhichao
 * @since 2022/6/22
 */
public class F_KeyframeOfObjectViewGroup extends LinearLayout implements View.OnClickListener {

    private final TextView tv;

    public F_KeyframeOfObjectViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_keyframe_ofobject, this);
        Button btnStartAnim = findViewById(R.id.btn_start_anim);
        tv = findViewById(R.id.tv);
        btnStartAnim.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ViewWrapper viewWrapper = new ViewWrapper(tv);
        Keyframe frame0 = Keyframe.ofObject(0f, 'A');
        Keyframe frame1 = Keyframe.ofObject(0.1f, 'T');
        Keyframe frame2 = Keyframe.ofObject(1f, 'Z');
        PropertyValuesHolder charHolder = PropertyValuesHolder.ofKeyframe("charText", frame0, frame1, frame2);
        charHolder.setEvaluator(new CharEvaluator());
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(viewWrapper, charHolder);
        objectAnimator.setDuration(5000L);
        objectAnimator.setInterpolator(new AccelerateInterpolator());
        objectAnimator.start();
    }

    static class ViewWrapper {
        private final TextView textView;

        public ViewWrapper(TextView textView) {
            this.textView = textView;
        }

        public void setCharText(Character value) {
            textView.setText(value.toString());
        }
    }
}
