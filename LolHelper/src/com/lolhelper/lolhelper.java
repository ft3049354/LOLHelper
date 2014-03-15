package com.lolhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class lolhelper extends Activity {
	/** Called when the activity is first created. */
	private ImageView hero;
	private ImageView item;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		item = (ImageView)findViewById(R.id.itemicon);
		hero = (ImageView)findViewById(R.id.heroicon);
		item.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//Test
				//Toast.makeText(lolhelper.this, "item clicked!", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				intent.setClass(lolhelper.this, lolitem.class);//Ìø×ª
				lolhelper.this.startActivity(intent);
			}
		});
		hero.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// Test
				Toast.makeText(lolhelper.this, "hero clicked!", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				intent.setClass(lolhelper.this, lolhero.class);//Ìø×ª
				lolhelper.this.startActivity(intent);
			}});
	}
	
}