package com.myself.latte.ec.mian.car;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;
import com.myself.latte.delegates.bottom.BottomItemDelegate;
import com.myself.latte.ec.R;
import com.myself.latte.ec.R2;
import com.myself.latte.net.RestClient;
import com.myself.latte.net.callback.ISuccess;
import com.myself.latte.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Kamh on 2018/4/9.
 */

public class ShopCarDelegate extends BottomItemDelegate implements ISuccess {

    @BindView(R2.id.rv_shop_car)
    RecyclerView mRecyclerView = null;

    @BindView(R2.id.icon_shop_car_select_all)
    IconTextView mIconSelectedAll = null;

    @OnClick(R2.id.icon_shop_car_select_all)
    void onClickSelectAll(){
        final int tag = (int) mIconSelectedAll.getTag();
        if (tag == 0){
            mIconSelectedAll.setTextColor(ContextCompat.getColor(getContext(), R.color.app_main));
            mIconSelectedAll.setTag(1);
            mAdapter.setIsSelectAll(true);
            //更新RecyclerVIew的显示状态
            mAdapter.notifyDataSetChanged();
        }else{
            mIconSelectedAll.setTextColor(Color.GRAY);
            mIconSelectedAll.setTag(0);
            mAdapter.setIsSelectAll(false);
            mAdapter.notifyDataSetChanged();
        }
    }

    private ShopCarAdapter mAdapter = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_car;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mIconSelectedAll.setTag(0);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("shop_car.php")
                .loader(getContext())
                .sueccess(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        final ArrayList<MultipleItemEntity> data = new ShopCarDataConverter().setJsonData(response).convert();
        mAdapter = new ShopCarAdapter(data);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
