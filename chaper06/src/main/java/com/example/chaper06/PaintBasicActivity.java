package com.example.chaper06;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class PaintBasicActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, PaintBasicActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_basic);
    }
}
