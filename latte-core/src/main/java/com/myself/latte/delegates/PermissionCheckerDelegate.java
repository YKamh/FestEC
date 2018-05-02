package com.myself.latte.delegates;


import android.Manifest;
import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.myself.latte.ui.camera.LatteCamera;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by Administrator on 2018/1/18.
 */
@RuntimePermissions
public abstract class PermissionCheckerDelegate extends BaseDelegates{

    //不是直接调用方法
    @NeedsPermission(Manifest.permission.CAMERA)
    void startCamera(){
        LatteCamera.start(this);
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
}
