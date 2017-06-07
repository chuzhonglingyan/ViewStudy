package com.yuntian.viewstudy.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.yuntian.viewstudy.R;

/**
 * description  .
 * Created by ChuYingYan on 2017/6/3.
 */

public class DrawView extends View {

    private Paint p;

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


    private int offY=0;

    public DrawView(Context context) {
        this(context, null);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        /**
         * 获得我们所定义的自定义样式属性
         */
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DrawView, defStyleAttr, 0);

        mTitleText = a.getString(R.styleable.DrawView_drawView_titleText);
        // 默认颜色设置为黑色
        mTitleTextColor = a.getColor(R.styleable.DrawView_drawView_titleTextColor, Color.BLACK);
        // 默认设置为16sp，TypeValue也可以把sp转化为px
        mTitleTextSize = a.getDimension(R.styleable.DrawView_drawView_titleTextSize, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics()));
        a.recycle();

        /**
         * 获得绘制文本的宽和高
         */
        p = new Paint();

        p.setTextSize(mTitleTextSize);
        //mPaint.setColor(mTitleTextColor);

        //设置字的区域
        //p.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*
         * 方法 说明
         * drawRect 绘制矩形 drawCircle 绘制圆形 drawOval 绘制椭圆 drawPath 绘制任意多边形
         * drawLine 绘制直线 drawPoin 绘制点
         */
        // 创建画笔
        p.setColor(Color.RED);// 设置红色

        drawCirlce(canvas);
