package com.example.administrator.newss.entity;

/**
 * Created by Administrator on 2017/1/18.
 */

public class LoginInfo {
    String message;
    int status;
    LoginData data;

    public LoginInfo(String message, int status, LoginData data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {

        this.status = status;
    }

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
    }

    public class LoginData{
        int result;
        String token;
        String explain;
        public LoginData(int result, String token, String explain) {
            this.result = result;
            this.token = token;
            this.explain = explain;
        }

        public int getResult() {
            return result;
        }

        public String getToken() {
            return token;
        }

        public String getExplain() {
            return explain;
        }
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }
}
