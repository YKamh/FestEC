package com.myself.latte.ec.mian.personal;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.myself.latte.delegates.LatteDelegate;
import com.myself.latte.ec.mian.personal.list.ListBean;

/**
 * Created by Kamh on 2018/5/5.
 */

public class PersonalOnclickListener extends SimpleClickListener {

    private final LatteDelegate DELEGATE;

    public PersonalOnclickListener(LatteDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        int id = bean.getId();
        switch (id){
            case 1:
                DELEGATE.getParentDelegate().getSupportDelegate().start(bean.getLatteDelegate());
                break;
            case 2:
                DELEGATE.getParentDelegate().getSupportDelegate().start(bean.getLatteDelegate());
                break;
            default:
                break;
        }

    }

    @Override
    public void onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }
}
