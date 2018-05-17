package com.myself.latte.ec.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.myself.latte.delegates.LatteDelegate;
import com.myself.latte.ec.R;
import com.myself.latte.ec.R2;
import com.myself.latte.ui.recycler.ItemType;
import com.myself.latte.ui.recycler.MultipleFields;
import com.myself.latte.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by yjh on 2018/5/17.
 */

public class ImageDelegate extends LatteDelegate {

    @BindView(R2.id.rv_image_container)
    RecyclerView mRecyclerView = null;

    private static final String ARG_PICTURES = "ARG_PICTURES";

    public static ImageDelegate create(ArrayList<String> pictures) {
        final Bundle args = new Bundle();
        args.putStringArrayList(ARG_PICTURES, pictures);
        final ImageDelegate detailDelegate = new ImageDelegate();
        detailDelegate.setArguments(args);
        return detailDelegate;
    }

    private void initImages(){
        final ArrayList<String> pictures = getArguments().getStringArrayList(ARG_PICTURES);
        final ArrayList<MultipleItemEntity> entities = new ArrayList<>();
        final int size;
        if (pictures != null){
            size = pictures.size();
            for (int i = 0; i < size; i++){
                final String imageUrl = pictures.get(i);
                final MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                        .setItemType(ItemType.SINGLE_BIG_IMAGE)
                        .setField(MultipleFields.IMAGE_URL,imageUrl)
                        .build();
                entities.add(itemEntity);
            }
            final RecyclerImageAdapter adapter = new RecyclerImageAdapter(entities);
            mRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_image;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        initImages();
    }
}