//        drawLine(canvas);
//        drawRect(canvas);
//        drawArcAndOval(canvas);
//        drawPolygon(canvas);
//        drawRoundRect(canvas);
//        drawBezierPath(canvas);
//        drawPoint(canvas);
//        drawBitmap(canvas);
    }

    /**
     * 画圆
     *
     * @param canvas
     */
    public void drawCirlce(Canvas canvas) {
        String text="画圆：";
        Rect rect=new Rect();
        p.getTextBounds(text, 0, text.length(), rect);
        canvas.drawText(text,10, rect.height(), p);// 画文本

        canvas.drawCircle(140, 20, 20, p);// 小圆
        p.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，一看效果就明白了
        canvas.drawCircle(240, 40, 40, p);// 大圆
        drawLine(canvas,120);
    }

    /**
     * 画线及弧线
     *
     * @param canvas
     */
    public void drawLine(Canvas canvas,int y) {
        String text="画线及弧线：";
        Rect rect=new Rect();
        p.getTextBounds(text, 0, text.length(), rect);
        canvas.drawText(text,10, y, p);// 画文本


        p.setColor(Color.GREEN);// 设置绿色

        canvas.drawLine(rect.width()+10, y, rect.width()+70, y, p);// 画线
        canvas.drawLine(rect.width()+100, y, rect.width()+170, y+80, p);// 斜线

//        //画笑脸弧线
        p.setStyle(Paint.Style.STROKE);//设置空心

        RectF oval1 = new RectF();

        oval1.set(rect.width()+200, y, rect.width()+260, y+60);
        canvas.drawArc(oval1, 180, 180, false, p);//小弧形

        oval1.set(rect.width()+300, y, rect.width()+360, y+60);
        canvas.drawArc(oval1, 180, 180, false, p);//小弧形

        oval1.set(rect.width()+250, y+20, rect.width()+310,y+80);
        canvas.drawArc(oval1, 0, 180, false, p);//小弧形

        drawRect(canvas,y+100);
    }


    /**
     * 画矩形
     *
     * @param canvas
     */
    public void drawRect(Canvas canvas,int y) {
        String text="画矩形：";
        Rect rect=new Rect();
        p.getTextBounds(text, 0, text.length(), rect);
        canvas.drawText(text,10, y, p);// 画文本

//
        p.setColor(Color.GRAY);// 设置灰色
        canvas.drawRect(rect.width()+10, y, rect.width()+70, y+60, p);// 正方形
//
        p.setStyle(Paint.Style.FILL);//设置填满
        canvas.drawRect(rect.width()+100, y, rect.width()+190,y+60, p);// 长方形

        drawArcAndOval(canvas,y+100);
    }

    /**
     * 画扇形和椭圆
     *
     * @param canvas
     */
    public void drawArcAndOval(Canvas canvas,int y) {
        String text="画扇形和椭圆：";
        Rect rect=new Rect();
        p.getTextBounds(text, 0, text.length(), rect);
        canvas.drawText(text,10,y, p);// 画文本

        /* 设置渐变色 这个正方形的颜色是改变的 */
//        Shader mShader = new LinearGradient(0, 0, 100, 100,
//                new int[]{Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
//                        Color.LTGRAY}, null, Shader.TileMode.REPEAT); // 一个材质,打造出一个线性梯度沿著一条线。
//        p.setShader(mShader);

        RectF oval2 = new RectF(rect.width()+10, y, rect.width()+100, y+60);// 设置个新的长方形，扫描测量
        // 画弧，第一个参数是RectF：该类是第二个参数是角度的开始，第三个参数是多少度，第四个参数是true的时候画扇形，是false的时候画弧线
        canvas.drawArc(oval2, 200, 130, true, p);
//
//        //画椭圆，把oval改一下
        oval2.set(rect.width()+140, y, rect.width()+200, y+40);
        canvas.drawOval(oval2, p);

        drawPolygon(canvas, y+80);
    }

    public void drawPolygon(Canvas canvas,int y) {
        String text="画三角形：";
        Rect rect=new Rect();
        p.getTextBounds(text, 0, text.length(), rect);
        canvas.drawText(text,10,y, p);// 画文本

        // 绘制这个三角形,你可以绘制任意多边形
        Path path = new Path();
        path.moveTo(rect.width()+10, y);// 此点为多边形的起始点
        path.lineTo(rect.width()+60, y); //终止点 起始点
        path.lineTo(rect.width()+10, y+80);//终止点
        path.close(); // 使这些点构成封闭的多边形
        canvas.drawPath(path, p);

        //你可以绘制很多任意多边形，比如下面画六连形
        p.reset();//重置
        p.setColor(Color.LTGRAY);
        p.setStyle(Paint.Style.STROKE);//设置空心
        Path path1 = new Path();
        path1.moveTo(rect.width()+80, y+40);
        path1.lineTo(rect.width()+120, y);
        path1.lineTo(rect.width()+160, y);
        path1.lineTo(rect.width()+200, y+40);
        path1.lineTo(rect.width()+160, y+80);
        path1.lineTo(rect.width()+120, y+80);
        path1.close();//封闭
        canvas.drawPath(path1, p);

        drawRoundRect(canvas,y+100);

    }

      /*
         * Path类封装复合(多轮廓几何图形的路径
         * 由直线段*、二次曲线,和三次方曲线，也可画以油画。drawPath(路径、油漆),要么已填充的或抚摸
         * (基于油漆的风格),或者可以用于剪断或画画的文本在路径。
         */

    /**
     * 画圆角矩形
     *
     * @param canvas
     */
    public void drawRoundRect(Canvas canvas,int y) {
        p.setStyle(Paint.Style.FILL);//充满
        p.setColor(Color.LTGRAY);
        p.setAntiAlias(true);// 设置画笔的锯齿效果

        String text="画圆角矩形：";
        Rect rect=new Rect();
        p.getTextBounds(text, 0, text.length(), rect);
        canvas.drawText(text,10,y, p);// 画文本

        RectF oval3 = new RectF(rect.width()+10, y, rect.width()+70, y+40);// 设置个新的长方形
        canvas.drawRoundRect(oval3, 20, 15, p);//第二个参数是x半径，第三个参数是y半径

        drawBezierPath(canvas,y+60);
    }



    /**
     * 画贝塞尔曲线
     *
     * @param canvas
     */
    public void drawBezierPath(Canvas canvas,int y) {
        String text="画贝塞尔曲线：";
        Rect rect=new Rect();
        p.getTextBounds(text, 0, text.length(), rect);
        canvas.drawText(text,10,y, p);// 画文本
        p.reset();

        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.GREEN);
        Path path2=new Path();
        path2.moveTo(rect.width()+10,y);//设置Path的起点
        path2.quadTo(rect.width()+50, y-10, rect.width()+70, y+80); //设置贝塞尔曲线的控制点坐标和终点坐标
        canvas.drawPath(path2, p);//画出贝塞尔曲线

        drawPoint(canvas,y+100);
    }

    /**
     * 画点
     *
     * @param canvas
     */
    public void drawPoint(Canvas canvas,int y) {

        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.BLACK);
        String text="画点：";
        Rect rect=new Rect();
        p.getTextBounds(text, 0, text.length(), rect);
        canvas.drawText(text,10,y, p);// 画文本

        canvas.drawPoint(rect.width()+10, y+40, p);//画一个点

        //canvas.drawPoints(new float[]{rect.width()+30,y+40,rect.width()+35,y+40,rect.width()+40,y+40}, p);//画多个点

        drawBitmap(canvas,y);

    }

   /**
     * 画图片，就是贴图
     * @param canvas
     */
    public void drawBitmap(Canvas canvas,int y) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        canvas.drawBitmap(bitmap, 250,y, p);
    }


}
