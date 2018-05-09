package com.myself.latte.ec.mian.personal.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.myself.latte.delegates.LatteDelegate;
import com.myself.latte.ec.R;
import com.myself.latte.ec.R2;
import com.myself.latte.ui.widget.StarLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Kamh on 2018/5/8.
 */

public class OrderCommentDelegate extends LatteDelegate {

    @BindView(R2.id.custom_star_layout)
    StarLayout mStarLayout = null;

    @OnClick(R2.id.top_tv_comment_commit)
    void onClick(){
        Toast.makeText(getContext(), "评分：" + mStarLayout.getStarCount(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
