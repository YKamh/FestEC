package com.myself.latte.ui.banner;

import android.widget.AdapterView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.myself.latte.R;

import java.util.ArrayList;

/**
 * Created by Kamh on 2018/3/7.
 */

public class BannerCreate {

    public static void setDefult(ConvenientBanner<String> convenientBanner,
                                 ArrayList<String> banners,
                                 OnItemClickListener clickListener){
        convenientBanner.setPages(new HolderCreate(), banners)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(clickListener)
//                .setPageTransformer()
                .startTurning(3000)
                .setCanLoop(true);
    }
}
