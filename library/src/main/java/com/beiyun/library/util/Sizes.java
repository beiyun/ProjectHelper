package com.beiyun.library.util;

/**
 * Created by beiyun on 2017/11/17.
 */
public class Sizes {


    /**
     * dp转化为px
     * @param dp dp
     * @return px
     */
    public static int dp2px(int dp){
        final float scale = Apps.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }


    /**
     * px转化为dp
     * @param px px
     * @return dp
     */
    public static int px2dp(int px){
        final float scale = Apps.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }


    /**
     * sp转化为px
     * @param sp sp
     * @return px
     */
    public static int sp2px(int sp){
        final float fontScale = Apps.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * fontScale + 0.5f);
    }


    /**
     * px转化为sp
     * @param px px
     * @return sp
     */
    public static int px2sp(int px){
        final float fontScale = Apps.getResources().getDisplayMetrics().scaledDensity;
        return (int) (px / fontScale + 0.5f);
    }


}
