package com.myself.latte.ec.mian.car;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.view.View;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.myself.latte.delegates.bottom.BottomItemDelegate;
import com.myself.latte.ec.R;
import com.myself.latte.ec.R2;
import com.myself.latte.net.RestClient;
import com.myself.latte.net.callback.ISuccess;
import com.myself.latte.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Kamh on 2018/4/9.
 */

public class ShopCarDelegate extends BottomItemDelegate implements ISuccess, ICarItemListener {

    private ShopCarAdapter mAdapter = null;
    //购物车数量标记
    private int mCurrentCount = 0;
    private int mTotalCount = 0;
    private double mTotalPrice = 0.00;


    @BindView(R2.id.rv_shop_car)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.icon_shop_car_select_all)
    IconTextView mIconSelectedAll = null;
    @BindView(R2.id.stub_no_item)
    ViewStubCompat mStubCompat = null;
    @BindView(R2.id.tv_shop_total_price)
    AppCompatTextView mTvTotalPrice = null;

    @OnClick(R2.id.icon_shop_car_select_all)
    void onClickSelectAll() {
        final int tag = (int) mIconSelectedAll.getTag();
        if (tag == 0) {
            mIconSelectedAll.setTextColor(ContextCompat.getColor(getContext(), R.color.app_main));
            mIconSelectedAll.setTag(1);
            mAdapter.setIsSelectAll(true);
            //更新RecyclerVIew的显示状态
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        } else {
            mIconSelectedAll.setTextColor(Color.GRAY);
            mIconSelectedAll.setTag(0);
            mAdapter.setIsSelectAll(false);
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        }
    }

    @OnClick(R2.id.tv_top_shop_car_remove_selected)
    void onClickRemoveSelectedItem() {
        final List<MultipleItemEntity> data = mAdapter.getData();
        //找到要删除的数据
        List<MultipleItemEntity> deleteEntity = new ArrayList<>();
        for (MultipleItemEntity entity : data) {
            final boolean isSelected = entity.getField(ShopCarItemFields.IS_SELECTED);
            if (isSelected) {
                deleteEntity.add(entity);
            }
        }
        for (MultipleItemEntity entity : deleteEntity) {
            int removePosition;
            final int entityPosition = entity.getField(ShopCarItemFields.POSITION);
            if (entityPosition > mCurrentCount - 1) {
                removePosition = entityPosition - (mTotalCount - mCurrentCount);
            } else {
                removePosition = entityPosition;
            }
            if (removePosition <= mAdapter.getItemCount()) {
                mAdapter.remove(removePosition);
                mCurrentCount = mAdapter.getItemCount();
                //更新数据
                mAdapter.notifyItemRangeChanged(removePosition, mAdapter.getItemCount());
            }
        }
        checkItemCount();
    }

    @OnClick(R2.id.tv_top_shop_car_clear)
    void onClickClear() {
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
        checkItemCount();
    }

    @OnClick(R2.id.tv_shop_car_pay)
    void onClickPay(){

    }

    //创建订单和支付是没有关系的
    private void createOrder(){
        final String orderUrl = "";
        final WeakHashMap<String, Object> orderParams = new WeakHashMap<>();
        orderParams.put("userId", 264392);
        orderParams.put("amount", 0.01);
        orderParams.put("comment", "测试支付");
        orderParams.put("type", 1);
        orderParams.put("orderType", 0);
        orderParams.put("isanonymous", true);
        orderParams.put("followeduer", 0);
        RestClient.builder()
                .url(orderUrl)
                .loader(getContext())
                .params(orderParams)
                .sueccess(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                    }
                })
                .build()
                .post();
    }

    @SuppressLint("RestrictedApi")
    private void checkItemCount() {
        final int count = mAdapter.getItemCount();
        if (count == 0) {
            final View stubView = mStubCompat.inflate();
            final AppCompatTextView tvToBy = stubView.findViewById(R.id.tv_stub_to_buy);
            tvToBy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "您该购物啦！", Toast.LENGTH_SHORT).show();
                }
            });
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

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
        mAdapter.setICarItemListener(this);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        mTotalPrice = mAdapter.getTotalPrice();
        checkItemCount();
    }

    @Override
    public void onItemClick(double itemTotalPrice) {
        final double price = mAdapter.getTotalPrice();
        mTvTotalPrice.setText(String.valueOf(price));
    }
}
