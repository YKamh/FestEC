package com.myself.latte.ec.mian.personal.address;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.myself.latte.ec.R;
import com.myself.latte.net.RestClient;
import com.myself.latte.net.callback.ISuccess;
import com.myself.latte.ui.recycler.MultipleFields;
import com.myself.latte.ui.recycler.MultipleItemEntity;
import com.myself.latte.ui.recycler.MultipleRecyclerAdapter;
import com.myself.latte.ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * Created by Kamh on 2018/5/5.
 */

public class AddressAdapter extends MultipleRecyclerAdapter {

    protected AddressAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(AddressItemType.ITEM_ADDRESS, R.layout.item_address);
    }

    @Override
    protected void convert(final MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()){
            case AddressItemType.ITEM_ADDRESS:
                final String name = entity.getField(MultipleFields.NAME);
                final String phone = entity.getField(AddressItemFields.PHONE);
                final String address = entity.getField(AddressItemFields.ADDRESS);
                final boolean isDefault = entity.getField(MultipleFields.TAG);
                final int id = entity.getField(MultipleFields.ID);

                final AppCompatTextView nameText = holder.getView(R.id.tv_address_name);
                final AppCompatTextView phoneText = holder.getView(R.id.tv_address_phone);
                final AppCompatTextView addressText = holder.getView(R.id.tv_address_address);
                final AppCompatTextView deleteText = holder.getView(R.id.tv_address_delete);
                deleteText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RestClient.builder()
                                .url("address.php")
                                .params("id", id)
                                .success(new ISuccess() {
                                    @Override
                                    public void onSuccess(String response) {
                                        remove(holder.getLayoutPosition());
                                    }
                                })
                                .build()
                                .post();
                    }
                });

                nameText.setText(name);
                phoneText.setText(phone);
                addressText.setText(address);

                break;
            default:
                break;
        }
    }
}
