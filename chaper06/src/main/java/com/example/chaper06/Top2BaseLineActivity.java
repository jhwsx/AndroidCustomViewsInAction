package com.example.chaper06;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Top2BaseLineActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, Top2BaseLineActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top2_base_line);
    }
}
