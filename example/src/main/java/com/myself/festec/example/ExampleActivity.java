package com.myself.festec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.myself.latte.activities.ProxyActivity;
import com.myself.latte.app.Latte;
import com.myself.latte.delegates.LatteDelegate;
import com.myself.latte.ec.launcher.LauncherDelegate;
import com.myself.latte.ec.launcher.LauncherScrollDelegate;
import com.myself.latte.ec.mian.EcBottomDelegate;
import com.myself.latte.ec.sign.ISignListener;
import com.myself.latte.ec.sign.SignInDelegate;
import com.myself.latte.ec.sign.SignUpDelegate;
import com.myself.latte.ui.launcher.ILauncherListener;
import com.myself.latte.ui.launcher.OnLauncherFinishTag;

public class ExampleActivity extends ProxyActivity implements
        ISignListener,
        ILauncherListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        Latte.getConfigurator().withActivity(this);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
//        switch (tag){
//            case SIGNED:
                startWithPop(new EcBottomDelegate());
//                break;
//            case NOT_SIGNED:
//                //启动fragment并把上一个Fragment清除
//                startWithPop(new SignInDelegate());
//                break;
//            default:
//
//                break;
//        }
    }
}
