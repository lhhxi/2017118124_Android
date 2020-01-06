package cn.edu.hstc.cs.ljc;
package com.example.asynctasktest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends Activity {
    private Button satrtButton;
    private Button cancelButton;
    private ProgressBar progressBar;
    private TextView textView;
    private DownLoaderAsyncTask downLoaderAsyncTask;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    public void initView() {
        satrtButton=(Button) findViewById(R.id.startButton);
        cancelButton=(Button) findViewById(R.id.cancelButton);
        satrtButton.setOnClickListener(new ButtonOnClickListener());
        cancelButton.setOnClickListener(new ButtonOnClickListener());
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        textView=(TextView) findViewById(R.id.textView);
    }
    private class ButtonOnClickListener implements OnClickListener{
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.startButton:
//注意:
//1 每次需new一个实例,新建的任务只能执行一次,否则会出现异常
//2 异步任务的实例必须在UI线程中创建
//3 execute()方法必须在UI线程中调用。
                    downLoaderAsyncTask=new DownLoaderAsyncTask();
                    downLoaderAsyncTask.execute("http://www.baidu.com");
                    break;
                case R.id.cancelButton:
//取消一个正在执行的任务,onCancelled()方法将会被调用
                    downLoaderAsyncTask.cancel(true);
                    break;
                default:
                    break;
            }
        }
    }
    //构造函数AsyncTask<Params, Progress, Result>参数说明:
//Params 启动任务执行的输入参数
//Progress 后台任务执行的进度
//Result 后台计算结果的类型
    private class DownLoaderAsyncTask extends AsyncTask<String, Integer, String>{
        //onPreExecute()方法用于在执行异步任务前,主线程做一些准备工作
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            textView.setText("调用onPreExecute()方法--->准备开始执行异步任务");
            System.out.println("调用onPreExecute()方法--->准备开始执行异步任务");
        }
        //doInBackground()方法用于在执行异步任务,不可以更改主线程中UI
        @Override
        protected String doInBackground(String... params) {
            System.out.println("调用doInBackground()方法--->开始执行异步任务");
            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(params[0]);
                HttpResponse response = client.execute(get);
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    InputStream is = entity.getContent();
                    long total = entity.getContentLength();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int count = 0;
                    int length = -1;
                    while ((length = is.read(buffer)) != -1) {
                        bos.write(buffer, 0, length);
                        count += length;
//publishProgress()为AsyncTask类中的方法
//常在doInBackground()中调用此方法
//用于通知主线程,后台任务的执行情况.
//此时会触发AsyncTask中的onProgressUpdate()方法
                        publishProgress((int) ((count / (float) total) * 100));
//为了演示进度,休眠1000毫秒
                        Thread.sleep(1000);
                    }
                    return new String(bos.toByteArray(), "UTF-8");
                }
            } catch (Exception e) {
                return null;
            }
            return null;
        }
        //onPostExecute()方法用于异步任务执行完成后,在主线程中执行的操作
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(getApplicationContext(), "调用onPostExecute()方法--->异步任务执行完毕", 0).show();
//textView显示网络请求结果
            textView.setText(result);
            System.out.println("调用onPostExecute()方法--->异步任务执行完毕");
        }
        //onProgressUpdate()方法用于更新异步执行中,在主线程中处理异步任务的执行信息
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
//更改进度条
            progressBar.setProgress(values[0]);
//更改TextView
            textView.setText("已经加载"+values[0]+"%");
        }
        //onCancelled()方法用于异步任务被取消时,在主线程中执行相关的操作
        @Override
        protected void onCancelled() {
            super.onCancelled();
//更改进度条进度为0
            progressBar.setProgress(0);
//更改TextView
            textView.setText("调用onCancelled()方法--->异步任务被取消");
            System.out.println("调用onCancelled()方法--->异步任务被取消");
        }
    }
}
