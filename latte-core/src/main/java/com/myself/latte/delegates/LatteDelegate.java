package com.myself.latte.delegates;

/**
 * Created by Administrator on 2018/1/18.
 */

public abstract class LatteDelegate extends PermissionCheckerDelegate {

    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate(){
        return (T) getParentFragment();
    }
}
