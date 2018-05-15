package com.myself.latte.util.callback;

import android.support.annotation.Nullable;

/**
 * Created by Kamh on 2018/5/4.
 */

public interface IGlobalCallback<T> {

    void executeCallback(@Nullable T args);
}
