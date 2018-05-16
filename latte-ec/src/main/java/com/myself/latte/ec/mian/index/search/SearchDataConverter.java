package com.myself.latte.ec.mian.index.search;

import com.alibaba.fastjson.JSONArray;
import com.myself.latte.ui.recycler.DataConverter;
import com.myself.latte.ui.recycler.MultipleFields;
import com.myself.latte.ui.recycler.MultipleItemEntity;
import com.myself.latte.util.storage.LattePreference;

import java.util.ArrayList;

/**
 * Created by yjh on 2018/5/16.
 */

public class SearchDataConverter extends DataConverter {

    public static final String TAG_SEARCH_HESTORY = "search_history";

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final String jsonStr = LattePreference.getCustomAppProfile(TAG_SEARCH_HESTORY);
        if (jsonStr!=null){
            final JSONArray array = JSONArray.parseArray(jsonStr);
            final int size = array.size();
            for (int i = 0; i < size; i++){
                final String historyItemText = array.getString(i);
                final MultipleItemEntity entity = MultipleItemEntity.builder().setItemType(SearchItemType.ITEM_SEARCH)
                        .setField(MultipleFields.TEXT, historyItemText)
                        .build();
                ENTITYS.add(entity);
            }
        }

        return ENTITYS;
    }
}
