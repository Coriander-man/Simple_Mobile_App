package com.feva.myapp;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;

import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MarketingJarActivity extends TabActivity {

	// default
	private int myMenuRes[] = { R.drawable.tab_icon_home,
			R.drawable.tab_icon_news, R.drawable.tab_icon_product,
			R.drawable.tab_icon_shop, R.drawable.tab_icon_about };

	TabHost tabHost;
	TabSpec firstTabSpec, secondTabSpec, thirdTabSpec, fourthTabSpec,	fifthTabSpec;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_below);
		
		/* TabHost will have Tabs */
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		// tabHost.setBackgroundResource(R.drawable.bg);

		/*
		 * TabSpec used to create a new tab. By using TabSpec only we can able
		 * to setContent to the tab. By using TabSpec setIndicator() we can set
		 * name to tab.
		 */
		/* tid1 is firstTabSpec Id. Its used to access outside. */
		firstTabSpec = tabHost.newTabSpec("tid1");
		secondTabSpec = tabHost.newTabSpec("tid2");
		thirdTabSpec = tabHost.newTabSpec("tid3");
		fourthTabSpec = tabHost.newTabSpec("tid4");
		fifthTabSpec = tabHost.newTabSpec("tid5");

		/* TabSpec setIndicator() is used to set name for the tab. */
		/* TabSpec setContent() is used to set content for a particular tab. */
		firstTabSpec.setIndicator(this.getString(R.string.tab1_text),
				getResources().getDrawable(myMenuRes[0]));
		secondTabSpec.setIndicator(this.getString(R.string.tab2_text),
				getResources().getDrawable(myMenuRes[1]));
		thirdTabSpec.setIndicator(this.getString(R.string.tab3_text),
				getResources().getDrawable(myMenuRes[2]));
		fourthTabSpec.setIndicator(this.getString(R.string.tab4_text),
				getResources().getDrawable(myMenuRes[3]));
		fifthTabSpec.setIndicator(this.getString(R.string.tab5_text),
				getResources().getDrawable(myMenuRes[4]));

		firstTabSpec.setContent(new Intent(this, TabHome.class));
		secondTabSpec.setContent(new Intent(this, TabNews.class));
		thirdTabSpec.setContent(new Intent(this, TabProductsCat.class));
		fourthTabSpec.setContent(new Intent(this, TabShops.class));
		fifthTabSpec.setContent(new Intent(this, TabContact.class));

		/* Add tabSpec to the TabHost to display. */
		tabHost.addTab(firstTabSpec);
		tabHost.addTab(secondTabSpec);
		tabHost.addTab(thirdTabSpec);
		tabHost.addTab(fourthTabSpec);
		tabHost.addTab(fifthTabSpec);

		tabHost.getTabWidget().setCurrentTab(0);
	}
}
