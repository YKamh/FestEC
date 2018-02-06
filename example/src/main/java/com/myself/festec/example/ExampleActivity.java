package com.myself.festec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.myself.latte.activities.ProxyActivity;
import com.myself.latte.delegates.LatteDelegate;
import com.myself.latte.ec.launcher.LauncherDelegate;
import com.myself.latte.ec.launcher.LauncherScrollDelegate;
import com.myself.latte.ec.sign.SignUpDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new SignUpDelegate();
    }
}
