package com.myself.latte.ui.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by Kamh on 2018/3/7.
 */

public class HolderCreate implements CBViewHolderCreator<ImageHolder>{

    @Override
    public ImageHolder createHolder() {
        return new ImageHolder();
    }
}
