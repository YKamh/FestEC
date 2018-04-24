package com.myself.latte.ec.mian.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.myself.latte.delegates.bottom.BottomItemDelegate;
import com.myself.latte.ec.R;
import com.myself.latte.ec.R2;
import com.myself.latte.ec.mian.personal.list.ListAdapter;
import com.myself.latte.ec.mian.personal.list.ListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.myself.latte.ec.mian.personal.list.ListItemType.ITEM_NORMAL;

/**
 * Created by Kamh on 2018/4/24.
 */

public class PersonalDelegate extends BottomItemDelegate {

    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final ListBean address = new ListBean.Builder()
                .setItemType(ITEM_NORMAL)
                .setId(1)
                .setText("收获地址")
                .build();
        final ListBean system = new ListBean.Builder()
                .setItemType(ITEM_NORMAL)
                .setId(2)
                .setText("系统设置")
                .build();
        final List<ListBean> data = new ArrayList<>();
        data.add(address);
        data.add(system);

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);
    }
}
