package com.feva.myapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.feva.myapp.data.JSONFeed;
import com.feva.myapp.data.JSONItem;

public class TabNews extends ListActivity {
	// Find you site id here:  https://developer.wordpress.com/docs/api/console/
	final static int WP_ID = 187635514;
	final static String URL_LINK = "https://public-api.wordpress.com/wp/v2/sites/" + WP_ID + "/posts/";
	

	private JSONFeed feed = null;
	ArrayList<HashMap<String, String>> listItems = null;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD)
		{
		    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		        .detectDiskReads()
		        .detectDiskWrites()
		        .detectNetwork()   // or .detectAll() for all detectable problems
		        .penaltyLog()
		        .build());
		}
		
		
		feed = new JSONFeed();
		listItems = new ArrayList<HashMap<String, String>>();
				
		fetchPublicTimeline();
		
		String[] columns = new String[] {  "title", "modified" };
		int[] renderTo = new int[] {R.id.text1, R.id.text2 };
        
        ListAdapter adapter = new SimpleAdapter(this, listItems, R.layout.layout_news_item, columns, renderTo);
		setListAdapter(adapter);
		
		setContentView(R.layout.layout_news);
	}

	public void fetchPublicTimeline() {
		JSONItem rssItem;
		HashMap<String, String> item;
		try {
			URL url = new URL(URL_LINK);
			URLConnection tc = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(tc.getInputStream()));

			String line;
			while ((line = in.readLine()) != null) {
				JSONArray jPostsAry = new JSONArray(line); // Get posts

				for (int i = 0; i < jPostsAry.length(); i++) {
					JSONObject jo = (JSONObject) jPostsAry.get(i);
					//System.out.println(jo.getString("featured_media_url"));

					item = new HashMap<String, String>();
					item.put("title", jo.getJSONObject("title").getString("rendered").replace("&nbsp;", " "));
					item.put("modified", jo.getString("modified"));
					listItems.add(item);
					
					rssItem = new JSONItem();
					rssItem.setTitle(jo.getJSONObject("title").getString("rendered"));
					rssItem.setLink(jo.getString("link"));
					rssItem.setDescription(jo.getJSONObject("content").getString("rendered"));
					rssItem.setCategory(jo.getString("type"));
					rssItem.setPubDate(jo.getString("modified"));
					feed.addItem(rssItem);

				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onListItemClick(ListView parent, View view, int position,
			long id) {
		Intent itemintent = new Intent(this, DetailsActivity.class);

		Bundle b = new Bundle();
		b.putString("title", feed.getItem(position).getTitle().replace("&nbsp;", " "));
		b.putString("description", feed.getItem(position).getDescription());
		b.putString("link", feed.getItem(position).getLink());
		b.putString("pubdate", feed.getItem(position).getPubDate());

		itemintent.putExtra("android.intent.extra.jsonItem.fullTest", b);
		startActivityForResult(itemintent, 0);
	}

}
