package com.myself.latte.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by Administrator on 2018/1/17.
 * 我们并不希望之后的使用者，包括我们自己去new出实例，所以用抽象类来声明他
 * 实现视图注入
 */

public abstract class BaseDelegates extends SwipeBackFragment{

    @SuppressWarnings("SpellCheckingInspection")
    private Unbinder mUnbinder = null;

    //可以传入是一个View 也可以是一个Layout的id
    //输入口
    public abstract Object setLayout();

    public abstract void onBindView(@Nullable Bundle savedInstanceState, View rootView);

    //处理
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        if (setLayout() instanceof Integer){
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        }else if (setLayout() instanceof View){
            rootView = (View) setLayout();
        }
        if (rootView!=null){
            mUnbinder = ButterKnife.bind(this, rootView);
            onBindView(savedInstanceState, rootView);
        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null){
            //解除绑定
            mUnbinder.unbind();
        }
    }
}
