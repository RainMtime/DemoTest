package com.example.chunyu.demotest.customeView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 人间一小雨 on 2017/6/25.
 */

public class TestDrawView extends View {

    Paint mPaint;
    public TestDrawView(Context context) {
        super(context);
        initPaint();
    }

    public TestDrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }


    private void initPaint(){
        mPaint = new Paint();
        mPaint.setColor(Color.YELLOW);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawRect(new Rect(50,50,100,100),mPaint);

        RectF rectF = new RectF(100,100,800,400);
// 绘制背景矩形
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(rectF,mPaint);

// 绘制圆弧
        mPaint.setColor(Color.BLUE);
        canvas.drawArc(rectF,0,90,false,mPaint);


    }
}
