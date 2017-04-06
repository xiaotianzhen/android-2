package com.qianwang.slidingmenudemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by sky on 2017/3/14.
 */

public class CircleButton extends ImageView {

    private Paint mPaint;
    public CircleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleButton(Context context) {
        super(context);
    }

    /**
     * 绘制圆角图片
     * @param canvas
     */

    @Override
    protected void onDraw(Canvas canvas) {

        Drawable drawable=getDrawable();
        if(drawable!=null){
            BitmapDrawable bd= (BitmapDrawable) drawable;
            Bitmap bitmap=bd.getBitmap();
            Bitmap b=getCircleButton(bitmap);
            mPaint.reset();
            canvas.drawBitmap(b,0,0,null);
        }else {
            super.onDraw(canvas);
        }
    }

    /**
     * 获取圆角图片
     * @param bitmap
     * @return
     */

    private Bitmap getCircleButton(Bitmap bitmap){

        Bitmap b = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(b);
        RectF rectF = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());

        mPaint = new Paint();
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setColor(Color.BLUE);

        canvas.drawRoundRect(rectF, 30, 30, mPaint);

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        canvas.drawBitmap(bitmap, 0, 0, mPaint);
        return b;
    }
}
