package com.yuntian.customrview;

import android.app.Application;
/**
 * description  .
 * Created by ChuYingYan on 2017/5/6.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new LogUtil.Builder(this)
                .setLogSwitch(BuildConfig.DEBUG);// 设置log总开关，默认开
    }
}
