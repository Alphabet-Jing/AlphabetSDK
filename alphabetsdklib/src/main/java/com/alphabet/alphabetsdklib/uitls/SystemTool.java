package com.alphabet.alphabetsdklib.uitls;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.view.inputmethod.InputMethodManager;

import java.io.File;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by alphabet on 15/10/3.
 */
public class SystemTool {

    /**
     * 指定格式返回当前时间
     *
     * @param format
     * @return
     */
    public static String getDateTime(String format) {
        SimpleDateFormat simpleFormatter = new SimpleDateFormat(format);
        return simpleFormatter.format(new Date());
    }

    /**
     * 返回电话的 IMEI 编码
     *
     * @param context
     * @return
     */
    public static String getPhoneIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(
                Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    /**
     * 返回手机系统 SDK 版本号
     *
     * @return eg: 21
     */
    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取系统版本号
     *
     * @return eg 5.1.1
     */
    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 发送短信
     *
     * @param context
     * @param smsBody
     */
    public static void sendSMS(Context context, String smsBody) {
        Uri smsToUri = Uri.parse("smsto:");
        Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        intent.putExtra("sms_body", smsBody);
        context.startActivity(intent);
    }

    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean checkNet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null;
    }

    /**
     * 判断 WiFi 是否连接
     *
     * @param context
     * @return
     */
    public static boolean isWiFi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State state = connectivityManager.
                getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState();
        return state == NetworkInfo.State.CONNECTED;
    }

    /**
     * 仅在键盘显示时调用，隐藏键盘
     *
     * @param activity
     */
    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS
        );
    }

    /**
     * 当前应用是否在后台运行
     *
     * @param context
     * @return
     */
    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(
                Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcessInfos =
                activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessInfos) {
            if (appProcessInfo.processName.equals(context.getPackageName())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * 判断是否处于休眠
     *
     * @param context
     * @return
     */
    public static boolean isSleeping(Context context) {
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(
                Context.KEYGUARD_SERVICE
        );
        return keyguardManager.inKeyguardRestrictedInputMode();
    }

    /**
     * 安装 apk
     *
     * @param context
     * @param file
     */
    public static void installApk(Context context, File file) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
//        intent.setType("application/vnd.android.package-archive");
//        intent.setData(Uri.fromFile(file));
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 获取应用的版本号
     *
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context) {
        String version = "0";
        try {
            version = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0
            ).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(SystemTool.class.getName() +
                    "the application not found");
        }
        return version;
    }

    /**
     * 返回 home ，在后台运行
     *
     * @param context
     */
    public static void goHome(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        context.startActivity(intent);
    }

    /**
     * 获取应用的签名
     *
     * @param context
     * @param pkgName
     * @return
     */
    public static String getSign(Context context, String pkgName) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(
                    pkgName,
                    PackageManager.GET_SIGNATURES
            );
            return hexdigest(packageInfo.signatures[0].toByteArray());
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(SystemTool.class.getName() +
                    "the " + pkgName + "'s application is not found");
        }
    }

    /**
     * 将签名字符串转换成需要的32位签名
     *
     * @param paramArrayOfByte
     * @return
     */
    private static String hexdigest(byte[] paramArrayOfByte) {
        final char[] hexDigits = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97,
                98, 99, 100, 101, 102};
        try {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(paramArrayOfByte);
            byte[] arrayOfByte = localMessageDigest.digest();
            char[] arrayOfChar = new char[32];
            for (int i = 0, j = 0; ; i++, j++) {
                if (i >= 16) {
                    return new String(arrayOfChar);
                }
                int k = arrayOfByte[i];
                arrayOfChar[j] = hexDigits[(0xF & k >>> 4)];
                arrayOfChar[++j] = hexDigits[(k & 0xF)];
            }
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 获取当前设备可用内存
     *
     * @param context
     * @return 单位 m
     */
    public static int getDeviceUsableMemory(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(
                Context.ACTIVITY_SERVICE
        );
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return (int) (memoryInfo.availMem / (1024 * 1024));
    }

    /**
     * 清理后台进程与服务
     *
     * @param context
     * @return 清理的进程数
     */
    public static int gc(Context context) {
        long i = getDeviceUsableMemory(context);
        int count = 0; // 进程数

        ActivityManager activityManager = (ActivityManager) context.getSystemService(
                Context.ACTIVITY_SERVICE
        );

        List<ActivityManager.RunningServiceInfo> serviceInfos = activityManager.
                getRunningServices(100);

        if (serviceInfos != null) {
            for (ActivityManager.RunningServiceInfo serviceInfo : serviceInfos) {
                if (serviceInfo.pid == android.os.Process.myPid()) {
                    continue;
                }
                try {
                    Process.killProcess(serviceInfo.pid);
                    count++;
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }

        List<ActivityManager.RunningAppProcessInfo> processInfos = activityManager
                .getRunningAppProcesses();
        if (processInfos != null) {
            for (ActivityManager.RunningAppProcessInfo processInfo : processInfos) {
                // 一般数值大于RunningAppProcessInfo.IMPORTANCE_SERVICE的进程都长时间没用或者空进程了
                // 一般数值大于RunningAppProcessInfo.IMPORTANCE_VISIBLE的进程都是非可见进程，也就是在后台运行着
                if (processInfo.importance > ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE) {
                    String[] pkgList = processInfo.pkgList;
                    // pkgList 得到该进程下运行的包名
                    for (String pkgName : pkgList) {
                        try {
                            activityManager.killBackgroundProcesses(pkgName);
                            count++;
                        } catch (Exception e) {
                            e.printStackTrace();
                            continue;
                        }
                    }
                }

            }
        }

        return count;
    }
}
