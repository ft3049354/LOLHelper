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
	private final String mapdata []= {"�ٻ�ʦϿ��","������Ԩ","Ť������","ˮ���Ѻ�"};
	private ArrayAdapter <String> mapadapter =null;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item);
		itemgride = (GridView) findViewById(R.id.itemgride);
		//��ʱΪ�˲�������hardcode����
		ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();  
		for(int i=0;i<30;i++)  
		{  
			HashMap<String, Object> map = new HashMap<String, Object>();  
			map.put("ItemImage", R.drawable.icon);//���ͼ����Դ��ID  
			map.put("ItemText", "NO."+String.valueOf(i));//�������ItemText  
			lstImageItem.add(map);  
		} 
		SimpleAdapter saImageItems = new SimpleAdapter(this, 
				lstImageItem,//������Դ   
				R.layout.item_item,//item_item��XMLʵ��  

				//��̬������ImageItem��Ӧ������          
				new String[] {"ItemImage","ItemText"},   

				//ImageItem��XML�ļ������һ��ImageView,����TextView ID  
				new int[] {R.id.ItemImage,R.id.ItemText});  
		//��Ӳ�����ʾ  
		itemgride.setAdapter(saImageItems);  
		//�����Ϣ����  
		itemgride.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0,View arg1,int arg2,long arg3 ) {  
				//�ڱ�����arg2=arg3  
				HashMap<String, Object> item=(HashMap<String, Object>) arg0.getItemAtPosition(arg2);  
				//��ʾ��ѡItem��ItemText  
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
				Toast.makeText(lolitem.this, "ѡ�еĵ�ͼΪ"+mapdata[arg2], Toast.LENGTH_SHORT).show();
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
