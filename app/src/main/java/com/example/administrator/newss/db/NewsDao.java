package com.example.administrator.newss.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.administrator.newss.entity.NewsInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/12.
 */

public class NewsDao {
    private static MyOpenHelper helper;
    private  static SQLiteDatabase db;

    public  NewsDao(Context context){
        helper = new MyOpenHelper(context);
    }
    //写增删改查的方法
    public void init(){
        //打开数据库
        db = helper.getReadableDatabase();
    }
    //添加的方法
    public boolean insert( NewsInfo info) {
        boolean isExist = isNewsExist(info);
            if (isExist){
                db.close();

                return false;//返回添加失败
            }else{
                ContentValues contentValues = new ContentValues();
                contentValues.put("newsURL",info.getUrl());
                contentValues.put("imageURL",info.getImageurl());
                contentValues.put("date",info.getDate());
                contentValues.put("title",info.getTitle());
                contentValues.put("author_name",info.getAuthor_name());
                db.insert("News",null,contentValues);
//                Log.i("Tag","-------------------------------1"+info.toString());
                db.close();
                return true;//返回添加成功
            }
    }
    public void delete( NewsInfo info){
        init();
        //根据newsURL进行数据删除
        db.delete("News","newsUrl = ?",new String[]{info.getUrl()});
        db.close();
    }
    //查找的方法
    public List<NewsInfo> select(){
        init();
        List<NewsInfo> list = new ArrayList<>();
        Cursor cursor = db.query("News",null,null,null,null,null,null);
        while (cursor.moveToNext()){
            String newsURL = cursor.getString(cursor.getColumnIndex("newsURL"));
            String imageURL = cursor.getString(cursor.getColumnIndex("imageURL"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String aothor_name = cursor.getString(cursor.getColumnIndex("author_name"));

            NewsInfo newsInfo = new NewsInfo(title,date,aothor_name,newsURL,imageURL);
//            Log.i("Tag","-----------------------------");
            list.add(newsInfo);
        }
        return list;
    }

    public boolean isNewsExist(NewsInfo newsInfo){
        init();
        Cursor cursor = db.query("News",null,"newsURL = ?",new String[]{newsInfo.getUrl()},null,null,null );
        Log.i("Tag",newsInfo.getUrl()+"=============================");
        if (cursor.moveToFirst()){
            return true;//已经存在该数据
        }else {
            return false;//不存在
        }
    }


}
