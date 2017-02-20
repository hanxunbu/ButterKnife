package com.example.administrator.newss.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.newss.R;
import com.example.administrator.newss.adapter.PagerFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/9.
 */
public class ViewPagerFragment  extends Fragment{
    @BindView(R.id.viewpagerfragment_tab)
    TabLayout title_tab;
    @BindView(R.id.viewpagerfragment_vp)
    ViewPager news_vp;
    List<NewsFragment> data = new ArrayList<>();
    PagerFragmentAdapter adapter;
    String [] type = new String[]{NewsFragment.TYPE_TOUTIAO};

    public ViewPagerFragment() {}

    @SuppressLint("ValidFragment")
    public ViewPagerFragment(String[] type) {
        this.type = type;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_viewpager,container,false);
        ButterKnife.bind(this,view);
        //设置适配器
        adapter = new PagerFragmentAdapter(getChildFragmentManager());
        news_vp.setAdapter(adapter);
        //初始化标题栏
        initTablagyout();
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化数据栏
        initPagerData();
    }

    /**
     * Viewpager的数据源
     * */
    private void initPagerData(){
        for(int i = 0;i < type.length ; i++){
            NewsFragment fragment = new NewsFragment(type[i]);
            data.add(fragment);
        }
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    /**
     * 初始化标题栏
     * */
    private void initTablagyout(){
        //添加标签
        title_tab.addTab(title_tab.newTab().setText("头条"));
        title_tab.addTab(title_tab.newTab().setText("科技"));
        title_tab.addTab(title_tab.newTab().setText("国际"));
        title_tab.addTab(title_tab.newTab().setText("社会"));
        title_tab.addTab(title_tab.newTab().setText("娱乐"));
        title_tab.addTab(title_tab.newTab().setText("体育"));

        //添加Tab的监听事件
       news_vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(title_tab));

        title_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                news_vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
