package com.yuntian.viewstudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.yuntian.viewstudy.view.MyTextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView= (MyTextView) findViewById(R.id.tv_test_view);

        LogUtil.d("textView.getX()="+textView.getX()+",textView.getY()"+textView.getY());

        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogUtil.d("onTouch:"+"event.getX()="+event.getX()+",event.getY()"+event.getY());
                LogUtil.d("onTouch: event.getRawX()="+event.getRawX()+",eevent.getRawY()"+event.getRawY());
                return false;
            }
        });

        textView.post(new Runnable() {
            @Override
            public void run() {
                LogUtil.d("textView.getWidth()="+textView.getWidth()+",textView.getHeight()"+textView.getHeight());
                LogUtil.d("textView.getTop()="+textView.getTop()+",textView.getBottom()"+textView.getBottom());
                LogUtil.d("textView.getLeft()="+textView.getLeft()+",textView.getRight()"+textView.getRight());
                LogUtil.d("textView.getPaddingTop()="+textView.getPaddingTop()+",textView.getPaddingBottom()"+textView.getPaddingBottom());
                LogUtil.d("textView.getPaddingLeft()="+textView.getPaddingLeft()+",textView.getPaddingRight()"+textView.getPaddingRight());
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //此处可以正常获取width、height等
        LogUtil.d("移动前 getX " + textView.getX() + "  getY " + textView.getY());
        LogUtil.d("移动前 getLeft " + textView.getLeft() + "tv getTop " + textView.getTop()
                + " tv getRight " + textView.getRight() + " tv getBottom " + textView.getBottom());
    }


}
