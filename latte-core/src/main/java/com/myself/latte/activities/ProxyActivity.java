package com.myself.latte.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.myself.latte.R;
import com.myself.latte.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by Administrator on 2018/1/17.
 * 容器Activity
 */

public abstract class ProxyActivity extends SupportActivity {
    //返回BaseDelegates
    //输入口
    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }
    //处理
    private void initContainer(@Nullable Bundle savedInstanceState){
        //实例化一个布局
        final ContentFrameLayout container = new ContentFrameLayout(this);
        //赋予该布局一个ID
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null){
            //赋予id 并显示
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
