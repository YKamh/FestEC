package com.myself.festec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.myself.latte.delegates.LatteDelegate;

/**
 * Created by Administrator on 2018/1/18.
 */

public class ExampleDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
