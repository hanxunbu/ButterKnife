package com.example.administrator.newss.entity;

/**
 * Created by Administrator on 2017/1/17.
 */

public class RegisterInfo {
    private String uid;
    private String email;
    private String pwd;
    private String token;

    public RegisterInfo(String uid, String email, String pwd, String token) {
        this.uid = uid;
        this.email = email;
        this.pwd = pwd;
        this.token = token;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
