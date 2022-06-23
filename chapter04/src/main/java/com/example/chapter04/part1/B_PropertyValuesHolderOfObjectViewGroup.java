package com.example.chapter04.part1;

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
 * TODO 这个类转成 Kotlin 后，就没有动画了，奇怪？
 * @author wangzhichao
 * @since 2022/6/20
 */
public class B_PropertyValuesHolderOfObjectViewGroup extends LinearLayout implements View.OnClickListener {

    private final TextView tv;

    public B_PropertyValuesHolderOfObjectViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_propertyvaluesholder_ofobject, this);
        Button btnStartAnim = findViewById(R.id.btn_start_anim);
        tv = findViewById(R.id.tv);
        btnStartAnim.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ViewWrapper viewWrapper = new ViewWrapper(tv);
        PropertyValuesHolder charHolder = PropertyValuesHolder.ofObject("charText", new CharEvaluator(), 'A', 'Z');
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
