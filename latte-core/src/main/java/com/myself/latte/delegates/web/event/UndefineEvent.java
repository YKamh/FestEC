package com.myself.latte.delegates.web.event;

import com.myself.latte.log.LatteLogger;

/**
 * Created by Kamh on 2018/3/26.
 */

public class UndefineEvent extends Event{
    @Override
    public String execute(String params) {
        LatteLogger.e("UndefineEvent", params);
        return null;
    }
}
