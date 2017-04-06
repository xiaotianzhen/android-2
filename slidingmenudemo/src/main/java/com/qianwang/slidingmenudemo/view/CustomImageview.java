package com.qianwang.slidingmenudemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;

import com.qianwang.slidingmenudemo.R;

/**
 * Created by sky on 2017/3/14.
 */

public class CustomImageview extends ImageView {
    private Paint mPaint;
    private Bitmap src;
    private int mRadius;
    private int type;
    private static final int TYPE_CIRCLE = 0;
    private static final int TYPE_ROUND = 1;
    private int mWidth;
    private int mHeight;

    public CustomImageview(Context context) {
        this(context, null);
    }

    public CustomImageview(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomImageview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CustomImageview, defStyleAttr, 0);

        int n = a.getIndexCount();
        for (int i = 0; i < n; i++)
        {
            int attr = a.getIndex(i);

            Log.i("520it", "attr" + "**************************"+attr);
            switch (attr)
            {
                case R.styleable.CustomImageview_src:
                    src = BitmapFactory.decodeResource(getResources(),
                            a.getResourceId(attr, 0));
                    break;
                case R.styleable.CustomImageview_type:
                    type = a.getInt(attr, 0);// 默认为Circle
                    break;
                case R.styleable.CustomImageview_BorderRadius:
                    mRadius = a.getDimensionPixelSize(attr, (int) TypedValue
                            .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f,
                                    getResources().getDisplayMetrics()));// 默认为10DP
                    break;
            }
        }
        a.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

// super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * 设置宽度
         */
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
        {
            mWidth = specSize;
        } else
        {
            // 由图片决定的宽
            int desireByImg = getPaddingLeft() + getPaddingRight()
                    + src.getWidth();

            if (specMode == MeasureSpec.AT_MOST)// wrap_content
            {
                mWidth = Math.min(desireByImg, specSize);
            } else

                mWidth = desireByImg;
        }

        /***
         * 设置高度
         */

        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
        {
            mHeight = specSize;
        } else
        {
            int desire = getPaddingTop() + getPaddingBottom()
                    + src.getHeight();

            if (specMode == MeasureSpec.AT_MOST)// wrap_content
            {
                mHeight = Math.min(desire, specSize);
            } else
                mHeight = desire;
        }

        setMeasuredDimension(mWidth, mHeight);

        Log.i("520it", "mWidth" + "**************************"+mWidth);
        Log.i("520it", "mHeight" + "**************************"+mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        switch (type) {
            case TYPE_CIRCLE:
                int min = Math.min(mWidth, mHeight);
                /**
                 * 长度如果不一致，按小的值进行压缩
                 */
                src = Bitmap.createScaledBitmap(src, min, min, false);

                canvas.drawBitmap(CreateCircleImageView(src, min), 0, 0, null);
                break;
            case TYPE_ROUND:
                canvas.drawBitmap(CreateRoundImageView(src), 0, 0, null);
                break;
        }
        super.onDraw(canvas);
    }

    private Bitmap CreateCircleImageView(Bitmap source, int min) {

        Bitmap b = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(b);
        Rect rect = new Rect(0, 0, min, min);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setColor(Color.BLUE);

        canvas.drawCircle(min / 2, min / 2, min / 2, mPaint);

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, rect, rect, mPaint);

        return b;
    }

    private Bitmap CreateRoundImageView(Bitmap bitmap) {

        Bitmap b = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(b);
        RectF rectF = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());

        mPaint = new Paint();
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setColor(Color.BLUE);

        canvas.drawRoundRect(rectF, mRadius, mRadius, mPaint);

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        canvas.drawBitmap(bitmap, 0, 0, mPaint);

        return b;
    }
}
