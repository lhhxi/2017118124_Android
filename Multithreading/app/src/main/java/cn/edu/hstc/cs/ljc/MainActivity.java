package cn.edu.hstc.cs.ljc;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView number;
    private Button stop;
    private int num = 0;
    private boolean flag = true;
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (flag) {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
                try {
                    thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    number.setText(String.valueOf(++num));
                    break;
                default:break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number = (TextView)findViewById(R.id.number);
        stop = (Button)findViewById(R.id.stop);
        thread.start();
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = false;
            }
        });
    }
}