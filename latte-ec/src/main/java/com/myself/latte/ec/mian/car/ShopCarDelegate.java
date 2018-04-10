package com.myself.latte.ec.mian.car;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.myself.latte.delegates.bottom.BottomItemDelegate;
import com.myself.latte.ec.R;
import com.myself.latte.ec.R2;
import com.myself.latte.net.RestClient;
import com.myself.latte.net.callback.ISuccess;
import com.myself.latte.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Kamh on 2018/4/9.
 */

public class ShopCarDelegate extends BottomItemDelegate implements ISuccess {

    @BindView(R2.id.rv_shop_car)
    RecyclerView mRecyclerView = null;
    private ShopCarAdapter mAdapter = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_car;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

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
