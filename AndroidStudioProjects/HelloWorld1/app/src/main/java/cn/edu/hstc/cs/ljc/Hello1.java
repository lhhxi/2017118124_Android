package cn.edu.hstc.cs.ljc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Hello1 extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Hello1";
    private static int count1=0;//整个类中只有一个,所有对象都能共享
    private int m_count1;//每个对象都有一个

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   //     count1++; //每当创建一个新的Hello1,count+1
        count1++;
        m_count1=count1;
        setContentView(R.layout.activity_main);
        Log.d(TAG+"-"+m_count1, "onCreate execute");
        setTitle("Hello1"); //设置标题名称
        settupClicks(); //
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
        count1--;
        Log.d(TAG,"onDestroy");   //打印出回调函数
    }


    @Override
    public void onClick(View view) {
        Intent intent;

        if(view.getId()== R.id.bttoHello1){
            //构造出Intent的意图（传入this作为上下文，传入Hello1.class作为目标活动）
            intent=new Intent(this,Hello1.class);  //即在Hello1这个活动的基础上打开Hello2活动
            startActivity(intent);
        }
        if(view.getId()==R.id.bttoHello2){
            //构造出Intent的意图（传入this作为上下文，传入Hello2.class作为目标活动）
            intent=new Intent(this,Hello2.class);  //即在Hello1这个活动的基础上打开Hello2活动
            startActivity(intent);
        }
        if(view.getId()==R.id.bttoHello3){
            //构造出Intent的意图（传入this作为上下文，传入Hello2.class作为目标活动）
            intent=new Intent(this,Hello3.class);  //即在Hello1这个活动的基础上打开Hello2活动
            startActivity(intent);
        }
    }

    private void settupClicks(){
        Button b;

        b=(Button)findViewById(R.id.bttoHello1); //在java事件中找到它
        b.setOnClickListener(this); //this是本类的实例对象，它是实现Listener的监听
        b=(Button)findViewById(R.id.bttoHello2);
        b.setOnClickListener(this);
        b=(Button)findViewById(R.id.bttoHello3);
        b.setOnClickListener(this);
    }
}
