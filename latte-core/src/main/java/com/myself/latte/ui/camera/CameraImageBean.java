package com.myself.latte.ui.camera;

import android.net.Uri;

/**
 * Created by Kamh on 2018/4/27.
 * 储存一些中价值
 */

public final class CameraImageBean {

    private Uri mPath = null;
    private static final CameraImageBean INSTANCE = new CameraImageBean();

    public static CameraImageBean getInstance(){
        return INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri path) {
        mPath = path;
    }
}
