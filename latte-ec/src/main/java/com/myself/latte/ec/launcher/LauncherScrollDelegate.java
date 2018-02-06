package com.myself.latte.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.myself.latte.delegates.LatteDelegate;
import com.myself.latte.ec.R;
import com.myself.latte.ui.launcher.LauncherHolderCreator;
import com.myself.latte.ui.launcher.ScrollLauncherTag;
import com.myself.latte.util.storage.LattePreference;

import java.util.ArrayList;

/**
 * Created by Kamh on 2018/2/6.
 */

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener{

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();


    private void initBanner(){
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        mConvenientBanner.setPages(new LauncherHolderCreator(), INTEGERS)//传入holder以及数据源
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})//设置底下的小点点
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)//设置小点点的位置
                .setOnItemClickListener(this)//绑定点击事件
                .setCanLoop(true);//设置可循环滚动
    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        //如果点击的是最后一个
        if (position == INTEGERS.size()-1){
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            //检查用户是否已经登录

        }
    }
}
