package cn.edu.hstc.cs.ljc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Hello2 extends AppCompatActivity implements View.OnClickListener {  //实现监听

    private static final String TAG = "Hello2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello2);
        Log.d(TAG, "onCreate execute");
        setTitle("Hello2"); //设置标题名称
        settupButtons();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");   //打印出回调函数
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");   //打印出回调函数
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");   //打印出回调函数
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");   //打印出回调函数
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");   //打印出回调函数
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
