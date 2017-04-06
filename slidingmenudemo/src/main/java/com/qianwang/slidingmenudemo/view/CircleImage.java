package com.qianwang.slidingmenudemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;



/**
 * Created by sky on 2017/3/13.
 */

public class CircleImage extends ImageView {
    private Paint paint;

    public CircleImage(Context context) {
        super(context);

    }

    public CircleImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 绘制圆形图片
     * @param canvas
     */

    @Override
    protected void onDraw(Canvas canvas) {

        Drawable drawable = getDrawable();
        if (drawable != null) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            Bitmap bitmap = bd.getBitmap();
            Bitmap b = getCircleIamge(bitmap);
            Rect rectSrc = new Rect(0, 0, b.getWidth(), b.getHeight());
            Rect rectDest = new Rect(0, 0, getWidth(), getHeight());
            paint.reset();
            canvas.drawBitmap(b, rectSrc, rectDest, paint);  //第一个rect是要绘制的bitmap区域，第二个rect是绘制在屏幕的什么区域

        } else {
            super.onDraw(canvas);
        }
    }

    /**
     * 制作圆形图片的方法
     * @param bitmap 布局上获取到的图片
     * @return
     */

    private Bitmap getCircleIamge(Bitmap bitmap) {
        // 创建一个与图片大小一样的画布
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        //创建一个矩形范围
        Rect rect=new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
        paint =new Paint();
        paint.setAntiAlias(true);//图片抗锯齿
        paint.setColor(Color.BLUE);


        int x=bitmap.getWidth();
        canvas.drawCircle(x/2,x/2,x/2,paint);
        //PorterDuffXfermode  控制Paint如何与已有的Canvas图像进行交互  SRC_IN  取两层绘制交集,显示上层。
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap,rect,rect,paint);

        return output;
    }

}
