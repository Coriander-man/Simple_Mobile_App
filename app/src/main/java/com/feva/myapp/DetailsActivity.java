package com.feva.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.view.*;

public class DetailsActivity extends Activity {
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.layout_details);
		
		String title = null;
		Intent startingIntent = getIntent();

		if (startingIntent != null) {
			Bundle bundle = startingIntent.getBundleExtra("android.intent.extra.jsonItem.fullTest");
			WebView web = (WebView) findViewById(R.id.webView1);
			if (bundle == null) {
				title = "沒有內容";
			} else {
				title = bundle.getString("title") + "\n"
						+ bundle.getString("pubdate");
				
				final String txtBody = bundle.getString("description").replace('\n', ' ');
				final String txtHtml = "<html>" +
					       "<head></head>" +
					       "<body>" +
					       txtBody +
					       "</body></html>";
					    
				web.loadDataWithBaseURL(null , txtHtml, "text/html", "utf-8", null); 
			}
		} else {
			title = "沒有內容";

		}

		TextView textView = (TextView) findViewById(R.id.tvTitle);
		textView.setText(title);

		Button backbutton = (Button) findViewById(R.id.back);

		backbutton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}
}
