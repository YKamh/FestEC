package com.myself.latte.ui.recycler;

import java.util.ArrayList;

/**
 * Created by Kamh on 2018/3/6.
 */

public abstract class DataConverter {

    protected final ArrayList<MultipleItemEntity> ENTITYS = new ArrayList<>();
    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String json) {
        this.mJsonData = json;
        return this;
    }

    protected String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("DATA IS NULL");
        }
        return mJsonData;
    }
}
