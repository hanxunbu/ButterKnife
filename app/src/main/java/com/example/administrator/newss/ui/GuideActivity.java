package com.example.administrator.newss.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.newss.R;
import com.example.administrator.newss.adapter.GuidePagerAdapter;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.targetClass;

/**
 * Created by Administrator on 2017/1/4.
 */

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    @BindView(R.id.activity_guide_circle_iv1)
    ImageView cirle_01;
    @BindView(R.id.activity_guide_circle_iv2)
    ImageView cirle_02;
    @BindView(R.id.activity_guide_circle_iv3)
    ImageView cirle_03;
    @BindView(R.id.activity_guide_circle_iv4)
    ImageView cirle_04;
    @BindView(R.id.activity_guide_vp)
    ViewPager vp;
    @BindView(R.id.activity_guide_bt)
    Button bt;

    private List<View> list = new ArrayList<>();
    private LayoutInflater inflater;
    private boolean isFirstCome;//flase 表示不是第一次来
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isFirstCome = getSharedPreferences("share",MODE_PRIVATE).getBoolean("isFirstCome",false);
        if(!isFirstCome || getIntent().getBooleanExtra("first",false)){
            setContentView(R.layout.activity_guide);
            ButterKnife.bind(this);

            initData();

            SharedPreferences.Editor edit = getSharedPreferences("share",MODE_PRIVATE).edit();
            edit.putBoolean("isFirstCome",true);
            edit.commit();

            GuidePagerAdapter adapter = new GuidePagerAdapter(list);
            vp.setAdapter(adapter);
            vp.addOnPageChangeListener(this);

            // ATTENTION: This was auto-generated to implement the App Indexing API.
            // See https://g.co/AppIndexing/AndroidStudio for more information.
            client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        } else {
            //跳转到Home页
            startActivity(new Intent(this,HomeActivity.class));
            finish();
        }

    }

    private void initData() {
        inflater = LayoutInflater.from(this);
        list.add(inflater.inflate(R.layout.guide_item_01, null));
        list.add(inflater.inflate(R.layout.guide_item_02, null));
        list.add(inflater.inflate(R.layout.guide_item_03, null));
        list.add(inflater.inflate(R.layout.guide_item_04, null));
    }

    @OnClick(R.id.activity_guide_bt)
    public void aa(View v) {
        switch (v.getId()) {
            case R.id.activity_guide_bt:
                if(getIntent().getBooleanExtra("first",false)){
                    finish();
                }else {
                    startActivity(new Intent(this,HomeActivity.class));
                }

                break;
        }
    }


    /**
     * 页面在滑动时会调用的方法
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 页面最终停止时会调用的方法
     */
    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            cirle_01.setImageResource(R.drawable.shape_guide_circle_red);
            cirle_02.setImageResource(R.drawable.shape_guide_circle_blue);
            cirle_03.setImageResource(R.drawable.shape_guide_circle_blue);
            cirle_04.setImageResource(R.drawable.shape_guide_circle_blue);
            bt.setVisibility(View.INVISIBLE);
        } else if (position == 1) {
            cirle_01.setImageResource(R.drawable.shape_guide_circle_blue);
            cirle_02.setImageResource(R.drawable.shape_guide_circle_red);
            cirle_03.setImageResource(R.drawable.shape_guide_circle_blue);
            cirle_04.setImageResource(R.drawable.shape_guide_circle_blue);
            bt.setVisibility(View.INVISIBLE);
        } else if (position == 2) {
            cirle_01.setImageResource(R.drawable.shape_guide_circle_blue);
            cirle_02.setImageResource(R.drawable.shape_guide_circle_blue);
            cirle_03.setImageResource(R.drawable.shape_guide_circle_red);
            cirle_04.setImageResource(R.drawable.shape_guide_circle_blue);
            bt.setVisibility(View.INVISIBLE);
        } else if (position == 3) {
            cirle_01.setImageResource(R.drawable.shape_guide_circle_blue);
            cirle_02.setImageResource(R.drawable.shape_guide_circle_blue);
            cirle_03.setImageResource(R.drawable.shape_guide_circle_blue);
            cirle_04.setImageResource(R.drawable.shape_guide_circle_red);
            bt.setVisibility(View.VISIBLE);
        }

    }


    /**
     * 当页面状态发生变化时
     */
    @Override
    public void onPageScrollStateChanged(int state) {

    }


}

