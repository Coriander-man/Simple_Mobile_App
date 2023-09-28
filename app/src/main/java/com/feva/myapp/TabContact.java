package com.feva.myapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class TabContact extends Activity {
	Button btnDial;
	Button btnEmail;
	OnClickListener listener1;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_contact);

		final String phone = this.getResources().getString(R.string.contact_call_phone);
		final String email = this.getResources().getString(R.string.contact_email);
		final String email_subject = this.getResources().getString(R.string.contact_email_subject);
		final String email_body = this.getResources().getString(R.string.contact_email_body);
		
		listener1 = new OnClickListener() {
			public void onClick(View v) {
				if (v.getId() == R.id.btnDial) {
					// AndroidManifest.xml need <uses-permission id="android.permission.CALL_PHONE" />
					Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
					startActivity(dialIntent);
				} else if (v.getId() == R.id.btnEmail) {
					String aEmailList[] = { email };
					Intent emailIntent = new Intent(Intent.ACTION_SEND);
					emailIntent.setType("message/rfc822");
					emailIntent.putExtra(Intent.EXTRA_EMAIL, aEmailList);
					emailIntent.putExtra(Intent.EXTRA_SUBJECT, email_subject);
					emailIntent.putExtra(Intent.EXTRA_TEXT, email_body);
					try{	
						startActivity(Intent.createChooser(emailIntent, "Choose Email Client"));
					} catch (android.content.ActivityNotFoundException ex) {
						Toast.makeText(TabContact.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
					}
				}
			}
		};

		btnDial = (Button) findViewById(R.id.btnDial);
		btnDial.setOnClickListener(listener1);
		btnEmail = (Button) findViewById(R.id.btnEmail);
		btnEmail.setOnClickListener(listener1);
	}

}