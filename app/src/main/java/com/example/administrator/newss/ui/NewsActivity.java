package com.example.administrator.newss.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.administrator.newss.R;
import com.example.administrator.newss.view.ActionbarView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Administrator on 2017/1/10.
 */

public class NewsActivity extends AppCompatActivity {
    @BindView(R.id.activity_news_iv)
    ImageView image;
    @BindView(R.id.acticity_news_wv)
    WebView wv;
    @BindView(R.id.activity_news_toolbar)
    Toolbar toolbar;

    private String title;
    private String url;
    private String imageUrl;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);

        //获取上个页面传递的值
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        imageUrl = getIntent().getStringExtra("imageUrl");
        //设置Actionbar
        setSupportActionBar(toolbar);// 设置actionbar为toolbar
        getSupportActionBar().setTitle(title);//给toolbar赋值

        //设置图片
        Picasso.with(this)
                .load(imageUrl)
                .error(R.mipmap.ic_launcher)
                .into(image);

        wv.loadUrl(url);
    }
    /***
     * 设置浮动按钮的监听事件
     *
     */
    @OnClick(R.id.fab)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.fab:
//                Snackbar.make(view,"Replace your own action",Snackbar.LENGTH_LONG)
//                        .setAction("action",null).show();
                showShare();
                break;
        }
    }
    /**
     * shareSDK 一键分享的方法
     * */
    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("哈哈");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl(url);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(title);
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);
    }
}
