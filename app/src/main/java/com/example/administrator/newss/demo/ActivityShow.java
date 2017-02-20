package com.example.administrator.newss.demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.newss.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/1/5.
 */

public class ActivityShow extends AppCompatActivity {
    private String imageUrl = "http://pic.58pic.com/58pic/16/58/28/80M58PICTcs_1024.jpg";
    private Bitmap bitmap;

    @BindView(R.id.activity_show_btn)
    Button btn;
    @BindView(R.id.activity_show_image)
    ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);



    }
    @OnClick(R.id.activity_show_btn)
    public void a(View v){
        switch (v.getId()){
            case R.id.activity_show_btn:
                image.setImageBitmap(returnBitMap(imageUrl));
                break;
        }
    }

    public Bitmap returnBitMap(String url){
        URL myFileUrl = null;
        bitmap = null;

        try {
            myFileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
