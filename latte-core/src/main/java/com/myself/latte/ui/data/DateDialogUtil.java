package com.myself.latte.ui.data;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Kamh on 2018/4/27.
 */

public class DateDialogUtil {

    public interface IDataListenter{
        void onDataChange(String date);
    }

    private IDataListenter mIDataListenter = null;

    public void setIDataListenter(IDataListenter IDataListenter) {
        mIDataListenter = IDataListenter;
    }

    public void showDialog(Context context){
        final LinearLayout ll=  new LinearLayout(context);
        final DatePicker picker = new DatePicker(context);
        final LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        picker.setLayoutParams(lp);
        picker.init(1990, 1, 1, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                final Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                final SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
                final String data = format.format(calendar.getTime());
                if (mIDataListenter != null){
                    mIDataListenter.onDataChange(data);
                }
            }
        });
        ll.addView(picker);
        new AlertDialog.Builder(context)
                .setTitle("选择日期")
                .setView(ll)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }
}
