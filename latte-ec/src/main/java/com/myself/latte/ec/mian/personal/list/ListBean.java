package com.myself.latte.ec.mian.personal.list;


import android.widget.CompoundButton;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.myself.latte.delegates.LatteDelegate;

/**
 * Created by Kamh on 2018/4/24.
 */

public class ListBean implements MultiItemEntity {

    private int mItemType = 0;
    private String mImageUrl = null;
    private String mText = null;
    private String mValue = null;
    private int mId = 0;
    private LatteDelegate mLatteDelegate = null;
    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = null;

    public ListBean(int itemType, String imageUrl, String text,
                    String value, int id, LatteDelegate latteDelegate,
                    CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        mItemType = itemType;
        mImageUrl = imageUrl;
        mText = text;
        mValue = value;
        mId = id;
        mLatteDelegate = latteDelegate;
        mOnCheckedChangeListener = onCheckedChangeListener;
    }

    public void setItemType(int itemType) {
        mItemType = itemType;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getText() {
        if (mText == null){
            return "";
        }
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public String getValue() {
        if (mValue == null){
            return "";
        }
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public LatteDelegate getLatteDelegate() {
        return mLatteDelegate;
    }

    public void setLatteDelegate(LatteDelegate latteDelegate) {
        mLatteDelegate = latteDelegate;
    }

    public CompoundButton.OnCheckedChangeListener getOnCheckedChangeListener() {
        return mOnCheckedChangeListener;
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        mOnCheckedChangeListener = onCheckedChangeListener;
    }

    @Override
    public int getItemType() {
        return 0;
    }

    public static final class Builder{
        private int id = 0;
        private int itemType = 0;
        private String imageUrl = null;
        private String text = null;
        private String value = null;
        private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = null;
        private LatteDelegate mLatteDelegate = null;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setItemType(int itemType) {
            this.itemType = itemType;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Builder setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
            mOnCheckedChangeListener = onCheckedChangeListener;
            return this;
        }

        public Builder setLatteDelegate(LatteDelegate latteDelegate) {
            mLatteDelegate = latteDelegate;
            return this;
        }

        public ListBean build(){
            return new ListBean(itemType, imageUrl, text, value, id, mLatteDelegate, mOnCheckedChangeListener);
        }
    }
}
