package com.myself.festec.example.event;

import android.webkit.WebView;
import android.widget.Toast;

import com.myself.latte.delegates.web.event.Event;

/**
 * Created by Kamh on 2018/3/26.
 */

public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(), params, Toast.LENGTH_SHORT).show();
        if (getAction().equals("test")){
            final WebView webView = getWebView();
            getWebView().post(new Runnable() {
                @Override
                public void run() {
//                    webView.evaluateJavascript("");
                }
            });
        }
        return null;
    }
}
