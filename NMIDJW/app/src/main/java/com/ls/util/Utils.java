package com.ls.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.DisplayMetrics;

import com.ls.nmidjw.R;

/**
 * Created by ls on 15-3-7.
 */
public class Utils {

    public static int getScreenWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        return screenW;
    }

    public static int getToolbarHeight(Context context) {
        TypedArray array = context.getTheme().obtainStyledAttributes(new int[]{R.attr
                .actionBarSize});
        int toolbarHeight = (int) array.getDimension(0, 0);
        array.recycle();
        return toolbarHeight;

    }

    public static int  getTabHeight(Context context){
        return (int)context.getResources().getDimension(R.dimen.news_fagment_tab_height)+10;

    }
}
