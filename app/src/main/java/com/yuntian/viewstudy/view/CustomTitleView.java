package com.yuntian.viewstudy.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.yuntian.viewstudy.LogUtil;
import com.yuntian.viewstudy.R;


/**
 * description  .
 * Created by ChuYingYan on 2017/6/3.
 */

public class CustomTitleView extends View {

    private Rect mBound;
    private Paint mPaint;

    /**
     * 文本
     */
    private String mTitleText="";
    /**
     * 文本的颜色
     */
    private int mTitleTextColor;
    /**
     * 文本的大小
     */
    private float mTitleTextSize;


    private int time = 0;

    public CustomTitleView(Context context) {
        this(context, null);
    }

    public CustomTitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    //我们重写了3个构造方法，默认的布局文件调用的是两个参数的构造方法，
    // 所以记得让所有的构造调用我们的三个参数的构造，我们在三个参数的构造中获得自定义属性。

    public CustomTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        /**
         * 获得我们所定义的自定义样式属性
         */
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyleAttr, 0);

        mTitleText = a.getString(R.styleable.CustomTitleView_titleText);
        // 默认颜色设置为黑色
        mTitleTextColor = a.getColor(R.styleable.CustomTitleView_titleTextColor, Color.BLACK);
        // 默认设置为16sp，TypeValue也可以把sp转化为px
        mTitleTextSize = a.getDimension(R.styleable.CustomTitleView_titleTextSize, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics()));
        a.recycle();

        /**
         * 获得绘制文本的宽和高
         */
        mPaint = new Paint();
        mBound = new Rect();

        mPaint.setTextSize(mTitleTextSize);
        //mPaint.setColor(mTitleTextColor);

        //设置字的区域
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);

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
        mPaint.setTextSize(mTitleTextSize);
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
        float textWidth = mBound.width();
        float textHeight = mBound.height();

        int defaultwith = (int) (getPaddingLeft() + getPaddingRight() + textWidth);
        int defaultHeight = (int) (getPaddingTop() + getPaddingBottom() +textHeight);
        setMeasuredDimension(getWidthSize(defaultwith, widthMeasureSpec),
                getHeightSize(defaultHeight, heightMeasureSpec));
        LogUtil.d("time =" + time);
    }


    public static int getWidthSize(int size, int measureSpec) {
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = size;
                //LogUtil.d("width specMode =" + specMode + ",specSize" + result);
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(result, specSize);
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
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
                result = Math.min(result, specSize);
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                // LogUtil.d("Height specMode =" + specMode + ",specSize" + result);
                break;
        }
        return result;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.YELLOW);  //设置画笔颜色
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);//设置画布区域

        mPaint.setColor(mTitleTextColor);   //重新设置字的颜色
        //画字的中心位置
        //第一个参数是你要显示的字符，第二个为 x起点，第三个为底部的Y坐标而不是Y的起点，第四个参数就是你的画笔
        canvas.drawText(mTitleText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);

    }

    //当View的大小发生改变时，会调用此方法。这里我们获取View的长和宽
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }


    public String getmTitleText() {
        return mTitleText;
    }

    public void setmTitleText(String mTitleText) {
        this.mTitleText = mTitleText;
    }

    public int getmTitleTextColor() {
        return mTitleTextColor;
    }

    public void setmTitleTextColor(int mTitleTextColor) {
        this.mTitleTextColor = mTitleTextColor;
    }

    public float getmTitleTextSize() {
        return mTitleTextSize;
    }

    public void setmTitleTextSize(float mTitleTextSize) {
        this.mTitleTextSize = mTitleTextSize;
    }
}
