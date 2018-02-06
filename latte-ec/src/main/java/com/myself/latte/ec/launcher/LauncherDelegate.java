package com.myself.latte.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.view.View;

import com.myself.latte.delegates.LatteDelegate;
import com.myself.latte.ec.R;
import com.myself.latte.ec.R2;

import butterknife.BindView;

/**
 * Created by Kamh on 2018/2/5.
 */

public class LauncherDelegate extends LatteDelegate {

    @BindView(R2.id.tv_launcher_timer)
    AppCompatAutoCompleteTextView mTvTimer = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
