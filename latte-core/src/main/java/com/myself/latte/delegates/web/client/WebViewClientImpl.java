package com.myself.latte.delegates.web.client;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.myself.latte.app.Latte;
import com.myself.latte.delegates.IPageLoadListener;
import com.myself.latte.delegates.web.WebDelegate;
import com.myself.latte.delegates.web.route.Router;
import com.myself.latte.log.LatteLogger;
import com.myself.latte.ui.loader.LatteLoader;

import android.os.Handler;

/**
 * Created by Kamh on 2018/3/22.
 */

public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;
    private static final Handler HANDLER = Latte.getHandler();

    public void setIPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LatteLogger.d("shouldOverrideUrlLoading", url);
        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
        LatteLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                LatteLoader.stopLoading();
            }
        },1000);
    }
}
