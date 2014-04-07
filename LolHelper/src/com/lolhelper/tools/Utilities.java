package com.lolhelper.tools;

import java.io.Closeable;
import java.io.File;
import java.util.Locale;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Environment;

public class Utilities {
    static Context sApplicationContext = null;
    static String sAppliationName = null;
    static String sMainProcessName = null;

    public static void initEnvironment(Context ctx) {
        sApplicationContext = ctx;
    }

    public static Context getApplicationContext() {
        if (sApplicationContext == null) {
            throw new java.lang.IllegalStateException("Common library is used before initialize!");
        }

        return sApplicationContext;
    }

    public static String getApplicationName() {
        return sAppliationName;
    }


    public static boolean isSDCardMounted() {
        return Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    public static void silentlyClose(Cursor c) {
        if (c != null) {
            try {
                c.close();
            } catch (Throwable e) {
            }
        }
    }

    public static void silentlyClose(SQLiteDatabase c) {
        if (c != null) {
            try {
                c.close();
            } catch (Throwable e) {
            }
        }
    }

    public static String getCurrentLanguage() {
        return Locale.getDefault().getLanguage();
    }

    private static Boolean sIsLargeScreen = null;

    public static boolean supportVelocityWithParam() {
        return Build.VERSION.SDK_INT >= 8;
    }
}
