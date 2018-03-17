package com.myself.latte.ec.mian.index;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.myself.latte.ec.R;
import com.myself.latte.ui.recycler.RgbValue;


/**
 * Created by Kamh on 2018/3/15.
 */

public class TranslucentBehavior extends CoordinatorLayout.Behavior<Toolbar> {
    //顶部距离
    private int mDistanceY = 0;
    //颜色变化速度
    private static final int SHOW_SPEED = 3;
    //变化颜色
    private final RgbValue RGB_VALUE = RgbValue.create(255, 124, 2);

    public TranslucentBehavior(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    //被依赖处理的View，就是根据这个View的动作来处理依赖的View（Toolbar）
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency.getId() == R.id.rv_index;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return true;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                  @NonNull Toolbar child,
                                  @NonNull View target,
                                  int dx,
                                  int dy,
                                  @NonNull int[] consumed,
                                  int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        //增加滑动距离
        mDistanceY += dy;
        //toolbar 的高度
        final int targetHeight = child.getBottom();

        //当滑动时，并且距离小于 toolbar高度的时候调整渐变色
        if (mDistanceY> 0 && mDistanceY <= targetHeight){
            final float scale = (float) mDistanceY / targetHeight;
            final float alpha = scale * 255;
            child.setBackgroundColor(Color.argb((int)alpha,RGB_VALUE.red(), RGB_VALUE.green(), RGB_VALUE.blue()));
        }else if (mDistanceY > targetHeight){//防止滑动过快的时候 ，不渐变直接设置颜色
            child.setBackgroundColor(Color.rgb(RGB_VALUE.red(), RGB_VALUE.green(), RGB_VALUE.blue()));
        }
    }
}
