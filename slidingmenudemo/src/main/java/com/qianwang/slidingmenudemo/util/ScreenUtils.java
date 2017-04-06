package com.qianwang.slidingmenudemo.util;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by Administrator on 2017/2/24 0024.
 */

public class ScreenUtils {

    public static  int getScreenWidth(Context context){

        WindowManager manager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        return manager.getDefaultDisplay().getWidth();
    }
}
