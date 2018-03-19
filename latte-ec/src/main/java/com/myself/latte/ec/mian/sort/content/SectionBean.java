package com.myself.latte.ec.mian.sort.content;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by Kamh on 2018/3/19.
 */

public class SectionBean extends SectionEntity<SectionContentItemEntity>{

    private boolean mIsMore = false;
    private int mId = -1;

    public SectionBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public SectionBean(SectionContentItemEntity sectionContentItemEntity) {
        super(sectionContentItemEntity);
    }

    public boolean isMore() {
        return mIsMore;
    }

    public void setMore(boolean more) {
        mIsMore = more;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }
}
