package com.myself.latte.ec.mian.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.myself.latte.delegates.bottom.BottomItemDelegate;
import com.myself.latte.ec.R;

/**
 * Created by Kamh on 2018/3/1.
 */

public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
