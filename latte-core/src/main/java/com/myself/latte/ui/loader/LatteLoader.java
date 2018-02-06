package com.myself.latte.ui.loader;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.myself.latte.R;
import com.myself.latte.util.dimen.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/1/26.
 */

public class LatteLoader {

    //缩放宽高比的默认值
    private static final int LOADER_SIZE_SCALE = 8;
    //偏移量
    private static final int LOADER_OFFSER_SCALE = 10;

    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();

    public static void showLoading(Context context, Enum<LoaderStyle> loaderStyleEnum){
        showLoading(context, loaderStyleEnum.name());
    }

    public static void showLoading(Context context, String type){

        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);

        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);
        dialog.setContentView(avLoadingIndicatorView);

        int deviceWidth = DimenUtil.getScreenWidth();
        int deciceHeight = DimenUtil.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();

        if (dialogWindow != null){
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth/LOADER_SIZE_SCALE;
            lp.height = deciceHeight/LOADER_SIZE_SCALE;
            lp.height = lp.height+deciceHeight/LOADER_OFFSER_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }

    public static void showLoading(Context context){
        showLoading(context, DEFAULT_LOADER);
    }

    public static void stopLoading(){
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null){
                if (dialog.isShowing()){
                    dialog.cancel();
                }
            }
        }
    }

}
