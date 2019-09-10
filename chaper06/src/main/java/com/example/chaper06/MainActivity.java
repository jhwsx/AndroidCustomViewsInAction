package com.example.chaper06;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void text(View view) {
        TextActivity.start(this);
    }

    public void textarea(View view) {
        TextAreaActivity.start(this);
    }

    public void top2baseline(View view) {
        Top2BaseLineActivity.start(this);
    }

    public void center2baseline(View view) {
        Center2BaseLineActivity.start(this);
    }

    public void paintbasic(View view) {
        PaintBasicActivity.start(this);
    }
}
