package com.example.administrator.newss.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/1/4.
 */

public class GuidePagerAdapter extends PagerAdapter {
    private List<View> list;

    public GuidePagerAdapter(List<View> list){
        this.list = list;
    }
    /**返回viewPager要显示的页面的数量*/
    @Override
    public int getCount() {
        return list.size();
    }
    /**判断视图是否来至于View对象*/
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    /**添加下一个即将显示的View*/
    @Override
    public Object instantiateItem(ViewGroup container, int position){
        //将数据源中的view对象，添加到容器中（如果显示，如何显示）
        container.addView(list.get(position));
        return list.get(position);
    }
    /**销毁上一个View*/
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));

    }
}
