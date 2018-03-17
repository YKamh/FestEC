package com.myself.latte.ec.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.myself.latte.delegates.LatteDelegate;
import com.myself.latte.ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by Kamh on 2018/3/17.
 */

public class GoodsDetailDelegate extends LatteDelegate{

    public static GoodsDetailDelegate create(){
        return new GoodsDetailDelegate();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_datail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
