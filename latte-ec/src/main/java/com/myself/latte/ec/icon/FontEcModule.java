package com.myself.latte.ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * Created by Administrator on 2018/1/16.
 * 字体库module
 */

public class FontEcModule implements IconFontDescriptor {
    //这里传入你的ttf文件
    @Override
    public String ttfFileName() {
        return "iconfont.ttf";
    }
    //这里返回一个icon
    @Override
    public Icon[] characters() {
        return EcIcons.values();
    }
}
