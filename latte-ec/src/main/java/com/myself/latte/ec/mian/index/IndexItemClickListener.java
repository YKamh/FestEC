package com.myself.latte.ec.mian.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.myself.latte.delegates.LatteDelegate;
import com.myself.latte.ec.detail.GoodsDetailDelegate;

/**
 * Created by Kamh on 2018/3/16.
 * IndexItem点击事件监听类
 */

public class IndexItemClickListener extends SimpleClickListener{

    private final LatteDelegate DELEGATE;

    private IndexItemClickListener(LatteDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static SimpleClickListener create(LatteDelegate delegate){
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        final GoodsDetailDelegate detailDelegate = GoodsDetailDelegate.create();
        DELEGATE.getSupportDelegate().start(detailDelegate);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {}

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {}

    @Override
    public void onItemChildLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {}
}
