package com.qianwang.slidingmenudemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by sky on 2017/3/13.
 */

public class PopupView extends View {
    private Paint paint;
    private int mWidth;  //当前的view
    private int mHeight;
    private int mRectWidth;
    private int mRectHeight;
    private float percent = 0.8f;

    public PopupView(Context context) {
        super(context);
    }

    public PopupView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMeasure = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMeasure = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (MeasureSpec.EXACTLY == widthMode) {
            mWidth = widthMeasure;
            mRectWidth = (int) (mWidth * percent);
        }

        if (MeasureSpec.EXACTLY == heightMode) {
            mHeight = heightMeasure;
            mRectHeight = (int) (mHeight * percent);
        }

        setMeasuredDimension(mRectWidth,mRectHeight);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        paint=new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);


        canvas.drawColor(Color.RED);
        canvas.drawRect(0,0,mRectWidth,mRectHeight,paint);

        Path path=new Path();
        path.moveTo(mRectWidth/2-10,mRectHeight);
        path.lineTo(mRectWidth/2,mRectHeight+10);
        path.lineTo(mRectWidth/2+10,mRectHeight);
        path.close();
        canvas.drawPath(path,paint);
        super.onDraw(canvas);
    }
}
