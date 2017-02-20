package com.example.administrator.newss.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrator.newss.R;
import com.example.administrator.newss.app.App;
import com.example.administrator.newss.entity.LoginInfo;
import com.example.administrator.newss.net.UserManager;
import com.example.administrator.newss.ui.HomeActivity;
import com.example.administrator.newss.ui.UserActivity;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by Administrator on 2017/1/16.
 */

public class LoginFragment extends Fragment  {

    @BindView(R.id.login_name_et)
    EditText name;
    @BindView(R.id.login_password_et)
    EditText password;
    @BindView(R.id.btn_login)
    Button login;
    @BindView(R.id.btn_register)
    Button register;
    @BindView(R.id.tv_forgot)
    TextView help;
    @BindView(R.id.qq)
    TextView qq;


    private String uname;
    private String upass;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg != null){
        switch (msg.what){
            case App.FALILER:
                Toast.makeText(getContext(),"请检查网络设置",Toast.LENGTH_SHORT).show();
                break;
            case App.SUCCEED:
                LoginInfo info = (LoginInfo) msg.obj;
                LoginInfo.LoginData data  = info.getData();
                if(data != null){
                    switch (data.getResult()){
                        case 0:
                            Toast.makeText(getContext(),"登录成功",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.putExtra("token",data.getToken());
                            intent.putExtra("name",uname);
                            getActivity().setResult(App.RESULTCODE,intent);
                            getActivity().finish();
                            break;
                        case -1:
                            Toast.makeText(getContext(),"用户名或密码错误",Toast.LENGTH_SHORT).show();
                            break;
                        case -2:
                            Toast.makeText(getContext(),"限制登陆(禁言,封IP)",Toast.LENGTH_SHORT).show();
                            break;
                        case -3:
                            Toast.makeText(getContext(),"限制登陆(异地登陆等异常)",Toast.LENGTH_SHORT).show();
                            break;
                    }

                    }else {
                        Toast.makeText(getContext(),"你输入的账号密码有误",Toast.LENGTH_LONG).show();
                }
                break;

        }
    }
}
};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment,container,false);

        ButterKnife.bind(this,view);
        return view;


    }
    @OnClick({R.id.btn_login,R.id.btn_register,R.id.tv_forgot})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_login:
                //获取用户输入
                 uname = name.getText().toString().trim();//去空格
                 upass = password.getText().toString().trim();
                //判断用户输入是否合法
                if(TextUtils.isEmpty(uname) || TextUtils.isEmpty(upass)){//isEmpty是否为空
                    Toast.makeText(getContext(),"请输入完整的用户名和密码",Toast.LENGTH_LONG).show();
                }else {
                    //网络请求，服务验证登录是否成功
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserManager.login(uname,upass,handler);
                        }
                    }).start();
                }
                break;
            case R.id.btn_register://点击注册按钮
                UserActivity activity_register = (UserActivity) getActivity();
                activity_register.setTargetFragment("RegisterFragment");
                break;
            case R.id.tv_forgot:
                UserActivity activity_forgot = (UserActivity) getActivity();
                activity_forgot.setTargetFragment("ForgotFragment");
                break;

        }
    }


}
