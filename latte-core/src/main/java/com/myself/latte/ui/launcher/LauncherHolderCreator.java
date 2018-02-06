package com.myself.latte.ui.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by Kamh on 2018/2/6.
 */

public class LauncherHolderCreator implements CBViewHolderCreator<LauncherHolder>{
    @Override
    public LauncherHolder createHolder() {
        return new LauncherHolder();
    }
}
