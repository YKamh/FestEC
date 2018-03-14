package com.myself.latte.ui.recycler;

import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;

/**
 * Created by Kamh on 2018/3/14.
 */

public class DividerLookupImpl implements DividerItemDecoration.DividerLookup {

    private final int COLOR;
    private final int SIZE;

    public DividerLookupImpl(int color, int size) {
        this.COLOR = color;
        this.SIZE = size;
    }

    @Override
    public Divider getVerticalDivider(int i) {
        return new Divider.Builder().size(SIZE).color(COLOR).build();
    }

    @Override
    public Divider getHorizontalDivider(int i) {
        return new Divider.Builder().size(SIZE).color(COLOR).build();
    }
}
