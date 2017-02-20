package com.example.administrator.newss.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.newss.R;
import com.example.administrator.newss.adapter.ImageAdapter;
import com.example.administrator.newss.app.App;
import com.example.administrator.newss.entity.ImageInfo;
import com.example.administrator.newss.net.HttpClientUtil;
import com.example.administrator.newss.entity.ImagetoGank;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/10.
 */

public class ImageFragment extends Fragment {
    @BindView(R.id.fragment_image_rv)
    RecyclerView rv;
    private List<ImageInfo> data  = new ArrayList<>();
    //判断什么时候获取数据
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg != null){
                switch (msg.what){
                    case App.IMAGE_LOAG:
                        ImagetoGank gank = (ImagetoGank) msg.obj;
                        data.addAll(gank.getResults());
                        adapter.notifyDataSetChanged();
                        break;
                    case 0x18:
                        Toast.makeText(getContext(),"网络异常",Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }
    };
    private ImageAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//      imageFragmentView =inflater.inflate(R.layout.fragment_image,container,false);
        View view = inflater.inflate(R.layout.fragment_image,container,false);
        ButterKnife.bind(this,view);
        //创建适配器
        adapter = new ImageAdapter(getContext());
        adapter.setList(data);
        //设置其中的每一项是否拥有固定大小。
        rv.setHasFixedSize(true);
        //给RecyclerView设置布局管理器 参数一：显示几列 参数二：数据的排布方式
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
//      rv.setItemAnimator(new DefaultItemAnimator());//给每一个项设置动画
        //设置适配器
        rv.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //网络访问获取数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClientUtil.getResult(App.IMAGE_BASE+App.IMAGE_PATH ,handler);
            }
        }).start();
    }
}
