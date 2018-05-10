package com.myself.latte.ui.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
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
    private IconTextView mIconAdd = null;
    private LayoutParams lp = null;
    //要删除的图片ID
    private int mDeleteId = 0;
    private AppCompatImageView mTarget = null;
    private int mImageMargin = 0;
    private LatteDelegate mDelegate = null;
    private List<View> mLineViews = null;
    private AlertDialog mTargetDialog = null;
    private static final String ICON_TEXT = "{fa-plus}";
    private float mIconSize = 0;

    private static final List<List<View>> ALL_VIEWS = new ArrayList<>();
    private static final List<Integer> LINE_HEIGHT = new ArrayList<>();

    //防止多次的测量和布局过程
    private boolean mIsOnceInitOnMeasure = false;
    private boolean mHasInitOnLayou = false;

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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int sizeWith = MeasureSpec.getSize(widthMeasureSpec);
        final int modeWith = MeasureSpec.getMode(widthMeasureSpec);
        final int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        final int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        //wrap_content
        int width = 0;
        int height = 0;
        //记录每一行的高度和宽度
        int lineWith = 0;
        int lineHeight = 0;
        //得到内部元素个数
        int cCount = getChildCount();
        for (int i = 0; i< cCount; i++){
            final View child = getChildAt(i);
            //测量子View的宽和高
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            //得到LayoutParams
            final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            //获取子View边距宽度
            final int childWith = child.getMeasuredWidth()+lp.leftMargin+lp.rightMargin;
            final int childHeight = child.getMeasuredHeight()+lp.topMargin+lp.bottomMargin;
            //换行
            if (lineWith+childWith>sizeWith-getPaddingLeft()-getPaddingRight()){
                //对比得到最大宽度
                width = Math.max(width, lineWith);
                //重置lineWidth
                lineWith = childWith;
                height+=lineHeight;
                lineHeight = childHeight;
            }else{
                //未换行
                lineWith+=childWith;
                //得到当前最大高度
                lineHeight = Math.max(lineHeight, childHeight);
            }
            //最后一个子控件
            if (i == cCount - 1){
                width = Math.max(lineWith, width);
                //判断是否超过最大拍照限制
                height+=lineHeight;
            }
        }
        setMeasuredDimension(
                modeWith == MeasureSpec.EXACTLY?sizeWith:width+getPaddingLeft(),
                modeHeight == MeasureSpec.EXACTLY?sizeHeight:height+getPaddingTop()
        );
        //设置一行图片的宽高
        final int imagesSideLen = sizeWith/mMaxLineNum;
        //只初始化一次
        if (!mIsOnceInitOnMeasure){
            lp = new LayoutParams(imagesSideLen, imagesSideLen);
            mIsOnceInitOnMeasure = true;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        ALL_VIEWS.clear();
        LINE_HEIGHT.clear();
        //获取当前ViewGroup的宽度
        final int width = getWidth();
        int lineWidth = 0;
        int lineHeight = 0;

        if (!mHasInitOnLayou){
            mLineViews = new ArrayList<>();
            mHasInitOnLayou = true;
        }
        final int cCount = getChildCount();
        for (int i = 0; i< cCount; i++){
            final View child = getChildAt(i);
            final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            final int childWidth = child.getMeasuredWidth();
            final int chidlHeight = child.getMeasuredHeight();
            //如果需要换行
            if (childWidth + lineWidth + lp.leftMargin + lp.rightMargin > width-getPaddingRight()-getPaddingLeft()){
                //纪录lineHeight
                LINE_HEIGHT.add(lineHeight);
                //纪录当前的一行的Views
                ALL_VIEWS.add(mLineViews);
                //重置宽和高
                lineWidth = 0;
                lineHeight = chidlHeight+lp.topMargin+lp.bottomMargin;
                //重置View集合
                mLineViews.clear();
            }
            lineWidth+=childWidth+lp.leftMargin+lp.rightMargin;
            lineHeight = Math.max(lineHeight, lineHeight+lp.topMargin+lp.bottomMargin);
            mLineViews.add(child);
        }
        //处理最后一行
        LINE_HEIGHT.add(lineHeight);
        ALL_VIEWS.add(mLineViews);
        //设置子View位置
        int left = getPaddingLeft();
        int top = getPaddingTop();
        //行数
        final int lineNum = ALL_VIEWS.size();
        for (int i = 0; i < lineNum; i++){
            //当前行的所有View
            mLineViews = ALL_VIEWS.get(i);
            lineHeight = LINE_HEIGHT.get(i);
            final int size = mLineViews.size();
            for (int j = 0; j < size; j++){
                final View child = mLineViews.get(i);
                //判断child的状态
                if (child.getVisibility() == GONE){
                    continue;
                }
                final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                //设置子View的边距
                final int lc = left+lp.leftMargin;
                final int tc = top+lp.topMargin;
                final int rc = lc + child.getMeasuredWidth() - mImageMargin;
                final int bc = tc + child.getMeasuredHeight();
                //为子View进行布局
                child.layout(lc, tc, rc, bc);
                left+=child.getMeasuredWidth()+lp.leftMargin+lp.rightMargin;
            }
            left = getPaddingLeft();
            top += lineHeight;
        }
        mIconAdd.setLayoutParams(lp);
        mHasInitOnLayou = false;
    }

    private void initAddIcon(){
        mIconAdd = new IconTextView(getContext());
        mIconAdd.setText(ICON_TEXT);
        mIconAdd.setGravity(Gravity.CENTER);
        mIconAdd.setTextSize(mIconSize);
        mIconAdd.setBackgroundResource(R.drawable.border_text);
        mIconAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mDelegate.startCameraWithCheck();
            }
        });
        this.addView(mIconAdd);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initAddIcon();
        mTargetDialog = new AlertDialog.Builder(getContext()).create();
    }
}
