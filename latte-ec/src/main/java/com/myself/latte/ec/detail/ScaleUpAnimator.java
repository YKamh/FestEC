package com.myself.latte.ec.detail;

import android.view.View;

import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.nineoldandroids.animation.ObjectAnimator;


/**
 * Created by yjh on 2018/5/17.
 */

public class ScaleUpAnimator extends BaseViewAnimator{



    @Override
    protected void prepare(View view) {
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(view, "scaleX", 0.8f, 1f, 1.4f, 1.2f, 1),
                ObjectAnimator.ofFloat(view, "scaleX", 0.8f, 1f, 1.4f, 1.2f, 1));
    }

}
