package com.yuntian.customrview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * description  .
 * Created by ChuYingYan on 2017/6/3.
 */

public class TestView extends View {

    private int time=0;

    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    //最顶层DecorView测量时的MeasureSpec是由ViewRootImpl中getRootMeasureSpec方法确定的
    // （LayoutParams宽高参数均为MATCH_PARENT，specMode是EXACTLY，specSize为物理屏幕大小）。

    //  MeasureSpec是由父布局与View 自身的LayoutParams来决定的
    //不重写onMeasure方法，则测量布局的 specMode=MeasureSpec.AT_MOST ，specWidthSize=父布局的宽，speHithtSize=父布局的高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        time++;

       // LogUtil.d("getSuggestedMinimumWidth =" + getSuggestedMinimumWidth());
        //LogUtil.d("getSuggestedMinimumHeight() =" + getSuggestedMinimumHeight());

       // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
       int defaultwith= getPaddingLeft()+getPaddingRight()+getSuggestedMinimumWidth();
       int defaultHeight= getPaddingTop()+getPaddingBottom()+getSuggestedMinimumHeight();
        setMeasuredDimension(getWidthSize(defaultwith, widthMeasureSpec),
                getHeightSize(defaultHeight, heightMeasureSpec));
     //   LogUtil.d("time =" + time);
    }


    public static int getWidthSize(int size, int measureSpec) {
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = size;
               // LogUtil.d("width specMode =" + specMode + ",specSize" + result);
                break;
            case MeasureSpec.AT_MOST:
                result =Math.min(result,specSize);
                break;
            case MeasureSpec.EXACTLY:
                result =specSize;
               // LogUtil.d("width specMode =" + specMode + ",specSize" + result);
                break;
        }
        return result;
    }

    public static int getHeightSize(int size, int measureSpec) {
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = size;
               // LogUtil.d("Height specMode =" + specMode + ",specSize" + result);
                break;
            case MeasureSpec.AT_MOST:
                result =Math.min(result,specSize);
                break;
            case MeasureSpec.EXACTLY:
                result =specSize;
               // LogUtil.d("Height specMode =" + specMode + ",specSize" + result);
                break;
        }
        return result;
    }

}
