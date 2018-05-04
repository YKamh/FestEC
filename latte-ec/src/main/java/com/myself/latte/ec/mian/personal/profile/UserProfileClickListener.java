package com.myself.latte.ec.mian.personal.profile;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.myself.latte.delegates.LatteDelegate;
import com.myself.latte.ec.R;
import com.myself.latte.ec.mian.personal.list.ListBean;
import com.myself.latte.log.LatteLogger;
import com.myself.latte.ui.data.DateDialogUtil;
import com.myself.latte.ui.loader.LatteLoader;
import com.myself.latte.util.callback.CallbackManager;
import com.myself.latte.util.callback.CallbackType;
import com.myself.latte.util.callback.IGlobalCallback;

/**
 * Created by Kamh on 2018/4/27.
 */

public class UserProfileClickListener extends SimpleClickListener {

    private final LatteDelegate DELEGATE;

    private String[] mGenders = new String[]{"男", "女", "保密"};

    public UserProfileClickListener(LatteDelegate delegate) {
        DELEGATE = delegate;
    }


    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, final View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        final int id = bean.getId();
        switch (id){
            case 1:
                //开始照相或者选择图片
                CallbackManager.getInstance().addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                    @Override
                    public void executeCallback(Uri args) {
                        LatteLogger.d("ON_CROP", args);
                    }
                });
                DELEGATE.startCameraWithCheck();
                break;
            case 2:
                final LatteDelegate nameDelegate = bean.getLatteDelegate();
                DELEGATE.getSupportDelegate().start(nameDelegate);
                break;
            case 3:
                getGenderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final TextView textView = view.findViewById(R.id.tv_arrow_value);
                        textView.setText(mGenders[which]);
                        dialog.cancel();
                    }
                });
                break;
            case 4:
                final DateDialogUtil dateDialogUtil = new DateDialogUtil();
                dateDialogUtil.setIDataListenter(new DateDialogUtil.IDataListenter() {
                    @Override
                    public void onDataChange(String date) {
                        final TextView textView = view.findViewById(R.id.tv_arrow_value);
                        textView.setText(date);
                    }
                });
                dateDialogUtil.showDialog(DELEGATE.getContext());
                break;
            default:
                break;
        }
    }

    private void getGenderDialog(DialogInterface.OnClickListener listener){
        final AlertDialog.Builder builder = new AlertDialog.Builder(DELEGATE.getContext());
        builder.setSingleChoiceItems(mGenders, 0, listener);
        builder.show();
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }
}
