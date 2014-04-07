
package com.lolhelper.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Vector;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.lolhelper.R;
import com.lolhelper.tools.Utilities;

public class DBManager {

    public String itemName;

    public String itemSellPrice;

    public String itemBuyPrice;

    public String itemProperty;

    public String itemPassivity;

    public String itemInitiative;

    public static DBManager dbManager = null;

    private final int BUFFER_SIZE = 400000;

    public final static String ITEM_NAME = "item_name";
    public final static String ITEM_SELL_PRICE = "item_sell_price";
    public final static String ITEM_BUY_PRICE = "item_buy_price";
    public final static String ITEM_PROPERTY = "item_property";
    public final static String ITEM_PASSIVITY = "item_passivity";
    public final static String ITEM_INITIATIVE = "item_initiative";
    public static final String DB_NAME = "extra.db"; // �������ݿ��ļ���

    public static final String PACKAGE_NAME = "com.android.ImportDatabase";

    public static final String DB_PATH;

    private SQLiteDatabase database;

    private Context context;

    DBManager(Context context) {
        this.context = context;
    }

    static {
        String pkgName = Utilities.getApplicationContext().getPackageName();
        DB_PATH = Environment.getDataDirectory().getPath() + "/data/" + pkgName + "/databases/";
    }
    private static SQLiteDatabase openDatabase() {
        try {
            Log.i("CYL", "DB_PATH == " + DB_PATH);
            return SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);
        } catch (Exception e) {
        }
        return null;
    }

    public static DBManager getInstance(Context context) {
        if (dbManager == null) {
            dbManager = new DBManager(context);
        }
        return dbManager;
    }

    public static Vector<HashMap<String, Object>> getLolHelperIteminformation(String str) {
        Vector<HashMap<String, Object>> list = new Vector<HashMap<String, Object>>();
        SQLiteDatabase db = null;
        Cursor c = null;
        try {
            db = openDatabase();
            Log.i("CYL","db === " + db);
            if (db == null)
                return list;
            String sql = "select distinct(item_name),item_name,item_sell_price, item_buy_price,item_property,item_passivity,item_initiative from lolhelper where item_name = '"
                    + str + "'";
            Log.i("CYL", "sql === " + sql);
            c = db.rawQuery(sql, null);
            Log.i("CYL","c === " + c);
            if (c.getCount() > 0 && c.moveToFirst()) {
                int itemNameIndex;
                int itemSellPriceIndex;
                int itemBuyPriceIndex;
                int itemPropertyIndex;
                int itemPassivityIndex;
                int itemInitiativeIndex;
                try {
                    itemNameIndex = c.getColumnIndexOrThrow(ITEM_NAME);
                    itemSellPriceIndex = c.getColumnIndexOrThrow(ITEM_SELL_PRICE);
                    itemBuyPriceIndex = c.getColumnIndexOrThrow(ITEM_BUY_PRICE);
                    itemPropertyIndex = c.getColumnIndex(ITEM_PROPERTY);
                    itemPassivityIndex = c.getColumnIndex(ITEM_PASSIVITY);
                    itemInitiativeIndex = c.getColumnIndex(ITEM_INITIATIVE);
                } catch (IllegalArgumentException e1) {
                    itemNameIndex = 0;
                    itemSellPriceIndex = 1;
                    itemBuyPriceIndex = 2;
                    itemPropertyIndex = 3;
                    itemPassivityIndex = 4;
                    itemInitiativeIndex = 5;
                }
                do {
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put(ITEM_NAME, c.getString(itemNameIndex));
                    map.put(ITEM_SELL_PRICE, c.getString(itemSellPriceIndex));
                    map.put(ITEM_BUY_PRICE, c.getString(itemBuyPriceIndex));
                    map.put(ITEM_PROPERTY, c.getString(itemPropertyIndex));
                    map.put(ITEM_PASSIVITY, c.getString(itemPassivityIndex));
                    map.put(ITEM_INITIATIVE, c.getString(itemInitiativeIndex));
                    Log.i("CYL", "c.getString(itemNameIndex) === " + c.getString(itemNameIndex));
                    list.add(map);
                } while (c.moveToNext());
            }
        } catch (Exception e) {
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return list;
    }

    public void closeDatabase() {
        this.database.close();
    }

    public String getItemName() {
        return null;

    }

    public String getItemBuyPrice() {
        return null;

    }

    public String getItemSellPrice() {
        return null;

    }

    public String getItemProperty() {
        return null;

    }

    public String getItemPassivity() {
        return null;

    }

    public String getItemInitiative() {
        return null;

    }
}
