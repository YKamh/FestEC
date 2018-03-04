package com.myself.latte.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;

import com.myself.latte.app.Latte;

/**
 * Created by Kamh on 2018/3/4.
 * 刷新助手
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener{

    private final SwipeRefreshLayout REFRESH_LAYOUT;

    public RefreshHandler(SwipeRefreshLayout REFRESH_LAYOUT) {
        this.REFRESH_LAYOUT = REFRESH_LAYOUT;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    private void refresh(){
        REFRESH_LAYOUT.setRefreshing(true);

    }

    @Override
    public void onRefresh() {
        refresh();
    }

}
