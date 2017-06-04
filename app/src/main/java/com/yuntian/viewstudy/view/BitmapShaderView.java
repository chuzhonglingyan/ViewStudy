package com.yuntian.viewstudy.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;

import com.yuntian.viewstudy.R;

/**
 * description  .
 * Created by ChuYingYan on 2017/6/3.
 */

public class BitmapShaderView extends View {

    private BitmapShader bitmapShader = null;
    private Bitmap bitmap = null;
    private Paint paint = null;
    private ShapeDrawable shapeDrawable = null;
    private int BitmapWidth = 0;
    private int BitmapHeight = 0;

    public BitmapShaderView(Context context) {
        super(context);

        // 得到图像
        bitmap = ((BitmapDrawable) getResources().getDrawable(R.mipmap.ic_launcher))
                .getBitmap();
        BitmapWidth = bitmap.getWidth();
        BitmapHeight = bitmap.getHeight();
        // 构造渲染器BitmapShader
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.MIRROR,Shader.TileMode.REPEAT);
    }

    public BitmapShaderView(Context context,AttributeSet set) {
        super(context, set);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        //将图片裁剪为椭圆形
        //构建ShapeDrawable对象并定义形状为椭圆
        shapeDrawable = new ShapeDrawable(new OvalShape());
        //得到画笔并设置渲染器
        shapeDrawable.getPaint().setShader(bitmapShader);
        //设置显示区域
        shapeDrawable.setBounds(20, 20,BitmapWidth-140,BitmapHeight);
        //绘制shapeDrawable
        shapeDrawable.draw(canvas);
    }
}
