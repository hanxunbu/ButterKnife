package com.example.administrator.newss.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.newss.R;
import com.example.administrator.newss.api.ShowWhatFragmentListener;
import com.example.administrator.newss.fragment.ForgotFragment;
import com.example.administrator.newss.fragment.LoginFragment;
import com.example.administrator.newss.fragment.RegisterFragment;

/**
 * Created by Administrator on 2017/1/17.
 */

public class UserActivity extends AppCompatActivity implements ShowWhatFragmentListener{

   private  Fragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        //默认显示登录的fragment
        showLoginFragment();

    }

    @Override
    public void setTargetFragment(String targetFragment) {
        switch (targetFragment){
            case "LoginFragment":
                showLoginFragment();
                break;
            case "RegisterFragment" :
                showRegisterFragment();
                break;
            case "ForgotFragment" :
                showForgotFragment();
                break;
        }
    }
        /**
         * 显示注册的fragment
         *
         * */
    private void showRegisterFragment() {
        RegisterFragment registerFragment = new RegisterFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        fragment = registerFragment;
        ft.replace(R.id.login_fragment,fragment);
        ft.commit();
    }
    /**登录页面*/
    private void showLoginFragment() {
        LoginFragment loginFragment = new LoginFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        fragment = loginFragment;
        ft.replace(R.id.login_fragment,fragment);
        ft.commit();
    }
    /**找回密码*/
    private void showForgotFragment(){
        ForgotFragment forgotFragment = new ForgotFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        fragment = forgotFragment;
        ft.replace(R.id.login_fragment,fragment);
        ft.commit();
    }

}
