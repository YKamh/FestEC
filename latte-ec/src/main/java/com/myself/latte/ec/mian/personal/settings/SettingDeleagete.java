package com.myself.latte.ec.mian.personal.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;

import com.myself.latte.delegates.LatteDelegate;
import com.myself.latte.ec.R;
import com.myself.latte.ec.R2;
import com.myself.latte.ec.mian.personal.PersonalOnclickListener;
import com.myself.latte.ec.mian.personal.address.AddressDelegate;
import com.myself.latte.ec.mian.personal.list.ListAdapter;
import com.myself.latte.ec.mian.personal.list.ListBean;
import com.myself.latte.util.callback.CallbackManager;
import com.myself.latte.util.callback.CallbackType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.myself.latte.ec.mian.personal.list.ListItemType.ITEM_AVATAR;
import static com.myself.latte.ec.mian.personal.list.ListItemType.ITEM_NORMAL;
import static com.myself.latte.ec.mian.personal.list.ListItemType.ITEM_SWITCH;

/**
 * Created by Kamh on 2018/5/7.
 */

public class SettingDeleagete extends LatteDelegate {

    @BindView(R2.id.rv_settings)
    RecyclerView mRecyclerView = null;


    @Override
    public Object setLayout() {
        return R.layout.delegate_settings;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final ListBean push = new ListBean.Builder()
                .setItemType(ITEM_SWITCH)
                .setId(1)
                .setLatteDelegate(new AddressDelegate())
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked){
                            CallbackManager.getInstance().getCallback(CallbackType.TAG_OPEN_PUSH).executeCallback(null);
                        }else{
                            CallbackManager.getInstance().getCallback(CallbackType.TAG_OPEN_PUSH).executeCallback(null);
                        }
                    }
                })
                .setText("消息推送")
                .build();
        final ListBean about = new ListBean.Builder()
                .setItemType(ITEM_NORMAL)
                .setId(2)
                .setLatteDelegate(new AboutDelegate())
                .setText("关于")
                .build();
        final List<ListBean> data = new ArrayList<>();
        data.add(push);
        data.add(about);

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new PersonalOnclickListener(this));
        mRecyclerView.addOnItemTouchListener(new SettingsClickListener(this));
    }
}
