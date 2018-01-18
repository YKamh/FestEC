package com.myself.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by Administrator on 2018/1/16.
 */

public enum  EcIcons implements Icon {
    icon_scan('\ue62f'),
    icon_ali_pay('\ue64b');

    private char cjaracter;

    EcIcons(char cjaracter) {
        this.cjaracter = cjaracter;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return 0;
    }
}
