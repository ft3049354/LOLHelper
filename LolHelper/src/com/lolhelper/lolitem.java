package com.lolhelper;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
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
		//��ʱΪ�˲�������hardcode����
		ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();  
		for(int i=0;i<10;i++)  
		{  
			HashMap<String, Object> map = new HashMap<String, Object>();  
			map.put("ItemImage", R.drawable.icon);//����ͼ����Դ��ID  
			map.put("ItemText", "NO."+String.valueOf(i));//�������ItemText  
			lstImageItem.add(map);  
		} 
		SimpleAdapter saImageItems = new SimpleAdapter(this, //ûʲô����  
				lstImageItem,//������Դ   
				R.layout.item_item,//item_item��XMLʵ��  

				//��̬������ImageItem��Ӧ������          
				new String[] {"ItemImage","ItemText"},   

				//ImageItem��XML�ļ������һ��ImageView,����TextView ID  
				new int[] {R.id.ItemImage,R.id.ItemText});  
		//���Ӳ�����ʾ  
		itemgride.setAdapter(saImageItems);  
		//������Ϣ����  
		itemgride.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0,//The AdapterView where the click happened   
					View arg1,//The view within the AdapterView that was clicked  
					int arg2,//The position of the view in the adapter  
					long arg3//The row id of the item that was clicked  
					) {  
				//�ڱ�����arg2=arg3  
				HashMap<String, Object> item=(HashMap<String, Object>) arg0.getItemAtPosition(arg2);  
				//��ʾ��ѡItem��ItemText  
				setTitle((String)item.get("ItemText"));  
			}  
		}); 

	}
}