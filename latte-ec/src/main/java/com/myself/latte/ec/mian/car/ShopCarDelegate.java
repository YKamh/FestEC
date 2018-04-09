package com.myself.latte.ec.mian.car;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.myself.latte.delegates.bottom.BottomItemDelegate;
import com.myself.latte.ec.R;

/**
 * Created by Kamh on 2018/4/9.
 */

public class ShopCarDelegate extends BottomItemDelegate{


    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_car;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
