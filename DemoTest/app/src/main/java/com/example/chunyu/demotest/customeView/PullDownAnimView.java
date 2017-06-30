package com.example.chunyu.demotest.customeView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.chunyu.demotest.R;
import com.example.chunyu.demotest.Utils.DemoUtils;

/**
 * Created by chunyu on 2017/6/29.
 */

public class PullDownAnimView extends View {

    private static int MIX_WIDTH = 100;
    private static int MIX_HEIGH = 100;

    private int mPaintColor;
    private int mPaintWidth;

    private Paint mPaint = new Paint();

    private int mWidth;
    private int mHeigh;
    private int mAnchorY = 0;
    private int mAnchorX = 0;
    private boolean inited = false;

    Path mPath = new Path();


    public PullDownAnimView(Context context) {
        super(context);
    }

    public PullDownAnimView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //第二个参数就是我们在styles.xml文件中的<declare-styleable>标签
        //即属性集合的标签，在R文件中名称为R.styleable+name
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PullDownAnimView);

        //第一个参数为属性集合里面的属性，R文件名称：R.styleable+属性集合名称+下划线+属性名称
        //第二个参数为，如果没有设置这个属性，则设置的默认的值
        mPaintColor = a.getColor(R.styleable.PullDownAnimView_paint_color, DemoUtils.getRandomColor());
        mPaintWidth = a.getDimensionPixelSize(R.styleable.PullDownAnimView_paint_width, 2);

        //最后记得将TypedArray对象回收
        a.recycle();
        initPaint();


    }

    private void initPaint() {
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.STROKE);  //设置画笔模式为填充
        mPaint.setStrokeWidth(5f);         //设置画笔宽度为10px
//        mPaint.setPathEffect(new CornerPathEffect(5));
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initWidthandHeight();
//        canvas.drawLines(new float[]{5f, 5f, mAnchorX, mAnchorY - 5f, mAnchorX, mAnchorY - 5f, mWidth - 5f, 5f}, mPaint);

        mPath.reset();
        mPath.moveTo(5f, 20f);
        mPath.lineTo(mAnchorX, mAnchorY - 5f);
        mPath.lineTo(mWidth-5f, 20f);
        canvas.drawPath(mPath,mPaint);
        Log.e("chunyu-draw", "x=" + mAnchorX + "\t y=" + mAnchorY);
    }

    private void initWidthandHeight() {
        if (!inited) {
            inited = true;
            mWidth = getWidth();
            mHeigh = getHeight();
            mAnchorY = getHeight();
            mAnchorX = mWidth / 2;
        }
    }


    public void setAnchorY(int anchorY) {
        mAnchorY = anchorY;
    }
}
