package com.lolhelper.activity;

import java.util.HashMap;
import java.util.Vector;

import com.lolhelper.R;
import com.lolhelper.database.DBManager;
import com.lolhelper.database.LolDBExtra;
import com.lolhelper.tools.Utilities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LolHelperItemsDetailActivity extends Activity
{
    private DrawerLayout mDrawerLayout = null;
    private ImageView lolHelperItem = null;
    private TextView lolHelperItemName = null;
    private TextView lolHelperItemBuyPrice = null;
    private TextView lolHelperItemSellPrice = null;
    private TextView lolHelperItemProperty = null;
    private TextView lolHelperItemPassivity = null;
    private TextView lolHelperItemInitiative = null;
    
    private ImageView lolHelperItemImage1 = null;
    private ImageView lolHelperItemImage2 = null;
    private ImageView lolHelperItemImage3 = null;
    private ImageView lolHelperItemImage4 = null;
    
    private TextView lolHelperItemText1 = null;
    private TextView lolHelperItemText2 = null;
    private TextView lolHelperItemText3 = null;
    private TextView lolHelperItemText4 = null;
    private Vector<HashMap<String,Object>> list = new Vector<HashMap<String,Object>>();
    String item_name;
    private void init(){
        lolHelperItem = (ImageView) findViewById(R.id.lolhelperitem);
        lolHelperItemName = (TextView) findViewById(R.id.lolhelperitemname);
        lolHelperItemBuyPrice = (TextView) findViewById(R.id.lolhelperitembuyprice);
        lolHelperItemSellPrice = (TextView) findViewById(R.id.lolhelperitemsellprice);
        lolHelperItemProperty = (TextView) findViewById(R.id.lolhelperitemproperty);
        lolHelperItemPassivity = (TextView) findViewById(R.id.lolhelperitempassivity);
        lolHelperItemInitiative = (TextView) findViewById(R.id.lolhelperiteminitiative);
        lolHelperItemImage1 = (ImageView) findViewById(R.id.lolhelperitemimage1);
        lolHelperItemImage2 = (ImageView) findViewById(R.id.lolhelperitemimage2);
        lolHelperItemImage3 = (ImageView) findViewById(R.id.lolhelperitemimage3);
        lolHelperItemImage4 = (ImageView) findViewById(R.id.lolhelperitemimage4);
        lolHelperItemText1 = (TextView) findViewById(R.id.lolhelperitemtext1);
        lolHelperItemText2 = (TextView) findViewById(R.id.lolhelperitemtext2);
        lolHelperItemText3 = (TextView) findViewById(R.id.lolhelperitemtext3);
        lolHelperItemText4 = (TextView) findViewById(R.id.lolhelperitemtext4);
        HashMap<String,Object> map = list.firstElement();
        DBManager dbManager = DBManager.getInstance(LolHelperItemsDetailActivity.this);
        String item_name = (String) map.get(dbManager.ITEM_NAME);
        String item_buy_price = (String) map.get(dbManager.ITEM_BUY_PRICE);
        String item_sell_price = (String) map.get(dbManager.ITEM_SELL_PRICE);
        String item_property = (String) map.get(dbManager.ITEM_PROPERTY);
        String item_passivity = (String) map.get(dbManager.ITEM_PASSIVITY);
        String item_initiative = (String) map.get(dbManager.ITEM_INITIATIVE);
        lolHelperItemName.setText(item_name);
        lolHelperItemBuyPrice.setText(item_buy_price);
        lolHelperItemSellPrice.setText(item_sell_price);
        lolHelperItemProperty.setText(item_property);
        lolHelperItemPassivity.setText(item_passivity);
        lolHelperItemInitiative.setText(item_initiative);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lolhelperitemsdetail);
        Utilities.initEnvironment(this);
        LolDBExtra.initLolExtra(this);
        Intent intent = getIntent();
        item_name = intent.getStringExtra("item_name");
        item_name = "ÎÞ¾¡Ö®ÈÐ";
        refreshList(item_name);
        init();

    }

    @Override
    public void onAttachedToWindow() {
        init();
        super.onAttachedToWindow();
    }

    public void refreshList(String str){
        if ("%".equals(str)) {
            str = null;
        }
        list = DBManager.getLolHelperIteminformation(str);
        Log.i("CYL", "refreshList == " + list);
    }
}