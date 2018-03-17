package com.myself.latte.ec.mian.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.myself.latte.delegates.bottom.BottomItemDelegate;
import com.myself.latte.ec.R;
import com.myself.latte.ec.mian.sort.content.ContentDelegate;
import com.myself.latte.ec.mian.sort.list.VerticalListDelegate;

/**
 * Created by Kamh on 2018/3/1.
 */

public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    //当Fragment显示的时候才开始加载布局
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate listDelegate = new VerticalListDelegate();
//        loadRootFragment(R.id.vertical_list_container, listDelegate);
//        replaceFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));
//        load
    }
}
