package com.lolhelper.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.lolhelper.tools.Utilities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

public class LolDBExtra {

    private static final int EXTRA_DATABASE_VERSION = 1;
    private static String DATABASE_PATH;
    private static String EXTRA_DATABASE_NAME;

    /**初始化数据库
     * @param context
     */
    public static void initLolExtra(Context context) {
                initExtraDB();
    }

    /**
     * 初始化数据库，
     */
    private static void initExtraDB() {
        final Context context = Utilities.getApplicationContext();
        String dbname = ExtraNameColumns.DB_NAME;
        int ver = ExtraNameColumns.DB_VERSION;
        try {
            File databaseFile = context.getDatabasePath(dbname);
            File fileDir = new File(databaseFile.getParent());
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            if (!databaseFile.exists()) {
                FileOutputStream fos = new FileOutputStream(databaseFile);
                byte[] buffer = new byte[8192];
                int count = 0;
                InputStream is = context.getAssets().open(dbname);
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
                SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(databaseFile, null);
                db.setVersion(ver);
                db.close();
            } else {
                SQLiteDatabase oldDb = SQLiteDatabase.openOrCreateDatabase(databaseFile, null);
                if (oldDb.getVersion() < ver) {
                    oldDb.close();
                    databaseFile.delete();
                    FileOutputStream fos = new FileOutputStream(databaseFile);
                    byte[] buffer = new byte[8192];
                    int count = 0;
                    InputStream is = context.getAssets().open(dbname);
                    while ((count = is.read(buffer)) > 0) {
                        fos.write(buffer, 0, count);
                    }
                    fos.close();
                    is.close();
                    SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(databaseFile, null);
                    db.setVersion(ver);
                    db.close();
                } else {
                    oldDb.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将数据库文件写到手机sd卡
     */
    static void initLolExtraImpl() {
        Context context = Utilities.getApplicationContext();

        final String dbname = ExtraNameColumns.DB_NAME;
        if (DATABASE_PATH == null) {
            // /data/data/com.dianxinos.launcher/databases
//            DATABASE_PATH = "/data/data/"+context.getPackageName()+"/databases";
            DATABASE_PATH = Environment.getDataDirectory().getAbsolutePath()
                    + "/data/" + context.getPackageName() + "/databases";
        }
        Log.i("CYL", "DATABASE_PATH  == " + DATABASE_PATH);
        try {
            File dir = new File(DATABASE_PATH);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String databaseFileName = DATABASE_PATH + "/" + EXTRA_DATABASE_NAME;
            File databaseFile = new File(databaseFileName);
            if (!databaseFile.exists()) {
                FileOutputStream fos = new FileOutputStream(databaseFileName);
                byte[] buffer = new byte[8192];
                int count = 0;
//                InputStream is = context.getResources().openRawResource(R.raw.extra);
                InputStream is = context.getAssets().open(dbname);
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
                SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DATABASE_PATH + "/"
                        + EXTRA_DATABASE_NAME, null);
                db.setVersion(EXTRA_DATABASE_VERSION);
                db.close();
            } else {
                SQLiteDatabase oldDb = SQLiteDatabase.openOrCreateDatabase(DATABASE_PATH + "/"
                        + EXTRA_DATABASE_NAME, null);
                if (oldDb.getVersion() < EXTRA_DATABASE_VERSION) {
                    oldDb.close();
                    databaseFile.delete();
                    FileOutputStream fos = new FileOutputStream(databaseFileName);
                    byte[] buffer = new byte[8192];
                    int count = 0;
//                    InputStream is = context.getResources().openRawResource(R.raw.extra);
                    InputStream is = context.getAssets().open(dbname);
                    while ((count = is.read(buffer)) > 0) {
                        fos.write(buffer, 0, count);
                    }
                    fos.close();
                    is.close();
                    SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DATABASE_PATH + "/"
                            + EXTRA_DATABASE_NAME, null);
                    db.setVersion(EXTRA_DATABASE_VERSION);
                    db.close();
                } else {
                    oldDb.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
