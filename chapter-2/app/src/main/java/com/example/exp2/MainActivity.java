package com.example.exp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity","onCreate");
        Button btn_my=findViewById(R.id.btn_my);
        btn_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MyActivity.class);
                startActivity(intent);
            }
        });
        Button btn_rv=findViewById(R.id.btn_rv);
        btn_rv.setOnClickListener(new View.OnClickListener(){
            @Override
           public void onClick(View v){
                Intent intent=new Intent(MainActivity.this,RecycleViewActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity","onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity","onResume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity","onPause");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity","onStop");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity","onDestroy");
    }
}
