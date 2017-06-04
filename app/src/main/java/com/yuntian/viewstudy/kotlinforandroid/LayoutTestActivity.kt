package com.yuntian.viewstudy.kotlinforandroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yuntian.viewstudy.R
import kotlinx.android.synthetic.main.activity_layout_test.*


class LayoutTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_test)
        setViewLayout01();
        setViewLayout02();
        setViewLayout03();
        setViewLayout04();
        setViewLayout05();
        setViewLayout06();
    }


    private fun setViewLayout01() {
        //这种方法，xml根布局的属性失效, 根布局里面的 tempView布局参数由自身决定，width=match_parent,hegiht= wrap_content
        val buttonLayout = layoutInflater.inflate(R.layout.layout_button, null)
        //val buttonLayout = layoutInflater.inflate(R.layout.layout_button, null,false)
        main_layout.addView(buttonLayout);
    }

    private fun setViewLayout02() {
        //这种方法，xml根布局的属性有效, 根布局里面的 tempView布局参数由根布局属性决定，setLayoutParams
        val buttonLayout = layoutInflater.inflate(R.layout.layout_button, main_layout, false);
        main_layout.addView(buttonLayout)
    }


    /**
     * 方法2等价方法3
     */
    private fun setViewLayout03() {
        //这种方法，xml根布局的属性有效, 根布局里面的 tempView布局参数由根布局属性决定，
        val buttonLayout = layoutInflater.inflate(R.layout.layout_button, main_layout, true);
    }


    private fun setViewLayout04() {
        //这种方法，xml根布局的属性失效, 根布局里面的 tempView布局参数由自身决定，width=match_parent,hegiht= wrap_content
        val buttonLayout = layoutInflater.inflate(R.layout.item_button, null)
        //val buttonLayout = layoutInflater.inflate(R.layout.layout_button, null,false)
        main_layout.addView(buttonLayout);
    }

    private fun setViewLayout05() {
        //这种方法，xml根布局的属性有效, 根布局里面的 tempView布局参数由根布局属性决定，
        val buttonLayout = layoutInflater.inflate(R.layout.item_button, main_layout, false);
        main_layout.addView(buttonLayout)
    }


    private fun setViewLayout06() {
        //这种方法，xml根布局的属性有效, 根布局里面的 tempView布局参数由根布局属性决定，
        val buttonLayout = layoutInflater.inflate(R.layout.item_button, main_layout, true);
    }
}
