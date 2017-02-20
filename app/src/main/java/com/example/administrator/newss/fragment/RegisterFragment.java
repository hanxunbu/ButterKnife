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
import android.widget.Toast;

import com.example.administrator.newss.R;
import com.example.administrator.newss.app.App;
import com.example.administrator.newss.entity.LoginInfo;
import com.example.administrator.newss.net.UserManager;
import com.example.administrator.newss.ui.UserActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/1/16.
 */

public class RegisterFragment extends Fragment{

    @BindView(R.id.register_name_et)
    EditText name;
    @BindView(R.id.register_email_et)
    EditText email;
    @BindView(R.id.register_pwd_et)
    EditText password;
    @BindView(R.id.register_zhuce_btn)
    Button commit;
    @BindView(R.id.register_cancel_btn)
    Button cancel;

    String uname;
    String upass;
    String uemail;

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
//                        System.out.println(data+"---------------------------------------");
                        if (data != null){
                            switch (data.getResult()){
                                case 0:
                                    Toast.makeText(getContext(),"注册成功",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent();
                                    intent.putExtra("token",data.getToken());
                                    intent.putExtra("name",uname);
                                    getActivity().setResult(App.RESULTCODE,intent);
                                    UserActivity activity = (UserActivity) getActivity();
                                    activity.setTargetFragment("LoginFragment");
                                    break;
                                case -1:
                                    Toast.makeText(getContext(),"服务器不允许注册(用户数量已满)",Toast.LENGTH_SHORT).show();
                                    break;
                                case -2:
                                    Toast.makeText(getContext(),"用户名重复",Toast.LENGTH_SHORT).show();
                                    break;
                                case -3:
                                    Toast.makeText(getContext(),"邮箱重复",Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }else {
                            Toast.makeText(getContext(),"你输入的信息有误",Toast.LENGTH_SHORT).show();
                        }
                        break;

                }
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick({R.id.register_zhuce_btn,R.id.register_cancel_btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.register_zhuce_btn:
                //获取用户输入
                uname = name.getText().toString().trim();//去空格
                upass = password.getText().toString().trim();
                uemail = email.getText().toString().trim();

                Pattern p = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
                Matcher m = p.matcher(uemail);

                if(TextUtils.isEmpty(uname)){
                    Toast.makeText(getContext(),"请输入用户名",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(upass)){
                    Toast.makeText(getContext(),"请输入密码",Toast.LENGTH_SHORT).show();
                }else if(!m.matches()){
                    Toast.makeText(getContext(),"请输入正确的邮箱地址",Toast.LENGTH_SHORT).show();
                }else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserManager.register(uname,upass,uemail,handler);
                        }
                    }).start();
                }
                break;
            case R.id.register_cancel_btn:
                getActivity().finish();
                break;
        }
    }
}

