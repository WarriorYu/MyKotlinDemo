package com.example.yuxibing.mykotlindemo.javacode.themedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FilePathUtil {

    private static String dbName = "homeplus.sqlite";
    /**
     * /mnt/sdcard
     */
    public static String SDCARD_PATH = null;

    static {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            SDCARD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();// + File.separator  /mnt/sdcard
        } else {
            // ?��??? 0-??��1-???SD??
            SDCARD_PATH = Environment.getDataDirectory().getAbsolutePath();// + File.separator   /data
        }

    }

    public static String getSDFilePath() {
        String filePath = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return filePath;
    }


    public static String getThemeFilePath() {
        String filePath = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + ".ATestTheme";
        }
        return filePath;
    }

    public static String saveBitmap(Context context, Bitmap b) {
        String path = getDefaultImagePath(context);
        long dataTake = System.currentTimeMillis();
        String jpegName = path + File.separator + dataTake + ".jpg";
        try {
            FileOutputStream fout = new FileOutputStream(jpegName);
            BufferedOutputStream bos = new BufferedOutputStream(fout);
            b.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
            return jpegName;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * ??? sdcard��??
     *
     * @return
     */
    public static String getSdcardPath() {
        return SDCARD_PATH;
    }

    /**
     * ??? sdcard????app's packageName??????????��??
     *
     * @param context
     * @return /mnt/sdcard/packageName
     */
    public static String getAppPath(Context context) {
        if (context != null && SDCARD_PATH != null) {
            return SDCARD_PATH + "/" + context.getPackageName();
        }
        return null;
    }

    /**
     * ????????????��???????��??<br>
     *
     * @param context
     * @return
     */
    public static String getDefaultStrPath(Context context) {
        if (context != null && SDCARD_PATH != null) {
            return SDCARD_PATH + "/" + context.getPackageName() + "/str";
        }
        return null;
    }

    /**
     * ??????????????????��??
     *
     * @param context
     * @return /sdcard/packageName/image
     */
    public static String getDefaultImagePath(Context context) {
        if (context != null && SDCARD_PATH != null) {

            String path = SDCARD_PATH + "/" + context.getPackageName() + "/image";
            File file = new File(path);
            if (!file.exists()) {

                file.mkdirs();
            }
            return path;
        }
        return null;
    }

    /**
     * ??????????????????��??
     *
     * @param context
     * @return /sdcard/packageName/db/
     */
    public static String getDefaultDBPath(Context context) {
        if (context != null && SDCARD_PATH != null) {
            return SDCARD_PATH + "/" + context.getPackageName() + "/db/";
        }
        return null;
    }

    /**
     * ??????????????????��??
     *
     * @param context
     * @return /sdcard/packageName/db/dn.db
     */

    public static String getDBPath(Context context) {
        if (context != null && SDCARD_PATH != null) {
            return SDCARD_PATH + "/" + context.getPackageName() + "/db/" + dbName;
        }
        return null;
    }

    /**
     * ???????????????????��??
     *
     * @param context
     * @return
     */
    public static String getDefaultDownLoadPath(Context context) {
        if (context != null && SDCARD_PATH != null) {
            return SDCARD_PATH + "/" + context.getPackageName() + "/download";
        }
        return null;
    }

    /**
     * ??????????????��??
     *
     * @param context
     * @return
     */
    public static String getDefaultUnzipPath(Context context) {
        if (context != null && SDCARD_PATH != null) {
            return SDCARD_PATH + "/" + context.getPackageName() + "/unZip";
        }
        return null;
    }

    /**
     * ???????????????????????��??
     *
     * @param context
     * @return
     */
    public static String getDefaultDataBasePath(Context context) {
        if (context != null && SDCARD_PATH != null) {
            return SDCARD_PATH + "/" + context.getPackageName() + "/database";
        }
        return null;
    }

    /**
     * ???????????????????????��??
     *
     * @param context
     * @return
     */
    public static String getDefaultVoicePath(Context context) {
        if (context != null && SDCARD_PATH != null) {

            String path = SDCARD_PATH + "/" + context.getPackageName() + "/voice";
            File file = new File(path);
            if (!file.exists()) {

                file.mkdirs();
            }
            return path;
        }
        return null;
    }

    /**
     * ?????????????????????��??
     *
     * @param context
     * @return
     */
    public static String getDefaultCrashPath(Context context) {
        if (context != null && SDCARD_PATH != null) {
            return SDCARD_PATH + "/" + context.getPackageName() + "/crash";
        }
        return null;
    }
}
