package com.myself.latte.delegates.web.client;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.myself.latte.delegates.web.WebDelegate;
import com.myself.latte.delegates.web.route.Router;
import com.myself.latte.log.LatteLogger;

/**
 * Created by Kamh on 2018/3/22.
 */

public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;

    public WebViewClientImpl(WebDelegate delegate){
        this.DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LatteLogger.d("shouldOverrideUrlLoading", url);
        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }
}
