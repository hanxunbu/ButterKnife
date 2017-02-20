package com.example.administrator.newss.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.administrator.newss.R;
import com.example.administrator.newss.app.App;
import com.example.administrator.newss.fragment.CollectionFragment;
import com.example.administrator.newss.fragment.ImageFragment;
import com.example.administrator.newss.fragment.NewsFragment;
import com.example.administrator.newss.fragment.ViewPagerFragment;
import com.squareup.picasso.Picasso;
import java.util.HashMap;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, PlatformActionListener {
    ImageView user_image;
    TextView user_name;
    TextView qq_image;


    /**
     * 显示当前的fragment
     */
    Fragment mCurrentFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShareSDK.initSDK(this);
        setContentView(R.layout.activity_home);
        //初始化控件
        initSystemViews();
        //显示新闻界面
        showNewsFragment();

    }

    private void initSystemViews() {
        //初始toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //添加悬浮动作按钮的监听事件
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //设置侧滑开关
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hander = navigationView.getHeaderView(0);
        user_image = (ImageView) hander.findViewById(R.id.imageView);
        user_name = (TextView) hander.findViewById(R.id.nav_login_tv);
        qq_image = (TextView) hander.findViewById(R.id.qq);

        user_name.setOnClickListener(this);
        user_image.setOnClickListener(this);
        qq_image.setOnClickListener(this);
    }

    //重写按下返回键的方法
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //引入菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    //菜单的监听事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Navigation每一项的监听事件
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            //添加ViewPagerFragment
            showNewsFragment();
        } else if (id == R.id.nav_gallery) {
            //添加图片fragment
            showImageFragment();
        } else if (id == R.id.nav_slideshow) {
            //添加收藏fragment
            showCollectionFragment();
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
            //添加分享
            showShare();
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 显示图片的fragment
     */
    private void showImageFragment() {
        ImageFragment imageFragment = new ImageFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.activity_home_fl, imageFragment);
        mCurrentFragment = imageFragment;
        ft.commit();
    }

    /**
     * 显示新闻的fragment
     */
    private void showNewsFragment() {
        String[] type = new String[]{NewsFragment.TYPE_TOUTIAO, NewsFragment.TYPE_KEJI, NewsFragment.TYPE_GUOJI, NewsFragment.TYPE_SHEHUI, NewsFragment.TYPE_YULE, NewsFragment.TYPE_TIYU};
        ViewPagerFragment viewPagerFragment = new ViewPagerFragment(type);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.activity_home_fl, viewPagerFragment);
        mCurrentFragment = viewPagerFragment;
        ft.commit();
    }


    /**
     * 显示收藏的fragment
     */
    private void showCollectionFragment() {
        CollectionFragment collectionFragment = new CollectionFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.activity_home_fl, collectionFragment);
        mCurrentFragment = collectionFragment;
        ft.commit();
    }

    /**
     * shareSDK 一键分享的方法
     */
    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView:

                break;

            case R.id.nav_login_tv:
                //判断是否登录状态，登录状态不能点击，未登录状态跳转到登录页面
                if (user_name.getText().equals("请先登录")) {
                    startActivityForResult(new Intent(this, UserActivity.class), App.REQUESTCODE);
                } else {
                    user_name.setClickable(false);
                }
                break;
            case R.id.qq:
                Platform weibo = ShareSDK.getPlatform(QQ.NAME);
                weibo.SSOSetting(false);  //设置false表示使用SSO授权方式
                weibo.setPlatformActionListener(this); // 设置分享事件回调

                weibo.authorize();//单独授权
                weibo.showUser(null);//授权并获取用户信息
                break;
        }
    }

    /**
     * 用来处理返回值的方法
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            String name = data.getStringExtra("name");
//            String email = data.getStringExtra("email");
            user_name.setText(name);
//            user_email.setText(email);
        }
    }

    @Override
    public void onComplete(final Platform platform, final int i, HashMap<String, Object> hashMap) {
//        Platform qzone = ShareSDK.getPlatform(QQ.NAME);
//        String accessToken = platform.getDb().getToken(); // 获取授权token
//        String openId = platform.getDb().getUserId(); // 获取用户在此平台的ID
        final String nickname = platform.getDb().getUserName(); // 获取用户昵称
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                qq_image.setText(nickname);
                String image = platform.getDb().getUserIcon();
                Picasso.with(HomeActivity.this)
                        .load(image)
                        .error(R.drawable.ssdk_oks_classic_qq)
                        .into(user_image);

            }
        });
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(Platform platform, int i) {

    }
}