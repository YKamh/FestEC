package com.myself.latte.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.myself.latte.delegates.LatteDelegate;
import com.myself.latte.ec.R;
import com.myself.latte.ec.R2;
import com.myself.latte.log.LatteLogger;
import com.myself.latte.net.RestClient;
import com.myself.latte.net.callback.ISuccess;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Kamh on 2018/2/7.
 */

public class SignInDelegate extends LatteDelegate {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_pwd)
    TextInputEditText mPwd = null;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener){
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn(){
        if (checkForm()){
            RestClient.builder()
                    .url("sign_up")
                    .params("email", mEmail.getText().toString())
                    .params("password", mPwd.getText().toString())
                    .sueccess(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LatteLogger.json("USER_PROFILE", response);
                            //将用户数据持久化写进数据库
                            SignHandler.onSignIn(response, mISignListener);
                        }
                    })
                    .build()
                    .post();
            Toast.makeText(getContext(), "验证通过", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeChat(){

    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink(){
        start(new SignUpDelegate());
    }

    //输入框验证方法
    private boolean checkForm(){
        final String email = mEmail.getText().toString();
        final String pwd = mPwd.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        }else{
            mEmail.setError(null);
        }

        if (pwd.isEmpty()||pwd.length()<6){
            mPwd.setError("请输入姓名");
            isPass = false;
        }else{
            mPwd.setError(null);
        }
        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
