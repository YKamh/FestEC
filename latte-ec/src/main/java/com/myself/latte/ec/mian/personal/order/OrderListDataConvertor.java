package com.myself.latte.ec.mian.personal.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myself.latte.ui.recycler.DataConverter;
import com.myself.latte.ui.recycler.MultipleFields;
import com.myself.latte.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by Kamh on 2018/4/25.
 */

public class OrderListDataConvertor extends DataConverter{

    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = array.size();
        for (int i = 0; i < size; i++){
            final JSONObject data = array.getJSONObject(i);
            final String thumb = data.getString("thumb");
            final String title = data.getString("title");
            final int id = data.getInteger("id");
            final double price = data.getDouble("price");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(OrderListItemType.ITEM_ORDER_LIST)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.IMAGE_URL, thumb)
                    .setField(MultipleFields.TITLE, title)
                    .setField(OrderItemFields.PRICE, price)
                    .setField(OrderItemFields.TIME, title)
                    .build();

            ENTITYS.add(entity);
        }
        return ENTITYS;
    }
}
