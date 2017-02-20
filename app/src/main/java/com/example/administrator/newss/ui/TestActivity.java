package com.example.administrator.newss.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.administrator.newss.R;
import com.example.administrator.newss.api.HttpClientListener;
import com.example.administrator.newss.app.App;
import com.example.administrator.newss.entity.NewstoJuhe;
import com.example.administrator.newss.net.HttpClientUtil;
import com.google.gson.Gson;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Administrator on 2017/1/5.
 */

public class TestActivity extends AppCompatActivity{
    public static final String TYPE_TOP = "top";
    public static final String TYPE_GUOJI = "guoji";


    private HttpClientListener listener = new HttpClientListener() {
        @Override
        public void getResultSucced(String result) {
            System.out.println(result);
            //Json解析
            Gson gson = new Gson();
            //Gson解析
            NewstoJuhe newstoJuhe = gson.fromJson(result,NewstoJuhe.class);
            //获取解析的结果
            List<NewstoJuhe.NewsData> datas = newstoJuhe.getResult().getData();
            for (NewstoJuhe.NewsData info : datas){
                System.out.println(info);
                //将解析好的数据添加到数据源中
            }
        }

        @Override
        public void getResultFailer(final String result) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(TestActivity.this,result,Toast.LENGTH_LONG).show();
                }
            });
        }

        @Override
        public void getResultExctption(Exception e) {
            Toast.makeText(TestActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asynctask_activity);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClientUtil.getResult(new URL(App.BASEURL + TYPE_GUOJI+ App.APP_KRY),listener);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
