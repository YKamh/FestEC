package com.myself.latte.delegates.web.event;

import android.content.Context;
import android.webkit.WebView;

import com.myself.latte.delegates.LatteDelegate;
import com.myself.latte.delegates.web.WebDelegate;

/**
 * Created by Kamh on 2018/3/26.
 */

public abstract class Event implements IEvent {

    private Context mContext = null;
    private String mAction = null;
    private WebDelegate mDelegate = null;
    private String mUrl = null;
    private WebView mWebView = null;

    public Context getContext() {
        return mContext;
    }

    public WebView getWebView(){
        return mDelegate.getWebView();
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String action) {
        mAction = action;
    }

    public LatteDelegate getDelegate() {
        return mDelegate;
    }

    public void setDelegate(WebDelegate delegate) {
        mDelegate = delegate;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}
