package com.alphabet.alphabetsdklib.uitls;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.view.View;

/**
 * Created by alphabet on 15/10/2.
 */
public class ViewUtils {

    /**
     * 控件截图
     * @param view 目标控件
     * @return Bitmap
     */
    public static Bitmap captureView(View view){
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        return view.getDrawingCache();
    }

    /**
     * 创建快捷方式
     *
     * @param cxt
     *            Context
     * @param icon
     *            快捷方式图标
     * @param title
     *            快捷方式标题
     * @param cls
     *            要启动的类
     */
    public void createDeskShortCut(Context cxt, int icon, String title,
                                   Class<?> cls) {
        // 创建快捷方式的Intent
        Intent shortcutIntent = new Intent(
                "com.android.launcher.action.INSTALL_SHORTCUT");
        // 不允许重复创建
        shortcutIntent.putExtra("duplicate", false);
        // 需要现实的名称
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
        // 快捷图片
        Parcelable ico = Intent.ShortcutIconResource.fromContext(
                cxt.getApplicationContext(), icon);
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, ico);
        Intent intent = new Intent(cxt, cls);
        // 下面两个属性是为了当应用程序卸载时桌面上的快捷方式会删除
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        // 点击快捷图片，运行的程序主入口
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
        // 发送广播。OK
        cxt.sendBroadcast(shortcutIntent);
    }
}
