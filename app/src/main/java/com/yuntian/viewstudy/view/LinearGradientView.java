package com.yuntian.viewstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * 相信很多人都看过歌词同步的效果, 一是竖直方向的滚动，另一方面是水平方面的歌词颜色渐变点亮效果,这种效果怎么
 * description  .
 * Created by ChuYingYan on 2017/6/3.
 */

public class LinearGradientView extends View {

    private LinearGradient linearGradient = null;
    private Paint paint = null;

    public LinearGradientView(Context context)
    {
        super(context);
        linearGradient = new LinearGradient(0, 0, 100, 100, new int[] {
                Color.YELLOW, Color.GREEN, Color.TRANSPARENT, Color.WHITE }, null,
                Shader.TileMode.REPEAT);
        paint = new Paint();
    }

    public LinearGradientView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        //设置渲染器
        paint.setShader(linearGradient);
        //绘制圆环
        canvas.drawCircle(240, 360, 200, paint);
    }
}
