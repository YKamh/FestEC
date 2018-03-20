package com.myself.latte.delegates.web;

import com.alibaba.fastjson.JSON;

/**
 * Created by Kamh on 2018/3/20.
 */

public class LatteWebInterface {

    private final WebDelegate DELEGATE;

    private LatteWebInterface(WebDelegate webDelegate) {
        DELEGATE = webDelegate;
    }

    static LatteWebInterface create(WebDelegate delegate){
        return new LatteWebInterface(delegate);
    }

    public String event(String params){
        final String action = JSON.parseObject(params).getString("action");
        return null;
    }
}
