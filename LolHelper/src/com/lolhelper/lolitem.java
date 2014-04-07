package com.lolhelper;

import java.util.ArrayList;
import java.util.HashMap;

import com.lolhelper.activity.LolHelperItemsDetailActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class lolitem extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item);
		GridView itemgride = (GridView) findViewById(R.id.itemgride);
		//暂时为了测试先用hardcode代替
		ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();  
		for(int i=0;i<10;i++)  
		{  
			HashMap<String, Object> map = new HashMap<String, Object>();  
			map.put("ItemImage", R.drawable.icon);//添加图像资源的ID  
			map.put("ItemText", "NO."+String.valueOf(i));//按序号做ItemText  
			lstImageItem.add(map);  
		} 
		SimpleAdapter saImageItems = new SimpleAdapter(this, //没什么解释  
				lstImageItem,//数据来源   
				R.layout.item_item,//item_item的XML实现  

				//动态数组与ImageItem对应的子项          
				new String[] {"ItemImage","ItemText"},   

				//ImageItem的XML文件里面的一个ImageView,两个TextView ID  
				new int[] {R.id.ItemImage,R.id.ItemText});  
		//添加并且显示  
		itemgride.setAdapter(saImageItems);  
		//添加消息处理  
		itemgride.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0,//The AdapterView where the click happened   
					View arg1,//The view within the AdapterView that was clicked  
					int arg2,//The position of the view in the adapter  
					long arg3//The row id of the item that was clicked  
					) {  
				//在本例中arg2=arg3  
				HashMap<String, Object> item=(HashMap<String, Object>) arg0.getItemAtPosition(arg2);  
				//显示所选Item的ItemText  
				setTitle((String)item.get("ItemText")); 
			}  
		}); 

	}
}
