package com.myself.latte.ec.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.myself.latte.delegates.LatteDelegate;
import com.myself.latte.delegates.bottom.BottomItemDelegate;
import com.myself.latte.ec.R;

/**
 * Created by Kamh on 2018/3/20.
 */

public class DiscoverDelegate extends BottomItemDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
