package com.myself.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by Administrator on 2018/1/16.
 */

public enum  EcIcons implements Icon {
    icon_scan('\ue62f'),
    icon_ali_pay('\ue64b');

    //一个返回变量
    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
