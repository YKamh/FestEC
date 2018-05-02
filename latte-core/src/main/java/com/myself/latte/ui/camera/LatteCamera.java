package com.myself.latte.ui.camera;

import android.net.Uri;

import com.myself.latte.delegates.PermissionCheckerDelegate;
import com.myself.latte.util.file.FileUtil;

/**
 * Created by Kamh on 2018/4/27.
 * 相机调用类
 */

public class LatteCamera {

    public static Uri createCropFile(){
        return Uri.parse
                (FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate){
        new CameraHandler(delegate).beginCamearaDialog();
    }
}
