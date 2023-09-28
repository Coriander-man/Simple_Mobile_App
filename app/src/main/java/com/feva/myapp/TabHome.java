package com.feva.myapp;

import android.app.Activity;
import android.os.Bundle;


public class TabHome extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		
		setContentView(R.layout.layout_home);
		
//		TextView txt = (TextView) findViewById(R.id.textView1);   
//		Typeface font = Typeface.createFromAsset(getAssets(), "Chantelli_Antiqua.ttf");   
//		txt.setTypeface(font);
	}
}