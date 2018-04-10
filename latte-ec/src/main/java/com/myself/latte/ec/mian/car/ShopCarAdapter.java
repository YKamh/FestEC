package com.myself.latte.ec.mian.car;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.joanzapata.iconify.widget.IconTextView;
import com.myself.latte.ec.R;
import com.myself.latte.ui.recycler.ItemType;
import com.myself.latte.ui.recycler.MultipleFields;
import com.myself.latte.ui.recycler.MultipleItemEntity;
import com.myself.latte.ui.recycler.MultipleRecyclerAdapter;
import com.myself.latte.ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * Created by Kamh on 2018/4/10.
 */

public class ShopCarAdapter extends MultipleRecyclerAdapter {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    protected ShopCarAdapter(List<MultipleItemEntity> data) {
        super(data);
        //添加购物车Item布局
        addItemType(ShopCarItemType.SHOP_CAR_ITEM, R.layout.item_shop_car);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
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
                //赋值
                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvPrice.setText((int) price);
                tvCount.setText(count);
                Glide.with(mContext)
                        .load(thumb)
                        .into(imgThumb);
                break;
            default:
                break;
        }
    }
}
