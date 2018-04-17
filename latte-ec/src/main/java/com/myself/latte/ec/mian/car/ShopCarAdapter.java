package com.myself.latte.ec.mian.car;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.joanzapata.iconify.widget.IconTextView;
import com.myself.latte.app.Latte;
import com.myself.latte.ec.R;
import com.myself.latte.net.RestClient;
import com.myself.latte.net.callback.ISuccess;
import com.myself.latte.ui.recycler.MultipleFields;
import com.myself.latte.ui.recycler.MultipleItemEntity;
import com.myself.latte.ui.recycler.MultipleRecyclerAdapter;
import com.myself.latte.ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * Created by Kamh on 2018/4/10.
 */

public class ShopCarAdapter extends MultipleRecyclerAdapter {

    private boolean mIsSelectedAll = false;
    private ICarItemListener mICarItemListener = null;
    private double mTotalPrice = 0.00;

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    protected ShopCarAdapter(List<MultipleItemEntity> data) {
        super(data);
        //初始化总价
        for (MultipleItemEntity entity : data) {
            final double price = entity.getField(ShopCarItemFields.PRICE);
            final int count = entity.getField(ShopCarItemFields.COUNT);
            final double total = price * count;
            mTotalPrice = mTotalPrice + total;
        }
        //添加购物车Item布局
        addItemType(ShopCarItemType.SHOP_CAR_ITEM, R.layout.item_shop_car);
    }

    public void setIsSelectAll(boolean isSelectedAll) {
        this.mIsSelectedAll = isSelectedAll;
    }

    public void setICarItemListener(ICarItemListener listener) {
        this.mICarItemListener = listener;
    }

    public double getTotalPrice(){
        return mTotalPrice;
    }

    @Override
    protected void convert(MultipleViewHolder holder, final MultipleItemEntity entity) {
        super.convert(holder, entity);

        switch (holder.getItemViewType()) {
            case ShopCarItemType.SHOP_CAR_ITEM:
                //取出所有值
                final int id = entity.getField(MultipleFields.ID);
                final String thumb = entity.getField(MultipleFields.IMAGE_URL);
                final String title = entity.getField(ShopCarItemFields.TITLE);
                final String desc = entity.getField(ShopCarItemFields.DESC);
                final int count = entity.getField(ShopCarItemFields.COUNT);
                final double price = entity.getField(ShopCarItemFields.PRICE);
                //取出所有控件
                final AppCompatImageView imgThumb = holder.getView(R.id.image_item_shop_car);
                final AppCompatTextView tvTitle = holder.getView(R.id.tv_item_shop_car_title);
                final AppCompatTextView tvDesc = holder.getView(R.id.tv_item_shop_car_desc);
                final AppCompatTextView tvPrice = holder.getView(R.id.tv_item_shop_car_price);
                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                final AppCompatTextView tvCount = holder.getView(R.id.tv_item_shop_car_count);
                final IconTextView iconIsSelect = holder.getView(R.id.icon_item_shop_car);
                //赋值
                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvPrice.setText((int) price);
                tvCount.setText(count);
                Glide.with(mContext)
                        .load(thumb)
                        .into(imgThumb);

                //在左侧勾勾渲染之前改变全选状态
                entity.setField(ShopCarItemFields.IS_SELECTED, mIsSelectedAll);
                final boolean isSelected = entity.getField(ShopCarItemFields.IS_SELECTED);
                //根据数据状态显示左侧勾勾
                if (isSelected) {
                    iconIsSelect.setTextColor(ContextCompat.getColor(Latte.getApplicationContext(), R.color.app_main));
                } else {
                    iconIsSelect.setTextColor(Color.GRAY);
                }
                //添加左侧勾勾点击事件
                iconIsSelect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final boolean currentSelected = entity.getField(ShopCarItemFields.IS_SELECTED);
                        if (currentSelected) {
                            iconIsSelect.setTextColor(Color.GRAY);
                            entity.setField(ShopCarItemFields.IS_SELECTED, false);
                        } else {
                            iconIsSelect.setTextColor(ContextCompat.getColor(Latte.getApplicationContext(), R.color.app_main));
                            entity.setField(ShopCarItemFields.IS_SELECTED, true);
                        }
                    }
                });
                //添加加减事件
                iconMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentCount = entity.getField(ShopCarItemFields.COUNT);
                        if (Integer.parseInt(tvCount.getText().toString()) > 1) {
                            RestClient.builder()
                                    .url("shop_car_count.php")
                                    .loader(mContext)
                                    .params("count", currentCount)
                                    .sueccess(new ISuccess() {
                                        @Override
                                        public void onSuccess(String response) {
                                            int countNum = Integer.parseInt(tvCount.getText().toString());
                                            countNum--;
                                            tvCount.setText(String.valueOf(countNum));
                                            if (mICarItemListener != null) {
                                                mTotalPrice = mTotalPrice - price;
                                                final double itemTotal = countNum * price;
                                                mICarItemListener.onItemClick(itemTotal);
                                            }
                                        }
                                    })
                                    .build()
                                    .post();
                        }
                    }
                });
                iconPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentCount = entity.getField(ShopCarItemFields.COUNT);
                        if (Integer.parseInt(tvCount.getText().toString()) > 1) {
                            RestClient.builder()
                                    .url("shop_car_count.php")
                                    .loader(mContext)
                                    .params("count", currentCount)
                                    .sueccess(new ISuccess() {
                                        @Override
                                        public void onSuccess(String response) {
                                            int countNum = Integer.parseInt(tvCount.getText().toString());
                                            countNum++;
                                            tvCount.setText(String.valueOf(countNum));
                                            if (mICarItemListener != null) {
                                                mTotalPrice = mTotalPrice + price;
                                                final double itemTotal = countNum * price;
                                                mICarItemListener.onItemClick(itemTotal);
                                            }
                                        }
                                    })
                                    .build()
                                    .post();
                        }
                    }
                });
                break;
            default:
                break;
        }
    }
}
