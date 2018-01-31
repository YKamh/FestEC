package com.myself.latte.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.myself.latte.app.Latte;

/**
 * Created by Administrator on 2018/1/26.
 */

public class DimenUtil {

    //得到屏幕的宽
    public static int getScreenWidth(){
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    //得到屏幕的高
    public static int getScreenHeight(){
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
