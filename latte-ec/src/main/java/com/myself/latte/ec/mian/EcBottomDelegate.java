package com.myself.latte.ec.mian;

import android.graphics.Color;

import com.myself.latte.delegates.bottom.BaseBottomDelegate;
import com.myself.latte.delegates.bottom.BottomItemDelegate;
import com.myself.latte.delegates.bottom.BottomTabBean;
import com.myself.latte.delegates.bottom.ItemBuilder;
import com.myself.latte.ec.discover.DiscoverDelegate;
import com.myself.latte.ec.mian.car.ShopCarDelegate;
import com.myself.latte.ec.mian.index.IndexDelegate;
import com.myself.latte.ec.mian.personal.PersonalDelegate;
import com.myself.latte.ec.mian.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by Kamh on 2018/3/1.
 */

public class EcBottomDelegate extends BaseBottomDelegate{
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}","主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}","分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}","发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}","购物车"), new ShopCarDelegate());
        items.put(new BottomTabBean("{fa-user}","我的"), new PersonalDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickColor() {
        return Color.parseColor("#ffff8800");
    }
}
