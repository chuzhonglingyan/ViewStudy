package com.yuntian.viewstudy.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;

import com.yuntian.viewstudy.LogUtil;

/**
 * description  .
 * Created by ChuYingYan on 2017/6/1.
 */

public class MyTextView extends AppCompatTextView {


    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtil.d("移动前 getX " + getX() + "  getY " + getY());
                LogUtil.d("移动前 getLeft " + getLeft() + "tv getTop " + getTop()
                        + " tv getRight " + getRight() + " tv getBottom " + getBottom());
                scrollToMove();
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LogUtil.d("移动后 getX " + getX() + "  getY " + getY());
                        LogUtil.d("移动后 getLeft " + getLeft() + "tv getTop " + getTop()
                                + " tv getRight " + getRight() + " tv getBottom " + getBottom());
                    }
                },1500);

                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_CANCEL:

                break;
        }
        return super.onTouchEvent(event);


    }

    /**
     * 沿对角线移动，向右移动300 px(X)，向下移动300 px(Y)
     * 方法一 同步
     */
    private void layoutMove() {
        layout(getLeft() + 300, getTop() + 300, getRight() + 300, getBottom() + 300);
        //移动前 getX 0.0  getY 0.0
        // 移动前 getLeft 0 tv getTop 0 tv getRight 300 tv getBottom 300

        //移动后 getX 300.0  getY 300.0
        // 移动后 getLeft 300 tv getTop 300 tv getRight 600 tv getBottom 600

        // 可以超出父布局  点击事件新位置
    }

    /**
     * 方法二 同步
     */
    private void offsetMove() {
        offsetLeftAndRight(300);
        offsetTopAndBottom(300);

        //移动前 getX 0.0  getY 0.0
        // 移动前 getLeft 0 tv getTop 0 tv getRight 300 tv getBottom 300

        //移动后 getX 300.0  getY 300.0
        // 移动后 getLeft 300 tv getTop 300 tv getRight 600 tv getBottom 600

        // 可以超出父布局  点击事件新位置
    }

    /**
     * 方法三 异步
     */
    private void layoutParamsMove() {
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) getLayoutParams();
        lp.leftMargin = getLeft() + 300;
        lp.topMargin = getTop() + 300;
        setLayoutParams(lp);
        //移动前 getX 0.0  get Y 0.0
        // 移动前 getLeft 0 tv getTop 0 tv getRight 300 tv getBottom 300

        //移动后 getX 300.0  getY 300.0
        // 移动后 getLeft 300 tv getTop 300 tv getRight 600 tv getBottom 600

        // 可以超出父布局  点击事件新位置
    }

    /**
     * 移动计算值 = 最开始点坐标 - 最后移动到的坐标
     * 原因是因为最终会调用这个方法
     * —— invalidateInternal(l - scrollX, t - scrollY, r - scrollX, b - scrollY, true, false);
     * 其中l,t,r,b为原来坐标点，scrollX,scrollY为目标坐标点，只有当目标坐标点值是负数时，移动到的位置才为正数！
     * 例如scrollTo ，我们要从（0,0）移动到（300,300）这个点，根据上面的公式可知为负值
     * 方法四
     */
    private void scrollToMove() {
        int dx = -300; //目标点的横坐标
        int dy = -300;//目标点的纵坐标
        ((View) getParent()).scrollTo(dx, dy);

        //因为x、y、left、top、right、bottom是相对于父控件而言的，父控件也一起移动了，所以没有变。
        //移动后 x,y getLeft等值不改变  点击事件新位置 不超出父布局
    }

    /**
     * TestTextView本身是View，scrollTo、scrollBy移动的都是View的Content，
     * 如果不加的话，使用的效果则是TestTextView的文字位置变化，而TestTextView本身不会变化。
     * 如果在ViewGroup中使用scrollTo、scrollBy，则移动的是ViewGroup中的View.我们这里需要让TestTextView移动，
     * 则需要先 ((View)getParent())，然后再((View)getParent()).scrollTo…
     * 方法五
     */
    private void scrollByMove() {
        ((View) getParent()).scrollBy(-300, -300);
        //移动后 x,y getLeft等值不改变  点击事件新位置 不超出父布局
    }


    /**
     * 方法六 属性动画
     * <p>
     * 属性动画是真实改变View的位置的，虽然属性动画、位移动画的getLeft等没有改变，
     * 但是属性动画的getX、getY是改变了的，位移动画的getX、getY仍未改变！
     */
    private void animatorToMove() {
        //向右移动300 px(X)，向下移动300 px(Y)
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(this, "translationX", 300),
                ObjectAnimator.ofFloat(this, "translationY", 300)

        );
        set.start();
        //移动之后，x、y的值改变了  移动后 getX 300.0  getY 300.0
        //移动后getLeft等值不改变  点击事件新位置  不超出父布局

//        getX() {
//            return mLeft + getTranslationX();
//        }
    }

    /**
     * 方法七 .位移动画
     * 这里的位移设置同样还是如此（原点在屏幕左上角，向右x为正，向下y为正）
     */
    private void translateoMove() {
        //（0,0）移动到（300,300）
        TranslateAnimation anim = new TranslateAnimation(0, 300, 0, 300);
        anim.setFillAfter(true);
        startAnimation(anim);

        //移动前 getX 0.0  get Y 0.0
        // 移动前 getLeft 0 tv getTop 0 tv getRight 300 tv getBottom 300

        //移动后 getX 0.0  getY 0.0
        // 移动后 getLeft 0 tv getTop 0 tv getRight 300 tv getBottom 300


        //移动后getLeft等值不改变 点击事件还是在原位置 不超出父布局
    }


}
