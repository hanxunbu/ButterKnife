package com.example.administrator.newss.adapter;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.newss.fragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/9.
 */
public class PagerFragmentAdapter extends FragmentPagerAdapter {
    List<NewsFragment> data = new ArrayList<>();

    public PagerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public NewsFragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    public List<NewsFragment> getData() {
        return data;
    }

    public void setData(List<NewsFragment> data) {
        this.data = data;
    }
}
