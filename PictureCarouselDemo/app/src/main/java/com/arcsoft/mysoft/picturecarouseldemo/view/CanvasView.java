package com.arcsoft.mysoft.picturecarouseldemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by yhp5210 on 2016/9/10.
 */
public class CanvasView extends View implements Runnable{

    private Thread mRefreshThread = null;
    private int mDesValue;
    private int mMinValue = 350;
    private int mMaxValue = 950;
    private int currentValue = mMinValue;
    private int width,height;
    private Paint mOvalPaint;//画中间弧形的画笔
    private int mOvalPaintColor = Color.parseColor("#39a8f7");
    private float mStartAngel = 150.0f;
    private float mSweepAngel = 240.0f;
    private float mOvalPaintWidth = 50.0f;
    private RectF mOvalRect = new RectF();
    private Paint mCircleLine;//画外侧圈的画笔
    private RectF mCircleLineRect = new RectF();
    private float mCircleLinepaintWidth = 20.0f;
    private int mCircleLineBasicColor = Color.parseColor("#45b1f8");
    private int mStartColor = mCircleLineBasicColor;
    private int mEndColor = Color.parseColor("#ffffff");
    private int mCurrentColor = mStartColor;
    private Paint mChangePaint;


    public CanvasView(Context context) {
        super(context);
    }
    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(){
        mOvalPaint = new Paint();
        mOvalPaint.setColor(mOvalPaintColor);
        mOvalPaint.setStyle(Paint.Style.STROKE);
        mOvalPaint.setStrokeWidth(mOvalPaintWidth);
        mOvalPaint.setAntiAlias(true);
        //初始化外层圆弧相关资源
        mCircleLine = new Paint();
        mCircleLine.setStyle(Paint.Style.STROKE);
        mCircleLine.setStrokeWidth(mCircleLinepaintWidth);
        mCircleLine.setAntiAlias(true);
        mCircleLine.setColor(mCircleLineBasicColor);

        mChangePaint = new Paint();
        mChangePaint.setStyle(Paint.Style.STROKE);
        mChangePaint.setStrokeWidth(mCircleLinepaintWidth);
        mChangePaint.setAntiAlias(true);



    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mOvalRect.left = 80;
        mOvalRect.right = getWidth() - 80;
        mOvalRect.bottom = getHeight() - 80;
        mOvalRect.top = 80;
        canvas.drawArc(mOvalRect,mStartAngel,mSweepAngel,false,mOvalPaint);
        canvas.save();
        //绘制外层默认的底线
        mCircleLineRect.left = 30;
        mCircleLineRect.right = getWidth()-30;
        mCircleLineRect.bottom = getHeight() - 30;
        mCircleLineRect.top = 30;
        canvas.drawArc(mCircleLineRect,mStartAngel,mSweepAngel,false,mCircleLine);
        canvas.restore();

        mChangePaint.setColor(mCurrentColor);
        canvas.drawArc(mCircleLineRect,mStartAngel,mSweepAngel*(currentValue-mMinValue)/(mMaxValue-mMinValue),false,mChangePaint);
        canvas.save();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width =  measureWidth(widthMeasureSpec);
        height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width,height);
    }

    public int measureWidth(int widthMeasureSpec){

        int result = 0;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size =  MeasureSpec.getSize(widthMeasureSpec);

        switch (mode){

            case MeasureSpec.EXACTLY:{
                result = size;

            }break;
            case MeasureSpec.AT_MOST:{

                result = 300;
                result = Math.min(result,size);

            }break;
            default:{
                result = 300;
            }break;

        }
        return result;
    }

    public int measureHeight(int heightMeasureSpec){

        int result = 0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        switch(mode){

            case MeasureSpec.EXACTLY:{
                result = size;
            }break;
            case MeasureSpec.AT_MOST:{

                result = 300;
                result = Math.min(result,size);

            }break;
            default:{
                result = 300;
            }break;
        }
        return size;
    }

    public void setCurrentValue(int destValue){

        mDesValue = destValue;
        mRefreshThread = new Thread(this);
        mRefreshThread.start();

    }

    @Override
    public void run() {

        if(currentValue < mDesValue){
            currentValue += 10;
            mCurrentColor += (mEndColor-mStartColor)/10;
        }else{
            currentValue = mDesValue;
            mCurrentColor = mEndColor;
        }
        try {
            mRefreshThread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        postInvalidate();
    }

    public void clear(){

        mRefreshThread = null;
        currentValue = mMinValue;
    }

}
