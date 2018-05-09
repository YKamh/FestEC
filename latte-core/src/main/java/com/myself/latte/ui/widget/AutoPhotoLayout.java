package com.myself.latte.ui.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;
import com.myself.latte.R;
import com.myself.latte.delegates.LatteDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamh on 2018/5/9.
 */

public class AutoPhotoLayout extends LinearLayoutCompat{

    private int mCurrentNum = 0;
    private int mMaxNum = 0;
    private int mMaxLineNum = 0;
    private IconTextView mIconTextView = null;
    private LayoutParams lp = null;
    //要删除的图片ID
    private int mDeleteId = 0;
    private AppCompatImageView mTarget = null;
    private int mImageMargin = 0;
    private LatteDelegate mDelegate = null;
    private List<View> mViewList = null;
    private AlertDialog mTargetDialog = null;
    private static final String ICON_TEXT = "{fa-plus}";
    private float mIconSize = 0;

    private static final List<List<View>> ALL_VIEWS = new ArrayList<>();
    private static final List<Integer> LINE_HEIGHT = new ArrayList<>();

    public AutoPhotoLayout(Context context) {
        this(context, null);
    }

    public AutoPhotoLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoPhotoLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.camera_flow_layout);
        mMaxNum = typedArray.getInt(R.styleable.camera_flow_layout_max_count, 1);
        mMaxLineNum = typedArray.getInt(R.styleable.camera_flow_layout_line_count, 3);
        mImageMargin = typedArray.getInt(R.styleable.camera_flow_layout_item_margin, 0);
        mIconSize = typedArray.getInt(R.styleable.camera_flow_layout_icon_size, 20);
        typedArray.recycle();
    }
}
