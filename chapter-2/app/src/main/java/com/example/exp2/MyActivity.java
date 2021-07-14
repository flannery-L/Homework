package com.example.exp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Button button =findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                Toast.makeText(MyActivity.this,"button click",Toast.LENGTH_SHORT).show();
                Log.i("button click","toast");
            }
        });

        Button btn_prac=findViewById(R.id.btn_prac);
        Button btn_baidu=findViewById(R.id.btn_baidu);
        Button btn_phone=findViewById(R.id.btn_phone);
        btn_prac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyActivity.this,PracticeActivity.class);
                startActivity(intent);
            }
        });
        btn_baidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.baidu.com"));
                startActivity(intent);
            }
        });
        btn_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EditText edittext =findViewById(R.id.editText);
//                String phonenumber=edittext.getText().toString();
                //Intent intent=new Intent(Intent.ACTION_DIAL,Uri.parse("tel"+phonenumber));
                Intent intent=new Intent(Intent.ACTION_CALL_BUTTON);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
