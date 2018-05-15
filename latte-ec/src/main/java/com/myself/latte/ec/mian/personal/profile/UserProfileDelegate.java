package com.myself.latte.ec.mian.personal.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.myself.latte.delegates.LatteDelegate;
import com.myself.latte.ec.R;
import com.myself.latte.ec.R2;
import com.myself.latte.ec.mian.personal.list.ListAdapter;
import com.myself.latte.ec.mian.personal.list.ListBean;
import com.myself.latte.ec.mian.personal.list.ListItemType;
import com.myself.latte.ec.mian.personal.settings.NameDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.myself.latte.ec.mian.personal.list.ListItemType.ITEM_NORMAL;

/**
 * Created by Kamh on 2018/4/27.
 */

public class UserProfileDelegate extends LatteDelegate {

    @BindView(R2.id.rv_user_profile)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_user_profile;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final ListBean image = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_AVATAR)
                .setId(1)
                .setImageUrl("http://i9.qhimg.com/t012d891ca365ef60b5.jpg")
                .build();
        final ListBean name = new ListBean.Builder()
                .setItemType(ITEM_NORMAL)
                .setId(2)
                .setText("姓名")
                .setValue("未设置姓名")
                .setLatteDelegate(new NameDelegate())
                .build();
        final ListBean gender = new ListBean.Builder()
                .setItemType(ITEM_NORMAL)
                .setId(3)
                .setText("性别")
                .setValue("未设置性别")
                .build();
        final ListBean birht = new ListBean.Builder()
                .setItemType(ITEM_NORMAL)
                .setId(4)
                .setText("生日")
                .setValue("未设置生日")
                .build();
        final List<ListBean> data = new ArrayList<>();
        data.add(image);
        data.add(name);
        data.add(gender);
        data.add(birht);

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new UserProfileClickListener(this));
    }
}
