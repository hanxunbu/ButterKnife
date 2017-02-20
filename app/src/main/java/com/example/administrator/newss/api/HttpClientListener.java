package com.example.administrator.newss.api;

/**
 * Created by Administrator on 2017/1/5.
 */

public interface HttpClientListener {
    void getResultSucced(String result);
    void getResultFailer(String result);
    void getResultExctption(Exception e);
}
