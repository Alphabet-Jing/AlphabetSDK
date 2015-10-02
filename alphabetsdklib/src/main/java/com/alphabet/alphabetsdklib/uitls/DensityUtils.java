package com.alphabet.alphabetsdklib.uitls;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * 系统屏幕分辨率常用方法
 *
 * Created by alphabet on 15/10/2.
 */
public class DensityUtils {

    /**
     * 获取屏幕密度
     *
     * @param context
     * @return density 屏幕密度
     */
    public static float getDensity(Context context){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.density;
    }

    /**
     * 获取屏幕的像素高度
     *
     * @param context
     * @return height 屏幕的像素高度
     */
    public static int getScreenHeight(Context context){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int height = displayMetrics.heightPixels;
        return height;
    }

    /**
     * 获取屏幕的像素宽度
     *
     * @param context
     * @return width 屏幕的像素宽度
     */
    public static int getScreenWidth(Context context){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        return width;
    }

    /**
     * 屏幕分辨率 dp 转换 px
     * @param context
     * @param dpValue
     * @return px
     */
    public static int dip2px(Context context,float dpValue){
        Resources resources = context.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpValue,
                resources.getDisplayMetrics());
        return (int) px;
    }

    /**
     * 分辨率 px 转换 dp
     * @param context
     * @param pxValue
     * @return dp
     */
    public static float px2dp(Context context,float pxValue){
        Resources resources = context.getResources();
        float dp = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                pxValue,
                resources.getDisplayMetrics());
        return dp;
    }

    /**
     * px 转换成 sp
     * @param context
     * @param pxValue
     * @return
     */
    public static float px2sp(Context context,float pxValue){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float sp = pxValue / displayMetrics.scaledDensity;
        return sp;
    }

    /**
     * sp 转换成 px
     * @param context
     * @param spValue
     * @return
     */
    public static int sp2px(Context context,float spValue){
        Resources resources = context.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spValue,
                resources.getDisplayMetrics());
        return (int) px;
    }
}
