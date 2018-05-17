package com.myself.latte.ec.detail;

import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.myself.latte.ec.R;
import com.myself.latte.ui.recycler.ItemType;
import com.myself.latte.ui.recycler.MultipleFields;
import com.myself.latte.ui.recycler.MultipleItemEntity;
import com.myself.latte.ui.recycler.MultipleRecyclerAdapter;
import com.myself.latte.ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * Created by yjh on 2018/5/17.
 */

public class RecyclerImageAdapter extends MultipleRecyclerAdapter {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .dontAnimate();

    protected RecyclerImageAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ItemType.SINGLE_BIG_IMAGE, R.layout.item_image);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        final int type  = holder.getItemViewType();
        switch (type){
            case ItemType.SINGLE_BIG_IMAGE:
                final AppCompatImageView imageView = holder.getView(R.id.image_rv_item);
                final String url = entity.getField(MultipleFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(url)
                        .apply(OPTIONS)
                        .into(imageView);
                break;
            default:
                break;
        }
    }
}
