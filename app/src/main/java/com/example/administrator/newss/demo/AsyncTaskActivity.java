package com.example.administrator.newss.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.example.administrator.newss.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/5.
 */

public class AsyncTaskActivity extends AppCompatActivity {
   @BindView(R.id.asynctask_pb)
   ProgressBar pb;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asynctask_activity);
        ButterKnife.bind(this);

        AsyncTaskDemo task = new AsyncTaskDemo(pb,this);
        task.execute();





    }
}
