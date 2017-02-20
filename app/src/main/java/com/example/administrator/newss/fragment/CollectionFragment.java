package com.example.administrator.newss.fragment;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.administrator.newss.R;
import com.example.administrator.newss.adapter.NewsAdapter;
import com.example.administrator.newss.db.NewsDao;
import com.example.administrator.newss.entity.NewsInfo;
import com.example.administrator.newss.ui.NewsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/10.
 */

public class CollectionFragment extends Fragment {
    private List<NewsInfo> data;//数据源
    private NewsAdapter adapter;
    private NewsDao dao;
    @BindView(R.id.fragment_collection_lv)
    ListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collection, container, false);
        ButterKnife.bind(this, view);
        dao = new NewsDao(getContext());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //查询数据库，显示数据
        data = dao.select();
//        Log.i("Tag",data.size()+"");

        adapter = new NewsAdapter(getContext());
        adapter.setData(data);
        lv.setAdapter(adapter);

        //添加监听事件
        //跳转到newsActicity
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View iv = view.findViewById(R.id.adapter_nf_item_iv);
                //跳转到下个页面（带值）
                String title = adapter.getItem(position).getImageurl();
                String imageUrl = adapter.getItem(position).getImageurl();
                String url = adapter.getItem(position).getUrl();

                Intent intent = new Intent(getContext(), NewsActivity.class);

                intent.putExtra("title", title);
                intent.putExtra("imageUrl", imageUrl);
                intent.putExtra("url", url);

                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity(), iv, "newsImage").toBundle());
            }
        });
        //添加长按事件
        //弹出对话框，删除数据库中的该条数据
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("是否删除");
                //设置积极按钮   参数一：按钮的名字  参数二：按钮的监听事件
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        NewsInfo info = adapter.getData().get(position);
                        //点击弹出对话框的确定按钮，将数据从数据库中删除
                        dao.delete(info);
                        data.remove(info);
                        adapter.notifyDataSetChanged();
                        //让对话框消失
                        dialog.dismiss();
                    }
                });
                //设置消极按钮
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
                return true;
            }
        });
    }
}

