package com.example.administrator.rxjavaandretrofitsimple.util.xmlparse;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.PowerManager;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/4/14
 *
 * 类描述：工具集合
 */

public class ScreenUtils {
    public static boolean isScreenOn(Context context) {
        PowerManager powerManager = ((PowerManager) context.getSystemService(Context.POWER_SERVICE));
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT_WATCH) {
            return powerManager.isScreenOn();
        } else {
            return powerManager.isInteractive();
        }
    }

    private static int screenWidth;
    private static int screenHeight;

    private static Object syncObj = new Object();
    private static DisplayMetrics dm = null;

    /**
     * Get width of phone window
     *
     * @param context context instance
     * @return width value
     */
    public static int getWidth(Context context) {
        if (screenWidth == 0) {
            synchronized (syncObj) {
                if (dm == null) {
                    dm = new DisplayMetrics();
                    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                    wm.getDefaultDisplay().getMetrics(dm);
                    screenWidth = dm.widthPixels;
                } else {
                    screenWidth = dm.widthPixels;
                }
            }
        }
        return screenWidth;
    }

    /**
     * Get height of phone window
     *
     * @param context context instance
     * @return height value
     */
    public static int getHeight(Context context) {
        if (screenHeight == 0) {
            synchronized (syncObj) {
                if (dm == null) {
                    dm = new DisplayMetrics();
                    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                    wm.getDefaultDisplay().getMetrics(dm);
                    screenHeight = dm.heightPixels;
                } else {
                    screenHeight = dm.heightPixels;
                }
            }
        }
        return screenHeight;
    }

    /**
     * Set window's alpha to 1.0
     *
     * @param activity current display activity
     */
    public static void becomeNormal(Activity activity) {
        if (activity instanceof Activity) {
            WindowManager.LayoutParams params = activity.getWindow().getAttributes();
            params.alpha = 1.0f;
            activity.getWindow().setAttributes(params);
        }
    }

    /**
     * Set window's alpha to what you want
     *
     * @param activity current display activity
     * @param alpha    0.0~1.0
     */
    public static void becomeDark(Activity activity, float alpha) {
        if (activity instanceof Activity) {
            WindowManager.LayoutParams params = activity.getWindow().getAttributes();
            params.alpha = alpha;
            activity.getWindow().setAttributes(params);
        }
    }

    public static boolean isRightPhone(String input) {
        try {
            return input.length() == 11 && isNumeric(input);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^\\+?[1-9][0-9]*$");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    public static final String LOCAL_LOOPBACK_ADDRESS = "127.0.0.1";


    public static void hideSoftKeyboardIfNecessary(Activity context, MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = context.getCurrentFocus();
            if (isShouldHideSoftKeyboard(v, ev)) {
                InputMethodManager imm = getInputMethodManager(context);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
    }

    private static boolean isShouldHideSoftKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }

    public static void hideSoftKeyboard(Context context, View v) {
        InputMethodManager imm = getInputMethodManager(context);
        if (imm != null) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    public static void showSoftKeyboard(Context context) {
        InputMethodManager imm = getInputMethodManager(context);
        if (imm != null) {
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private static InputMethodManager imm = null;

    private static InputMethodManager getInputMethodManager(Context context) {
        return (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }





    public static int dp2px(Context ctx, float dp) {
        float density = ctx.getResources().getDisplayMetrics().density;
        int px = (int) (dp * density + 0.5f);
        return px;
    }

    public static float px2dp(Context ctx, int px) {
        float density = ctx.getResources().getDisplayMetrics().density;
        float dp = px / density;
        return dp;
    }

    public static String sign(String str) {
        try {
            String md51 = md5Encode(str, "utf-8");
            String sh11 = sha1(md51);
            String md52 = md5Encode(sh11, "utf-8");
            String sh12 = sha1(md52);
            return sh12.substring(4, 36);
        } catch (Exception e) {
            return null;
        }
    }

    public static String base64Encode(String input) {
        try {
            return Base64.encodeToString(input.getBytes(), Base64.DEFAULT);
        } catch (Exception e) {
            return input;
        }
    }

    public static String base64Decode(String input) {
        try {
            return new String(Base64.decode(input, Base64.DEFAULT), "utf-8");
        } catch (Exception e) {
            return input;
        }
    }

    public static String md5Encode(String input, String charSetName) {
        String result = null;
        try {
            result = new String(input);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charSetName == null || "".equals(charSetName)) {
                result = byteArrayToHexString(md.digest(result.getBytes()));
            } else {
                result = byteArrayToHexString(md.digest(result.getBytes(charSetName)));
            }
        } catch (Exception exception) {
        }
        return result;
    }

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String sha1(String decrypt) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        digest.update(decrypt.getBytes());
        byte messageDigest[] = digest.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < messageDigest.length; i++) {
            String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
            if (shaHex.length() < 2) {
                hexString.append(0);
            }
            hexString.append(shaHex);
        }
        return hexString.toString();
    }
}
