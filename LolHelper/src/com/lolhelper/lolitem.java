package com.lolhelper;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class lolitem extends Activity{
	private Spinner mapspinner = null;
	private GridView itemgride = null;
	private Button filtertrigger = null;
	private RelativeLayout filter = null;
	private final String mapdata []= {"召唤师峡谷","哀嚎深渊","扭曲丛林","水晶裂痕"};
	private ArrayAdapter <String> mapadapter =null;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item);
		itemgride = (GridView) findViewById(R.id.itemgride);
		//暂时为了测试先用hardcode代替
		ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();  
		for(int i=0;i<30;i++)  
		{  
			HashMap<String, Object> map = new HashMap<String, Object>();  
			map.put("ItemImage", R.drawable.icon);//添加图像资源的ID  
			map.put("ItemText", "NO."+String.valueOf(i));//按序号做ItemText  
			lstImageItem.add(map);  
		} 
		SimpleAdapter saImageItems = new SimpleAdapter(this, 
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
			public void onItemClick(AdapterView<?> arg0,View arg1,int arg2,long arg3 ) {  
				//在本例中arg2=arg3  
				HashMap<String, Object> item=(HashMap<String, Object>) arg0.getItemAtPosition(arg2);  
				//显示所选Item的ItemText  
				setTitle((String)item.get("ItemText"));  
				Toast.makeText(lolitem.this, "Item"+item.get("ItemText"), Toast.LENGTH_SHORT).show();
			}  
		}); 
		
		mapspinner = (Spinner)findViewById(R.id.mapselect);
		mapadapter = new ArrayAdapter<String>(this,R.layout.spinnertextstyle,mapdata);
		mapadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mapspinner.setAdapter(mapadapter);
		mapspinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(lolitem.this, "选中的地图为"+mapdata[arg2], Toast.LENGTH_SHORT).show();
			}
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(lolitem.this, "Nothing changed", Toast.LENGTH_SHORT).show();
			}
		});
		filtertrigger = (Button)findViewById(R.id.filtertrigger);
		filter = (RelativeLayout)(findViewById(R.id.filter));
		filtertrigger.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(filter.getVisibility()==View.GONE){
					filter.setVisibility(View.VISIBLE);
					
				}else filter.setVisibility(View.GONE);
			}
			
		});
	}
}
