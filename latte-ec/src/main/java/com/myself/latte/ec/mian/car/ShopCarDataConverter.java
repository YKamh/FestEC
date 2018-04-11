package com.myself.latte.ec.mian.car;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myself.latte.ui.recycler.DataConverter;
import com.myself.latte.ui.recycler.MultipleFields;
import com.myself.latte.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by Kamh on 2018/4/10.
 */

public class ShopCarDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");

        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final String thumb = data.getString("thumb");
            final String desc = data.getString("desc");
            final String title = data.getString("title");
            final int id = data.getInteger("id");
            final int count = data.getInteger("count");
            final double price = data.getDouble("price");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ShopCarItemType.SHOP_CAR_ITEM)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.IMAGE_URL, thumb)
                    .setField(ShopCarItemFields.TITLE, title)
                    .setField(ShopCarItemFields.DESC, desc)
                    .setField(ShopCarItemFields.COUNT, count)
                    .setField(ShopCarItemFields.PRICE, price)
                    .setField(ShopCarItemFields.IS_SELECTED, false)
                    .build();
            dataList.add(entity);
        }
        return dataList;
    }
}
