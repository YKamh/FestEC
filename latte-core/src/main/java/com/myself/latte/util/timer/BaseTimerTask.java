package com.myself.latte.util.timer;

import java.util.TimerTask;

/**
 * Created by Kamh on 2018/2/5.
 */

public class BaseTimerTask extends TimerTask{

    private ITimerListener mITimerListener;

    public BaseTimerTask(ITimerListener timerListener) {
        mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if (mITimerListener != null){
            mITimerListener.onTimer();
        }
    }
}
