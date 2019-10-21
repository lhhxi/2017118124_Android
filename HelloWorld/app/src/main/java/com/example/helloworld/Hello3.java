package com.example.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Hello3 extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Hello3";
    private static int count3=0;//整个类中只有一个,所有对象都能共享
    private int m_count3;//每个对象都有一个

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        count3++;
        m_count3=count3;
        setContentView(R.layout.activity_hello3);
        Log.d(TAG+"-"+m_count3, "onCreate execute");
        setTitle("Hello3");
        settupButtons();
        //(10.15)
        Intent intent=this.getIntent();
        if(intent==null){
            Log.d("test tag","This activity is invoked without an intent");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG+"-"+m_count3,"onStart");   //打印出回调函数
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG+"-"+m_count3,"onResume");   //打印出回调函数
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG+"-"+m_count3,"onPause");   //打印出回调函数
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG+"-"+m_count3,"onStop");   //打印出回调函数
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        count3--;
        Log.d(TAG+"-"+m_count3,"onDestroy");   //打印出回调函数
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
