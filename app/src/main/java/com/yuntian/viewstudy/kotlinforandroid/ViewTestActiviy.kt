package com.yuntian.viewstudy.kotlinforandroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yuntian.viewstudy.LogUtil
import com.yuntian.viewstudy.R
import kotlinx.android.synthetic.main.activity_view_test_activiy.*
import java.util.*


class ViewTestActiviy : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_test_activiy)


        //这里使用了onGlobalLayoutListener接口，当view树的状态翻身改变或者view树内部的view可见性发生改变时，
        // onGlobalLayout会被回调，这也说明onGlobalLayout会被调用多次。
//        val observer = tv_test.getViewTreeObserver()
//        observer.addOnGlobalLayoutListener({
//            LogUtil.d("getMeasuredWidth =" + tv_test.getMeasuredWidth())
//            LogUtil.d("getMeasuredHeight =" + tv_test.getMeasuredHeight())
//        })


        // getWidth获取的是屏幕中能看到的宽度，getMeasureWidth()是控件本身的自身宽度
        tv_test.postDelayed({
            LogUtil.d("getMeasuredWidth =" + tv_test.getMeasuredWidth())
            LogUtil.d("getMeasuredHeight =" + tv_test.getMeasuredHeight())
            LogUtil.d("width=" + tv_test.width)
            LogUtil.d("height=" + tv_test.height)
        }, 1000)

        tv_test.setOnClickListener {
            var mTitleText = randomText()
            tv_test.setmTitleText(mTitleText)
            tv_test.postInvalidate()
        }

    }

    private fun randomText(): String {
        val random = Random()
        val set = HashSet<Int>()
        while (set.size < 4) {
            val randomInt = random.nextInt(10)
            set.add(randomInt)
        }
        val sb = StringBuffer()
        for (i in set) {
            sb.append("" + i)
        }

        return sb.toString()
    }

}
