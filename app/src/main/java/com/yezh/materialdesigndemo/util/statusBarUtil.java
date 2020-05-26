package com.yezh.materialdesigndemo.util;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class statusBarUtil {

    /**
     * 给状态栏设置颜色
     */
    public static void setStatusBarColor(Activity activity, int color) {
        //SDK大于5.0版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(color);


            //SDK在4.4-5.0的版本
        } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            // 首先把他弄成全屏（），在状态栏的部分加一个布局
//             activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            // 电量 时间 网络状态 都还在
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 在状态栏的部分加一个布局 setContentView 源码分析，自己加一个布局 (高度是状态栏的高度)
            View view = new View(activity);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
            view.setLayoutParams(params);
            view.setBackgroundColor(color);

            //  android:fitsSystemWindows="true" 每个布局都要写
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            decorView.addView(view);

            ViewGroup contentView = activity.findViewById(android.R.id.content);
            View activityView = contentView.getChildAt(0);
            activityView.setFitsSystemWindows(true);
        }
    }

    //获取状态栏的高度
    public static int getStatusBarHeight(Activity activity) {
        Resources resources = activity.getResources();
        int statusBarHeightId = resources.getIdentifier("status_bar_height", "dimen", "android");
        Log.e("TAG", statusBarHeightId + " -> " + resources.getDimensionPixelOffset(statusBarHeightId));
        return resources.getDimensionPixelOffset(statusBarHeightId);
    }

    //设置activity全屏
    public static void setActivityTranslucent(Activity activity) {

        //SDK大于5.0版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //设置Activity全屏，且带状态栏
            //decorView是一个FragLayout布局，会加载一个系统布局，是一个LinearLayout布局，在此布局中有一个id为
            View decorView = activity.getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            //设置Activity全屏，且不带状态栏
//            activity.getWindow().
//                    setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


            //SDK在4.4-5.0的版本
        } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
