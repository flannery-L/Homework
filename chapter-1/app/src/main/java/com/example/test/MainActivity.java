package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup Sexrg=findViewById(R.id.radiogroup);

        Button btn = findViewById(R.id.button1);
        final TextView tv = findViewById(R.id.tv_title);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("I love bytedance");
            }
        });

        Log.v("MainActivity","The gender is chosen");
        Log.i("111111", "onCreate: 1111");
    }
}
