package com.example.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Hello2 extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Hello2";
    private static int count2=0;//整个类中只有一个,所有对象都能共享
    private int m_count2;//每个对象都有一个

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        count2++;
        m_count2=count2;
        setContentView(R.layout.activity_hello2);
        Log.d(TAG+"-"+m_count2, "onCreate execute");
        setTitle("Hello2"); //设置标题名称
        settupButtons();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG+"-"+m_count2,"onStart");   //打印出回调函数
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG+"-"+m_count2,"onResume");   //打印出回调函数
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG+"-"+m_count2,"onPause");   //打印出回调函数
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG+"-"+m_count2,"onStop");   //打印出回调函数
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        count2--;
        Log.d(TAG+"-"+m_count2,"onDestroy");   //打印出回调函数
    }

    @Override
    public void onClick(View view) {  //
        Intent intent;
        if(view.getId()==R.id.bttoHello1){
            intent=new Intent(this,Hello1.class);
            startActivity(intent);
        }
        if(view.getId()==R.id.bttoHello2){
            intent=new Intent(this,Hello2.class);
            startActivity(intent);
        }
        if(view.getId()==R.id.bttoHello3){
            intent=new Intent(this,Hello3.class);
            startActivity(intent);
        }

    }

    private void settupButtons(){
        Button b;

        b=(Button)findViewById(R.id.bttoHello1);
        b.setOnClickListener(this);
        b=(Button)findViewById(R.id.bttoHello2);
        b.setOnClickListener(this);
        b=(Button)findViewById(R.id.bttoHello3);
        b.setOnClickListener(this);

    }


}
